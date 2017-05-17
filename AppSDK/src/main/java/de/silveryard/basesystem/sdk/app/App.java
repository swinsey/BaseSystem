package de.silveryard.basesystem.sdk.app;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.app.AppManagerResult;

import static de.silveryard.basesystem.sdk.kernel.app.InstalledApp.*;

/**
 * Created by silveryard on 17.05.17.
 */
public final class App {
    private final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private final Wrapper<AppManagerResult> appManagerResultWrapper = new Wrapper<>();
    private final Wrapper<String> stringWrapper = new Wrapper<>();
    private final Wrapper<Short> shortWrapper1 = new Wrapper<>();
    private final Wrapper<Short> shortWrapper2 = new Wrapper<>();

    private String appIdentifier;

    /**
     * Constructor
     * @param appIdentifier Application Identifier
     */
    public App(String appIdentifier){
        this.appIdentifier = appIdentifier;
    }

    /**
     * Returns the applications unique identifier
     * @return Application Identifier
     */
    public String getAppIdentifier(){
        return appIdentifier;
    }

    /**
     * Returns the applications name
     * @return App name
     */
    public synchronized String getAppName(){
        systemCallAppInstalledAppGetAppName(appIdentifier, returnCodeWrapper, appManagerResultWrapper, stringWrapper);

        if(appManagerResultWrapper.value != AppManagerResult.OK){
            throw new AppException(appManagerResultWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }

    /**
     * Returns the apps version
     * @param outMajorVersion App Major Version
     * @param outMinorVersion App Minor Version
     */
    public synchronized void getAppVersion(Wrapper<Short> outMajorVersion, Wrapper<Short> outMinorVersion){
        systemCallAppInstalledAppGetAppVersion(appIdentifier, returnCodeWrapper, appManagerResultWrapper, outMajorVersion, outMinorVersion);

        if(appManagerResultWrapper.value != AppManagerResult.OK){
            throw new AppException(appManagerResultWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Returns the apps major version
     * @return App Major Version
     */
    public synchronized short getAppMajorVersion(){
        systemCallAppInstalledAppGetAppVersion(appIdentifier, returnCodeWrapper, appManagerResultWrapper, shortWrapper1, shortWrapper2);

        if(appManagerResultWrapper.value != AppManagerResult.OK){
            throw new AppException(appManagerResultWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return shortWrapper1.value;
    }
    /**
     * Returns the apps minor version
     * @return App Minor Version
     */
    public synchronized short getAppMinorVersion(){
        systemCallAppInstalledAppGetAppVersion(appIdentifier, returnCodeWrapper, appManagerResultWrapper, shortWrapper1, shortWrapper2);

        if(appManagerResultWrapper.value != AppManagerResult.OK){
            throw new AppException(appManagerResultWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return shortWrapper2.value;
    }

    /**
     * Starts the application
     */
    public synchronized void startApp(){
        systemCallAppInstalledAppStartApp(appIdentifier, returnCodeWrapper, appManagerResultWrapper);

        if(appManagerResultWrapper.value != AppManagerResult.OK){
            throw new AppException(appManagerResultWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Stops the application
     */
    public synchronized void stopApp(){
        systemCallAppInstalledAppStopApp(appIdentifier, returnCodeWrapper, appManagerResultWrapper);

        if(appManagerResultWrapper.value != AppManagerResult.OK){
            throw new AppException(appManagerResultWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
}
