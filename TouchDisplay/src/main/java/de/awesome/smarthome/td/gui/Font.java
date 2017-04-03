package de.awesome.smarthome.td.gui;

import de.awesome.smarthome.td.util.IDisposable;

import static de.awesome.smarthome.td.gui.SDLFont.fontLoad;
import static de.awesome.smarthome.td.gui.SDLFont.fontUnload;

/**
 * Created by beppo on 04/02/17.
 */
public class Font implements IDisposable {
    public static Font create(String path, int size){
        int result = fontLoad(path, size);
        if(result < 0){
            //TODO do error reporting
            return null;
        }

        return new Font(result, size);
    }

    private int id;
    private int size;

    private Font(int id, int size){
        this.id = id;
        this.size = size;
    }

    public int getId(){
        return id;
    }
    public int getSize(){
        return size;
    }

    public void dispose(){
        fontUnload(id);
    }
}
