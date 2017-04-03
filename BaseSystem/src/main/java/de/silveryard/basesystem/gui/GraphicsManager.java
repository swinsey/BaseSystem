package de.silveryard.basesystem.gui;

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

    public static void initialize(String title, int width, int height, boolean showCursor){
        if(instance != null){
            throw new RuntimeException("Already initialized");
        }

        int result = SDLWindow.windowInit(title, width, height, showCursor);
        if(result != 0){
            throw new RuntimeException("Failed initializing window. Error Code: " + result);
        }

        instance = new GraphicsManager(title, width, height);
    }
    public static GraphicsManager getInstance(){
        return instance;
    }

    private String title;
    private int    width;
    private int    height;

    private boolean quitRequested;
    private boolean dirtyFlag;
    private boolean mouseDown;

    private List<Frame>                       frames;
    private List<Frame>                       fRenderList;
    private SortedList<Integer, Frame> frameOrder;
    private SortedList<Integer, RenderObject> renderList;

    private List<Action> nextFrameActions;

    private GraphicsManager(String title, int width, int height){
        this.title  = title;
        this.width  = width;
        this.height = height;

        this.dirtyFlag = true;
        this.mouseDown = false;

        frames = new ArrayList<>();
        fRenderList = new ArrayList<>();
        frameOrder = new SortedList<>();
        renderList = new SortedList<>();

        nextFrameActions = new ArrayList<>();
    }

    public String getTitle(){
        return title;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public void setDirty(){
        dirtyFlag = true;
    }

    public Frame createFrame(){
        Frame frame = new Frame(this::setDirty);
        frames.add(frame);
        return frame;
    }

    public void addFrameToRenderList(Frame frame){
        fRenderList.add(frame);
        setDirty();
    }
    public void removeFrameFromRenderList(Frame frame){
        fRenderList.remove(frame);
        setDirty();
    }

    public boolean isQuitRequested(){
        return quitRequested;
    }

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

    public void render(){
        runActions();
        handleEvents();
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
        SDLWindow.windowSetDrawColor((byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF);
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
