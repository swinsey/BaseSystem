package de.silveryard.basesystem.sdk.kernel.bluetooth.phone;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;

/**
 * Created by silveryard on 29.05.17.
 */
public abstract class BtPhoneManager {
    /**
     * Fetches all bluetooth phone devices from the system
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outDeviceIds List of phone device ids
     */
    public static void systemCallDriverBtPhoneManagerGetDevices(
        Wrapper<ReturnCode> outReturnCode,
        Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
        Wrapper<Integer[]> outDeviceIds
    ){

        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.manager.getdevices", new ArrayList<>(0));
        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        int numIds = response.getParameters().get(2).getInt();
        outDeviceIds.value = new Integer[numIds];
        for(int i = 0; i < numIds; i++){
            outDeviceIds.value[i] = response.getParameters().get(3 + i).getInt();
        }
    }

    private BtPhoneManager(){}
}
