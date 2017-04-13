package de.silveryard.basesystem.gui;

import de.silveryard.basesystem.util.IDisposable;

import static de.silveryard.basesystem.gui.SDLTexture.*;

/**
 * Created by beppo on 03/02/17.
 */
public class Texture implements IDisposable {
    /**
     * Creates a new texture. You should call Frame.createTexture instead
     * @param path Path of the texture to load
     * @return Texture instance
     */
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

    /**
     * Returns the textures internal ID
     * @return Texture identifier
     */
    public int getId(){
        return id;
    }

    /**
     * Returns the textures width
     * @return Width value
     */
    public int getWidth(){
        return textureGetWidth(id);
    }
    /**
     * Returns the textures height
     * @return Height value
     */
    public int getHeight(){
        return textureGetHeight(id);
    }

    /**
     * Sets the textures alpha
     * @param alpha Alpha value. 0-255
     * @return Return code
     */
    public int setAlpha(byte alpha){
        return textureSetAlpha(id, alpha);
    }

    @Override
    public void dispose() {
        textureUnload(id);
    }
}
