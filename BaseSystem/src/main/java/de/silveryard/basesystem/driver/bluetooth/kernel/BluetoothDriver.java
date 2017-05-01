package de.silveryard.basesystem.driver.bluetooth.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.driver.DriverManager;
import de.silveryard.basesystem.driver.bluetooth.BluetoothDevice;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.List;

/**
 * Created by silveryard on 01.05.17.
 */
abstract class BluetoothDriver {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.bluetoothdriver.getdevices", BluetoothDriver::systemCallDriverBTBluetoothDriverGetDevices);
    }

    private static QAMessage systemCallDriverBTBluetoothDriverGetDevices(RunningApp app, QAMessage message){
        List<BluetoothDevice> devices = DriverManager.getInstance().getDriver(de.silveryard.basesystem.driver.bluetooth.BluetoothDriver.class).getDevices();
        Parameter[] params = new Parameter[devices.size() + 1];
        params[0] = Parameter.createInt(devices.size());

        for(int i = 0; i < devices.size(); i++){
            params[i + 1] = Parameter.createInt(devices.get(i).getId());
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(), params);
    }
}
