package de.silveryard.basesystem.sdk.kernel.sound;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 13.04.2017.
 */
public class SystemVolume {
    /**
     * Fetches the mute flag of the systems output driver
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outMute Mute flag value
     */
    public static void systemCallSoundSystemVolumeGetMute(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, 
            Wrapper<Boolean> outMute
    ){
        
        List<Parameter> params = new ArrayList<>();
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getmute", params);
        
        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outMute.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Sets the mute flag of the systems output driver
     * @param mute Mute flag value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundSystemVolumeSetMute(
            boolean mute, 
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
                
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createBoolean(mute));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.setmute", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Fetches the master volume of the systems output driver
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outVolume Master volume value
     */
    public static void systemCallSoundSystemVolumeGetMasterVolume(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, 
            Wrapper<Float> outVolume
    ){

        List<Parameter> params = new ArrayList<>();
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getmastervolume", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outVolume.value = response.getParameters().get(2).getFloat();
    }
    /**
     * Sets the master volume of the systems output driver
     * @param volume Master volume value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundSystemVolumeSetMasterVolume(
            float volume, Wrapper<ReturnCode> outReturnCode, 
            Wrapper<SoundReturnCode> outSoundReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createFloat(volume));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.setmastervolume", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Fetches the number of channels of the systems output driver
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outNumChannels Number of channels
     */
    public static void systemCallSoundSystemVolumeGetNumOutputChannels(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, 
            Wrapper<Integer> outNumChannels
    ){

        List<Parameter> params = new ArrayList<>();
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getnumoutputchannels", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outNumChannels.value = response.getParameters().get(2).getInt();
    }
    /**
     * Fetches the type of a given channel of the systems output driver
     * @param index Channel index
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outType Channel type
     */
    public static void systemCallSoundSystemVolumeGetOutputChannelType(
            int index, 
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, 
            Wrapper<SystemVolumeChannelType> outType
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(index));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getoutputchanneltype", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outType.value = SystemVolumeChannelType.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the volume of a given channel of the systems output driver
     * @param index Channel index
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outVolume Volume value
     */
    public static void systemCallSoundSystemVolumeGetOutputChannelVolume(
            int index, 
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, 
            Wrapper<Float> outVolume
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(index));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getoutputchannelvolume", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outVolume.value = response.getParameters().get(2).getFloat();
    }
    /**
     * Sets the volume of a given channel of the systems output driver
     * @param index Channel index
     * @param volume Volume value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundSystemVolumeSetOutputChannelVolume(
            int index, float volume, 
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(index));
        params.add(Parameter.createFloat(volume));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.setoutputchannelvolume", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
