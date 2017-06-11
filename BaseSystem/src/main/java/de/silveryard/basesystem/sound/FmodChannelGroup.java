package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.Wrapper;

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

    public FmodResult addGroup(FmodChannelGroup group){
        return addGroup(group, true, null);
    }
    public FmodResult addGroup(FmodChannelGroup group, boolean propagatedSpClock){
        return addGroup(group, propagatedSpClock, null);
    }
    public FmodResult addGroup(FmodChannelGroup group, FmodDSPConnection connection){
        return addGroup(group, true, connection);
    }
    public native FmodResult addGroup(FmodChannelGroup group, boolean propagatedSpClock, FmodDSPConnection connection);
    public native FmodResult getNumGroups(Wrapper<Integer> numGroups);
    public native FmodResult getGroup(int index, FmodChannelGroup group);
    public native FmodResult getParentGroup(FmodChannelGroup group);

    public native FmodResult getName(Wrapper<String> name);
    public native FmodResult getNumChannels(Wrapper<Integer> numChannels);
    public native FmodResult getChannel(int index, FmodChannel channel);

    public native FmodResult getDSP(int index, FmodDSP dsp);
    public native FmodResult addDSP(int index, FmodDSP dsp);
    public native FmodResult removeDSP(FmodDSP dsp);
    public native FmodResult getNumDSPs(Wrapper<Integer> numDSPs);
    public native FmodResult setDSPIndex(FmodDSP dsp, int index);
    public native FmodResult getDSPIndex(FmodDSP dsp, Wrapper<Integer> index);
}
