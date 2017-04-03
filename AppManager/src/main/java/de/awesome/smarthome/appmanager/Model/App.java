package de.awesome.smarthome.appmanager.Model;

/**
 * Created by Sebif on 18.03.2017.
 */
public class App {
    private String appIdentifier;
    private String appName;
    private String version;

    public App(String appIdentifier, String appName, String version){
        this.appIdentifier = appIdentifier;
        this.appName = appName;
        this.version = version;
    }

    public String getAppIdentifier(){
        return appIdentifier;
    }
    public String getAppName(){
        return appName;
    }
    public String getVersion(){
        return version;
    }
}
