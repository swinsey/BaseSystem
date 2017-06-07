package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.IDisposable;

/**
 * Created by Sebif on 06.06.2017.
 */
public class FmodDSPMeteringInfo implements IDisposable {
    private long handle;

    /**
     * Constructor
     */
    public FmodDSPMeteringInfo(){
        handle = 0;
        init();
    }

    private native void init();

    /**
     * Returns the internal handle of this object
     * @return Internal handle
     */
    public native long getHandle();

    public native int getNumSamples();
    public native float getPeakLevel(int index);
    public native float getRmsLevel(int index);
    public native short getNumChannels();

    @Override
    public native void dispose();
}
