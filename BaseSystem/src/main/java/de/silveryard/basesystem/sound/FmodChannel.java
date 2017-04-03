package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.Wrapper;

/**
 * Created by Sebif on 27.03.2017.
 */
public class FmodChannel {
    private long handle;

    public FmodChannel(){
        handle = 0;
    }

    public native void setHandle(long handle);
    public native long getHandle();

    public native FmodResult stop();
    public native FmodResult setPaused(boolean paused);
    public native FmodResult getPaused(Wrapper<Boolean> paused);
    public native FmodResult setVolume(float volume);
    public native FmodResult getVolume(Wrapper<Float> volume);
    public native FmodResult setVolumeRamp(boolean paused);
    public native FmodResult getVolumeRamp(Wrapper<Boolean> paused);
    public native FmodResult getAudibility(Wrapper<Float> audibility);
    public native FmodResult setPitch(float volume);
    public native FmodResult getPitch(Wrapper<Float> volume);
    public native FmodResult setMute(boolean paused);
    public native FmodResult getMute(Wrapper<Boolean> paused);

    public native FmodResult isPlaying(Wrapper<Boolean> isPlaying);

    public native FmodResult setFrequency(float frequency);
    public native FmodResult getFrequency(Wrapper<Float> frequency);
    public native FmodResult setPriority(int priority);
    public native FmodResult getPriority(Wrapper<Integer> priority);
    public native FmodResult setPosition(int position, int posType);
    public native FmodResult getPosition(Wrapper<Integer> position, int posType);
}
