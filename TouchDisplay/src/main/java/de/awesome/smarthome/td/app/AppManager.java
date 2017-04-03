package de.awesome.smarthome.td.app;

import de.awesome.smarthome.apf.ApplicationPackageFile;
import de.awesome.smarthome.apf.Parser;
import de.awesome.smarthome.td.util.ActionP1;
import de.awesome.smarthome.td.util.IDisposable;
import de.awesome.smarthome.td.util.LRValue;

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
    public static void initialize(Path appDirectory, Path dataDirectory){
        if(instance == null){
            instance = new AppManager(appDirectory, dataDirectory);
        }
    }
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

    public synchronized List<String> getInstalledApps(){
        return appDB.getInstalledApps();
    }

    public synchronized void registerOnAppInstalledHandler(ActionP1<String> handler){
        onAppInstalledHandlers.add(handler);
    }
    public synchronized void registerOnAppUninstalledHandler(ActionP1<String> handler){
        onAppUninstalledHandlers.add(handler);
    }
    public synchronized void registerOnAppStartedHandler(ActionP1<String> handler){
        onAppStartedHandlers.add(handler);
    }
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

    public synchronized LRValue<String, AppManagerResult> getAppName(String appIdentifier){
        return appDB.getAppName(appIdentifier);
    }
    public synchronized LRValue<Path, AppManagerResult> getAppIcon(String appIdentifier, int desiredSize){
        return appDB.getAppIcon(appIdentifier, desiredSize);
    }
    public synchronized LRValue<Path, AppManagerResult> getSplashImage(String appIdentifier, int desiredWidth, int desiredHeight){
        return appDB.getAppSplashImage(appIdentifier, desiredWidth, desiredHeight);
    }
    public synchronized LRValue<Short[], AppManagerResult> getAppVersion(String appIdentifier){
        return appDB.getAppVersion(appIdentifier);
    }

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
    public synchronized AppManagerResult installApp(Path path){
        return installApp(path, false);
    }
    public synchronized AppManagerResult installApp(Path path, boolean force){
        try {
            byte[] data = Files.readAllBytes(path);
            return installApp(data, force);
        }catch(Exception e){
            e.printStackTrace();
            return AppManagerResult.UNKNOWN_ERROR;
        }
    }
    public synchronized AppManagerResult installApp(byte[] data){
        return installApp(data, false);
    }
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

        AppLoader appLoader = AppLoader.create(binaryPath, dataPath, readonlyPath);
        RunningApp runningApp = new RunningApp(appIdentifier, appLoader);
        runningApps.add(runningApp);
        onAppStarted(appIdentifier);
        return LRValue.createLValue(runningApp);
    }
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

    public synchronized boolean isAppRunning(String appIdentifier){
        for(int i = 0; i < runningApps.size(); i++){
            if(runningApps.get(i).getAppIdentifier().equals(appIdentifier)){
                return true;
            }
        }
        return false;
    }
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
