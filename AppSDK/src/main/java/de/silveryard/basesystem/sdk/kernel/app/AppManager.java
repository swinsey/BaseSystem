package de.silveryard.basesystem.sdk.kernel.app;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;

/**
 * Created by silveryard on 16.05.17.
 */
public abstract class AppManager {
    /**
     * Returns the app identifiers of all installed apps
     * @param outReturnCode General Return Code
     * @param outAppManagerResult App BtPhoneManager Result
     * @param outInstalledApps List of installed apps
     */
    public static void systemCallAppAppManagerGetInstalledApps(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<AppManagerResult> outAppManagerResult,
            Wrapper<String[]> outInstalledApps
    ){

        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.app.appmanager.getinstalledapps", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outAppManagerResult.value = AppManagerResult.getEnumValue(message.getParameters().get(1).getInt());

        int count = message.getParameters().get(2).getInt();
        outInstalledApps.value = new String[count];
        for(int i = 0; i < count; i++){
            outInstalledApps.value[i] = message.getParameters().get(3 + i).getString();
        }
    }
    /**
     * Returns the app identifiers of all running apps
     * @param outReturnCode General Return Code
     * @param outAppManagerResult App BtPhoneManager Result
     * @param outRunningApps List of running apps
     */
    public static void systemCallAppAppManagerGetRunningApps(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<AppManagerResult> outAppManagerResult,
            Wrapper<String[]> outRunningApps
    ){

        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.app.appmanager.getrunningapps", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outAppManagerResult.value = AppManagerResult.getEnumValue(message.getParameters().get(1).getInt());

        int count = message.getParameters().get(3).getInt();
        outRunningApps.value = new String[count];
        for(int i = 0; i < count; i++){
            outRunningApps.value[i] = message.getParameters().get(3 + i).getString();
        }
    }

    private AppManager(){
    }
}
