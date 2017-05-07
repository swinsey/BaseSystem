package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.Device;

/**
 * Created by silveryard on 30.04.17.
 */
public abstract class BluetoothDevice extends Device {
    private static int nextId = 1;

    private final int id;

    protected BluetoothDevice(){
        id = nextId;
        nextId++;
    }

    /**
     * Returns the internal identifier
     * @return Internal id
     */
    public final int getId(){
        return id;
    }
    /**
     * Returns if this device is blocked
     * @return True if blocked. False otherwise
     */
    public abstract boolean isBlocked();
    /**
     * Returns if this device is connected
     * @return True if connected. False otherwise
     */
    public abstract boolean isConnected();
    /**
     * Returns if this device is paired
     * @return True if paired. False otherwise
     */
    public abstract boolean isPaired();
    /**
     * Returns if this device is trusted
     * @return True if trusted. False otherwise
     */
    public abstract boolean isTrusted();

    /**
     * Returns the devices MAC address
     * @return MAC address
     */
    public abstract String getAddress();
    /**
     * Returns the devices alias name
     * @return Alias
     */
    public abstract String getAlias();
    /**
     * Returns the devices icon name
     * TODO: Change to 'getType' with enum return type
     * @return Icon type
     */
    public abstract String getIcon();
    /**
     * Returns the devices name
     * @return Device name
     */
    public abstract String getName();

    /**
     * Pairs with this device
     * @return
     */
    public abstract boolean pair();
    /**
     * Cancels an active pairing process
     */
    public abstract void cancelPairing();
    /**
     * Removes an already paired device
     */
    public abstract void remove();
    /**
     * Connects to this device
     */
    public abstract void connect();
    /**
     * Disconnects from this device
     */
    public abstract void disconnect();
}
