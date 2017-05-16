package de.silveryard.basesystem.app.kernel;

import de.silveryard.basesystem.app.*;
import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 15.05.17.
 */
abstract class AppManager {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.app.appmanager.getinstalledapps", AppManager::systemCallAppAppManagerGetInstalledApps);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.app.appmanager.getrunningapps", AppManager::systemCallAppAppManagerGetRunningApps);
    }

    private static QAMessage systemCallAppAppManagerGetInstalledApps(de.silveryard.basesystem.app.RunningApp app, QAMessage message){
        List<String> installedApps = de.silveryard.basesystem.app.AppManager.getInstance().getInstalledApps();

        Parameter[] params = new Parameter[installedApps.size() + 1];
        params[0] = Parameter.createInt(installedApps.size());
        for(int i = 0; i < installedApps.size(); i++){
            params[1 + i] = Parameter.createString(installedApps.get(i));
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), AppManagerResult.OK.getValue(), params);
    }
    private static QAMessage systemCallAppAppManagerGetRunningApps(de.silveryard.basesystem.app.RunningApp app, QAMessage message){
        List<RunningApp> runningApps = de.silveryard.basesystem.app.AppManager.getInstance().getRunningApps();

        Parameter[] params = new Parameter[runningApps.size() + 1];
        params[0] = Parameter.createInt(runningApps.size());
        for(int i = 0; i < runningApps.size(); i++){
            params[1 + i] = Parameter.createString(runningApps.get(i).getAppIdentifier());
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), AppManagerResult.OK.getValue(), params);
    }


    private AppManager(){

    }
}
