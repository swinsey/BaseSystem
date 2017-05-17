package de.silveryard.basesystem.sdk.app;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.app.AppManagerResult;

import java.util.ArrayList;
import java.util.List;

import static de.silveryard.basesystem.sdk.kernel.app.AppManager.systemCallAppAppManagerGetInstalledApps;
import static de.silveryard.basesystem.sdk.kernel.app.AppManager.systemCallAppAppManagerGetRunningApps;

/**
 * Created by silveryard on 17.05.17.
 */
public abstract class AppManager {
    private static final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private static final Wrapper<AppManagerResult> appManagerResultWrapper = new Wrapper<>();
    private static final Wrapper<String[]> stringArrayWrapper = new Wrapper<>();

    private static final List<App> installedApps = new ArrayList<>();
    private static final List<App> runningApps = new ArrayList<>();

    private static final List<AppHandler> appInstalledHandler = new ArrayList<>();
    private static final List<AppHandler> appUninstalledHandler = new ArrayList<>();
    private static final List<AppHandler> appStartedHandler = new ArrayList<>();
    private static final List<AppHandler> appStoppedHandler = new ArrayList<>();

    /**
     * Updates the internal list of installed and running apps
     * This will cause events to get fired
     */
    public static synchronized void update(){
        //Handle Installed Apps
        systemCallAppAppManagerGetInstalledApps(returnCodeWrapper, appManagerResultWrapper, stringArrayWrapper);

        if(appManagerResultWrapper.value != AppManagerResult.OK){
            throw new AppException(appManagerResultWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        String[] tmpInstalledApps = stringArrayWrapper.value;

        for(int i = 0; i < tmpInstalledApps.length; i++){
            boolean alreadyInstalled = false;

            for(int j = 0; j < installedApps.size(); j++){
                if(installedApps.get(j).getAppIdentifier().equals(tmpInstalledApps[i])){
                    alreadyInstalled = true;
                    break;
                }
            }

            if(!alreadyInstalled){
                App app = new App(tmpInstalledApps[i]);
                installedApps.add(app);

                for(int j = 0; j < appInstalledHandler.size(); j++){
                    appInstalledHandler.get(j).invoke(app);
                }
            }
        }

        for(int i = 0; i < installedApps.size(); i++){
            boolean stillInstalled = false;

            for(int j = 0; j < tmpInstalledApps.length; j++){
                if(tmpInstalledApps[j].equals(installedApps.get(i).getAppIdentifier())){
                    stillInstalled = true;
                    break;
                }
            }

            if(!stillInstalled){
                App app = installedApps.get(i);
                installedApps.remove(app);

                for(int j = 0; j < appUninstalledHandler.size(); j++){
                    appUninstalledHandler.get(i).invoke(app);
                }
            }
        }

        //Handle Running Apps
        systemCallAppAppManagerGetRunningApps(returnCodeWrapper, appManagerResultWrapper, stringArrayWrapper);

        if(appManagerResultWrapper.value != AppManagerResult.OK){
            throw new AppException(appManagerResultWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        String[] tmpRunningApps = stringArrayWrapper.value;

        for(int i = 0; i < tmpRunningApps.length; i++){
            boolean alreadyRunning = false;

            for(int j = 0; j < runningApps.size(); j++){
                if(runningApps.get(j).getAppIdentifier().equals(tmpInstalledApps[i])){
                    alreadyRunning = true;
                    break;
                }
            }

            if(!alreadyRunning){
                App app = new App(tmpRunningApps[i]);
                runningApps.add(app);

                for(int j = 0; j < appStartedHandler.size(); j++){
                    appStartedHandler.get(j).invoke(app);
                }
            }
        }

        for(int i = 0; i < runningApps.size(); i++){
            boolean stillRunning = false;

            for(int j = 0; j < tmpRunningApps.length; j++){
                if(tmpRunningApps[j].equals(runningApps.get(i).getAppIdentifier())){
                    stillRunning = true;
                    break;
                }
            }

            if(!stillRunning){
                App app = runningApps.get(i);
                runningApps.remove(app);

                for(int j = 0; j < appStoppedHandler.size(); j++){
                    appStoppedHandler.get(i).invoke(app);
                }
            }
        }
    }

    /**
     *
     * @param handler
     */
    public static synchronized void registerAppInstalledHandler(AppHandler handler){
        appInstalledHandler.add(handler);
    }
    /**
     *
     * @param handler
     */
    public static synchronized void unregisterAppInstalledHandler(AppHandler handler){
        appInstalledHandler.remove(handler);
    }

    /**
     *
     * @param handler
     */
    public static synchronized void registerAppUninstalledHandler(AppHandler handler){
        appUninstalledHandler.add(handler);
    }
    /**
     *
     * @param handler
     */
    public static synchronized void unregisterAppUninstalledHandler(AppHandler handler){
        appUninstalledHandler.remove(handler);
    }

    /**
     *
     * @param handler
     */
    public static synchronized void registerStartedHandler(AppHandler handler){
        appStartedHandler.add(handler);
    }
    /**
     *
     * @param handler
     */
    public static synchronized void unregisterStartedHandler(AppHandler handler){
        appStartedHandler.remove(handler);
    }

    /**
     *
     * @param handler
     */
    public static synchronized void registerStoppedHandler(AppHandler handler){
        appStoppedHandler.add(handler);
    }
    /**
     *
     * @param handler
     */
    public static synchronized void unregisterStoppedHandler(AppHandler handler){
        appStoppedHandler.remove(handler);
    }

    private AppManager(){
    }
}
