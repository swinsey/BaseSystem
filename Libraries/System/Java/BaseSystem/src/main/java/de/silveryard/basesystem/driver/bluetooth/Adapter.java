package de.silveryard.basesystem.driver.bluetooth;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class Adapter {
    /**
     * Returns the MAC address of this adapter
     * @return MAC address
     */
    public abstract String getAddress();
    /**
     * Returns the alias name of this adapter
     * @return Alias name
     */
    public abstract String getAlias();
    /**
     * Returns the modalias name of this adapter
     * @return Modalias name
     */
    public abstract String getModalias();
    /**
     * Returns the name of this adapter
     * @return Name value
     */
    public abstract String getName();

    /**
     * Returns the discoverable flag of this adapter
     * @return True if this adapter is set to discoverable. False otherwise
     */
    public abstract boolean isDiscoverable();
    /**
     * Returns the discovering flag of this adapter
     * @return True if this adapter is currently discovering. False otherwise
     */
    public abstract boolean isDiscovering();
    /**
     * Returns the pariable flag of this adapter
     * @return True if this adapter is currently pairable. False otherwise
     */
    public abstract boolean isPairable();
    /**
     * Returns the powered flag of this adapter
     * @return True if this adapter is currently powered. False otherwise
     */
    public abstract boolean isPowered();

    /**
     * Starts the discovery
     */
    public abstract void startDiscovery();
    /**
     * Stops the discovery
     */
    public abstract void stopDiscovery();
}
