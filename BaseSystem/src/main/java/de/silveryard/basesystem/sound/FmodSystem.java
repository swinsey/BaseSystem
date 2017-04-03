package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.Wrapper;

/**
 * Created by Sebif on 25.03.2017.
 */
public class FmodSystem {
    public static native FmodSystem createSystem();

    private long handle;

    private FmodSystem(long handle){
        this.handle = handle;
    }

    public native FmodResult init(int maxChannels, int initFlags);
    public native FmodResult update();
    public native FmodResult dispose();

    public native FmodResult setOutput(FmodOutputType output);
    public native FmodResult getOutput(Wrapper<FmodOutputType> output);
    public native FmodResult getNumDrivers(Wrapper<Integer> numDrivers);
    public native FmodResult getDriverInfo(int id, Wrapper<String> name, Wrapper<String> guid, Wrapper<Integer> systemRate, Wrapper<FmodSpeakerMode> speakerMode, Wrapper<Integer> speakerModeChannels);
    public native FmodResult setDriver(int driver);
    public native FmodResult getDriver(Wrapper<Integer> driver);

    public native FmodResult createSound(String nameOrData, int mode, FmodCreateSoundExInfo exInfo, FmodSound sound);
    public native FmodResult createStream(String nameOrData, int mode, FmodCreateSoundExInfo exInfo, FmodSound sound);

    public native FmodResult playSound(FmodSound sound, boolean paused, FmodChannel channel);

    public native FmodResult getRecordNumDrivers(Wrapper<Integer> numDrivers, Wrapper<Integer> numConnected);
    public native FmodResult getRectordDriverInfo(int id, Wrapper<String> name, Wrapper<String> guid, Wrapper<Integer> systemRate, Wrapper<FmodSpeakerMode> speakerMode, Wrapper<Integer> speakerModeChannels, Wrapper<Integer> state);
    public native FmodResult getRecordPosition(int id, Wrapper<Integer> position);
    public native FmodResult recordStart(int id, FmodSound sound, boolean loop);
    public native FmodResult recordStop(int id);
    public native FmodResult isRecording(int id, Wrapper<Boolean> recording);
}
