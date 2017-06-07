package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.Wrapper;

/**
 * Created by Sebif on 06.06.2017.
 */
public class FmodDSPConnection {
    private long handle;

    /**
     * Constructor
     */
    public FmodDSPConnection(){
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

    public native FmodResult getInput(FmodDSP input);
    public native FmodResult getOuput(FmodDSP output);
    public native FmodResult setMix(float volume);
    public native FmodResult getMix(Wrapper<Float> volume);
    public native FmodResult setMixMatrix(float[][] matrix);
    public native FmodResult getMixMatrix(Wrapper<Float[][]> matrix);
    public native FmodResult getType(Wrapper<FmodDSPConnectionType> type);
}
