package de.awesome.smarthome.apfcreator;

import de.awesome.smarthome.apf.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static de.awesome.smarthome.apfcreator.Assert.*;

/**
 * Created by Sebif on 24.02.2017.
 */
public class FileTemplate {
    private String filename;
    private List<String> subDirs;
    private byte[] data;

    public FileTemplate(Path filePath, Path basePath){
        assertNotNull(filePath, "File Path cannot be null");
        assertNotNull(basePath, "Base Path cannot be null");
        assertTrue(Files.exists(filePath), "File '" + filePath.toString() + "' does not exist");
        assertFalse(Files.isDirectory(filePath), "File '" + filePath.toString() + "' cannot be a directory");
        assertTrue(Files.exists(basePath), "Base Path '" + basePath.toString() + "' does not exist");
        assertTrue(Files.isDirectory(basePath), "Base Path '" + basePath.toString() + "' has to be a directory");

        try {
            data = Files.readAllBytes(filePath);
        }catch(Exception e){
            assertTrue(false, "Could not read '" + filePath.toString() + "'");
        }
        assertLowerEquals(data.length, (1 * 1024 * 1024 * 1024), "File '" + filePath.toString() + "' is larger than 1GiB. Do not do that");

        filename = filePath.getFileName().toString();
        assertGreater(filename.length(),0, "File Name length of file '" + filePath.toString() + "' has to be greater than 0");
        assertLowerEquals(filename.length(), 255, "File Name length of file '" + filePath.toString() + "' has to be max 255");

        String[] splitsFile = filePath.toString().replace("\\", "/").split("/");
        String[] splitsBase = basePath.toString().replace("\\", "/").split("/");
        subDirs = new ArrayList<>();

        for(int i = 0; i < splitsBase.length; i++){
            assertEquals(splitsFile[i], splitsBase[i], "Base Path '" + basePath.toString() + "' is not base of '" + filePath.toString() + "'");
        }

        for(int i = splitsBase.length; i < splitsFile.length - 1; i++){
            assertLowerEquals(splitsFile[i].length(), 255, "File '" + filePath.toString() + "' subdirs cannot be longer than 255");
            subDirs.add(splitsFile[i]);
        }
    }

    public File create(){
        return new File(filename, subDirs.toArray(new String[subDirs.size()]), data);
    }
}
