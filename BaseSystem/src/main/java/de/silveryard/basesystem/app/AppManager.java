package de.silveryard.basesystem.app;

import de.silveryard.apf.ApplicationPackageFile;
import de.silveryard.apf.Parser;
import de.silveryard.basesystem.util.ActionP1;
import de.silveryard.basesystem.util.IDisposable;
import de.silveryard.basesystem.util.LRValue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 01.03.2017.
 */
public class AppManager implements IDisposable {
    private static AppManager instance;
    /**
     * Initializes the application manager
     * @param appDirectory Directory where apps are installed
     * @param dataDirectory Directory where apps can store data
     */
    public static void initialize(Path appDirectory, Path dataDirectory){
        if(instance == null){
            instance = new AppManager(appDirectory, dataDirectory);
        }
    }
    /**
     * Singleton getter
     * @return
     */
    public static AppManager getInstance(){
        return instance;
    }

    private AppDB appDB;
    private List<RunningApp> runningApps;

    private List<ActionP1<String>> onAppInstalledHandlers;
    private List<ActionP1<String>> onAppUninstalledHandlers;
    private List<ActionP1<String>> onAppStartedHandlers;
    private List<ActionP1<String>> onAppStoppedHandlers;

    private AppManager(Path appDirectory, Path dataDirectory){
        if(!Files.exists(appDirectory))
            throw new RuntimeException("AppDirectory does not exist: " + appDirectory.toString());
        if(!Files.isDirectory(appDirectory))
            throw new RuntimeException("AppDirectory is no directory: " + appDirectory.toString());
        if(!Files.exists(dataDirectory))
            throw new RuntimeException("DataDirectory does not exist: " + dataDirectory.toString());
        if(!Files.isDirectory(dataDirectory))
            throw new RuntimeException("DataDirectory is no directory: " + dataDirectory.toString());

        appDB = new AppDB(Paths.get(appDirectory.toString(), "appdb.sqlite"), appDirectory, dataDirectory);
        runningApps = new ArrayList<>();

        onAppInstalledHandlers = new ArrayList<>();
        onAppUninstalledHandlers = new ArrayList<>();
        onAppStartedHandlers = new ArrayList<>();
        onAppStoppedHandlers = new ArrayList<>();
    }

    /**
     * @return Returns the appidentifiers of all installed apps on this system
     */
    public synchronized List<String> getInstalledApps(){
        return appDB.getInstalledApps();
    }

    /**
     * Registers a handler that is called when an app is installed successfully
     * @param handler Handler
     */
    public synchronized void registerOnAppInstalledHandler(ActionP1<String> handler){
        onAppInstalledHandlers.add(handler);
    }
    /**
     * Registers a handler that is called when an app is uninstalled successfully
     * @param handler Handler
     */
    public synchronized void registerOnAppUninstalledHandler(ActionP1<String> handler){
        onAppUninstalledHandlers.add(handler);
    }
    /**
     * Registers a handler that is called when an app is started successfully
     * @param handler Handler
     */
    public synchronized void registerOnAppStartedHandler(ActionP1<String> handler){
        onAppStartedHandlers.add(handler);
    }
    /**
     * Registers a handler that is called when an app stops running
     * @param handler Handler
     */
    public synchronized void registerOnAppStoppedHandler(ActionP1<String> handler){
        onAppStoppedHandlers.add(handler);
    }

    private void onAppUninstalled(String appIdentifier){
        for(ActionP1<String> handler : onAppUninstalledHandlers){
            handler.invoke(appIdentifier);
        }
    }
    private void onAppInstalled(String appIdentifier){
        for(ActionP1<String> handler : onAppInstalledHandlers){
            handler.invoke(appIdentifier);
        }
    }
    private void onAppStarted(String appIdentifier){
        for(ActionP1<String> handler : onAppStartedHandlers){
            handler.invoke(appIdentifier);
        }
    }
    private void onAppStopped(String appIdentifier){
        for(ActionP1<String> handler : onAppStoppedHandlers){
            handler.invoke(appIdentifier);
        }
    }

    /**
     * Returns the name of a given app
     * @param appIdentifier Application identifier
     * @return The apps name on success. A value of AppManagerResult otherwise
     */
    public synchronized LRValue<String, AppManagerResult> getAppName(String appIdentifier){
        return appDB.getAppName(appIdentifier);
    }
    /**
     * Returns the path to an icon for a given app with a given desired size
     * @param appIdentifier Application identifier
     * @param desiredSize Desired size to get an icon for. The path returned will match the icon that is closest to the desired size
     * @return The icons path on success. A value of AppManagerResult otherwise
     */
    public synchronized LRValue<Path, AppManagerResult> getAppIcon(String appIdentifier, int desiredSize){
        return appDB.getAppIcon(appIdentifier, desiredSize);
    }
    /**
     * Returns the path to a splash image for a given app with a given desired width and desired height
     * @param appIdentifier Application identifier
     * @param desiredWidth Desired width to get a splash image for. The path returned will match the image that is closed to the desired size
     * @param desiredHeight Desired height to get a splash image for. The path returned will match the image that is closed to the desired size
     * @return The images path on success. A value of AppManagerResult otherwise
     */
    public synchronized LRValue<Path, AppManagerResult> getSplashImage(String appIdentifier, int desiredWidth, int desiredHeight){
        return appDB.getAppSplashImage(appIdentifier, desiredWidth, desiredHeight);
    }
    /**
     * Returns the version of a given app
     * @param appIdentifier Application identifier
     * @return 2 value array on success. [0] = MajorVersion [1} = MinorVersion. A value of AppManagerResult otherwise
     */
    public synchronized LRValue<Short[], AppManagerResult> getAppVersion(String appIdentifier){
        return appDB.getAppVersion(appIdentifier);
    }

    /**
     * Uninstalls a given app
     * @param appIdentifier Application identifier
     * @return AppManagerResult that indicates the result of the operation
     */
    public synchronized AppManagerResult uninstallApp(String appIdentifier){
        if(isAppRunning(appIdentifier)){
            AppManagerResult stopApp = stopApp(appIdentifier);
            if(stopApp != AppManagerResult.OK){
                return stopApp;
            }
        }

        AppManagerResult result = appDB.uninstallApp(appIdentifier);
        if(result == AppManagerResult.OK) {
            onAppUninstalled(appIdentifier);
        }
        return result;
    }
    /**
     * Installs an app from a file from the filesystem.
     * Force flag is not set
     * @param path Path to an existing ApplicationPackageFile (.apf)
     * @return AppManagerResult that indicates the result of the operation
     */
    public synchronized AppManagerResult installApp(Path path){
        return installApp(path, false);
    }
    /**
     * Installs an app from a file from the filesystem
     * @param path Path to an existing ApplicationPackageFile (.apf)
     * @param force If set, an application will be installed even when an equal or never version of the app is already installed
     * @return AppManagerResult that indicates the result of the operation
     */
    public synchronized AppManagerResult installApp(Path path, boolean force){
        try {
            byte[] data = Files.readAllBytes(path);
            return installApp(data, force);
        }catch(Exception e){
            e.printStackTrace();
            return AppManagerResult.UNKNOWN_ERROR;
        }
    }
    /**
     * Installs an app from a byte array.
     * Force flag is not set
     * @param data Byte array containing the content of an ApplicationPackageFile (.apf)
     * @return AppManagerResult that indicates the result of the operation
     */
    public synchronized AppManagerResult installApp(byte[] data){
        return installApp(data, false);
    }
    /**
     * Installs an app from a byte array
     * @param data Byte array containing the content of an ApplicationPackageFile (.apf)
     * @param force If set, an application will be installed even when an equal or never version of the app is already installed
     * @return AppManagerResult that indicates the result of the operation
     */
    public synchronized AppManagerResult installApp(byte[] data, boolean force){
        try {
            Parser parser = new Parser(data);
            ApplicationPackageFile apf = parser.parse();
            AppManagerResult result = appDB.installApp(apf, force);
            if(result == AppManagerResult.OK) {
                onAppInstalled(apf.getAppIdentifier());
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return AppManagerResult.UNKNOWN_ERROR;
        }
    }

    /**
     * Starts a given app
     * @param appIdentifier Application identifier
     * @return RunningApp instance on success. A value of AppManagerResult otherwise
     */
    public synchronized LRValue<RunningApp, AppManagerResult> startApp(String appIdentifier){
        if(!getInstalledApps().contains(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.NOT_INSTALLED);
        }
        if(isAppRunning(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.ALREADY_RUNNING);
        }

        LRValue<Path, AppManagerResult> binaryPathResult = appDB.getAppBinary(appIdentifier);
        if(binaryPathResult.isRValue()){
            return LRValue.createRValue(binaryPathResult.getRValue());
        }
        Path binaryPath = binaryPathResult.getLValue();

        LRValue<Path, AppManagerResult> dataPathResult = appDB.getDataPath(appIdentifier);
        if(dataPathResult.isRValue()){
            return LRValue.createRValue(dataPathResult.getRValue());
        }
        Path dataPath = dataPathResult.getLValue();

        LRValue<Path, AppManagerResult> readonlyPathResult = appDB.getReadonlyPath(appIdentifier);
        if(readonlyPathResult.isRValue()){
            return LRValue.createRValue(dataPathResult.getRValue());
        }
        Path readonlyPath = readonlyPathResult.getLValue();

        LRValue<String, AppManagerResult> appNameResult = appDB.getAppName(appIdentifier);
        if(appNameResult.isRValue()){
            return LRValue.createRValue(appNameResult.getRValue());
        }
        String appName = appNameResult.getLValue();

        AppLoader appLoader = AppLoader.create(appName, binaryPath, dataPath, readonlyPath);
        RunningApp runningApp = new RunningApp(appIdentifier, appLoader);
        runningApps.add(runningApp);
        onAppStarted(appIdentifier);
        return LRValue.createLValue(runningApp);
    }
    /**
     * Stops a given app
     * @param appIdentifier Application identifier
     * @return AppManagerResult that indicates the result of the operation
     */
    public synchronized AppManagerResult stopApp(String appIdentifier){
        if(!getInstalledApps().contains(appIdentifier)){
            return AppManagerResult.NOT_INSTALLED;
        }
        if(!isAppRunning(appIdentifier)){
            return AppManagerResult.NOT_RUNNING;
        }

        LRValue<RunningApp, AppManagerResult> runningAppResult = getRunningApp(appIdentifier);
        RunningApp app = null;
        if(runningAppResult.isRValue()){
            return runningAppResult.getRValue();
        }else{
            app = runningAppResult.getLValue();
        }

        app.dispose();
        try{
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
            return AppManagerResult.UNKNOWN_ERROR;
        }
        onAppStopped(appIdentifier);
        return AppManagerResult.OK;
    }

    /**
     * Returns if a given application is currently running
     * @param appIdentifier Application identifier
     * @return True if the app is currently running. False otherwise
     */
    public synchronized boolean isAppRunning(String appIdentifier){
        for(int i = 0; i < runningApps.size(); i++){
            if(runningApps.get(i).getAppIdentifier().equals(appIdentifier)){
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a RunningApp of a given currently running app
     * @param appIdentifier Application identifier
     * @return An instance of RunningApp on success. A value of AppManagerResult otherwise
     */
    public synchronized LRValue<RunningApp, AppManagerResult> getRunningApp(String appIdentifier){
        if(!getInstalledApps().contains(appIdentifier)){
            return LRValue.createRValue(AppManagerResult.NOT_INSTALLED);
        }

        for(int i = 0; i < runningApps.size(); i++){
            if(runningApps.get(i).getAppIdentifier().equals(appIdentifier)){
                return LRValue.createLValue(runningApps.get(i));
            }
        }

        return LRValue.createRValue(AppManagerResult.NOT_RUNNING);
    }

    @Override
    public synchronized void dispose() {
        if(appDB != null){
            appDB.dispose();
        }
    }
}
