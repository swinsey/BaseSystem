package de.silveryard.basesystem.app.kernel;

import de.silveryard.basesystem.app.*;
import de.silveryard.basesystem.app.AppManager;
import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.util.LRValue;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by silveryard on 15.05.17.
 */
abstract class InstalledApp {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.app.installedapp.getappname", InstalledApp::systemCallAppInstalledAppGetAppName);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.app.installedapp.getappversion", InstalledApp::systemCallAppInstalledAppGetAppVersion);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.app.installedapp.startapp", InstalledApp::systemCallAppInstalledAppStartApp);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.app.installedapp.stopapp", InstalledApp::systemCallAppInstalledAppStopApp);
    }

    private static QAMessage systemCallAppInstalledAppGetAppName(RunningApp app, QAMessage message){
        String appIdentifier = message.getParameters().get(0).getString();

        LRValue<String, AppManagerResult> result = AppManager.getInstance().getAppName(appIdentifier);

        if(result.isRValue()){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), result.getRValue().getValue(), Parameter.createString(""));
        }else{
            return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), AppManagerResult.OK.getValue(), Parameter.createString(result.getLValue()));
        }
    }
    private static QAMessage systemCallAppInstalledAppGetAppVersion(RunningApp app, QAMessage message){
        String appIdentifier = message.getParameters().get(0).getString();

        LRValue<Short[], AppManagerResult> result = AppManager.getInstance().getAppVersion(appIdentifier);

        if(result.isRValue()){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    result.getRValue().getValue(),
                    Parameter.createInt(0),
                    Parameter.createInt(0));
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    AppManagerResult.OK.getValue(),
                    Parameter.createInt(result.getLValue()[0]),
                    Parameter.createInt(result.getLValue()[1])
            );
        }
    }

    private static QAMessage systemCallAppInstalledAppStartApp(RunningApp app, QAMessage message){
        String appIdentifier = message.getParameters().get(0).getString();

        LRValue<RunningApp, AppManagerResult> result = AppManager.getInstance().startApp(appIdentifier);

        if(result.isRValue()){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    result.getRValue().getValue(),
                    Parameter.createInt(0)
            );
        } else {
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    AppManagerResult.OK.getValue(),
                    Parameter.createInt(result.getLValue().getProcessId())
            );
        }
    }
    private static QAMessage systemCallAppInstalledAppStopApp(RunningApp app, QAMessage message){
        String appIdentifier = message.getParameters().get(0).getString();

        AppManagerResult result = AppManager.getInstance().stopApp(appIdentifier);

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                result.getValue()
        );
    }

    private InstalledApp(){

    }
}
