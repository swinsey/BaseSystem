package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.Wrapper;

/**
 * Created by Sebif on 25.03.2017.
 */
public class FmodSystem {
    private static FmodSystem instance;

    /**
     * Creates the Singleton instance
     */
    public static void createFmodSystem(){
        instance = createSystem();
    }
    /**
     * Singleton Getter
     * @return FmodSystem instance
     */
    public static FmodSystem getInstance(){
        return instance;
    }
    private static native FmodSystem createSystem();

    private long handle;

    private FmodSystem(long handle){
        this.handle = handle;
    }

    /**
     * FMOD::System::init
     * @param maxChannels
     * @param initFlags
     * @return
     */
    public native FmodResult init(int maxChannels, int initFlags);
    /**
     * FMOD::System::update
     * @return
     */
    public native FmodResult update();
    /**
     * FMOD::System::dispose
     * @return
     */
    public native FmodResult dispose();
    /**
     * FMOD::System::setOutput
     * @param output
     * @return
     */
    public native FmodResult setOutput(FmodOutputType output);
    /**
     * FMOD::System::getOutput
     * @param output
     * @return
     */
    public native FmodResult getOutput(Wrapper<FmodOutputType> output);
    /**
     * FMOD::System::getNumDrivers
     * @param numDrivers
     * @return
     */
    public native FmodResult getNumDrivers(Wrapper<Integer> numDrivers);
    /**
     * FMOD::System::getDriverInfo
     * @param id
     * @param name
     * @param guid
     * @param systemRate
     * @param speakerMode
     * @param speakerModeChannels
     * @return
     */
    public native FmodResult getDriverInfo(int id, Wrapper<String> name, Wrapper<String> guid, Wrapper<Integer> systemRate, Wrapper<FmodSpeakerMode> speakerMode, Wrapper<Integer> speakerModeChannels);
    /**
     * FMOD::System::setDriver
     * @param driver
     * @return
     */
    public native FmodResult setDriver(int driver);
    /**
     * FMOD::System::getDriver
     * @param driver
     * @return
     */
    public native FmodResult getDriver(Wrapper<Integer> driver);

    /**
     * FMOD::System::createSound
     * @param nameOrData
     * @param mode
     * @param exInfo
     * @param sound
     * @return
     */
    public native FmodResult createSound(String nameOrData, int mode, FmodCreateSoundExInfo exInfo, FmodSound sound);
    /**
     * FMOD::System::createStream
     * @param nameOrData
     * @param mode
     * @param exInfo
     * @param sound
     * @return
     */
    public native FmodResult createStream(String nameOrData, int mode, FmodCreateSoundExInfo exInfo, FmodSound sound);

    /**
     * FMOD::System::playSound
     * @param sound
     * @param paused
     * @param channel
     * @return
     */
    public native FmodResult playSound(FmodSound sound, boolean paused, FmodChannel channel);

    /**
     * FMOD::System::update
     * @param numDrivers
     * @param numConnected
     * @return
     */
    public native FmodResult getRecordNumDrivers(Wrapper<Integer> numDrivers, Wrapper<Integer> numConnected);
    /**
     * FMOD::System::getRectordDriverInfo
     * @param id
     * @param name
     * @param guid
     * @param systemRate
     * @param speakerMode
     * @param speakerModeChannels
     * @param state
     * @return
     */
    public native FmodResult getRectordDriverInfo(int id, Wrapper<String> name, Wrapper<String> guid, Wrapper<Integer> systemRate, Wrapper<FmodSpeakerMode> speakerMode, Wrapper<Integer> speakerModeChannels, Wrapper<Integer> state);
    /**
     * FMOD::System::getRecordPosition
     * @param id
     * @param position
     * @return
     */
    public native FmodResult getRecordPosition(int id, Wrapper<Integer> position);
    /**
     * FMOD::System::recordStart
     * @param id
     * @param sound
     * @param loop
     * @return
     */
    public native FmodResult recordStart(int id, FmodSound sound, boolean loop);
    /**
     * FMOD::System::recordStop
     * @param id
     * @return
     */
    public native FmodResult recordStop(int id);
    /**
     * FMOD::System::isRecording
     * @param id
     * @param recording
     * @return
     */
    public native FmodResult isRecording(int id, Wrapper<Boolean> recording);
}
