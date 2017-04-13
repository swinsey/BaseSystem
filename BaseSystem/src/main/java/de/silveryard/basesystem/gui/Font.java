package de.silveryard.basesystem.gui;

import de.silveryard.basesystem.util.IDisposable;

import static de.silveryard.basesystem.gui.SDLFont.fontLoad;
import static de.silveryard.basesystem.gui.SDLFont.fontUnload;

/**
 * Created by beppo on 04/02/17.
 */
public class Font implements IDisposable {
    /**
     * Loads a new font from the file system. You should call Font.createFont instead
     * @param path Path to the font file
     * @param size Font size
     * @return Font instance
     */
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

    /**
     * Returns the internal id of this loaded font
     * @return Identifier
     */
    public int getId(){
        return id;
    }
    /**
     * Returns the size of this local font
     * @return Font size
     */
    public int getSize(){
        return size;
    }

    @Override
    public void dispose(){
        fontUnload(id);
    }
}
