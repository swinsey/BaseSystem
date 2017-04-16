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
public abstract class FmodSystem {
    /**
     * Sets the output driver type
     * @param outputType Output type
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodSystemSetOutput(
            FmodOutputType outputType,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(outputType.getValue()));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.setoutput", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the output driver type
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outOutputType Output type
     */
    public static void systemCallSoundFmodSystemGetOutput(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<FmodOutputType> outOutputType
    ){

        List<Parameter> params = new ArrayList<>();
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getoutput", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outOutputType.value = FmodOutputType.getEnumValue(response.getParameters().get(3).getInt());
    }
    /**
     * Fetches the number of output drivers
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outNumDrivers Number of output drivers
     */
    public static void systemCallSoundFmodSystemGetNumDrivers(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Integer> outNumDrivers
    ){

        List<Parameter> params = new ArrayList<>();
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getnumdrivers", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outNumDrivers.value = response.getParameters().get(3).getInt();
    }
    /**
     * Fetches information about a given output driver
     * @param id Output driver id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outName Drivers name
     * @param outGuid Drivers guid
     * @param outSystemrate Drivers output rate
     * @param outSpeakerMode Drivers speaker mode
     * @param outSpeakerModeChannels Number of channels of the drivers speaker mode
     */
    public static void systemCallSoundFmodSystemGetDriverInfo(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<String> outName, Wrapper<String> outGuid, Wrapper<Integer> outSystemrate,
            Wrapper<FmodSpeakerMode> outSpeakerMode, Wrapper<Integer> outSpeakerModeChannels
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getdriverinfo", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outName.value = response.getParameters().get(3).getString();
        outGuid.value = response.getParameters().get(4).getString();
        outSystemrate.value = response.getParameters().get(5).getInt();
        outSpeakerMode.value = FmodSpeakerMode.getEnumValue(response.getParameters().get(6).getInt());
        outSpeakerModeChannels.value = response.getParameters().get(7).getInt();
    }
    /**
     * Sets the output driver
     * @param driver Driver id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodSystemSetDriver(
            int driver,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(driver));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.setdriver", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches the output driver
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outDriver Output driver id
     */
    public static void systemCallSoundFmodSystemGetDriver(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Integer> outDriver
    ){

        List<Parameter> params = new ArrayList<>();
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getdriver", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outDriver.value = response.getParameters().get(3).getInt();
    }

    /**
     * Creates a new sound
     * @param nameOrData Sound name or data
     * @param mode Sound mode
     * @param fmodCreateSoundExInfoID ID of a FmodCreateSoundExInfo object. Optional. Pass -1 to use no info
     * @param soundID ID of a FmodSound object
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodSystemCreateSound(
            String nameOrData, int mode, int fmodCreateSoundExInfoID, int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){
        if(nameOrData == null){
            nameOrData = "";
        }

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createString(nameOrData));
        params.add(Parameter.createInt(mode));
        params.add(Parameter.createInt(fmodCreateSoundExInfoID));
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.createsound", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Creates a new sound as stream
     * @param nameOrData Sound name or data
     * @param mode Sound mode
     * @param fmodCreateSoundExInfoID ID of a FmodCreateSoundExInfo object. Optional. Pass -1 to use no info
     * @param soundID ID of a FmodSound object
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return COde
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodSystemCreateStream(
            String nameOrData, int mode, int fmodCreateSoundExInfoID, int soundID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){
        if(nameOrData == null){
            nameOrData = "";
        }

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createString(nameOrData));
        params.add(Parameter.createInt(mode));
        params.add(Parameter.createInt(fmodCreateSoundExInfoID));
        params.add(Parameter.createInt(soundID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.createstream", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }

    /**
     * Plays a given sound
     * @param soundID ID of the sound to play
     * @param paused Flag if the created channel should be paused
     * @param channelID ID of a FmodChannel object
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodSystemPlaySound(
            int soundID, boolean paused, int channelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createBoolean(paused));
        params.add(Parameter.createInt(channelID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.playsound", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }

    /**
     * Fetches the number of record drivers
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outNumDrivers Number of record drivers
     * @param outNumConnected Number of record drivers currently connected
     */
    public static void systemCallSoundFmodSystemGetRecordNumDrivers(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Integer> outNumDrivers, Wrapper<Integer> outNumConnected
    ){

        List<Parameter> params = new ArrayList<>();
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getrecordnumdrivers", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outNumDrivers.value = response.getParameters().get(3).getInt();
        outNumConnected.value = response.getParameters().get(4).getInt();
    }
    /**
     * Fetches information about a given record driver
     * @param id Record driver id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outName Drivers name
     * @param outGuid Drivers guid
     * @param outSystemRate Drivers record rate
     * @param outSpeakerMode Drivers speaker mode
     * @param outSpeakerModeChannels Number of channels of the drivers speaker mode
     * @param outState Drivers current state
     */
    public static void systemCallSoundFmodSystemGetRecordDriverInfo(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<String> outName, Wrapper<String> outGuid, Wrapper<Integer> outSystemRate,
            Wrapper<FmodSpeakerMode> outSpeakerMode, Wrapper<Integer> outSpeakerModeChannels, Wrapper<Integer> outState
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getrecorddriverinfo", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outName.value = response.getParameters().get(3).getString();
        outGuid.value = response.getParameters().get(4).getString();
        outSystemRate.value = response.getParameters().get(5).getInt();
        outSpeakerMode.value = FmodSpeakerMode.getEnumValue(response.getParameters().get(6).getInt());
        outSpeakerModeChannels.value = response.getParameters().get(7).getInt();
        outState.value = response.getParameters().get(8).getInt();
    }
    /**
     * Fetches a record drivers record position
     * @param id Record driver id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outPosition Position value
     */
    public static void systemCallSoundFmodSystemGetRecordPosition(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Integer> outPosition
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getrecordposition", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outPosition.value = response.getParameters().get(3).getInt();
    }
    /**
     * Starts the recording of a given record driver
     * @param id Record driver id
     * @param soundID ID of a FmodSound object
     * @param loop Flag if recording should loop
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodSystemRecordStart(
            int id, int soundID, boolean loop,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(soundID));
        params.add(Parameter.createBoolean(loop));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.recordstart", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Stops the recording of a given record driver
     * @param id Record driver id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     */
    public static void systemCallSoundFmodSystemRecordStop(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.recordstop", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Fetches if a given record driver is currently recording
     * @param id Record driver id
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outIsPlaying 'isPlaying' flag
     */
    public static void systemCallSoundFmodSystemIsRecording(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode, Wrapper<FmodResult> outFmodResult,
            Wrapper<Boolean> outIsPlaying
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.isrecording", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(response.getParameters().get(2).getInt());
        outIsPlaying.value = response.getParameters().get(3).getBoolean();
    }
}
