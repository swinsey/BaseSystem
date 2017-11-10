package de.silveryard.basesystem.gui;

/**
 * Created by Beppo on 09.11.2017.
 */
public final class DisplayHandle {
    private long value1;

    public DisplayHandle(){
        this(0);
    }
    public DisplayHandle(long value1){
        this.value1 = value1;
    }

    @Override
    public String toString() {
        return "[ " + value1 + " ]";
    }
}
