package de.silveryard.basesystem.gui;

import de.silveryard.basesystem.util.IDisposable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beppo on 04/02/17.
 */
public class FontCollection implements IDisposable {
    private Path directory;
    private List<FontCollectionElement> fonts;

    /**
     * Constructor
     * @param directory Path to a directory that contains font files
     */
    public FontCollection(Path directory){
        if(!Files.exists(directory)){
            throw new RuntimeException("Path not found");
        }
        if(!Files.isDirectory(directory)){
            throw new RuntimeException("Path is no directory");
        }

        this.directory = directory;
        this.fonts = new ArrayList<>();
    }

    /**
     * Returns the instance of a font based on a name and a size. Loads the font when not already loaded
     * @param name Font name to get
     * @param size Font size
     * @return Font instance
     */
    public Font get(String name, int size){
        for(FontCollectionElement font : fonts){
            if(font.getName().equals(name) && font.getSize() == size){
                return font.getFont();
            }
        }

        Path path = Paths.get(directory.toString(), name + ".ttf");
        Font font = Font.create(path.toString(), size);
        if(font == null){
            throw new RuntimeException("Failed to load font: " + name);
        }
        FontCollectionElement element = new FontCollectionElement(font, name, size);
        fonts.add(element);
        return font;
    }

    @Override
    public void dispose() {
        for(FontCollectionElement font : fonts){
            font.getFont().dispose();
        }
        fonts.clear();
    }
}
