package de.silveryard.basesystem.sdk.kernel.bluetooth.audio;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;

/**
 * Created by silveryard on 11.05.17.
 */
public abstract class BtAudioDriver {
    /**
     * Returns all currently avaliable audio devices
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     * @param outDeviceIDs List of AudioDevice IDs
     */
    public static void systemCallDriverBtAudioBluetoothAudioDriverGetDevices(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<Integer[]> outDeviceIDs
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodriver.getdevices", new ArrayList<>(0));
        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        int count = response.getParameters().get(2).getInt();
        outDeviceIDs.value = new Integer[count];

        for(int i = 0; i < count; i++){
            outDeviceIDs.value[i] = response.getParameters().get(3 + i).getInt();
        }
    }
}
