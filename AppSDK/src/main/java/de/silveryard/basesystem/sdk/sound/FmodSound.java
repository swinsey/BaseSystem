package de.silveryard.basesystem.sdk.sound;

import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.SoundKernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.sound.FmodResult;
import de.silveryard.basesystem.sdk.kernel.sound.SoundReturnCode;

import static de.silveryard.basesystem.sdk.kernel.sound.FmodSound.systemCallSoundFmodSoundCreate;
import static de.silveryard.basesystem.sdk.kernel.sound.FmodSound.systemCallSoundFmodSoundGetLength;

/**
 * Created by Sebif on 15.04.2017.
 */
public class FmodSound {
    private final Wrapper<ReturnCode> returnCodeWrapper;
    private final Wrapper<SoundReturnCode> soundReturnCodeWrapper;
    private final Wrapper<Integer> integerWrapper;
    private final Wrapper<FmodResult> fmodResultWrapper;

    private final int id;

    /**
     * Constructor
     */
    public FmodSound(){
        returnCodeWrapper = new Wrapper<>();
        soundReturnCodeWrapper = new Wrapper<>();
        integerWrapper = new Wrapper<>();
        fmodResultWrapper = new Wrapper<>();

        systemCallSoundFmodSoundCreate(returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

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
     * FMOD::Sound::getLength
     * @param length
     * @return
     */
    public synchronized FmodResult getLength(Wrapper<Integer> length, int lengthType){
        systemCallSoundFmodSoundGetLength(id, lengthType, returnCodeWrapper, soundReturnCodeWrapper, fmodResultWrapper, length);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodResultWrapper.value;
    }
}
