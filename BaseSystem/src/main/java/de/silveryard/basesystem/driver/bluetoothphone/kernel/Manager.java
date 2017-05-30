package de.silveryard.basesystem.driver.bluetoothphone.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.driver.DriverManager;
import de.silveryard.basesystem.driver.bluetoothphone.BluetoothPhoneDevice;
import de.silveryard.basesystem.driver.bluetoothphone.BluetoothPhoneDriver;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.List;

/**
 * Created by silveryard on 29.05.17.
 */
abstract class Manager {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.manager.getdevices", Manager::systemCallDriverBtPhoneManagerGetDevices);
    }

    private static QAMessage systemCallDriverBtPhoneManagerGetDevices(RunningApp app, QAMessage message){
        List<BluetoothPhoneDevice> devices = DriverManager.getInstance().getDriver(BluetoothPhoneDriver.class).getDevices();

        Parameter[] params = new Parameter[devices.size() + 1];
        params[0] = Parameter.createInt(devices.size());
        for(int i = 0; i < devices.size(); i++){
            params[1 + i] = Parameter.createInt(devices.get(i).getId());
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtPhoneReturnCode.OK.getValue(), params);
    }
}
