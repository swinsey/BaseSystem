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

    public Frame(Action dirtyHandler){
        this.dirtyHandler   = dirtyHandler;
        this.layer          = 0;
        this.renderList     = new ArrayList<>();
        this.resources      = new ArrayList<>();
        this.srenderList    = new SortedList<>();
    }

    public void setLayer(int layer){
        this.layer = layer;
    }
    public int getLayer(){
        return layer;
    }

    public void setDirty(){
        dirtyHandler.invoke();
    }

    public void addObjectToRenderList(RenderObject object){
        renderList.add(object);
        object.setDirtyHandler(this::setDirty);
        setDirty();
    }
    public void removeObjectFromRenderList(RenderObject object){
        renderList.remove(object);
        object.setDirtyHandler(null);
        setDirty();
    }

    public List<RenderObject> getRenderList(){
        return renderList;
    }

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

    public Texture createTexture(String path){
        Texture texture = Texture.create(path);
        if(texture != null){
            resources.add(texture);
        }
        return texture;
    }
    public Font createFont(String path, int size){
        Font font = Font.create(path, size);
        if(font != null){
            resources.add(font);
        }
        return font;
    }
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

    public void disposeObject(IDisposable obj){
        if(resources.contains(obj)){
            resources.remove(obj);
            obj.dispose();
        }
    }
    public void dispose(){
        for(IDisposable resource : resources){
            resource.dispose();
        }
        resources.clear();
    }
}
