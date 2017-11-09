package de.silveryard.basesystem.gui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.IDisposable;
import de.silveryard.basesystem.util.SortedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beppo on 01/02/17.
 */
public class GraphicsManager implements IDisposable{
    private static GraphicsManager instance;

    /**
     * Initializes the graphics manager
     * @param title Window title. Only shown when the system is rendered into a window
     * @param width Window width. X resolution when the system is not rendered into a window
     * @param height Window height. Y resolution when the system is not rendered into a window
     * @param showCursor Flag if a cursor should be shown. Set to false on platforms that use Touch for input
     * @param systemFontCollection A font collection that contains all system fonts this system provides
     */
    public static void initialize(String title, int width, int height, boolean showCursor, FontCollection systemFontCollection){
        if(instance != null){
            throw new RuntimeException("Already initialized");
        }

        System.loadLibrary("lib-bs-sys-sdlnative-1-0-0");
        int result = SDLWindow.windowInit(title, width, height, showCursor);
        if(result != 0){
            throw new RuntimeException("Failed initializing window. Error Code: " + result);
        }

        instance = new GraphicsManager(title, width, height, systemFontCollection);
    }
    /**
     * Singleton getter
     * @return GraphicsManager instance
     */
    public static GraphicsManager getInstance(){
        return instance;
    }

    private String         title;
    private int            width;
    private int            height;
    private FontCollection systemFontCollection;

    private boolean quitRequested;
    private boolean dirtyFlag;
    private boolean mouseDown;

    private TweenManager    tweenManager;
    private long            lastTimestamp;
    private float           deltaTime;

    private List<Frame>                       frames;
    private List<Frame>                       fRenderList;
    private SortedList<Integer, Frame>      frameOrder;
    private SortedList<Integer, RenderObject> renderList;

    private List<Action> nextFrameActions;

    private GraphicsManager(String title, int width, int height, FontCollection systemFontCollection){
        this.title                = title;
        this.width                = width;
        this.height               = height;
        this.systemFontCollection = systemFontCollection;

        this.dirtyFlag = true;
        this.mouseDown = false;

        tweenManager = new TweenManager();
        lastTimestamp = System.currentTimeMillis();

        frames = new ArrayList<>();
        fRenderList = new ArrayList<>();
        frameOrder = new SortedList<>();
        renderList = new SortedList<>();

        nextFrameActions = new ArrayList<>();
    }

    /**
     * Returns the window title
     * @return Window title
     */
    public String getTitle(){
        return title;
    }
    /**
     * Returns the screens width
     * @return Width value
     */
    public int getWidth(){
        return width;
    }
    /**
     * Returns the screens height
     * @return Height value
     */
    public int getHeight(){
        return height;
    }
    /**
     * Returns the system font collection
     * @return FontCollection instance
     */
    public FontCollection getSystemFontCollection(){
        return systemFontCollection;
    }

    public TweenManager getTweenManager(){
        return tweenManager;
    }

    /**
     * Sets the GraphicsManager dirty. It will rerender the screen on the next frame
     */
    public void setDirty(){
        dirtyFlag = true;
    }

    /**
     * Creates a new frame. This frame will be disposed when the GraphicsManager is disposed
     * @return Frame instance
     */
    public Frame createFrame(){
        Frame frame = new Frame(this::setDirty);
        frames.add(frame);
        return frame;
    }
    /**
     * Disposes a frame created by this manager
     */
    public void disposeFrame(Frame frame){
        frames.remove(frame);
        frame.dispose();
    }

    /**
     * Adds a frame to the render list. GraphicsManager will render all frames on this list
     * @param frame Frame to add
     */
    public void addFrameToRenderList(Frame frame){
        fRenderList.add(frame);
        setDirty();
    }
    /**
     * Removes a frame from the render list. GraphicsManager will only render frames on this list
     * @param frame Frame to remove
     */
    public void removeFrameFromRenderList(Frame frame){
        fRenderList.remove(frame);
        setDirty();
    }

    /**
     * Check if the quit button is pressed (red x in the window corner)
     * @return True if the button is pressed. False otherwise
     */
    public boolean isQuitRequested(){
        return quitRequested;
    }

    /**
     * Runs an action on the gui thread on the next frame
     * @param action Action to execute
     */
    public void runNextFrame(Action action){
        nextFrameActions.add(action);
        setDirty();
    }

    @Override
    public void dispose() {
        for(Frame frame : frames){
            frame.dispose();
        }
        frames.clear();
        SDLWindow.windowDispose();
    }

    /**
     * Renders the screen
     */
    public void render(){
        //Calculate deltaTime
        long timestamp = System.currentTimeMillis();
        long delta = timestamp - lastTimestamp;
        deltaTime = delta / 1000f;
        lastTimestamp = timestamp;

        runActions();
        handleEvents();
        updateTweens();
        renderInternal();
    }

    private void runActions(){
        List<Action> tmpAction = new ArrayList<>(nextFrameActions);
        nextFrameActions.clear();
        for(Action action : tmpAction){
            action.invoke();
        }
    }
    private void handleEvents(){
        while (SDLWindow.windowPollEvent() != 0) {
            int type = SDLEvent.TYPE;

            if(type == SDLEventTypes.SDL_QUIT) {
                quitRequested = true;
            }else if (type == SDLEventTypes.SDL_MOUSEMOTION) {
                if(mouseDown){
                    int x = SDLWindow.windowGetMouseX();
                    int y = SDLWindow.windowGetMouseY();
                    handleTouchEvent(-1, TouchType.PRESSED, x, y);
                }
            } else if (type == SDLEventTypes.SDL_MOUSEBUTTONDOWN) {
                mouseDown = true;
                int x = SDLWindow.windowGetMouseX();
                int y = SDLWindow.windowGetMouseY();
                handleTouchEvent(-1, TouchType.DOWN, x, y);
            } else if (type == SDLEventTypes.SDL_MOUSEBUTTONUP) {
                mouseDown = false;
                int x = SDLWindow.windowGetMouseX();
                int y = SDLWindow.windowGetMouseY();
                handleTouchEvent(-1, TouchType.UP, x, y);
            } else if (type == SDLEventTypes.SDL_FINGERMOTION){
                int x = (int)(getWidth() * SDLEvent.TFINGER_X);
                int y = (int)(getHeight() * SDLEvent.TFINGER_Y);
                handleTouchEvent(SDLEvent.TFINGER_FINGERID, TouchType.PRESSED, x, y);
            } else if(type == SDLEventTypes.SDL_FINGERDOWN){
                int x = (int)(getWidth() * SDLEvent.TFINGER_X);
                int y = (int)(getHeight() * SDLEvent.TFINGER_Y);
                handleTouchEvent(SDLEvent.TFINGER_FINGERID, TouchType.DOWN, x, y);
            } else if(type == SDLEventTypes.SDL_FINGERUP){
                int x = (int)(getWidth() * SDLEvent.TFINGER_X);
                int y = (int)(getHeight() * SDLEvent.TFINGER_Y);
                handleTouchEvent(SDLEvent.TFINGER_FINGERID, TouchType.UP, x, y);
            } else {
                System.out.println("Unknown SDL Event: " + Integer.toHexString(type));
            }
        }
    }
    private void updateTweens(){
        tweenManager.update(deltaTime);
    }
    private void renderInternal(){
        if(!dirtyFlag){
            try{
                Thread.sleep(16);
            }catch(Exception e){
                throw new RuntimeException(e);
            }
            return;
        }
        dirtyFlag = false;

        //Clear screen
        SDLWindow.windowSetDrawColor((byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00);
        SDLWindow.windowClear();

        //Create frame order
        frameOrder.clear();
        for(int f = 0; f < fRenderList.size(); f++){
            Frame frame = fRenderList.get(f);
            frameOrder.add(frame.getLayer(), frame);
        }

        for(int f = 0; f < frameOrder.size(); f++){
            Frame frame = frameOrder.get(f);
            List<RenderObject> frameRenderList = frame.getRenderList();
            renderList.clear();

            //Sort objects to render
            for(int o = 0; o < frameRenderList.size(); o++){
                RenderObject ro = frameRenderList.get(o);
                renderList.add(ro.getLayer(), ro);
            }

            //Render this frame
            for(int o = 0; o < renderList.size(); o++){
                RenderObject ro = renderList.get(o);
                ro.draw();
            }
        }

        //Update the window
        SDLWindow.windowUpdateScreen();
    }

    private void handleTouchEvent(long id, TouchType type, int x, int y){
        //Create frame order
        frameOrder.clear();
        for(int f = 0; f < fRenderList.size(); f++){
            Frame frame = fRenderList.get(f);
            frameOrder.add(frame.getLayer(), frame);
        }

        for(int i = frameOrder.size() - 1; i >= 0; i--){
            Frame frame = frameOrder.get(i);
            if(frame.handleTouchEvent(id, type, x, y)){
                break;
            }
        }
    }
}
