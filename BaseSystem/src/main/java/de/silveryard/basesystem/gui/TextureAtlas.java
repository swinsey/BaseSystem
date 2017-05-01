package de.silveryard.basesystem.gui;

import java.util.Map;

/**
 * Created by beppo on 04/02/17.
 */
public class TextureAtlas {
    private Map<String, TextureSprite> sprites;

    /**
     * Creates a new texture atlas based on names and texturesprites
     * @param sprites Map Key: Texture sprite name. Value: Texture sprite
     */
    public TextureAtlas(Map<String, TextureSprite> sprites){
        this.sprites = sprites;
    }

    /**
     * Returns a texture sprite based on a given name
     * @param name Texture sprite name
     * @return TextureSprite instance
     */
    public TextureSprite get(String name){
        return sprites.get(name);
    }
}
