package de.silveryard.basesystem.gui;

import de.silveryard.basesystem.util.IDisposable;

import static de.silveryard.basesystem.gui.SDLTexture.*;

/**
 * Created by beppo on 03/02/17.
 */
public class Texture implements IDisposable {
    public static Texture create(String path){
        int result = textureLoad(path);
        if(result < 0){
            //TODO provide more error information
            return null;
        }

        return new Texture(result);
    }

    private int id;

    private Texture(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public int getWidth(){
        return textureGetWidth(id);
    }
    public int getHeight(){
        return textureGetHeight(id);
    }

    public int setAlpha(byte alpha){
        return textureSetAlpha(id, alpha);
    }

    public void dispose() {
        textureUnload(id);
    }
}
