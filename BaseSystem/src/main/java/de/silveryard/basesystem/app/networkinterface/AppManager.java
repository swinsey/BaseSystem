package de.silveryard.basesystem.app.networkinterface;

import de.silveryard.basesystem.app.AppManagerResult;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.networkinterface.NetworkInterface;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.LRValue;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sebif on 16.03.2017.
 */
public abstract class AppManager {
    public static void enableInterface(){
        NetworkInterface.registerQaCommand("de.awesome.smarthome.td.networkinterface.appmanager.getInstalledApps", AppManager::handleGetInstalledApps);
        NetworkInterface.registerQaCommand("de.awesome.smarthome.td.networkinterface.appmanager.uninstallApp", AppManager::handleUninstallApp);
        NetworkInterface.registerQaCommand("de.awesome.smarthome.td.networkinterface.appmanager.installApp", AppManager::handleInstallApp);
        NetworkInterface.registerQaCommand("de.awesome.smarthome.td.networkinterface.appmanager.getIcon", AppManager::handleGetIcon);
        NetworkInterface.registerQaCommand("de.awesome.smarthome.td.networkinterface.appmanager.getVersion", AppManager::handleGetVersion);
    }

    private static QAMessage handleGetInstalledApps(QAMessage message, byte[] data){
        final Wrapper<List<String>> result = new Wrapper<>();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                result.value = de.silveryard.basesystem.app.AppManager.getInstance().getInstalledApps();;
            }
        });

        Utils.waitForWrapper(result);

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(result.value.size()));
        for(String appIdentifier : result.value){
            String appName = de.silveryard.basesystem.app.AppManager.getInstance().getAppName(appIdentifier).getLValue();
            params.add(Parameter.createString(appIdentifier));
            params.add(Parameter.createString(appName));
        }

        return new QAMessage(message, params);
    }
    private static QAMessage handleUninstallApp(QAMessage message, byte[] data){
        final Wrapper<AppManagerResult> result = new Wrapper<>();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                String appIdentifier = message.getParameters().get(0).getString();
                result.value = de.silveryard.basesystem.app.AppManager.getInstance().uninstallApp(appIdentifier);
            }
        });

        Utils.waitForWrapper(result);

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(result.value.getValue()));
        return new QAMessage(message, params);
    }
    private static QAMessage handleInstallApp(QAMessage message, byte[] data){
        final Wrapper<AppManagerResult> result = new Wrapper<>();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                boolean force = message.getParameters().get(0).getBoolean();
                result.value = de.silveryard.basesystem.app.AppManager.getInstance().installApp(data, force);
            }
        });

        Utils.waitForWrapper(result);

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(result.value.getValue()));
        return new QAMessage(message, params);
    }
    private static QAMessage handleGetIcon(QAMessage message, byte[] data){
        String appIdentifier = message.getParameters().get(0).getString();
        int desiredSize = message.getParameters().get(1).getInt();
        LRValue<Path, AppManagerResult> result = de.silveryard.basesystem.app.AppManager.getInstance().getAppIcon(appIdentifier, desiredSize);

        AppManagerResult amr = result.isRValue() ? result.getRValue() : AppManagerResult.OK;
        byte[] imageData = null;

        List<Parameter> params = new ArrayList<>();
        params.add(null);
        if(amr == AppManagerResult.OK){
            try {
                Path path = result.getLValue();
                imageData = Files.readAllBytes(path);

                int required = imageData.length / Parameter.PARAM_DATA_MAX_LENGTH;
                int rest = imageData.length % Parameter.PARAM_DATA_MAX_LENGTH;
                for(int i = 0; i < required; i++){
                    byte[] cur = Arrays.copyOfRange(imageData, i * Parameter.PARAM_DATA_MAX_LENGTH, (i + 1) * Parameter.PARAM_DATA_MAX_LENGTH);
                    params.add(Parameter.createByteArray(cur));
                }
                if(rest != 0){
                    byte[] cur = Arrays.copyOfRange(imageData, imageData.length - rest, imageData.length);
                    params.add(Parameter.createByteArray(cur));
                }
            } catch (IOException e) {
                e.printStackTrace();
                amr = AppManagerResult.UNKNOWN_ERROR;
            }
        }

        params.set(0, Parameter.createInt(amr.getValue()));

        return new QAMessage(message, params);
    }
    private static QAMessage handleGetVersion(QAMessage message, byte[] data){
        String appIdentifier = message.getParameters().get(0).getString();
        LRValue<Short[], AppManagerResult> result = de.silveryard.basesystem.app.AppManager.getInstance().getAppVersion(appIdentifier);

        AppManagerResult amr = result.isRValue() ? result.getRValue() : AppManagerResult.OK;
        int majorVersion = result.isRValue() ? -1 : result.getLValue()[0];
        int minorVersion = result.isRValue() ? -1 : result.getLValue()[1];

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(amr.getValue()));
        params.add(Parameter.createInt(majorVersion));
        params.add(Parameter.createInt(minorVersion));

        return new QAMessage(message, params);
    }
}
