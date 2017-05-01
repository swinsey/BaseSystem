package de.silveryard.basesystem.driver;

/**
 * Created by silveryard on 29.04.17.
 */
public abstract class Device {
    private boolean valid;

    public Device(){
        valid = true;
    }

    public final void invalidate(){
        valid = false;
    }
    public final boolean isValid(){
        return valid;
    }
}
