package de.awesome.smarthome.apfcreator;

import de.awesome.smarthome.apf.ApplicationPackageFile;
import de.awesome.smarthome.apf.Parser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static de.awesome.smarthome.apfcreator.Assert.*;

/**
 * Created by Sebif on 25.02.2017.
 */
public class Validator {
    public static void validate(String[] args){
        assertEqual(args.length, 2, "Invalid argument count");

        Path filePath = Paths.get(args[1]);
        assertTrue(Files.exists(filePath), "File not found");
        assertFalse(Files.isDirectory(filePath), "Argument cannot be a directory");

        byte[] data = null;
        try {
            data = Files.readAllBytes(filePath);
        }catch(Exception e){
            assertTrue(false, "Could not read file: " + e.getMessage());
        }

        Parser p = new Parser(data);
        ApplicationPackageFile apf = p.parse();

        Main.print(apf, data);
    }
}
