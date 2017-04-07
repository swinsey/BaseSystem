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
    public static void systemCallSoundFmodChannelCreate(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.create", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    public static void systemCallSoundFmodChannelStop(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.stop", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    public static void systemCallSoundFmodChannelSetPaused(
            int soundID, boolean paused,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createBoolean(paused));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.setpaused", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    public static void systemCallSoundFmodChannelGetPaused(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Boolean> outPaused
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.getpaused", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outPaused.value = response.getParameters().get(3).getBoolean();
    }
    public static void systemCallSoundFmodChannelSetVolume(
            int soundID, float volume,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createFloat(volume));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.setvolume", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    public static void systemCallSoundFmodChannelGetVolume(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Float> outVolume
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.getvolume", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outVolume.value = response.getParameters().get(3).getFloat();
    }
    public static void systemCallSoundFmodChannelSetVolumeRamp(
            int soundID, boolean ramp,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createBoolean(ramp));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.setvolumeramp", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    public static void systemCallSoundFmodChannelGetVolumeRamp(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Boolean> outRamp
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.getvolumeramp", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outRamp.value = response.getParameters().get(3).getBoolean();
    }
    public static void systemCallSoundFmodChannelGetAudibility(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Float> outAudibility
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.getaudibility", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outAudibility.value = response.getParameters().get(3).getFloat();
    }
    public static void systemCallSoundFmodChannelSetPitch(
            int soundID, float pitch,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createFloat(pitch));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.setpitch", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    public static void systemCallSoundFmodChannelGetPitch(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Float> outPitch
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.getpitch", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outPitch.value = response.getParameters().get(3).getFloat();
    }
    public static void systemCallSoundFmodChannelSetMute(
            int soundID, boolean mute,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createBoolean(mute));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.setmute", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    public static void systemCallSoundFmodChannelGetMute(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Boolean> outMute
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.getmute", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outMute.value = response.getParameters().get(3).getBoolean();
    }

    public static void systemCallSoundFmodChannelIsPlaying(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Boolean> outIsPlaying
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.isplaying", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outIsPlaying.value = response.getParameters().get(3).getBoolean();
    }

    public static void systemCallSoundFmodChannelSetFrequency(
            int soundID, float frequency,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createFloat(frequency));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.setfrequency", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    public static void systemCallSoundFmodChannelGetFrequency(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Float> outFrequency
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.getfrequency", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outFrequency.value = response.getParameters().get(3).getFloat();
    }
    public static void systemCallSoundFmodChannelSetPriority(
            int soundID, int priority,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createInt(priority));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.setpriority", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    public static void systemCallSoundFmodChannelGetPriority(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Integer> outPriority
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.getpriority", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outPriority.value = response.getParameters().get(3).getInt();
    }
    public static void systemCallSoundFmodChannelSetPosition(
            int soundID, int position,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createInt(position));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.setposition", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    public static void systemCallSoundFmodChannelGetPosition(
            int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Integer> outPosition
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodchannel.getposition", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outPosition.value = response.getParameters().get(3).getInt();
    }
}
