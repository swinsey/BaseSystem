package de.silveryard.basesystem.driver.bluetoothaudio.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.driver.DriverManager;
import de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.List;

/**
 * Created by silveryard on 07.05.17.
 */
abstract class BluetoothAudioDriver {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodriver.getdevices", BluetoothAudioDriver::systemCallDriverBtAudioBluetoothAudioDriverGetDevices);
    }

    private static QAMessage systemCallDriverBtAudioBluetoothAudioDriverGetDevices(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDriver driver = DriverManager.getInstance().getDriver(de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDriver.class);
        List<BluetoothAudioDevice> devices = driver.getDevices();
        Parameter[] parameters = new Parameter[devices.size() + 1];
        parameters[0] = Parameter.createInt(devices.size());

        for(int i = 0; i < devices.size(); i++){
            parameters[1 + i] = Parameter.createInt(devices.get(i).getId());
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtAudioReturnCode.OK.getValue(), parameters);
    }
}
