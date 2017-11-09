package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.Wrapper;

/**
 * FMOD::ChannelGroup
 * Created by Sebif on 06.06.2017.
 */
public class FmodChannelGroup {
    @SuppressWarnings({"unused", "FieldCanBeLocal"})
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

    public FmodResult addGroup(FmodChannelGroup group){
        return addGroup(group, true, null);
    }
    /**
     *
     * @param group ChannelGroup Value
     * @param propagatedSpClock PropagatedSpClock Value
     * @return Fmod Result
     */
    public FmodResult addGroup(FmodChannelGroup group, boolean propagatedSpClock){
        return addGroup(group, propagatedSpClock, null);
    }
    /**
     *
     * @param group ChannelGroup Value
     * @param connection Connection Value
     * @return Fmod Result
     */
    public FmodResult addGroup(FmodChannelGroup group, FmodDSPConnection connection){
        return addGroup(group, true, connection);
    }
    /**
     *
     * @param group ChannelGroup Value
     * @param propagatedSpClock PropagatedSpClock Value
     * @param connection Connection Value
     * @return Fmod Result
     */
    public native FmodResult addGroup(FmodChannelGroup group, boolean propagatedSpClock, FmodDSPConnection connection);
    /**
     *
     * @param numGroups NumGroups Value
     * @return Fmod Result
     */
    public native FmodResult getNumGroups(Wrapper<Integer> numGroups);
    /**
     *
     * @param index Index Value
     * @param group ChannelGroup Value
     * @return Fmod Result
     */
    public native FmodResult getGroup(int index, FmodChannelGroup group);
    /**
     *
     * @param group ChannelGroup Value
     * @return Fmod Result
     */
    public native FmodResult getParentGroup(FmodChannelGroup group);

    /**
     *
     * @param name Name Value
     * @return Fmod Result
     */
    public native FmodResult getName(Wrapper<String> name);
    /**
     *
     * @param numChannels NumChannels value
     * @return Fmod Result
     */
    public native FmodResult getNumChannels(Wrapper<Integer> numChannels);
    /**
     *
     * @param index Index Value
     * @param channel Channel Value
     * @return Fmod Result
     */
    public native FmodResult getChannel(int index, FmodChannel channel);

    /**
     *
     * @param index Index Value
     * @param dsp DSP Value
     * @return Fmod Result
     */
    public native FmodResult getDSP(int index, FmodDSP dsp);
    /**
     *
     * @param index Index Value
     * @param dsp DSP Value
     * @return Fmod Result
     */
    public native FmodResult addDSP(int index, FmodDSP dsp);
    /**
     *
     * @param dsp DSP Value
     * @return Fmod Result
     */
    public native FmodResult removeDSP(FmodDSP dsp);
    /**
     *
     * @param numDSPs NumDSPs Value
     * @return Fmod Result
     */
    public native FmodResult getNumDSPs(Wrapper<Integer> numDSPs);
    /**
     *
     * @param dsp DSP Value
     * @param index Index Value
     * @return Fmod Result
     */
    public native FmodResult setDSPIndex(FmodDSP dsp, int index);
    /**
     *
     * @param dsp DSP Value
     * @param index Index Value
     * @return Fmod Result
     */
    public native FmodResult getDSPIndex(FmodDSP dsp, Wrapper<Integer> index);
}
