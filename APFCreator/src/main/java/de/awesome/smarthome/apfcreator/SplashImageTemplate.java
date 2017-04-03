package de.awesome.smarthome.apfcreator;

import de.awesome.smarthome.apf.SplashImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.awesome.smarthome.apfcreator.Assert.*;

/**
 * Created by Sebif on 24.02.2017.
 */
public class SplashImageTemplate {
    private int width;
    private int height;
    private byte[] data;

    public SplashImageTemplate(Path path){
        assertNotNull(path, "Splash Image path cannot be null");
        assertTrue(Files.exists(path), "Splash Image '" + path.toString() + "' does not exist");
        assertFalse(Files.isDirectory(path), "Splash Image '" + path.toString() + "' is a directory");

        try {
            data = Files.readAllBytes(path);
        }catch(Exception e){
            assertTrue(false, "Could not read Splash Image '" + path.toString() + "': " + e.getMessage());
        }
        assertLowerEquals(data.length, (10 * 1024 * 1024), "Splash Image '" + path.toString() + "' is greater than 10MiB. Do not do that");

        BufferedImage image = null;

        try {
            image = ImageIO.read(path.toFile());
        }catch(Exception e){
            assertTrue(false, "Could not read Splash Image '" + path.toString() + "': " + e.getMessage());
        }

        assertFalse(image.getColorModel().hasAlpha(), "Splash Image '" + path.toString() + "' has alpha. Transparent Splash images are not supported");
        width = image.getWidth();
        height = image.getHeight();
        assertLowerEquals(width, 65535, "Splash Image '" + path.toString() + "' width cannot be greater that 65535. What did you do???");
        assertLowerEquals(height, 65535, "Splash Image '" + path.toString() + "' height cannot be greater that 65535. What did you do???");
    }

    public SplashImage create(){
        return new SplashImage((short)width, (short)height, data);
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
