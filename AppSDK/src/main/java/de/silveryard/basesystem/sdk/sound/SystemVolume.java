package de.silveryard.basesystem.sdk.sound;

import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.SoundKernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;
import de.silveryard.basesystem.sdk.kernel.sound.SoundReturnCode;
import de.silveryard.basesystem.sdk.kernel.sound.SystemVolumeChannelType;

import static de.silveryard.basesystem.sdk.kernel.sound.SystemVolume.*;

/**
 * Created by Sebif on 13.04.2017.
 */
public abstract class SystemVolume {
    private static final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private static final Wrapper<SoundReturnCode> soundReturnCodeWrapper = new Wrapper<>();
    private static final Wrapper<Integer> integerWrapper = new Wrapper<>();
    private static final Wrapper<Boolean> booleanWrapper = new Wrapper<>();
    private static final Wrapper<Float> floatWrapper = new Wrapper<>();
    private static final Wrapper<SystemVolumeChannelType> channelTypeWrapper = new Wrapper<>();

    /**
     * Fetches the mute flag of the systems output driver
     * @return Mute flag value
     */
    public static boolean getMute(){
        systemCallSoundSystemVolumeGetMute(returnCodeWrapper, soundReturnCodeWrapper, booleanWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    /**
     * Sets the mute flag of the systems output driver
     * @param mute Mute flag value
     */
    public static void setMute(boolean mute){
        systemCallSoundSystemVolumeSetMute(mute, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Fetches the master volume of the systems output driver
     * @return Master volume value
     */
    public static float getMasterVolume(){
        systemCallSoundSystemVolumeGetMasterVolume(returnCodeWrapper, soundReturnCodeWrapper, floatWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return floatWrapper.value;
    }
    /**
     * Sets the master volume of the systems output driver
     * @param volume Master volume value
     */
    public static void setMasterVolume(float volume){
        systemCallSoundSystemVolumeSetMasterVolume(volume, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Fetches the number of channels of the systems output driver
     * @return Number of channels
     */
    public static int getNumOutputChannels(){
        systemCallSoundSystemVolumeGetNumOutputChannels(returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Fetches the type of a given channel of the systems output driver
     * @param index Channel index
     * @return Channel type
     */
    public static SystemVolumeChannelType getOutputChannelType(int index){
        systemCallSoundSystemVolumeGetOutputChannelType(index, returnCodeWrapper, soundReturnCodeWrapper, channelTypeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return channelTypeWrapper.value;
    }
    /**
     * Fetches the volume of a given channel of the systems output driver
     * @param index Channel index
     * @return Volume value
     */
    public static float getOutputChannelVolume(int index){
        systemCallSoundSystemVolumeGetOutputChannelVolume(index, returnCodeWrapper, soundReturnCodeWrapper, floatWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return floatWrapper.value;
    }
    /**
     * Sets the volume of a given channel of the systems output driver
     * @param index Channel index
     * @param volume Volume value
     */
    public static void setOutputChannelVolume(int index, float volume){
        systemCallSoundSystemVolumeSetOutputChannelVolume(index, volume, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
}
