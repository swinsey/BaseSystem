package de.silveryard.basesystem.gui;

import java.util.Map;

/**
 * Created by beppo on 04/02/17.
 */
public class TextureAtlas {
    private Map<String, TextureSprite> sprites;

    public TextureAtlas(Map<String, TextureSprite> sprites){
        this.sprites = sprites;
    }

    public TextureSprite get(String name){
        return sprites.get(name);
    }
}
