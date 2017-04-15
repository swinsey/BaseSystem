package de.silveryard.basesystem.sdk.sound;

import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.SoundKernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.sound.FmodOutputType;
import de.silveryard.basesystem.sdk.kernel.sound.FmodResult;
import de.silveryard.basesystem.sdk.kernel.sound.FmodSpeakerMode;
import de.silveryard.basesystem.sdk.kernel.sound.SoundReturnCode;

import static de.silveryard.basesystem.sdk.kernel.sound.FmodSystem.*;

/**
 * Created by Sebif on 15.04.2017.
 */
public abstract class FmodSystem {
    private static final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private static final Wrapper<SoundReturnCode> soundReturnCodeWrapper = new Wrapper<>();
    private static final Wrapper<FmodResult> fmodResultWrapper = new Wrapper<>();

    /**
     * FMOD::System::setOutput
     * @param outputType
     * @return
     */
    public static synchronized FmodResult setOutput(FmodOutputType outputType){
        systemCallSoundFmodSystemSetOutput(outputType, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::getOutput
     * @param outputType
     * @return
     */
    public static synchronized FmodResult getOutput(Wrapper<FmodOutputType> outputType){
        systemCallSoundFmodSystemGetOutput(returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, outputType);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::getNumDrivers
     * @param numDrivers
     * @return
     */
    public static synchronized FmodResult getNumDrivers(Wrapper<Integer> numDrivers){
        systemCallSoundFmodSystemGetNumDrivers(returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, numDrivers);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::getDriverInfo
     * @param driver
     * @param name
     * @param guid
     * @param systemRate
     * @param speakerMode
     * @param speakerModeChannels
     * @return
     */
    public static synchronized FmodResult getDriverInfo(
            int driver,
            Wrapper<String> name, Wrapper<String> guid, Wrapper<Integer> systemRate,
            Wrapper<FmodSpeakerMode> speakerMode, Wrapper<Integer> speakerModeChannels
    ){
        systemCallSoundFmodSystemGetDriverInfo(driver, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, name, guid, systemRate, speakerMode, speakerModeChannels);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::setDriver
     * @param driver
     * @return
     */
    public static synchronized FmodResult setDriver(int driver){
        systemCallSoundFmodSystemSetDriver(driver, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::getDriver
     * @param driver
     * @return
     */
    public static synchronized FmodResult getDriver(Wrapper<Integer> driver){
        systemCallSoundFmodSystemGetDriver(returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, driver);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }

    /**
     * FMOD::System::createSound
     * @param nameOrData
     * @param mode
     * @param exInfo
     * @param sound
     * @return
     */
    public static synchronized FmodResult createSound(
            String nameOrData, int mode, 
            FmodCreateSoundExInfo exInfo, FmodSound sound
    ){
        systemCallSoundFmodSystemCreateSound(nameOrData, mode, exInfo == null ? -1 : exInfo.getId(), sound.getId(), returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::createStream
     * @param nameOrData
     * @param mode
     * @param exInfo
     * @param sound
     * @return
     */
    public static synchronized FmodResult createStream(
            String nameOrData, int mode,
            FmodCreateSoundExInfo exInfo, FmodSound sound
    ){
        systemCallSoundFmodSystemCreateStream(nameOrData, mode, exInfo == null ? - 1 : exInfo.getId(), sound.getId(), returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }

    /**
     * FMOD::System::playSound
     * @param sound
     * @param paused
     * @param channel
     * @return
     */
    public static synchronized FmodResult playSound(FmodSound sound, boolean paused, FmodChannel channel){
        systemCallSoundFmodSystemPlaySound(sound.getId(), paused, channel.getId(), returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }

    /**
     * FMOD::System::getRecordNumDrivers
     * @param numDrivers
     * @param numConnected
     * @return
     */
    public static synchronized FmodResult getRecordNumDrivers(Wrapper<Integer> numDrivers, Wrapper<Integer> numConnected){
        systemCallSoundFmodSystemGetRecordNumDrivers(returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, numDrivers, numConnected);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::getRecordDriverInfo
     * @param driver
     * @param name
     * @param guid
     * @param systemRate
     * @param speakerMode
     * @param speakerModeChannels
     * @param state
     * @return
     */
    public static synchronized FmodResult getRecordDriverInfo(
            int driver,
            Wrapper<String> name, Wrapper<String> guid, Wrapper<Integer> systemRate,
            Wrapper<FmodSpeakerMode> speakerMode, Wrapper<Integer> speakerModeChannels, Wrapper<Integer> state
    ){
        systemCallSoundFmodSystemGetRecordDriverInfo(driver, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, name, guid, systemRate, speakerMode, speakerModeChannels, state);
        
        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::getRecordPosition
     * @param driver
     * @param position
     * @return
     */
    public static synchronized FmodResult getRecordPosition(int driver, Wrapper<Integer> position){
        systemCallSoundFmodSystemGetRecordPosition(driver, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, position);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::recordStart
     * @param driver
     * @param sound
     * @param loop
     * @return
     */
    public static synchronized FmodResult recordStart(int driver, FmodSound sound, boolean loop){
        systemCallSoundFmodSystemRecordStart(driver, sound.getId(), loop, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::recordStop
     * @param driver
     * @return
     */
    public static synchronized FmodResult recordStop(int driver){
        systemCallSoundFmodSystemRecordStop(driver, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::System::isRecording
     * @param driver
     * @param isRecording
     * @return
     */
    public static synchronized FmodResult isRecording(int driver, Wrapper<Boolean> isRecording){
        systemCallSoundFmodSystemIsRecording(driver, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, isRecording);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
}
