package de.silveryard.apfcreator;

import de.silveryard.apf.ApplicationPackageFile;
import de.silveryard.apf.Builder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static de.silveryard.apfcreator.Assert.*;

/**
 * Created by Sebif on 25.02.2017.
 */
public class Creator {
    /**
     * Creates an apf file based on argument args
     * @param args
     */
    public static void create(String... args){
        assertEqual(args.length, 3, "Invalid amount of arguments");
        assertTrue(args[0].equals("create"), "First parameter must be create to call Creator.create");

        create(Paths.get(args[1]), Paths.get(args[2]));
    }
    /**
     * Creates an apf file based on a config file
     * @param configFile
     * @param outputFile
     */
    public static void create(Path configFile, Path outputFile){
        assertTrue(Files.exists(configFile), "Configuration file does not exist");
        assertFalse(Files.isDirectory(configFile), "Configuration input can not be a directory");

        try {
            List<String> lines = Files.readAllLines(configFile);
            String content = "";

            for(int i = 0; i < lines.size(); i++){
                content += lines.get(i) + "\n";
            }
            create(content, configFile.getParent(), outputFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * Creates an apf file based on a config string
     * @param config
     * @param workingDirectory
     * @param outputFile
     */
    public static void create(String config, Path workingDirectory, Path outputFile){
        JSONObject obj = new JSONObject(config);
        create(obj, workingDirectory, outputFile);
    }
    /**
     * Creates an apf file based on config json object
     * @param config
     * @param workingDirectory
     * @param outputFile
     */
    public static void create(JSONObject config, Path workingDirectory, Path outputFile){
        JSONObject configMetadata = tryGetObj(config, "metadata", true);
        JSONArray configIcons = tryGetArray(config, "icons", true);
        JSONArray configSplashs = tryGetArray(config, "splashs", true);
        JSONArray configFiles = tryGetArray(config, "files", true);

        //Metadata
        String appName = tryGetString(configMetadata, "appName", true);
        String appIdentifier = tryGetString(configMetadata, "appIdentifier", true);
        byte majorVersion = 0;
        byte minorVersion = 0;
        try {
            int iMajorVersion = Integer.parseInt(tryGetString(configMetadata, "majorVersion", true));
            assertGreaterEquals(iMajorVersion, 0, "Major version cannot be negative");
            assertLowerEquals(iMajorVersion, 255, "Major version cannot be larger than 255");
            majorVersion = (byte)iMajorVersion;
        }catch(Exception e){
            assertTrue(false, "Failed to parse major version as integer");
        }
        try{
            int iMinorVersion = Integer.parseInt(tryGetString(configMetadata, "minorVersion", true));
            assertGreaterEquals(iMinorVersion, 0, "Minor version cannot be negative");
            assertLowerEquals(iMinorVersion, 255, "Minor version cannot be larger than 255");
            minorVersion = (byte)iMinorVersion;
        }catch(Exception e){
            assertTrue(false, "Failed to parse minor version as integer");
        }

        //Read Data
        System.out.println("Reading Data");
        ApplicationPackageFileTemplate template = new ApplicationPackageFileTemplate();
        template.setAppName(appName);
        template.setAppIdentifier(appIdentifier);
        template.setMajorVersion(majorVersion);
        template.setMinorVersion(minorVersion);

        readBinary(template, config, workingDirectory);
        readAppIcons(template, configIcons, workingDirectory);
        readSplashImages(template, configSplashs, workingDirectory);
        readFiles(template, configFiles, workingDirectory);

        //Parsing
        System.out.println("Parsing");
        ApplicationPackageFile apf = template.create();
        byte[] data = Builder.build(apf);

        System.out.println("Building");
        try {
            Files.write(outputFile, data);
        }catch(Exception e){
            assertTrue(false, "Could not write output file");
        }

        System.out.println("");
        Main.print(apf, data);
    }

    private static void readBinary(ApplicationPackageFileTemplate template, JSONObject config, Path workingDirectory){
        String binPathString = tryGetString(config, "binary", true);
        Path binPath = Paths.get(workingDirectory.toString(), binPathString.split("\\/"));

        assertTrue(Files.exists(binPath), "Binary file does not exist");
        assertFalse(Files.isDirectory(binPath), "Binary file cannot be a directory");

        try {
            byte[] binary = Files.readAllBytes(binPath);
            template.setBinary(binary);
        } catch (IOException e) {
            assertTrue(false, "Failed to read binary: " + e.getMessage());
        }
    }
    private static void readAppIcons(ApplicationPackageFileTemplate template, JSONArray config, Path workingDirectory){
        assertGreater(config.length(), 0, "You have to specify at least one icon!");

        for(int i = 0; i < config.length(); i++){
            String pathString = tryGetArrayString(config, i);
            Path path = Paths.get(workingDirectory.toString(), pathString.split("\\/"));

            assertTrue(Files.exists(path), "Icon at index " + i + " does not exist");
            assertFalse(Files.isDirectory(path), "Icon at index " + i + " cannot be a directory");

            template.addIcon(path);
        }
    }
    private static void readSplashImages(ApplicationPackageFileTemplate template, JSONArray config, Path workingDirectory){
        assertGreater(config.length(), 0, "You have to specify at least one splash image!");

        for(int i = 0; i < config.length(); i++){
            String pathString = tryGetArrayString(config, i);
            Path path = Paths.get(workingDirectory.toString(), pathString.split("\\/"));

            assertTrue(Files.exists(path), "Splash at index " + i + " does not exist");
            assertFalse(Files.isDirectory(path), "Splash at index " + i + " cannot be a directory");

            template.addSplashImage(path);
        }
    }
    private static void readFiles(ApplicationPackageFileTemplate template, JSONArray config, Path workingDirectory){
        for(int i = 0; i < config.length(); i++){
            JSONObject obj = tryGetArrayObj(config, i);
            String sourcePathString = tryGetString(obj, "source", true);
            String destPathString = tryGetString(obj, "destination", true);

            Path sourcePath = Paths.get(workingDirectory.toString(), sourcePathString.split("\\/"));

            assertTrue(Files.exists(sourcePath), "File at index " + i + " does not exist");

            if(Files.isDirectory(sourcePath)){
                addDirectory(template, sourcePath, sourcePath, destPathString);
            }else{
                addFile(template, sourcePath, destPathString);
            }
        }
    }
    private static void addDirectory(ApplicationPackageFileTemplate template, Path base, Path cur, String baseDestination){
        File dirFile = new File(cur.toString());
        String[] files = dirFile.list();
        for(int i = 0; i < files.length; i++){
            Path file = Paths.get(cur.toString(), files[i]);
            if(Files.isDirectory(file)){
                addDirectory(template, base, file, baseDestination);
                continue;
            }

            String[] splitsCur = cur.toString().replace("\\", "/").split("/");
            String[] splitsBase = base.toString().replace("\\", "/").split("/");

            for(int j = 0; j < splitsBase.length; j++){
                assertEquals(splitsCur[j], splitsBase[j], "Base Path '" + base.toString() + "' is not base of '" + cur.toString() + "'");
            }

            String fileDestination = baseDestination;
            if(fileDestination.endsWith("/")){
                fileDestination = fileDestination.substring(0, fileDestination.length() - 1);
            }

            for(int j = splitsBase.length; j < splitsCur.length; j++){
                fileDestination += "/" + splitsCur[j];

            }
            fileDestination += "/" + file.getFileName().toString();

            if(fileDestination.startsWith("/")){
                fileDestination = fileDestination.substring(1);
            }

            addFile(template, file, fileDestination);
        }
    }
    private static void addFile(ApplicationPackageFileTemplate template, Path path, String destination){
        template.addFile(path, destination);
    }

    private static JSONObject tryGetObj(JSONObject obj, String key, boolean failHard){
        if(!obj.has(key)){
            if(failHard){
                assertTrue(false, "Expected Key: " + key);
            }else{
                return null;
            }
        }
        if(obj.isNull(key)){
            if(failHard){
                assertTrue(false, "Key " + key + " is null!");
            }else{
                return null;
            }
        }

        try{
            return obj.getJSONObject(key);
        }catch(Exception e){
            if(failHard){
                e.printStackTrace();
                throw new RuntimeException(e);
            }else{
                return null;
            }
        }
    }
    private static JSONArray tryGetArray(JSONObject obj, String key, boolean failHard){
        if(!obj.has(key)){
            if(failHard){
                assertTrue(false, "Expected Key: " + key);
            }else{
                return null;
            }
        }
        if(obj.isNull(key)){
            if(failHard){
                assertTrue(false, "Key " + key + " is null!");
            }else{
                return null;
            }
        }

        try{
            return obj.getJSONArray(key);
        }catch(Exception e){
            if(failHard){
                e.printStackTrace();
                throw new RuntimeException(e);
            }else{
                return null;
            }
        }
    }
    private static String tryGetString(JSONObject obj, String key, boolean failHard){
        if(!obj.has(key)){
            if(failHard){
                assertTrue(false, "Expected Key: " + key);
            }else{
                return null;
            }
        }
        if(obj.isNull(key)){
            if(failHard){
                assertTrue(false, "Key " + key + " is null!");
            }else{
                return null;
            }
        }

        try{
            return obj.getString(key);
        }catch(Exception e){
            try{
                return "" + obj.getInt(key);
            }catch(Exception e2){
                if(failHard){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }else{
                    return null;
                }
            }
        }
    }
    private static String tryGetArrayString(JSONArray arr, int index){
        try{
            return arr.getString(index);
        }catch(Exception e){
            assertTrue(false, "Failed to read index " + index + " as String");
            return null;
        }
    }
    private static JSONObject tryGetArrayObj(JSONArray arr, int index){
        try{
            return arr.getJSONObject(index);
        }catch(Exception e){
            assertTrue(false, "Failed to read index " + index + " as Object");
            return null;
        }
    }

    private Creator(){}
}
