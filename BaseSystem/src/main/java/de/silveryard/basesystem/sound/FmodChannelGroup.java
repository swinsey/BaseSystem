package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 06.06.2017.
 */
public class FmodChannelGroup {
    private long handle;

    /**
     * Constructor
     */
    public FmodChannelGroup(){
        handle = 0;
    }

    /**
     * Sets the internal handle of this object
     * @param handle Internal handle
     */
    public native void setHandle(long handle);
    /**
     * Returns the internal handle of this object
     * @return Internal handle
     */
    public native long getHandle();
}
