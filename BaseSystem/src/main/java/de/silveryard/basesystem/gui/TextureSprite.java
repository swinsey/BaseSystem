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

    /**
     * Constructor
     * @param texture Texture to use
     * @param texX Pixel x coordinate
     * @param texY Pixel y coordinate
     * @param texWidth Pixel width
     * @param texHeight Pixel height
     */
    public TextureSprite(Texture texture, int texX, int texY, int texWidth, int texHeight){
        this.texture      = texture;
        this.texX         = texX;
        this.texY         = texY;
        this.texWidth     = texWidth;
        this.texHeight    = texHeight;
    }

    /**
     * Returns the used texture
     * @return Texture instance
     */
    public Texture getTexture(){
        return texture;
    }

    /**
     * Returns the textures source x position
     * @return Pixel x coordinate
     */
    public int getTextureX(){
        return texX;
    }
    /**
     * Returns the textures source y position
     * @return Pixel y coordinate
     */
    public int getTextureY(){
        return texY;
    }
    /**
     * Returns the texture source width
     * @return Pixel width
     */
    public int getTextureWidth(){
        return texWidth;
    }
    /**
     * Returns the texture source height
     * @return Pixel height
     */
    public int getTextureHeight(){
        return texHeight;
    }
}
