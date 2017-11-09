package de.silveryard.basesystem.sdk.kernel.bluetooth;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class Adapter {
    /**
     * Returns the MAC address of this adapter
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outAddress MAC address
     */
    public static void systemcallDriverBTAdapterGetAddress(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<String> outAddress
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.getaddress", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outAddress.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the alias name of this adapter
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outAlias Alias name
     */
    public static void systemcallDriverBTAdapterGetAlias(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<String> outAlias
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.getalias", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outAlias.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the modalias name of this adapter
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outModalias Modalias Name
     */
    public static void systemcallDriverBTAdapterGetModalias(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<String> outModalias
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.getmodalias", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outModalias.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the name of this adapter
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outName Name value
     */
    public static void systemcallDriverBTAdapterGetName(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<String> outName
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.getname", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outName.value = response.getParameters().get(2).getString();
    }

    /**
     * Returns the discoverable flag of this adapter
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outIsDiscoverable True if this adapter is set to discoverable. False otherwise
     */
    public static void systemcallDriverBTAdapterIsDiscoverable(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outIsDiscoverable
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.isdiscoverable", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIsDiscoverable.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Returns the discovering flag of this adapter
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outIsDiscovering True if this adapter is currently discovering. False otherwise
     */
    public static void systemcallDriverBTAdapterIsDiscovering(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outIsDiscovering
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.isdiscovering", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIsDiscovering.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Returns the pairable flag of this adapter
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outIsPairable True if this adapter is currently pairable. False otherwise
     */
    public static void systemcallDriverBTAdapterIsPairable(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outIsPairable
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.ispairable", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIsPairable.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Returns the powered flag of this adapter
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outIsPowered True if this adapter is currently powered. False otherwise
     */
    public static void systemcallDriverBTAdapterIsPowered(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outIsPowered
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.ispowered", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIsPowered.value = response.getParameters().get(2).getBoolean();
    }

    /**
     * Starts the discovery
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     */
    public static void systemcallDriverBTAdapterStartDiscovery(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.startdiscovery", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Stops the discovery
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     */
    public static void systemcallDriverBTAdapterStopDiscovery(
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode
            ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.stopdiscovery", new ArrayList<>(0));

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
