package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.Wrapper;

/**
 * Created by Sebif on 27.03.2017.
 */
public class FmodChannel {
    private long handle;

    /**
     * Constructor
     */
    public FmodChannel(){
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
     * FMOD::Channel::stop
     * @return Fmod Result
     */
    public native FmodResult stop();
    /**
     * FMOD::Channel::setPaused
     * @param paused Paused value
     * @return Fmod Result
     */
    public native FmodResult setPaused(boolean paused);
    /**
     * FMOD::Channel::getPaused
     * @param paused Paused value
     * @return Fmod Result
     */
    public native FmodResult getPaused(Wrapper<Boolean> paused);
    /**
     * FMOD::Channel::setVolume
     * @param volume Volume Value
     * @return Fmod Result
     */
    public native FmodResult setVolume(float volume);
    /**
     * FMOD::Channel::getVolume
     * @param volume Volume Value
     * @return Fmod Result
     */
    public native FmodResult getVolume(Wrapper<Float> volume);
    /**
     * FMOD::Channel::setVolumeRamp
     * @param paused VolumeRamp Value
     * @return Fmod Result
     */
    public native FmodResult setVolumeRamp(boolean paused);
    /**
     * FMOD::Channel::getVolumeRamp
     * @param paused VolumeRamp Value
     * @return Fmod Result
     */
    public native FmodResult getVolumeRamp(Wrapper<Boolean> paused);
    /**
     * FMOD::Channel::getAudibility
     * @param audibility Audibility Value
     * @return Fmod Result
     */
    public native FmodResult getAudibility(Wrapper<Float> audibility);
    /**
     * FMOD::Channel::setPitch
     * @param volume Volume Value
     * @return Fmod Result
     */
    public native FmodResult setPitch(float volume);
    /**
     * FMOD::Channel::getPitch
     * @param volume Volume Value
     * @return Fmod Result
     */
    public native FmodResult getPitch(Wrapper<Float> volume);
    /**
     * FMOD::Channel::setMute
     * @param paused Mute Value
     * @return Fmod Result
     */
    public native FmodResult setMute(boolean paused);
    /**
     * FMOD::Channel::getMute
     * @param paused Mute Value
     * @return Fmod Result
     */
    public native FmodResult getMute(Wrapper<Boolean> paused);
    /**
     * FMOD::Channel::isPlaying
     * @param isPlaying IsPlaying Value
     * @return Fmod Result
     */
    public native FmodResult isPlaying(Wrapper<Boolean> isPlaying);

    /**
     * FMOD::Channel::setFrequency
     * @param frequency Frequency Value
     * @return Fmod Result
     */
    public native FmodResult setFrequency(float frequency);
    /**
     * FMOD::Channel::getFrequency
     * @param frequency Frequency Value
     * @return Fmod Result
     */
    public native FmodResult getFrequency(Wrapper<Float> frequency);
    /**
     * FMOD::Channel::setPriority
     * @param priority Priority Value
     * @return Fmod Result
     */
    public native FmodResult setPriority(int priority);
    /**
     * FMOD::Channel::getPriority
     * @param priority Priority Value
     * @return Fmod Result
     */
    public native FmodResult getPriority(Wrapper<Integer> priority);
    /**
     * FMOD::Channel::setPosition
     * @param position Position Value
     * @param posType PosType Value
     * @return Fmod Result
     */
    public native FmodResult setPosition(int position, int posType);
    /**
     * FMOD::Channel::getPosition
     * @param position Position Value
     * @param posType PosType Value
     * @return Fmod Result
     */
    public native FmodResult getPosition(Wrapper<Integer> position, int posType);

    public native FmodResult setChannelGroup(FmodChannelGroup channelGroup);
    public native FmodResult getChannelGroup(FmodChannelGroup channelGroup);

    public native FmodResult getDSP(int index, FmodDSP dsp);
    public native FmodResult addDSP(int index, FmodDSP dsp);
    public native FmodResult removeDSP(FmodDSP dsp);
    public native FmodResult getNumDSPs(Wrapper<Integer> numDSPs);
    public native FmodResult setDSPIndex(FmodDSP dsp, int index);
    public native FmodResult getDSPIndex(FmodDSP dsp, Wrapper<Integer> index);
}
