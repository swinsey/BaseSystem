package de.silveryard.basesystem.sdk.kernel.sound;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 07.04.2017.
 */
public abstract class FmodChannel {
    /**
     * Creates a new FmodChannel object
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outChannelID Created Channel ID
     */
    public static void systemCallSoundFmodChannelCreate(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outChannelID
    ){

        List<Parameter> params = new ArrayList<>();
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.create", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outChannelID.value = response.getParameters().get(2).getInt();
    }

    /**
     * Stops the given channel playing
     * @param channelID The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodChannelStop(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.stop", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Sets the paused flag of a given channel
     * @param channelID The given channels id
     * @param paused Value of the paused flag
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodChannelSetPaused(
            int channelID, boolean paused,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createBoolean(paused));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setpaused", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the paused flag of a given channel
     * @param channelID The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outPaused Value of the paused flag
     */
    public static void systemCallSoundFmodChannelGetPaused(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Boolean> outPaused
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getpaused", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outPaused.value = response.getParameters().get(3).getBoolean();
    }
    /**
     * Sets the volume of a given channel
     * @param channelID The given channels id
     * @param volume Volume value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodChannelSetVolume(
            int channelID, float volume,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createFloat(volume));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setvolume", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the volume of a given channel
     * @param channelID The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outVolume Volume value
     */
    public static void systemCallSoundFmodChannelGetVolume(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Float> outVolume
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getvolume", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outVolume.value = response.getParameters().get(3).getFloat();
    }
    /**
     * Sets the volume ramp flag of a given channel
     * @param channelID The given channels id
     * @param ramp Volume ramp flag value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodChannelSetVolumeRamp(
            int channelID, boolean ramp,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createBoolean(ramp));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setvolumeramp", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the volume ramp flag of a given channel
     * @param channelID The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outRamp Volume ramp flag value
     */
    public static void systemCallSoundFmodChannelGetVolumeRamp(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Boolean> outRamp
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getvolumeramp", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outRamp.value = response.getParameters().get(3).getBoolean();
    }
    /**
     * Fetches the audibility value of a given channel
     * @param channelID The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outAudibility Audibility value
     */
    public static void systemCallSoundFmodChannelGetAudibility(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Float> outAudibility
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getaudibility", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outAudibility.value = response.getParameters().get(3).getFloat();
    }
    /**
     * Sets the pitch value of a given channel
     * @param channelID The given channels id
     * @param pitch Pitch value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodChannelSetPitch(
            int channelID, float pitch,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createFloat(pitch));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setpitch", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the pitch value of a given channel
     * @param channelID The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outPitch Pitch value
     */
    public static void systemCallSoundFmodChannelGetPitch(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Float> outPitch
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getpitch", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outPitch.value = response.getParameters().get(3).getFloat();
    }
    /**
     * Sets the mute flag of a given channel
     * @param channelID The given channels id
     * @param mute Mute flag value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodChannelSetMute(
            int channelID, boolean mute,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createBoolean(mute));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setmute", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the mute flag of a given channel
     * @param channelID The given channel id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outMute Mute flag value
     */
    public static void systemCallSoundFmodChannelGetMute(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Boolean> outMute
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getmute", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outMute.value = response.getParameters().get(3).getBoolean();
    }

    /**
     * Fetches the 'isPlaying' flag of a given channel
     * @param channelID The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outIsPlaying Value of the 'isPlaying' flag
     */
    public static void systemCallSoundFmodChannelIsPlaying(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Boolean> outIsPlaying
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.isplaying", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outIsPlaying.value = response.getParameters().get(3).getBoolean();
    }

    /**
     * Sets the frequency value of a given channel
     * @param channelID The given channels id
     * @param frequency Frequency value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodChannelSetFrequency(
            int channelID, float frequency,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createFloat(frequency));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setfrequency", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the frequency value of a given channel
     * @param channelID The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outFrequency Frequency value
     */
    public static void systemCallSoundFmodChannelGetFrequency(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Float> outFrequency
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getfrequency", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outFrequency.value = response.getParameters().get(3).getFloat();
    }
    /**
     * Sets the priority value of a given channel
     * @param channelID The given channels id
     * @param priority Priority value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodChannelSetPriority(
            int channelID, int priority,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createInt(priority));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setpriority", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the priority value of a given channel
     * @param channelID The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outPriority Priority value
     */
    public static void systemCallSoundFmodChannelGetPriority(
            int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Integer> outPriority
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getpriority", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outPriority.value = response.getParameters().get(3).getInt();
    }
    /**
     * Sets the position value of a given channel
     * @param channelID The given channels id
     * @param position Position value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodChannelSetPosition(
            int channelID, int position,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createInt(position));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setposition", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the position value of a given channel
     * @param channelID  The given channels id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outPosition Position value
     */
    public static void systemCallSoundFmodChannelGetPosition(
            int channelID, int posType,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Integer> outPosition
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createInt(posType));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getposition", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outPosition.value = response.getParameters().get(3).getInt();
    }
}
