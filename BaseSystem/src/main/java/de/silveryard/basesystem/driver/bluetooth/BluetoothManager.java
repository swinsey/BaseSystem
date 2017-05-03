package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.DeviceHandler;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class BluetoothManager {
    private DeviceHandler<BluetoothDevice> connectedHandler;
    private DeviceHandler<BluetoothDevice> disconnectedHandler;

    public BluetoothManager(DeviceHandler<BluetoothDevice> connectedHandler, DeviceHandler<BluetoothDevice> disconnectedHandler){
        this.connectedHandler = connectedHandler;
        this.disconnectedHandler = disconnectedHandler;
    }
    public abstract Adapter getAdapter();

    public abstract void update();
    public abstract void dispose();

    protected void onDeviceConnected(BluetoothDevice device){
        connectedHandler.handle(device);
    }
    protected void onDeviceDisconnected(BluetoothDevice device){
        disconnectedHandler.handle(device);
    }
}
