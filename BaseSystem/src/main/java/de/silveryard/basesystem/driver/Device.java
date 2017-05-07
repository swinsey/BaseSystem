package de.silveryard.basesystem.driver;

/**
 * Created by silveryard on 29.04.17.
 */
public abstract class Device {
    private boolean valid;

    /**
     * Constructor
     */
    public Device(){
        valid = true;
    }

    /**
     * Marks this device as invalid. Invalid devices should not be used as they are already disconnected
     */
    public final void invalidate(){
        valid = false;
    }
    /**
     * Checks if this device is still valid and connected
     * @return True if valid. False otherwise
     */
    public final boolean isValid(){
        return valid;
    }
}
