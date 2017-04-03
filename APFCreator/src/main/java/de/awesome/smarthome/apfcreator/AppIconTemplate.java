package de.awesome.smarthome.apfcreator;

import de.awesome.smarthome.apf.AppIcon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.awesome.smarthome.apfcreator.Assert.*;

/**
 * Created by Sebif on 24.02.2017.
 */
public class AppIconTemplate {
    private int size;
    private byte[] data;

    public AppIconTemplate(Path path){
        assertNotNull(path, "Icon path cannot be null");
        assertTrue(Files.exists(path), "Icon '" + path.toString() + "' does not exist");
        assertFalse(Files.isDirectory(path), "Icon '" + path.toString() + "' is a directory");

        try {
            data = Files.readAllBytes(path);
        }catch(Exception e){
            assertTrue(false, "Could not read icon '" + path.toString() + "': " + e.getMessage());
        }
        assertLowerEquals(data.length, (4 * 1024 * 1024), "Icon '" + path.toString() + "' is greater than 4MiB. Do not do that");

        BufferedImage image = null;

        try {
            image = ImageIO.read(path.toFile());
            System.out.println("Image: " + image + " W: " + image.getWidth() + " H: " + image.getHeight());
        }catch(Exception e){
            assertTrue(false, "Could not read icon '" + path.toString() + "': " + e.getMessage());
        }

        assertEqual(image.getWidth(), image.getHeight(), "Icon '" + path.toString() + "' is not sqare");
        size = image.getWidth();
        assertLowerEquals(size, 65535, "Icon '" + path.toString() + "' size cannot be greater that 65535. What did you do???");
    }

    public AppIcon create(){
        return new AppIcon((short)size, data);
    }

    public int getSize(){
        return size;
    }
}
