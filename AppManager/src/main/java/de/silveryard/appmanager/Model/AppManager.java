package de.silveryard.appmanager.Model;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.transport.Parameter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 18.03.2017.
 */
public abstract class AppManager {
    public static List<App> refreshAppList(){
        QAMessage response = Connection.sendMessage("de.awesome.smarthome.td.networkinterface.appmanager.getInstalledApps", new ArrayList<>());

        List<App> apps = new ArrayList<>();
        int index = 0;
        int appCount = response.getParameters().get(index++).getInt();
        for(int i = 0; i < appCount; i++){
            String appIdentifier = response.getParameters().get(index++).getString();
            String appName = response.getParameters().get(index++).getString();
            String version = getAppVersion(appIdentifier);
            App app = new App(appIdentifier, appName, version);
            apps.add(app);
        }

        return apps;
    }
    public static int uninstallApp(App app){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createString(app.getAppIdentifier()));
        QAMessage response = Connection.sendMessage("de.awesome.smarthome.td.networkinterface.appmanager.uninstallApp", params);
        return response.getParameters().get(0).getInt();
    }
    public static int installApp(Path path, boolean force){
        byte[] data = null;
        try{
            data = Files.readAllBytes(path);
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createBoolean(force));
        QAMessage response = Connection.sendMessage("de.awesome.smarthome.td.networkinterface.appmanager.installApp", params, data);
        return response.getParameters().get(0).getInt();
    }
    public static byte[] getAppIcon(String appIdentifier, int desiredSize){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createString(appIdentifier));
        params.add(Parameter.createInt(desiredSize));
        QAMessage response = Connection.sendMessage("de.awesome.smarthome.td.networkinterface.appmanager.getIcon", params);
        int result = response.getParameters().get(0).getInt();

        if(result != 1){
            return null;
        }

        int size = 0;
        for(int i = 1; i < response.getParameters().size(); i++){
            size += response.getParameters().get(i).getSize();
        }
        byte[] data = new byte[size];
        int index = 0;
        for(int i = 1; i < response.getParameters().size(); i++){
            Parameter param = response.getParameters().get(i);
            System.arraycopy(param.getData(), 0, data, index, param.getSize());
            index += param.getSize();
        }

        return data;
    }
    public static String getAppVersion(String appIdentifier){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createString(appIdentifier));
        QAMessage response = Connection.sendMessage("de.awesome.smarthome.td.networkinterface.appmanager.getVersion", params);

        int result = response.getParameters().get(0).getInt();

        if(result != 1){
            return "Error Code:  " + result;
        }

        int majorVersion = response.getParameters().get(1).getInt();
        int minorVersion = response.getParameters().get(2).getInt();

        return majorVersion + "." + minorVersion;
    }
}
