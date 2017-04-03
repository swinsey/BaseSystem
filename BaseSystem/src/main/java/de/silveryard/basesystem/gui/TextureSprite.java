package de.silveryard.basesystem.gui;

/**
 * Created by beppo on 04/02/17.
 */
public class TextureSprite {
    private Texture texture;
    private int texX;
    private int texY;
    private int texWidth;
    private int texHeight;

    public TextureSprite(Texture texture, int texX, int texY, int texWidth, int texHeight){
        this.texture      = texture;
        this.texX         = texX;
        this.texY         = texY;
        this.texWidth     = texWidth;
        this.texHeight    = texHeight;
    }

    public Texture getTexture(){
        return texture;
    }

    public int getTextureX(){
        return texX;
    }
    public int getTextureY(){
        return texY;
    }
    public int getTextureWidth(){
        return texWidth;
    }
    public int getTextureHeight(){
        return texHeight;
    }
}
