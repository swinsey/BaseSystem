package de.silveryard.basesystem.sdk.kernel.app;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 16.05.17.
 */
public abstract class InstalledApp {
    /**
     * Returns the app name of an app
     * @param appIdentifier App Identifier of the app to fetch data from
     * @param outReturnCode General Return Code
     * @param outAppManagerResult App BtPhoneManager Result
     * @param outAppName Readable App Name
     */
    public static void systemCallAppInstalledAppGetAppName(
            String appIdentifier,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<AppManagerResult> outAppManagerResult,
            Wrapper<String> outAppName
    ){

        List<Parameter> parameters = new ArrayList<>(1);
        parameters.add(Parameter.createString(appIdentifier));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.app.installedapp.getappname", parameters);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outAppManagerResult.value = AppManagerResult.getEnumValue(response.getParameters().get(1).getInt());
        outAppName.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the version of an app
     * @param appIdentifier App Identifier of the app to fetch data from
     * @param outReturnCode General Return Code
     * @param outAppManagerResult App BtPhoneManager Result
     * @param outMajorVersion Applications major version
     * @param outMinorVersion Applications minor version
     */
    public static void systemCallAppInstalledAppGetAppVersion(
            String appIdentifier,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<AppManagerResult> outAppManagerResult,
            Wrapper<Short> outMajorVersion,
            Wrapper<Short> outMinorVersion
    ){

        List<Parameter> parameters = new ArrayList<>(1);
        parameters.add(Parameter.createString(appIdentifier));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.app.installedapp.getappversion", parameters);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outAppManagerResult.value = AppManagerResult.getEnumValue(response.getParameters().get(1).getInt());
        outMajorVersion.value = (short)(int)response.getParameters().get(2).getInt();
        outMinorVersion.value = (short)(int)response.getParameters().get(3).getInt();
    }

    /**
     * Starts an application
     * @param appIdentifier App Identifier of the app to start
     * @param outReturnCode General Return Code
     * @param outAppManagerResult App BtPhoneManager Result
     */
    public static void systemCallAppInstalledAppStartApp(
            String appIdentifier,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<AppManagerResult> outAppManagerResult
    ){

        List<Parameter> parameters = new ArrayList<>(1);
        parameters.add(Parameter.createString(appIdentifier));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.app.installedapp.startapp", parameters);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outAppManagerResult.value = AppManagerResult.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Stops an application
     * @param appIdentifier App Identifier of the app to stop
     * @param outReturnCode General Return Code
     * @param outAppManagerResult App BtPhoneManager Result
     */
    public static void systemCallAppInstalledAppStopApp(
            String appIdentifier,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<AppManagerResult> outAppManagerResult
    ){

        List<Parameter> parameters = new ArrayList<>(1);
        parameters.add(Parameter.createString(appIdentifier));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.app.installedapp.stopapp", parameters);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outAppManagerResult.value = AppManagerResult.getEnumValue(response.getParameters().get(1).getInt());
    }

    private InstalledApp(){
    }
}
