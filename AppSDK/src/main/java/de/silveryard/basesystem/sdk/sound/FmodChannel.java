package de.silveryard.basesystem.sdk.sound;

import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.SoundKernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.sound.FmodResult;
import de.silveryard.basesystem.sdk.kernel.sound.SoundReturnCode;

import static de.silveryard.basesystem.sdk.kernel.sound.FmodChannel.*;

/**
 * Created by Sebif on 15.04.2017.
 */
public class FmodChannel {
    private final Wrapper<ReturnCode> returnCodeWrapper;
    private final Wrapper<SoundReturnCode> soundReturnCodeWrapper;
    private final Wrapper<Integer> integerWrapper;
    private final Wrapper<FmodResult> fmodResultWrapper;

    private final int id;

    /**
     * Constructor
     */
    public FmodChannel(){
        returnCodeWrapper = new Wrapper<>();
        soundReturnCodeWrapper = new Wrapper<>();
        integerWrapper = new Wrapper<>();
        fmodResultWrapper = new Wrapper<>();

        systemCallSoundFmodChannelCreate(returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        id = integerWrapper.value;
    }

    /**
     * Returns the internal id
     * @return internal identifier
     */
    public int getId(){
        return id;
    }

    /**
     * FMOD::Channel::stop
     * @return
     */
    public synchronized FmodResult stop(){
        systemCallSoundFmodChannelStop(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::setPaused
     * @param paused
     * @return
     */
    public synchronized FmodResult setPaused(boolean paused){
        systemCallSoundFmodChannelSetPaused(id, paused, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::getPaused
     * @param paused
     * @return
     */
    public synchronized FmodResult getPaused(Wrapper<Boolean> paused){
        systemCallSoundFmodChannelGetPaused(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, paused);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::setVolume
     * @param volume
     * @return
     */
    public synchronized FmodResult setVolume(float volume){
        systemCallSoundFmodChannelSetVolume(id, volume, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::getVolume
     * @param volume
     * @return
     */
    public synchronized FmodResult getVolume(Wrapper<Float> volume){
        systemCallSoundFmodChannelGetVolume(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, volume);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::setVolumeRamp
     * @param ramp
     * @return
     */
    public synchronized FmodResult setVolumeRamp(boolean ramp){
        systemCallSoundFmodChannelSetVolumeRamp(id, ramp, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::getVolumeRamp
     * @param ramp
     * @return
     */
    public synchronized FmodResult getVolumeRamp(Wrapper<Boolean> ramp){
        systemCallSoundFmodChannelGetVolumeRamp(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, ramp);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::getAudibility
     * @param audibility
     * @return
     */
    public synchronized FmodResult getAudibility(Wrapper<Float> audibility){
        systemCallSoundFmodChannelGetAudibility(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, audibility);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::setPitch
     * @param pitch
     * @return
     */
    public synchronized FmodResult setPitch(float pitch){
        systemCallSoundFmodChannelSetPitch(id, pitch, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::getPitch
     * @param pitch
     * @return
     */
    public synchronized FmodResult getPitch(Wrapper<Float> pitch){
        systemCallSoundFmodChannelGetPitch(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, pitch);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::setMute
     * @param mute
     * @return
     */
    public synchronized FmodResult setMute(boolean mute){
        systemCallSoundFmodChannelSetMute(id, mute, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::getMute
     * @param mute
     * @return
     */
    public synchronized FmodResult getMute(Wrapper<Boolean> mute){
        systemCallSoundFmodChannelGetMute(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, mute);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }

    /**
     * FMOD::Channel::isPlaying
     * @param isPlaying
     * @return
     */
    public synchronized FmodResult isPlaying(Wrapper<Boolean> isPlaying){
        systemCallSoundFmodChannelIsPlaying(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, isPlaying);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }

    /**
     * FMOD::Channel::setFrequency
     * @param frequency
     * @return
     */
    public synchronized FmodResult setFrequency(float frequency){
        systemCallSoundFmodChannelSetFrequency(id, frequency, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::getFrequency
     * @param frequency
     * @return
     */
    public synchronized FmodResult getFrequency(Wrapper<Float> frequency){
        systemCallSoundFmodChannelGetFrequency(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, frequency);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::setPriority
     * @param priority
     * @return
     */
    public synchronized FmodResult setPriority(int priority){
        systemCallSoundFmodChannelSetPriority(id, priority, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::getPriority
     * @param priority
     * @return
     */
    public synchronized FmodResult getPriority(Wrapper<Integer> priority){
        systemCallSoundFmodChannelGetPriority(id, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, priority);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::setPosition
     * @param position
     * @return
     */
    public synchronized FmodResult setPosition(int position){
        systemCallSoundFmodChannelSetPosition(id, position, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
    /**
     * FMOD::Channel::getPosition
     * @param position
     * @return
     */
    public synchronized FmodResult getPosition(Wrapper<Integer> position, int posType){
        systemCallSoundFmodChannelGetPosition(id, posType, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, position);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
}
