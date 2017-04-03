package de.awesome.smarthome.td.gui;

import de.awesome.smarthome.td.util.Action;

/**
 * Created by beppo on 03/02/17.
 */
public abstract class RenderObject {
    private Action dirtyHandler;
    protected int layer;

    public abstract void draw();

    public void setLayer(int layer){
        this.layer = layer;
        setDirty();
    }
    public int getLayer(){
        return layer;
    }

    public void setDirtyHandler(Action handler){
        dirtyHandler = handler;
    }
    public void setDirty(){
        if(dirtyHandler != null) {
            dirtyHandler.invoke();
        }
    }
}
