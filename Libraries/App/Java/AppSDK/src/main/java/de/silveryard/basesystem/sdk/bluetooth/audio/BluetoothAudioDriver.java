package de.silveryard.basesystem.sdk.bluetooth.audio;

import de.silveryard.basesystem.sdk.kernel.KernelException;
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

    /**
     * Returns all currently known audio devices
     * @return
     */
    public static synchronized List<AudioDevice> getDevices() {
        return devices;
    }

    /**
     * Updates the internal list of devices. Will trigger events when nececary
     */
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

    /**
     * Registers a new handler to the event 'connected'. This handler will be called when a new audio device has connected
     * @param handler
     */
    public static synchronized void registerConnectedHandler(DeviceHandler handler) {
        connectedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event 'connected'. This handler will no longer be called when a new audio device has connected
     * @param handler
     */
    public static synchronized void unregisterConnectedHandler(DeviceHandler handler) {
        connectedHandlers.remove(handler);
    }
    /**
     * Registers a new handler to the event 'disconnected'. This handler will be called when a connected audio device will no longer be avaliable
     * @param handler
     */
    public static synchronized void registerDisconnectedHandler(DeviceHandler handler) {
        disconnectedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event 'disconnected'. This handler will no longer be called when a connected audio device will no longer be avaliable
     * @param handler
     */
    public static synchronized void unregisterDisconnectedHandler(DeviceHandler handler) {
        disconnectedHandlers.remove(handler);
    }
}
