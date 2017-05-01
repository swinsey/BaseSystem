package de.silveryard.basesystem.driver.bluetooth;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class Adapter {
    public abstract String getAddress();
    public abstract String getAlias();
    public abstract String getModalias();
    public abstract String getName();

    public abstract boolean isDiscoverable();
    public abstract boolean isDiscovering();
    public abstract boolean isPairable();
    public abstract boolean isPowered();

    public abstract void startDiscovery();
    public abstract void stopDiscovery();
}
