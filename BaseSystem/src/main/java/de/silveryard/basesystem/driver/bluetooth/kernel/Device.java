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
abstract class Device {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.isblocked", Device::systemCallDriverBTDeviceIsBlocked);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.isconnected", Device::systemCallDriverBTDeviceIsConnected);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.ispaired", Device::systemCallDriverBTDeviceIsPaired);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.istrusted", Device::systemCallDriverBTDeviceIsTrusted);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.getaddress", Device::systemCallDriverBTDeviceGetAddress);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.getalias", Device::systemCallDriverBTDeviceGetAlias);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.geticon", Device::systemCallDriverBTDeviceGetIcon);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.getname", Device::systemCallDriverBTDeviceGetName);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.pair", Device::systemCallDriverBTDevicePair);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.cancelpairing", Device::systemCallDriverBTDeviceCancelPairing);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.remove", Device::systemCallDriverBTDeviceRemove);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.connect", Device::systemCallDriverBTDeviceConnect);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.device.disconnect", Device::systemCallDriverBTDeviceDisconnect);
    }
    private static BluetoothDevice getDevice(int id){
        List<BluetoothDevice> devices = DriverManager.getInstance().getDriver(de.silveryard.basesystem.driver.bluetooth.BluetoothDriver.class).getDevices();
        for(int i = 0; i < devices.size(); i++){
            if(devices.get(i).getId() == id){
                return devices.get(i);
            }
        }
        return null;
    }

    private static QAMessage systemCallDriverBTDeviceIsBlocked(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue(), Parameter.createBoolean(false));
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(), Parameter.createBoolean(device.isBlocked()));
    }
    private static QAMessage systemCallDriverBTDeviceIsConnected(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue(), Parameter.createBoolean(false));
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(), Parameter.createBoolean(device.isConnected()));
    }
    private static QAMessage systemCallDriverBTDeviceIsPaired(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue(), Parameter.createBoolean(false));
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(), Parameter.createBoolean(device.isPaired()));
    }
    private static QAMessage systemCallDriverBTDeviceIsTrusted(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue(), Parameter.createBoolean(false));
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(), Parameter.createBoolean(device.isTrusted()));
    }

    private static QAMessage systemCallDriverBTDeviceGetAddress(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue(), Parameter.createString(""));
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(), Parameter.createString(device.getAddress()));
    }
    private static QAMessage systemCallDriverBTDeviceGetAlias(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue(), Parameter.createString(""));
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(), Parameter.createString(device.getAlias()));
    }
    private static QAMessage systemCallDriverBTDeviceGetIcon(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue(), Parameter.createString(""));
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(), Parameter.createString(device.getIcon()));
    }
    private static QAMessage systemCallDriverBTDeviceGetName(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue(), Parameter.createString(""));
        }

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(), Parameter.createString(device.getName()));
    }

    private static QAMessage systemCallDriverBTDevicePair(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue());
        }

        device.pair();
        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue());
    }
    private static QAMessage systemCallDriverBTDeviceCancelPairing(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue());
        }

        device.cancelPairing();
        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue());
    }
    private static QAMessage systemCallDriverBTDeviceRemove(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue());
        }

        device.remove();
        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue());
    }
    private static QAMessage systemCallDriverBTDeviceConnect(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.INVALID_ID.getValue());
        }

        device.connect();
        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue());
    }
    private static QAMessage systemCallDriverBTDeviceDisconnect(RunningApp app, QAMessage message){
        BluetoothDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), BtReturnCode.OK.getValue());
        }

        device.disconnect();
        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue());
    }
}
