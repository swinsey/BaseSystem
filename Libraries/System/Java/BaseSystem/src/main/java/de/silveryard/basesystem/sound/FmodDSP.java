package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.Wrapper;

/**
 * FMOD::DSP
 * Created by Sebif on 06.06.2017.
 */
public class FmodDSP {
    @SuppressWarnings({"unused", "FieldCanBeLocal"})
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

    /**
     *
     * @return
     */
    public native FmodResult release();

    /**
     *
     * DSP unit control
     *
     */

    /**
     *
     * @param active
     * @return
     */
    public native FmodResult setActive(boolean active);
    /**
     *
     * @param active
     * @return
     */
    public native FmodResult getActive(Wrapper<Boolean> active);
    /**
     *
     * @param bypass
     * @return
     */
    public native FmodResult setBypass(boolean bypass);
    /**
     *
     * @param bypass
     * @return
     */
    public native FmodResult getBypass(Wrapper<Boolean> bypass);

    /**
     *
     * DSP parameter control
     *
     */

    /**
     *
     * @param index
     * @param value
     * @return
     */
    public native FmodResult setParameterFloat(int index, float value);
    /**
     *
     * @param index
     * @param value
     * @return
     */
    public native FmodResult setParameterInt(int index, int value);
    /**
     *
     * @param index
     * @param value
     * @return
     */
    public native FmodResult setParameterBool(int index, boolean value);
    /**
     *
     * @param index
     * @param value
     * @return
     */
    public native FmodResult getParameterFloat(int index, Wrapper<Float> value);
    /**
     *
     * @param index
     * @param value
     * @return
     */
    public native FmodResult getParameterInt(int index, Wrapper<Integer> value);
    /**
     *
     * @param index
     * @param value
     * @return
     */
    public native FmodResult getParameterBool(int index, Wrapper<Boolean> value);
    /**
     *
     * @param numParams
     * @return
     */
    public native FmodResult getNumParameters(Wrapper<Integer> numParams);

    /**
     *
     * DSP attributes
     *
     */

    /**
     *
     * @param name
     * @param version
     * @param channels
     * @param configWidth
     * @param configHeight
     * @return
     */
    public native FmodResult getInfo(Wrapper<String> name, Wrapper<Integer> version, Wrapper<Integer> channels, Wrapper<Integer> configWidth, Wrapper<Integer> configHeight);
    /**
     *
     * @param type
     * @return
     */
    public native FmodResult getType(Wrapper<FmodDSPType> type);
    /**
     *
     * @param idle
     * @return
     */
    public native FmodResult getIdle(Wrapper<Boolean> idle);

    /**
     *
     * Metering
     *
     */

    /**
     *
     * @param inputEnabled
     * @param outputEnabled
     * @return
     */
    public native FmodResult setMeteringEnabled(boolean inputEnabled, boolean outputEnabled);
    /**
     *
     * @param inputEnabled
     * @param outputEnabled
     * @return
     */
    public native FmodResult getMeteringEnabled(Wrapper<Boolean> inputEnabled, Wrapper<Boolean> outputEnabled);
    /**
     *
     * @param inputInfo
     * @param outputInfo
     * @return
     */
    public native FmodResult getMeteringInfo(FmodDSPMeteringInfo inputInfo, FmodDSPMeteringInfo outputInfo);
}
