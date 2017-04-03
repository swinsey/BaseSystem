package de.awesome.smarthome.td.app;

import de.awesome.smarthome.apf.AppIcon;
import de.awesome.smarthome.apf.ApplicationPackageFile;
import de.awesome.smarthome.apf.SplashImage;
import de.awesome.smarthome.td.util.IDisposable;
import de.awesome.smarthome.td.util.LRValue;
import de.awesome.smarthome.td.util.Utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DB Layout:
 * [apps]
 * appIdentifier char(255) NOT NULL
 * appName CHAR(255) PRIMARY KEY NOT NULL
 * majorVersion INT NOT NULL
 * minorVersion INT NOT NULL
 * binaryPath INT NOT NULL
 * binaryHash CHAR(32) NOT NULL
 * dataPath CHAR(255) NOT NULL
 * PRIMARY KEY(appIdentifier)
 *
 * [appIcons]
 * appIdentifier CHAR(255) NOT NULL
 * size INT NOT NULL
 * iconPath CHAR(255) NOT NULL
 * hash CHAR(32) NOT NULL
 * PRIMARY KEY(appIdentifier, size)
 *
 * [splashImages]
 * appIdentifier CHAR(255) NOT NULL
 * width INT NOT NULL
 * height INT NOT NULL
 * splashPath CHAR(255) NOT NULL
 * hash CHAR(32) NOT NULL
 * PRIMARY KEY(appIdentifier, width, height)
 *
 * [readonlyFiles]
 * ID INTEGER AUTOINCREAMENT NOT NULL
 * appIdentifier CHAR(255) NOT NULL
 * localPath CHAR(255) NOT NULL
 * hash CHAR(32) NOT NULL
 * PRIMARY KEY(ID)
 *
 * Created by Sebif on 12.02.2017.
 */
class AppDB implements IDisposable {
    private static final String TABLE_APPS = "apps";
    private static final String TABLE_APP_ICONS = "appIcons";
    private static final String TABLE_SPLASH_IMAGES = "splashImages";
    private static final String TABLE_READONLY_FILES = "readonlyFiles";

    private static final String COLOUMN_APP_IDENTIFIER = "appIdentifier";
    private static final String COLOUMN_APP_NAME = "appName";
    private static final String COLOUMN_MAJOR_VERSION = "majorVersion";
    private static final String COLOUMN_MINOR_VERSION = "minorVersion";
    private static final String COLOUMN_BINARY_PATH = "binaryPath";
    private static final String COLOUMN_BINARY_HASH = "binaryHash";
    private static final String COLOUMN_DATA_PATH = "dataPath";
    private static final String COLOUMN_READONLY_PATH = "readonlyPath";

    private static final String COLOUMN_ICON_SIZE = "size";
    private static final String COLOUMN_ICON_PATH = "iconPath";
    private static final String COLOUMN_ICON_HASH = "hash";

    private static final String COLOUMN_SPLASH_WIDTH = "width";
    private static final String COLOUMN_SPLASH_HEIGHT = "height";
    private static final String COLOUMN_SPLASH_PATH = "imagePath";
    private static final String COLOUMN_SPLASH_HASH = "hash";

    private static final String COLOUMN_FILE_ID = "ID";
    private static final String COLOUMN_FILE_PATH = "path";
    private static final String COLOUMN_FILE_HASH = "hash";

    private static final String STMT_CREATE_TABLE_APPS = "" +
            "CREATE TABLE " + TABLE_APPS + " (" +
            "    " + COLOUMN_APP_IDENTIFIER + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_APP_NAME + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_MAJOR_VERSION + " INT NOT NULL," +
            "    " + COLOUMN_MINOR_VERSION + " INT NOT NULL," +
            "    " + COLOUMN_BINARY_PATH + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_BINARY_HASH + " CHAR(32) NOT NULL," +
            "    " + COLOUMN_DATA_PATH + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_READONLY_PATH + " CHAR(255) NOT NULL," +
            "    PRIMARY KEY(appIdentifier)" +
            ")";
    private static final String STMT_CREATE_TABLE_APP_ICONS = "" +
            "CREATE TABLE " + TABLE_APP_ICONS + " (" +
            "    " + COLOUMN_APP_IDENTIFIER + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_ICON_SIZE + " INT NOT NULL," +
            "    " + COLOUMN_ICON_PATH + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_ICON_HASH + " CHAR(32) NOT NULL," +
            "    PRIMARY KEY(appIdentifier, size)" +
            ")";
    private static final String STMT_CREATE_TABLE_SPLASH_IMAGES = "" +
            "CREATE TABLE " + TABLE_SPLASH_IMAGES + " (" +
            "    " + COLOUMN_APP_IDENTIFIER + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_SPLASH_WIDTH + " INT NOT NULL," +
            "    " + COLOUMN_SPLASH_HEIGHT + " INT NOT NULL," +
            "    " + COLOUMN_SPLASH_PATH + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_SPLASH_HASH + " CHAR(32) NOT NULL," +
            "    PRIMARY KEY(appIdentifier, width, height)" +
            ")";
    private static final String STMT_CREATE_TABLE_READONLY_FILES = "" +
            "CREATE TABLE " + TABLE_READONLY_FILES + " (" +
            "    " + COLOUMN_FILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "    " + COLOUMN_APP_IDENTIFIER + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_FILE_PATH + " CHAR(255) NOT NULL," +
            "    " + COLOUMN_FILE_HASH + " CHAR(32) NOT NULL" +
            ")";

    private static final String STMT_ADD_APP = "" +
            "INSERT INTO " + TABLE_APPS + " (" +
            COLOUMN_APP_IDENTIFIER + ", " + COLOUMN_APP_NAME + ", " +
            COLOUMN_MAJOR_VERSION + ", " + COLOUMN_MINOR_VERSION + ", " +
            COLOUMN_BINARY_PATH + ", " + COLOUMN_BINARY_HASH + ", " +
            COLOUMN_DATA_PATH + ", " + COLOUMN_READONLY_PATH + ") VALUES ( " +
            "'%s', '%s', '%d', '%d', '%s', '%s', '%s', '%s')";
    private static final String STMT_ADD_APP_ICON = "" +
            "INSERT INTO " + TABLE_APP_ICONS + " (" +
            COLOUMN_APP_IDENTIFIER + ", " + COLOUMN_ICON_SIZE + ", " +
            COLOUMN_ICON_PATH + ", " + COLOUMN_ICON_HASH + ") VALUES ( " +
            "'%s', '%d', '%s', '%s')";
    private static final String STMT_ADD_SPLASH_IMAGE = "" +
            "INSERT INTO " + TABLE_SPLASH_IMAGES + " (" +
            COLOUMN_APP_IDENTIFIER + ", " +
            COLOUMN_SPLASH_WIDTH + ", " + COLOUMN_SPLASH_HEIGHT + ", " +
            COLOUMN_SPLASH_PATH + ", " + COLOUMN_SPLASH_HASH + ") VALUES ( " +
            "'%s', '%d', '%d', '%s', '%s')";
    private static final String STMT_ADD_READONLY_FILE = "" +
            "INSERT INTO " + TABLE_READONLY_FILES + " (" +
            COLOUMN_APP_IDENTIFIER + ", " +
            COLOUMN_FILE_PATH + ", " + COLOUMN_FILE_HASH + ") VALUES ( " +
            "'%s', '%s', '%s')";

    private static final String STMT_REMOVE_APP = "" +
            "DELETE " +
            "    FROM " + TABLE_APPS +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";
    private static final String STMT_REMOVE_APP_ICONS = "" +
            "DELETE " +
            "    FROM " + TABLE_APP_ICONS +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";
    private static final String STMT_REMOVE_SPLASH_IMAGES = "" +
            "DELETE " +
            "    FROM " + TABLE_SPLASH_IMAGES +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";
    private static final String STMT_REMOVE_READONLY_FILES = "" +
            "DELETE " +
            "    FROM " + TABLE_READONLY_FILES +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";

    private static final String STMT_GET_APP_IDENTIFIERS = "" +
            "SELECT " + COLOUMN_APP_IDENTIFIER +
            "    FROM " + TABLE_APPS;
    private static final String STMT_GET_APP_NAME = "" +
            "SELECT " + COLOUMN_APP_NAME +
            "    FROM " + TABLE_APPS +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";
    private static final String STMT_GET_APP_VERSION = "" +
            "SELECT " + COLOUMN_MAJOR_VERSION + ", " + COLOUMN_MINOR_VERSION +
            "    FROM " + TABLE_APPS +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";
    private static final String STMT_GET_DATA_PATH = "" +
            "SELECT " + COLOUMN_DATA_PATH +
            "    FROM " + TABLE_APPS +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";
    private static final String STMT_GET_READONLY_PATH = "" +
            "SELECT " + COLOUMN_READONLY_PATH +
            "    FROM " + TABLE_APPS +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";
    private static final String STMT_GET_BINARY_PATH = ""  +
            "SELECT " + COLOUMN_BINARY_PATH +
            "    FROM " + TABLE_APPS +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";
    private static final String STMT_GET_ICON_PATHS = "" +
            "SELECT " + COLOUMN_ICON_SIZE + ", " + COLOUMN_ICON_PATH +
            "    FROM " + TABLE_APP_ICONS +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";
    private static final String STMT_GET_SPLASH_PATHS = "" +
            "SELECT " + COLOUMN_SPLASH_WIDTH + ", " + COLOUMN_SPLASH_HEIGHT + ", " + COLOUMN_SPLASH_PATH +
            "    FROM " + TABLE_SPLASH_IMAGES +
            "    WHERE " + COLOUMN_APP_IDENTIFIER + " = '%s'";



    private Path appDbFile;
    private Path appDirectory;
    private Path dataDirectory;

    private Connection dbConnection;

    public AppDB(Path appDbFile, Path appDirectory, Path dataDirectory){
        this.appDbFile = appDbFile;
        this.appDirectory = appDirectory;
        this.dataDirectory = dataDirectory;

        boolean recreate = false;
        if(!Files.exists(appDbFile)){
            recreate = true;
        }
        try {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:" + appDbFile.toString());
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        if(recreate){
            executeSql(STMT_CREATE_TABLE_APPS);
            executeSql(STMT_CREATE_TABLE_APP_ICONS);
            executeSql(STMT_CREATE_TABLE_SPLASH_IMAGES);
            executeSql(STMT_CREATE_TABLE_READONLY_FILES);
        }
    }

    public synchronized List<String> getInstalledApps(){
        List<String> appIdentifiers = new ArrayList<>();

        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(STMT_GET_APP_IDENTIFIERS);
            while(resultSet.next()){
                appIdentifiers.add(resultSet.getString(COLOUMN_APP_IDENTIFIER));
            }
            resultSet.close();
            stmt.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return appIdentifiers;
    }
    public synchronized LRValue<Short[], AppManagerResult> getAppVersion(String appIdentifier){
        List<String> installedApps = getInstalledApps();
        if(!installedApps.contains(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.NOT_INSTALLED);
        }

        short majorVersion = 0;
        short minorVersion = 0;

        String sql = String.format(STMT_GET_APP_VERSION, appIdentifier);
        try{
            Statement stmt = dbConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();

            majorVersion = (short)resultSet.getInt(COLOUMN_MAJOR_VERSION);
            minorVersion = (short)resultSet.getInt(COLOUMN_MINOR_VERSION);

            resultSet.close();
            stmt.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return LRValue.createLValue(new Short[]{majorVersion, minorVersion});
    }
    public synchronized LRValue<Path, AppManagerResult> getDataPath(String appIdentifier){
        List<String> installedApps = getInstalledApps();
        if(!installedApps.contains(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.NOT_INSTALLED);
        }

        String dataPath = null;

        try{
            String sql = String.format(STMT_GET_DATA_PATH, appIdentifier);
            Statement stmt = dbConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();

            dataPath = resultSet.getString(COLOUMN_DATA_PATH);

            resultSet.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
            return LRValue.createRValue(AppManagerResult.UNKNOWN_ERROR);
        }

        return LRValue.createLValue(Paths.get(dataPath));
    }
    public synchronized LRValue<Path, AppManagerResult> getReadonlyPath(String appIdentifier){
        List<String> installedApps = getInstalledApps();
        if(!installedApps.contains(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.NOT_INSTALLED);
        }

        String dataPath = null;

        try{
            String sql = String.format(STMT_GET_READONLY_PATH, appIdentifier);
            Statement stmt = dbConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();

            dataPath = resultSet.getString(COLOUMN_READONLY_PATH);

            resultSet.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
            return LRValue.createRValue(AppManagerResult.UNKNOWN_ERROR);
        }

        return LRValue.createLValue(Paths.get(dataPath));
    }
    public synchronized LRValue<String, AppManagerResult> getAppName(String appIdentifier){
        List<String> installedApps = getInstalledApps();
        if(!installedApps.contains(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.NOT_INSTALLED);
        }

        String appName = null;

        try{
            String sql = String.format(STMT_GET_APP_NAME, appIdentifier);
            Statement stmt = dbConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();

            appName = resultSet.getString(COLOUMN_APP_NAME);

            resultSet.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
            return LRValue.createRValue(AppManagerResult.UNKNOWN_ERROR);
        }

        return LRValue.createLValue(appName);
    }
    public synchronized LRValue<Path, AppManagerResult> getAppBinary(String appIdentifier){
        List<String> installedApps = getInstalledApps();
        if(!installedApps.contains(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.NOT_INSTALLED);
        }

        String binaryPath = null;

        try{
            String sql = String.format(STMT_GET_BINARY_PATH, appIdentifier);
            Statement stmt = dbConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();

            binaryPath = resultSet.getString(COLOUMN_BINARY_PATH);

            resultSet.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
            return LRValue.createRValue(AppManagerResult.UNKNOWN_ERROR);
        }

        return LRValue.createLValue(Paths.get(binaryPath));
    }
    public synchronized LRValue<Path, AppManagerResult> getAppIcon(String appIdentifier, int desiredSize){
        List<String> installedApps = getInstalledApps();
        if(!installedApps.contains(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.NOT_INSTALLED);
        }

        Map<Integer, Path> paths = new HashMap<>();

        try{
            String sql = String.format(STMT_GET_ICON_PATHS, appIdentifier);
            Statement stmt = dbConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while(resultSet.next()){
                int size = resultSet.getInt(COLOUMN_ICON_SIZE);
                Path path = Paths.get(resultSet.getString(COLOUMN_ICON_PATH));

                paths.put(size, path);
            }

            resultSet.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
            return LRValue.createRValue(AppManagerResult.UNKNOWN_ERROR);
        }

        int bestSize = Integer.MIN_VALUE + 1;
        for(Integer key : paths.keySet()){
            int bestDelta = bestSize - desiredSize;
            int curDelta = key - desiredSize;

            if(bestDelta < 0)
                bestDelta *= -1;
            if(curDelta < 0)
                curDelta *= -1;

            if(curDelta < bestDelta)
                bestSize = key;
        }

        return LRValue.createLValue(paths.get(bestSize));
    }
    public synchronized LRValue<Path, AppManagerResult> getAppSplashImage(String appIdentifier, int desiredWidth, int desiredHeight){
        List<String> installedApps = getInstalledApps();
        if(!installedApps.contains(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.NOT_INSTALLED);
        }

        Map<Integer[], Path> paths = new HashMap<>();

        try{
            String sql = String.format(STMT_GET_SPLASH_PATHS, appIdentifier);
            Statement stmt = dbConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while(resultSet.next()){
                int width = resultSet.getInt(COLOUMN_SPLASH_WIDTH);
                int height = resultSet.getInt(COLOUMN_SPLASH_HEIGHT);
                Path path = Paths.get(resultSet.getString(COLOUMN_SPLASH_PATH));

                Integer[] arr = new Integer[2];
                arr[0] = width;
                arr[1] = height;
                paths.put(arr, path);
            }

            resultSet.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
            return LRValue.createRValue(AppManagerResult.UNKNOWN_ERROR);
        }

        Integer[] bestSize = new Integer[2];
        bestSize[0] = Integer.MIN_VALUE;
        bestSize[1] = Integer.MIN_VALUE;

        for(Integer[] key : paths.keySet()){
            int bestDelta = (bestSize[0] - desiredWidth) + (bestSize[1] - desiredHeight);
            int curDelta = (key[0] - desiredWidth) + (key[1] - desiredHeight);

            if(bestDelta < 0)
                bestDelta *= -1;
            if(curDelta < 0)
                curDelta *= -1;

            if(curDelta < bestDelta)
                bestSize = key;
        }

        return LRValue.createLValue(paths.get(bestSize));
    }

    public synchronized AppManagerResult uninstallApp(String appIdentifier){
        try{
            List<String> installedApps = getInstalledApps();
            if(!installedApps.contains(appIdentifier)){
                return AppManagerResult.NOT_INSTALLED;
            }

            //Get Paths
            LRValue<Path, AppManagerResult> result = getDataPath(appIdentifier);
            if(result.isRValue())
                return result.getRValue();

            Path dataPath = result.getLValue();
            Path appPath = Paths.get(appDirectory.toString(), appIdentifier);

            //Remove entries
            executeSql(String.format(STMT_REMOVE_APP, appIdentifier));
            executeSql(String.format(STMT_REMOVE_APP_ICONS, appIdentifier));
            executeSql(String.format(STMT_REMOVE_SPLASH_IMAGES, appIdentifier));
            executeSql(String.format(STMT_REMOVE_READONLY_FILES, appIdentifier));

            //Remove directories
            deleteDirectory(dataPath);
            deleteDirectory(appPath);
        }catch(Exception e){
            e.printStackTrace();
            return AppManagerResult.UNKNOWN_ERROR;
        }

        return AppManagerResult.OK;
    }
    public synchronized AppManagerResult installApp(ApplicationPackageFile apf, boolean force){
        try {
            List<String> installedApps = getInstalledApps();
            if(installedApps.contains(apf.getAppIdentifier())){
                LRValue<Short[], AppManagerResult> result = getAppVersion(apf.getAppIdentifier());

                if(result.isRValue())
                    return result.getRValue();

                short majorVersion = result.getLValue()[0];
                short minorVersion = result.getLValue()[1];

                if(!force && !((apf.getMajorVersion() > majorVersion) || (apf.getMinorVersion() > minorVersion))){
                    return AppManagerResult.NEWER_VERSION_INSTALLED;
                }

                AppManagerResult amr = uninstallApp(apf.getAppIdentifier());
                if(amr != AppManagerResult.OK)
                    return amr;
            }

            //Define directories
            Path appDir = Paths.get(appDirectory.toString(), apf.getAppIdentifier());
            Path binDir = Paths.get(appDir.toString(), "bin");
            Path iconDir = Paths.get(appDir.toString(), "icons");
            Path splashDir = Paths.get(appDir.toString(), "splashs");
            Path filesDir = Paths.get(appDir.toString(), "files");
            Path dataDir = Paths.get(dataDirectory.toString(), apf.getAppIdentifier());

            //Create directories
            createDirectory(appDir);
            createDirectory(binDir);
            createDirectory(iconDir);
            createDirectory(splashDir);
            createDirectory(filesDir);
            createDirectory(dataDir);

            //Define files
            Path appJarFile = Paths.get(binDir.toString(), "app.jar");
            List<Path> iconPaths = new ArrayList<>();
            List<Path> splashPaths = new ArrayList<>();
            List<Path> filePaths = new ArrayList<>();

            for (int i = 0; i < apf.getIcons().length; i++) {
                AppIcon icon = apf.getIcons()[i];
                Path path = Paths.get(iconDir.toString(), "icon_" + icon.getSize() + ".png");
                iconPaths.add(path);
            }

            for (int i = 0; i < apf.getSplashImages().length; i++) {
                SplashImage image = apf.getSplashImages()[i];
                Path path = Paths.get(splashDir.toString(), "splash_" + image.getImageWidth() + "_" + image.getImageHeight() + ".png");
                splashPaths.add(path);
            }

            for (int i = 0; i < apf.getFiles().length; i++) {
                de.awesome.smarthome.apf.File file = apf.getFiles()[i];
                Path path = Paths.get(filesDir.toString(), file.getSubDirs());
                createDirectory(path);
                path = Paths.get(path.toString(), file.getFilename());
                filePaths.add(path);
            }

            //Define Hashs
            String appJarHash = Utils.generateMd5(apf.getBinary());
            List<String> iconHashs = new ArrayList<>();
            List<String> splashHashs = new ArrayList<>();
            List<String> fileHashs = new ArrayList<>();

            for (int i = 0; i < apf.getIcons().length; i++) {
                String hash = Utils.generateMd5(apf.getIcons()[i].getData());
                iconHashs.add(hash);
            }
            for (int i = 0; i < apf.getSplashImages().length; i++) {
                String hash = Utils.generateMd5(apf.getSplashImages()[i].getData());
                splashHashs.add(hash);
            }
            for (int i = 0; i < apf.getFiles().length; i++) {
                String hash = Utils.generateMd5(apf.getFiles()[i].getData());
                fileHashs.add(hash);
            }

            //Copy files
            writeFile(appJarFile, apf.getBinary());

            for (int i = 0; i < apf.getIcons().length; i++) {
                writeFile(iconPaths.get(i), apf.getIcons()[i].getData());
            }
            for (int i = 0; i < apf.getSplashImages().length; i++) {
                writeFile(splashPaths.get(i), apf.getSplashImages()[i].getData());
            }
            for (int i = 0; i < apf.getFiles().length; i++) {
                writeFile(filePaths.get(i), apf.getFiles()[i].getData());
            }

            String sql = String.format(STMT_ADD_APP,
                    apf.getAppIdentifier(), apf.getAppName(),
                    0xFF & apf.getMajorVersion(), 0xFF & apf.getMinorVersion(),
                    appJarFile.toString(), appJarHash, dataDir.toString(), filesDir.toString());
            executeSql(sql);

            for (int i = 0; i < apf.getIcons().length; i++) {
                sql = String.format(STMT_ADD_APP_ICON,
                        apf.getAppIdentifier(), 0xFF & apf.getIcons()[i].getSize(),
                        iconPaths.get(i), iconHashs.get(i));
                executeSql(sql);
            }
            for (int i = 0; i < apf.getSplashImages().length; i++) {
                sql = String.format(STMT_ADD_SPLASH_IMAGE,
                        apf.getAppIdentifier(),
                        0xFF & apf.getSplashImages()[i].getImageWidth(), 0xFF & apf.getSplashImages()[i].getImageHeight(),
                        splashPaths.get(i), splashHashs.get(i));
                executeSql(sql);
            }
            for (int i = 0; i < apf.getFiles().length; i++) {
                sql = String.format(STMT_ADD_READONLY_FILE,
                        apf.getAppIdentifier(),
                        filePaths.get(i), fileHashs.get(i));
                executeSql(sql);
            }
        }catch(Exception e){
            e.printStackTrace();
            return AppManagerResult.UNKNOWN_ERROR;
        }

        return AppManagerResult.OK;
    }

    private synchronized void executeSql(String sql){
        try{
            Statement stmt = dbConnection.createStatement();
            stmt.execute(sql);
            stmt.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    private synchronized void writeFile(Path path, byte[] data){
        try {
            Files.write(path, data, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    private synchronized void createDirectory(Path path){
        try{
            Files.createDirectories(path);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    private synchronized void deleteDirectory(Path path){
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }

            });
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void dispose() {
        try {
            dbConnection.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
