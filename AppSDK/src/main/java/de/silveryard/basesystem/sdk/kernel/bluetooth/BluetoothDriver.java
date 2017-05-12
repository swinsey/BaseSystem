package de.silveryard.basesystem.sdk.kernel.bluetooth;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class BluetoothDriver {
    /**
     * Fetches all currently avaliable devices
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outDeviceIds List of device IDs
     */
    public static void systemCallDriverBTBluetoothDriverGetDevices(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Integer[]> outDeviceIds
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.bluetoothdriver.getdevices", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());

        int numDevices = response.getParameters().get(2).getInt();
        outDeviceIds.value = new Integer[numDevices];
        for(int i = 0; i < numDevices; i++){
            outDeviceIds.value[i] = response.getParameters().get(3 + i).getInt();
        }
    }
}
