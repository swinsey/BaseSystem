package de.silveryard.basesystem.gui;

/**
 * Created by beppo on 04/02/17.
 */
public class FontCollectionElement {
    private Font font;
    private String name;
    private int size;

    public FontCollectionElement(Font font, String name, int size){
        this.font = font;
        this.name = name;
        this.size = size;
    }

    public Font getFont(){
        return font;
    }
    public String getName(){
        return name;
    }
    public int getSize(){
        return size;
    }
}
