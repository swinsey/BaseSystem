package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.Wrapper;

/**
 * Created by Sebif on 06.06.2017.
 */
public class FmodDSP {
    private long handle;

    /**
     * Constructor
     */
    public FmodDSP(){
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

    /**
     *
     * General
     *
     */

    public native FmodResult release();

    /**
     *
     * DSP unit control
     *
     */

    public native FmodResult setActive(boolean active);
    public native FmodResult getActive(Wrapper<Boolean> active);
    public native FmodResult setBypass(boolean bypass);
    public native FmodResult getBypass(Wrapper<Boolean> bypass);

    /**
     *
     * DSP parameter control
     *
     */

    public native FmodResult setParameterFloat(int index, float value);
    public native FmodResult setParameterInt(int index, int value);
    public native FmodResult setParameterBool(int index, boolean value);
    public native FmodResult getParameterFloat(int index, Wrapper<Float> value);
    public native FmodResult getParameterInt(int index, Wrapper<Integer> value);
    public native FmodResult getParameterBool(int index, Wrapper<Boolean> value);
    public native FmodResult getNumParameters(Wrapper<Integer> numParams);

    /**
     *
     * DSP attributes
     *
     */

    public native FmodResult getInfo(Wrapper<String> name, Wrapper<Integer> version, Wrapper<Integer> channels, Wrapper<Integer> configWidth, Wrapper<Integer> configHeight);
    public native FmodResult getType(Wrapper<FmodDSPType> type);
    public native FmodResult getIdle(Wrapper<Boolean> idle);

    /**
     *
     * Metering
     *
     */

    public native FmodResult setMeteringEnabled(boolean inputEnabled, boolean outputEnabled);
    public native FmodResult getMeteringEnabled(Wrapper<Boolean> inputEnabled, Wrapper<Boolean> outputEnabled);
    public native FmodResult getMeteringInfo(FmodDSPMeteringInfo inputInfo, FmodDSPMeteringInfo outputInfo);
}
