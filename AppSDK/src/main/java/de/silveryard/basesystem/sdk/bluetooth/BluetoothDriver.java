package de.silveryard.basesystem.sdk.bluetooth;

import de.silveryard.basesystem.sdk.BtKernelException;
import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.bluetooth.BtReturnCode;

import java.util.ArrayList;
import java.util.List;

import static de.silveryard.basesystem.sdk.kernel.bluetooth.BluetoothDriver.systemCallDriverBTBluetoothDriverGetDevices;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class BluetoothDriver {
    private static final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private static final Wrapper<BtReturnCode> btReturnCodeWrapper = new Wrapper<>();
    private static final Wrapper<Integer[]> intArrayWrapper = new Wrapper<>();

    private static final List<DeviceHandler> connectedHandlers = new ArrayList<>();
    private static final List<DeviceHandler> disconnectedHandlers = new ArrayList<>();
    private static final List<Device> devices = new ArrayList<>();

    public static List<Device> getDevices(){
        return devices;
    }

    public void updateDevices(){
        systemCallDriverBTBluetoothDriverGetDevices(returnCodeWrapper, btReturnCodeWrapper, intArrayWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        for(int i = 0; i < intArrayWrapper.value.length; i++){
            boolean alreadyConnected = false;

            for(int j = 0; j < devices.size(); j++){
                if(devices.get(j).getId() == intArrayWrapper.value[i]){
                    alreadyConnected = true;
                    break;
                }
            }

            if(!alreadyConnected){
                Device device = new Device(intArrayWrapper.value[i]);
                devices.add(device);

                for(int k = 0; k < connectedHandlers.size(); k++){
                    connectedHandlers.get(k).handle(device);
                }
            }
        }

        for(int i = 0; i < devices.size(); i++){
            boolean stillConnected = false;

            for(int j = 0; j < intArrayWrapper.value.length; j++){
                if(devices.get(i).getId() == intArrayWrapper.value[j]){
                    stillConnected = true;
                    break;
                }
            }

            if(!stillConnected){
                devices.remove(devices.get(i));

                for(int k = 0; k < disconnectedHandlers.size(); k++){
                    disconnectedHandlers.get(k).handle(devices.get(i));
                }
            }
        }
    }

    public static void registerConnectedHandler(DeviceHandler handler){
        connectedHandlers.add(handler);
    }
    public static void unregisterConnectedHandler(DeviceHandler handler){
        connectedHandlers.remove(handler);
    }
    public static void registerDisconnectedHandler(DeviceHandler handler){
        disconnectedHandlers.add(handler);
    }
    public static void unregisterDisconnectedHandler(DeviceHandler handler){
        disconnectedHandlers.remove(handler);
    }
}
