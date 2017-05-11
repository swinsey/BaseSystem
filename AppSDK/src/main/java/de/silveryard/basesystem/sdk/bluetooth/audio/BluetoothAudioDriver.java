package de.silveryard.basesystem.sdk.bluetooth.audio;

import de.silveryard.basesystem.sdk.BtAudioKernelException;
import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.bluetooth.audio.BtAudioReturnCode;

import java.util.ArrayList;
import java.util.List;

import static de.silveryard.basesystem.sdk.kernel.bluetooth.audio.BtAudioDriver.systemCallDriverBtAudioBluetoothAudioDriverGetDevices;

/**
 * Created by silveryard on 11.05.17.
 */
public abstract class BluetoothAudioDriver {
    private static final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private static final Wrapper<BtAudioReturnCode> btAudioReturnCodeWrapper = new Wrapper<>();
    private static final Wrapper<Integer[]> intArrayWrapper = new Wrapper<>();

    private static final List<DeviceHandler> connectedHandlers = new ArrayList<>();
    private static final List<DeviceHandler> disconnectedHandlers = new ArrayList<>();
    private static final List<AudioDevice> devices = new ArrayList<>();

    public static synchronized List<AudioDevice> getDevices() {
        return devices;
    }

    public static synchronized void updateDevices() {
        systemCallDriverBtAudioBluetoothAudioDriverGetDevices(returnCodeWrapper, btAudioReturnCodeWrapper, intArrayWrapper);

        if (btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK) {
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if (returnCodeWrapper.value != ReturnCode.OK) {
            throw new KernelException(returnCodeWrapper.value);
        }

        for (int i = 0; i < intArrayWrapper.value.length; i++) {
            boolean alreadyConnected = false;

            for (int j = 0; j < devices.size(); j++) {
                if (devices.get(j).getId() == intArrayWrapper.value[i]) {
                    alreadyConnected = true;
                    break;
                }
            }

            if (!alreadyConnected) {
                AudioDevice device = new AudioDevice(intArrayWrapper.value[i]);
                devices.add(device);

                for (int k = 0; k < connectedHandlers.size(); k++) {
                    connectedHandlers.get(k).handle(device);
                }
            }
        }

        for (int i = 0; i < devices.size(); i++) {
            boolean stillConnected = false;

            for (int j = 0; j < intArrayWrapper.value.length; j++) {
                if (devices.get(i).getId() == intArrayWrapper.value[j]) {
                    stillConnected = true;
                    break;
                }
            }

            if (!stillConnected) {
                devices.remove(devices.get(i));

                for (int k = 0; k < disconnectedHandlers.size(); k++) {
                    disconnectedHandlers.get(k).handle(devices.get(i));
                }
            }
        }
    }

    public static synchronized void registerConnectedHandler(DeviceHandler handler) {
        connectedHandlers.add(handler);
    }
    public static synchronized void unregisterConnectedHandler(DeviceHandler handler) {
        connectedHandlers.remove(handler);
    }
    public static synchronized void registerDisconnectedHandler(DeviceHandler handler) {
        disconnectedHandlers.add(handler);
    }
    public static synchronized void unregisterDisconnectedHandler(DeviceHandler handler) {
        disconnectedHandlers.remove(handler);
    }
}
