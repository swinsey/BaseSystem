package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.DeviceHandler;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class BluetoothManager {
    private DeviceHandler<BluetoothDevice> connectedHandler;
    private DeviceHandler<BluetoothDevice> disconnectedHandler;

    /**
     * Constructor
     * @param connectedHandler
     * @param disconnectedHandler
     */
    public BluetoothManager(DeviceHandler<BluetoothDevice> connectedHandler, DeviceHandler<BluetoothDevice> disconnectedHandler){
        this.connectedHandler = connectedHandler;
        this.disconnectedHandler = disconnectedHandler;
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
     * Call when a device gets connected
     * @param device
     */
    protected void onDeviceConnected(BluetoothDevice device){
        connectedHandler.handle(device);
    }
    /**
     * Call when a device gets disconnected
     * @param device
     */
    protected void onDeviceDisconnected(BluetoothDevice device){
        disconnectedHandler.handle(device);
    }
}
