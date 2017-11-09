package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.DeviceHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 01.05.17.
 */
abstract class BluetoothManager {
    private final DeviceHandler<BluetoothDevice> connectedHandler;
    private final DeviceHandler<BluetoothDevice> disconnectedHandler;

    private final List<DeviceHandler<BluetoothDevice>> pairedHandlers;
    private final List<DeviceHandler<BluetoothDevice>> failedPairingHandlers;
    private final List<DeviceHandler<BluetoothDevice>> connectedHandlers;
    private final List<DeviceHandler<BluetoothDevice>> failedConnectingHandlers;
    private final List<DeviceHandler<BluetoothDevice>> disconnectedHandlers;
    private final List<DeviceHandler<BluetoothDevice>> removedHandlers;

    /**
     * Constructor
     * @param connectedHandler
     * @param disconnectedHandler
     */
    public BluetoothManager(DeviceHandler<BluetoothDevice> connectedHandler, DeviceHandler<BluetoothDevice> disconnectedHandler){
        this.connectedHandler = connectedHandler;
        this.disconnectedHandler = disconnectedHandler;

        pairedHandlers = new ArrayList<>();
        failedPairingHandlers = new ArrayList<>();
        connectedHandlers = new ArrayList<>();
        failedConnectingHandlers = new ArrayList<>();
        disconnectedHandlers = new ArrayList<>();
        removedHandlers = new ArrayList<>();
    }

    /**
     * Returns the default adapter
     * @return Adapter object
     */
    public abstract Adapter getAdapter();

    /**
     * Updates this manager
     */
    public abstract void update();
    /**
     * Disposes this manager
     */
    public abstract void dispose();

    /**
     * Registers a new handler for the event "paired". This handler will be called when pairing to a device succeeds
     * @param handler Handler to be registered
     */
    public final synchronized void registerPairedHandler(DeviceHandler<BluetoothDevice> handler){
        pairedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "paired". This handler will no longer be called when pairing to a device succeeds
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterPairedHandler(DeviceHandler<BluetoothDevice> handler){
        pairedHandlers.remove(handler);
    }
    /**
     * Registers a new handler to the event "failedPairing". This handler will be called when pairing to a device fails
     * @param handler Handler to be registered
     */
    public final synchronized void registerFailedPairingHandler(DeviceHandler<BluetoothDevice> handler){
        failedPairingHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "failedPairing". This handler will no longer be called when pairing to a device failed
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterFailedPairingHandler(DeviceHandler<BluetoothDevice> handler){
        failedPairingHandlers.remove(handler);
    }
    /**
     * Registers a new handler to the event "connected". This handler will be called when connecting to a device succeeds
     * @param handler Handler to be registered
     */
    public final synchronized void registerConnectedHandler(DeviceHandler<BluetoothDevice> handler){
        connectedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "connected". This handler will no longer be called when connecting to a device succeeds
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterConnectedHandler(DeviceHandler<BluetoothDevice> handler){
        connectedHandlers.remove(handler);
    }
    /**
     * Registers a new handler to the event "failedConnecting". This handler will be called when connecting to a device failed
     * @param handler Handler to be registered
     */
    public final synchronized void registerFailedConnectingHandler(DeviceHandler<BluetoothDevice> handler){
        failedConnectingHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "failedConnecting". This handler will no longer be called when connecting to a device failed
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterFailedConnectingHandler(DeviceHandler<BluetoothDevice> handler){
        failedConnectingHandlers.remove(handler);
    }
    /**
     * URegisters a new handler to the event "disconnected". This handler will be called when a device got disconnected
     * @param handler Handler to be registered
     */
    public final synchronized void registerDisconnectedHandler(DeviceHandler<BluetoothDevice> handler){
        disconnectedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "disconnected". This handler will no longer be called when a device git disconnected
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterDisconnectedHandler(DeviceHandler<BluetoothDevice> handler){
        disconnectedHandlers.remove(handler);
    }
    /**
     * Registers a new handler to the event "removed". This handler will be called when a device got removed
     * @param handler Handler to be registered
     */
    public final synchronized void registerRemovedHandler(DeviceHandler<BluetoothDevice> handler){
        removedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "removed". This handler will no longer be called when a device got removed
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterRemovedHandler(DeviceHandler<BluetoothDevice> handler){
        removedHandlers.remove(handler);
    }

    /**
     * Call when a device gets connected
     * @param device
     */
    protected void onDeviceConnected(BluetoothDevice device){
        device.registerPairedHandler(this::handleBtPaired);
        device.registerFailedPairingHandler(this::handleBtFailedPairing);
        device.registerConnectedHandler(this::handleBtConnected);
        device.registerFailedConnectingHandler(this::handleBtFailedConnecting);
        device.registerDisconnectedHandler(this::handleBtDisconnected);
        device.registerRemovedHandler(this::handleBtRemoved);

        connectedHandler.handle(device);
    }
    /**
     * Call when a device gets disconnected
     * @param device
     */
    protected void onDeviceDisconnected(BluetoothDevice device){
        device.unregisterPairedHandler(this::handleBtPaired);
        device.unregisterFailedPairingHandler(this::handleBtFailedPairing);
        device.unregisterConnectedHandler(this::handleBtConnected);
        device.unregisterFailedConnectingHandler(this::handleBtFailedConnecting);
        device.unregisterDisconnectedHandler(this::handleBtDisconnected);
        device.unregisterRemovedHandler(this::handleBtRemoved);

        disconnectedHandler.handle(device);
    }

    private void handleBtPaired(BluetoothDevice device){
        callEvent(pairedHandlers, device);
    }
    private void handleBtFailedPairing(BluetoothDevice device){
        callEvent(failedPairingHandlers, device);
    }
    private void handleBtConnected(BluetoothDevice device){
        callEvent(connectedHandlers, device);
    }
    private void handleBtFailedConnecting(BluetoothDevice device){
        callEvent(failedConnectingHandlers, device);
    }
    private void handleBtDisconnected(BluetoothDevice device){
        callEvent(disconnectedHandlers, device);
    }
    private void handleBtRemoved(BluetoothDevice device){
        callEvent(removedHandlers, device);
    }

    private void callEvent(List<DeviceHandler<BluetoothDevice>> event, BluetoothDevice device){
        for(int i = 0; i < event.size(); i++){
            event.get(i).handle(device);
        }
    }
}
