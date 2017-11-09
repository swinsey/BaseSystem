package de.silveryard.basesystem.gui;

import de.silveryard.basesystem.util.Action;

/**
 * Created by beppo on 03/02/17.
 */
public abstract class RenderObject {
    private Action dirtyHandler;
    /**
     * Layer of this object. Objects with higher layers on a frame will be drawn on top of objects with lower values
     */
    protected int layer;

    /**
     * Draws this object
     */
    public abstract void draw();

    /**
     * Sets the layer of this object.
     * @param layer Layer value. Objects with higher layers on a frame will be drawn on top of objects with lower values
     */
    public void setLayer(int layer){
        this.layer = layer;
        setDirty();
    }
    /**
     * Returns the layer of this object
     * @return Layer value. Objects with higher layers on a frame will be drawn on top of objects with lower values
     */
    public int getLayer(){
        return layer;
    }

    /**
     * Sets the handler that is called when setDirty is called
     * @param handler New handler
     */
    public void setDirtyHandler(Action handler){
        dirtyHandler = handler;
    }
    /**
     * Sets this objects as dirty. It will be rerendered on the next frame
     */
    public void setDirty(){
        if(dirtyHandler != null) {
            dirtyHandler.invoke();
        }
    }
}
