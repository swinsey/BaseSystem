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

    public static void systemCallDriverBTDevicePair(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.pair", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
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
    public static void systemCallDriverBTDeviceConnect(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtReturnCode> outBtReturnCode
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.bt.device.connect", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtReturnCode.value = BtReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
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
}
