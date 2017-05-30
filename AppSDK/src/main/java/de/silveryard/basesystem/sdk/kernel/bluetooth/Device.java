package de.silveryard.basesystem.sdk.kernel.bluetooth;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class Device {
    /**
     * Returns if a given device is blocked
     * @param deviceId ID of the device to fetch data from
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outIsBlocked True if blocked. False otherwise
     */
    public static void systemCallDriverBTDeviceIsBlocked(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outIsBlocked
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.isblocked", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIsBlocked.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Returns if a given device is connected
     * @param deviceId ID of the device to fetch data from
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outIsConnected True if connected. False otherwise
     */
    public static void systemCallDriverBTDeviceIsConnected(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outIsConnected
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.isconnected", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIsConnected.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Returns if a given device is paired
     * @param deviceId ID of the device to fetch data from
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outIsPaired True if paired. False otherwise
     */
    public static void systemCallDriverBTDeviceIsPaired(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outIsPaired
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.ispaired", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIsPaired.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Returns if a given device is trusted
     * @param deviceId ID of the device to fetch data from
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Reteurn Code
     * @param outIsTrusted True if trusted. False otherwise
     */
    public static void systemCallDriverBTDeviceIsTrusted(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outIsTrusted
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.istrusted", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIsTrusted.value = response.getParameters().get(2).getBoolean();
    }

    /**
     * Returns the MAC address of a given device
     * @param deviceId ID of the device to fetch data from
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outAddress MAC address
     */
    public static void systemCallDriverBTDeviceGetAddress(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<String> outAddress
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.getaddress", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outAddress.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the alias of a given device
     * @param deviceId ID of the device to fetch data from
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outAlias Alias name
     */
    public static void systemCallDriverBTDeviceGetAlias(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<String> outAlias
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.getalias", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outAlias.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the icon of a given device.
     * You can use this as 'device type'
     * @param deviceId ID of the device to fetch data from
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outIcon Icon type
     */
    public static void systemCallDriverBTDeviceGetIcon(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<String> outIcon
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.geticon", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIcon.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the name of a given device
     * @param deviceId ID of the device to fetch data from
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outName BtPhoneDevice name
     */
    public static void systemCallDriverBTDeviceGetName(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<String> outName
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.getname", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outName.value = response.getParameters().get(2).getString();
    }

    /**
     * Pairs with a given device
     * @param deviceId ID of the device to pair with
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outSuccess Success Flag
     */
    public static void systemCallDriverBTDevicePair(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outSuccess
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.pair", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outSuccess.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Cancels an active pairing process with a given device
     * @param deviceId ID of the device to cancel the pairing
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     */
    public static void systemCallDriverBTDeviceCancelPairing(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.cancelpairing", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Connects to a given device
     * @param deviceId ID of the device to connect to
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     * @param outSuccess Success Flag
     */
    public static void systemCallDriverBTDeviceConnect(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode,
            Wrapper<Boolean> outSuccess
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.connect", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outSuccess.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Disconnects from a given device
     * @param deviceId ID of the device to disconnect from
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     */
    public static void systemCallDriverBTDeviceDisconnect(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.disconnect", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Removes the device and its pairing
     * @param deviceId ID of the device to remove
     * @param outReturnCode General Return Code
     * @param outBtReturnCode Bt Return Code
     */
    public static void systemCallDriverBTDeviceRemove(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.remove", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
