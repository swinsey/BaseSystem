package de.silveryard.apfcreator;

import de.silveryard.apf.ApplicationPackageFile;
import de.silveryard.apf.Builder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static de.silveryard.apfcreator.Assert.*;

/**
 * Created by Sebif on 25.02.2017.
 */
public class Creator {
    public static void create(String[] args){
        assertEqual(args.length, 6, "Invalid amount of arguments");
        for(int i = 0; i < args.length; i++){
            assertNotNull(args[i], "Argument " + i + " is null. This should not happen");
        }

        String appName = args[1];
        String appIdentifier = args[2];
        String fileDir = args[5];
        byte majorVersion = 0;
        byte minorVersion = 0;

        try{
            majorVersion = Byte.parseByte(args[3]);
        }catch(Exception e){
            assertTrue(false, "Failed to parse major version");
        }
        try{
            minorVersion = Byte.parseByte(args[4]);
        }catch(Exception e){
            assertTrue(false, "Failed to parse minor version");
        }

        System.out.println("Reading Data");

        Path dir = Paths.get(fileDir);
        Arguments arguments = checkDir(dir);
        arguments.outFile = Paths.get(arguments.baseDir.toString(), appIdentifier + "_" + (0xFF&majorVersion) + "_" + (0xFF&minorVersion) + ".apf");

        ApplicationPackageFileTemplate template = new ApplicationPackageFileTemplate();
        template.setAppName(appName);
        template.setAppIdentifier(appIdentifier);
        template.setMajorVersion(majorVersion);
        template.setMinorVersion(minorVersion);

        readBinary(template, arguments);
        readAppIcons(template, arguments);
        readSplashImages(template, arguments);
        readFiles(arguments.fileDir, arguments.fileDir, template);

        System.out.println("Parsing");
        ApplicationPackageFile apf = template.create();
        byte[] data = Builder.build(apf);

        System.out.println("Building");
        try {
            Files.write(arguments.outFile, data);
        }catch(Exception e){
            assertTrue(false, "Could not write output file");
        }

        System.out.println("");
        Main.print(apf, data);
    }

    private static Arguments checkDir(Path dir){
        Arguments arguments = new Arguments();
        arguments.baseDir = dir;

        assertNotNull(arguments.baseDir, "Directory cannot be null");
        assertTrue(Files.exists(arguments.baseDir), "App Directory not found");
        assertTrue(Files.isDirectory(arguments.baseDir), "App Directory is no directory");

        arguments.appFile = Paths.get(arguments.baseDir.toString(), "app.jar");
        assertTrue(Files.exists(arguments.appFile), "app.jar does not exist");
        assertFalse(Files.isDirectory(arguments.appFile), "app.jar cannot be a directory");

        arguments.iconDir = Paths.get(arguments.baseDir.toString(), "icons");
        assertTrue(Files.exists(arguments.iconDir), "icons directory does not exist");
        assertTrue(Files.isDirectory(arguments.iconDir), "icons directoy cannot be a file");

        arguments.splashDir = Paths.get(arguments.baseDir.toString(), "splashImages");
        assertTrue(Files.exists(arguments.splashDir), "splashImages directory does not exist");
        assertTrue(Files.isDirectory(arguments.splashDir), "splashImages directory cannot be a file");

        arguments.fileDir = Paths.get(arguments.baseDir.toString(),  "files");
        assertTrue(Files.exists(arguments.fileDir), "files directory does not exist");
        assertTrue(Files.isDirectory(arguments.fileDir), "files directory cannot be a file");

        return arguments;
    }

    private static void readBinary(ApplicationPackageFileTemplate template, Arguments arguments){
        try{
            template.setBinary(Files.readAllBytes(arguments.appFile));
        }catch(Exception e){
            assertTrue(false, "Failed to read app.jar: " + e.getMessage());
        }
    }
    private static void readAppIcons(ApplicationPackageFileTemplate template, Arguments arguments){
        File dirFile = new File(arguments.iconDir.toString());
        String[] files = dirFile.list();
        for(int i = 0; i < files.length; i++){
            Path file = Paths.get(arguments.iconDir.toString(), files[i]);
            if(Files.isDirectory(file)){
                continue;
            }

            template.addIcon(file);
        }
    }
    private static void readSplashImages(ApplicationPackageFileTemplate template, Arguments arguments){
        File dirFile = new File(arguments.splashDir.toString());
        String[] files = dirFile.list();
        for(int i = 0; i < files.length; i++){
            Path file = Paths.get(arguments.splashDir.toString(), files[i]);
            if(Files.isDirectory(file)){
                continue;
            }

            template.addSplashImage(file);
        }
    }
    private static void readFiles(Path base, Path cur, ApplicationPackageFileTemplate template){
        File dirFile = new File(cur.toString());
        String[] files = dirFile.list();
        for(int i = 0; i < files.length; i++){
            Path file = Paths.get(cur.toString(), files[i]);
            if(Files.isDirectory(file)){
                readFiles(base, file, template);
                continue;
            }

            template.addFile(file, base);
        }
    }
}
