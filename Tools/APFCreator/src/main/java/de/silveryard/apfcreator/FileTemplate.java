package de.silveryard.apfcreator;

import de.silveryard.apf.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static de.silveryard.apfcreator.Assert.*;

/**
 * Created by Sebif on 24.02.2017.
 */
public class FileTemplate {
    private String filename;
    private List<String> subDirs;
    private byte[] data;

    public FileTemplate(Path filePath, String destination){
        assertNotNull(filePath, "File Path cannot be null");
        assertNotNull(destination, "Destination cannot be null");
        assertTrue(Files.exists(filePath), "File '" + filePath.toString() + "' does not exist");
        assertFalse(Files.isDirectory(filePath), "File '" + filePath.toString() + "' cannot be a directory");

        try {
            data = Files.readAllBytes(filePath);
        }catch(Exception e){
            assertTrue(false, "Could not read '" + filePath.toString() + "'");
        }
        assertLowerEquals(data.length, (1 * 1024 * 1024 * 1024), "File '" + filePath.toString() + "' is larger than 1GiB. Do not do that");

        String[] destinationSplits = destination.split("/");
        filename = destinationSplits[destinationSplits.length - 1];
        subDirs = new ArrayList<>();
        for(int i = 0; i < destinationSplits.length - 1; i++){
            subDirs.add(destinationSplits[i]);
        }
    }

    public File create(){
        return new File(filename, subDirs.toArray(new String[subDirs.size()]), data);
    }
}
