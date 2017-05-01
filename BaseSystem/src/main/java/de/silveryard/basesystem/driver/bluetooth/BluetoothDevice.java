package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.Device;

/**
 * Created by silveryard on 30.04.17.
 */
public abstract class BluetoothDevice extends Device {
    public abstract boolean isBlocked();
    public abstract boolean isConnected();
    public abstract boolean isPaired();
    public abstract boolean isTrusted();

    public abstract String getAddress();
    public abstract String getAlias();
    public abstract String getIcon();
    public abstract String getName();

    public abstract void pair();
    public abstract void cancelPairing();
    public abstract void connect();
    public abstract void disconnect();
}
