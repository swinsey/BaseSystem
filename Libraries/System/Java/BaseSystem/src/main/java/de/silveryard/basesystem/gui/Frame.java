package de.silveryard.basesystem.gui;

import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.IDisposable;
import de.silveryard.basesystem.util.SortedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beppo on 04/02/17.
 */
public class Frame implements IDisposable {
    private Action              dirtyHandler;
    private int                 layer;
    private List<RenderObject>  renderList;
    private List<IDisposable>   resources;
    private SortedList<Integer, RenderObject> srenderList;

    /**
     * Constructor
     * @param dirtyHandler Handler this frame can invoke when it is dirty
     */
    public Frame(Action dirtyHandler){
        this.dirtyHandler   = dirtyHandler;
        this.layer          = 0;
        this.renderList     = new ArrayList<>();
        this.resources      = new ArrayList<>();
        this.srenderList    = new SortedList<>();
    }

    /**
     * Sets the layer of this frame
     * @param layer Layer. Frames with higher layers will be rendered on top of frames with lower values
     */
    public void setLayer(int layer){
        this.layer = layer;
    }
    /**
     * Returns the layer of this frame
     * @return Layer. Frames with higher layers will be rendered on top of frames with lower values
     */
    public int getLayer(){
        return layer;
    }

    /**
     * Sets this frame as dirty. This frame will be rerendered on the next frame
     */
    public void setDirty(){
        dirtyHandler.invoke();
    }

    /**
     * Adds a RenderObject to the render list. This object will be rendered when the frame is rendered
     * @param object Object to render
     */
    public void addObjectToRenderList(RenderObject object){
        renderList.add(object);
        object.setDirtyHandler(this::setDirty);
        setDirty();
    }
    /**
     * Removes a RenderObject from the render list. This object will no longer be rendered when the frame is rendered
     * @param object Object to remove
     */
    public void removeObjectFromRenderList(RenderObject object){
        renderList.remove(object);
        object.setDirtyHandler(null);
        setDirty();
    }

    /**
     * Returns a list of objects in this frame
     * @return
     */
    public List<RenderObject> getRenderList(){
        return renderList;
    }

    /**
     * Handles a touch
     * @param id Touch ID
     * @param type Touch Type
     * @param x X Position
     * @param y Y Position
     * @return True if this frame blocks the input for lower layers. False otherwise
     */
    public boolean handleTouchEvent(long id, TouchType type, int x, int y){
        srenderList.clear();
        for(int i = 0; i < renderList.size(); i++){
            srenderList.add(renderList.get(i).getLayer(), renderList.get(i));
        }

        for(int i = srenderList.size() - 1; i >= 0; i--){
            RenderObject obj = srenderList.get(i);
            if(!(obj instanceof ITouchEventHandler)){
                continue;
            }
            ITouchEventHandler handler = (ITouchEventHandler)obj;

            if(handler.handleTouchEvent(id, type, x, y)){
                return true;
            }
        }

        return false;
    }

    /**
     * Creates a new texture. This texture will be disposed when this frame gets disposed
     * @param path Path to the texture file
     * @return Texture instance
     */
    public Texture createTexture(String path){
        Texture texture = Texture.create(path);
        if(texture != null){
            resources.add(texture);
        }
        return texture;
    }
    /**
     * Creates a new Font. This font will be disposed when this frame gets disposed
     * @param path Path to the font to load
     * @param size Font size
     * @return Font instance
     */
    public Font createFont(String path, int size){
        Font font = Font.create(path, size);
        if(font != null){
            resources.add(font);
        }
        return font;
    }
    /**
     * Creates a new Label. This label will be disposed when this frame gets disposed
     * @param font Font to use for rendering this label
     * @param text Text. Cannot be empty
     * @param horizontalAlignment Horizontal alignment of this label
     * @param verticalAlignment Vertical alignment of this label
     * @param x X position
     * @param y Y position
     * @param width Label width
     * @param height Label height
     * @param r Color red value. 0-255
     * @param g Color green value. 0-255
     * @param b Color blue value. 0-255
     * @param a Color alpha value. 0-255
     * @return Label instance
     */
    public Label createLabel(Font font, String text,
                             HorizontalAlignment horizontalAlignment,
                             VerticalAlignment verticalAlignment,
                             int x, int y, int width, int height,
                             byte r, byte g, byte b, byte a){
        Label label = Label.create(font, text,
                                    horizontalAlignment, verticalAlignment,
                                    x, y, width, height,
                                    r, g, b, a);
        if(label != null){
            resources.add(label);
        }
        return label;
    }

    /**
     * Disposes a disposable object that is created by this frame
     * @param obj Disposable object
     */
    public void disposeObject(IDisposable obj){
        if(resources.contains(obj)){
            resources.remove(obj);
            obj.dispose();
        }
    }
    @Override
    public void dispose(){
        for(IDisposable resource : resources){
            resource.dispose();
        }
        resources.clear();
    }
}
