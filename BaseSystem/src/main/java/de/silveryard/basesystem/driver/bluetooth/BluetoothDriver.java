package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.DeviceHandler;
import de.silveryard.basesystem.driver.Driver;
import org.apache.commons.lang3.SystemUtils;

/**
 * Created by silveryard on 30.04.17.
 */
public final class BluetoothDriver extends Driver<BluetoothDevice> {
    private final BluetoothManager manager;

    /**
     * Constructor
     */
    public BluetoothDriver(){
        if(SystemUtils.IS_OS_LINUX){
            manager = new BluetoothManagerLinux(this::onDeviceConnected, this::onDeviceDisconnected);
        } else {
            manager = new BluetoothManagerFallback(this::onDeviceConnected, this::onDeviceDisconnected);
        }
    }

    @Override
    public void onUnload(){
        manager.dispose();
    }
    @Override
    public void update(){
        manager.update();
    }

    /**
     * Registers a new handler for the event "paired". This handler will be called when pairing to a device succeeds
     * @param handler Handler to be registered
     */
    public synchronized void registerBtPairedHandler(DeviceHandler<BluetoothDevice> handler){
        manager.registerPairedHandler(handler);
    }
    /**
     * Unregisters a handler from the event "paired". This handler will no longer be called when pairing to a device succeeds
     * @param handler Handler to be unregistered
     */
    public synchronized void unregisterBtPairedHandler(DeviceHandler<BluetoothDevice> handler){
        manager.unregisterPairedHandler(handler);
    }
    /**
     * Registers a new handler to the event "failedPairing". This handler will be called when pairing to a device fails
     * @param handler Handler to be registered
     */
    public synchronized void registerBtFailedPairingHandler(DeviceHandler<BluetoothDevice> handler){
        manager.registerFailedConnectingHandler(handler);
    }
    /**
     * Unregisters a handler from the event "failedPairing". This handler will no longer be called when pairing to a device failed
     * @param handler Handler to be unregistered
     */
    public synchronized void unregisterBtFailedPairingHandler(DeviceHandler<BluetoothDevice> handler){
        manager.unregisterFailedPairingHandler(handler);
    }
    /**
     * Registers a new handler to the event "connected". This handler will be called when connecting to a device succeeds
     * @param handler Handler to be registered
     */
    public synchronized void registerBtConnectedHandler(DeviceHandler<BluetoothDevice> handler){
        manager.registerConnectedHandler(handler);
    }
    /**
     * Unregisters a handler from the event "connected". This handler will no longer be called when connecting to a device succeeds
     * @param handler Handler to be unregistered
     */
    public synchronized void unregisterBtConnectedHandler(DeviceHandler<BluetoothDevice> handler){
        manager.unregisterConnectedHandler(handler);
    }
    /**
     * Registers a new handler to the event "failedConnecting". This handler will be called when connecting to a device failed
     * @param handler Handler to be registered
     */
    public synchronized void registerBtFailedConnectingHandler(DeviceHandler<BluetoothDevice> handler){
        manager.registerFailedConnectingHandler(handler);
    }
    /**
     * Unregisters a handler from the event "failedConnecting". This handler will no longer be called when connecting to a device failed
     * @param handler Handler to be unregistered
     */
    public synchronized void unregisterBtFailedConnectingHandler(DeviceHandler<BluetoothDevice> handler){
        manager.unregisterFailedConnectingHandler(handler);
    }
    /**
     * URegisters a new handler to the event "disconnected". This handler will be called when a device got disconnected
     * @param handler Handler to be registered
     */
    public synchronized void registerBtDisconnectedHandler(DeviceHandler<BluetoothDevice> handler){
        manager.registerDisconnectedHandler(handler);
    }
    /**
     * Unregisters a handler from the event "disconnected". This handler will no longer be called when a device git disconnected
     * @param handler Handler to be unregistered
     */
    public synchronized void unregisterBtDisconnectedHandler(DeviceHandler<BluetoothDevice> handler){
        manager.unregisterDisconnectedHandler(handler);
    }
    /**
     * Registers a new handler to the event "removed". This handler will be called when a device got removed
     * @param handler Handler to be registered
     */
    public synchronized void registerBtRemovedHandler(DeviceHandler<BluetoothDevice> handler){
        manager.registerRemovedHandler(handler);
    }
    /**
     * Unregisters a handler from the event "removed". This handler will no longer be called when a device got removed
     * @param handler Handler to be unregistered
     */
    public synchronized void unregisterBtRemovedHandler(DeviceHandler<BluetoothDevice> handler){
        manager.unregisterRemovedHandler(handler);
    }

    /**
     * Returns the default adapter
     * @return Adapter object
     */
    public Adapter getAdapter(){
        return manager.getAdapter();
    }
}
