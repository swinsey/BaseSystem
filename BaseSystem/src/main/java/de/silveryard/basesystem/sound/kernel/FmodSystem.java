package de.silveryard.basesystem.sound.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.sound.FmodChannel;
import de.silveryard.basesystem.sound.FmodCreateSoundExInfo;
import de.silveryard.basesystem.sound.*;
import de.silveryard.basesystem.sound.FmodSound;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 06.04.2017.
 */
abstract class FmodSystem {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.setoutput", FmodSystem::systemCallSoundFmodSystemSetOutput);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getoutput", FmodSystem::systemCallSoundFmodSystemGetOutput);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getnumdrivers", FmodSystem::systemCallSoundFmodSystemGetNumDrivers);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getdriverinfo", FmodSystem::systemCallSoundFmodSystemGetDriverInfo);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.setdriver", FmodSystem::systemCallSoundFmodSystemSetDriver);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getdriver", FmodSystem::systemCallSoundFmodSystemGetDriver);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.createsound", FmodSystem::systemCallSoundFmodSystemCreateSound);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.createstream", FmodSystem::systemCallSoundFmodSystemCreateStream);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.playsound", FmodSystem::systemCallSoundFmodSystemPlaySound);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getrecordnumdrivers", FmodSystem::systemCallSoundFmodSystemGetRecordNumDrivers);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getrecorddriverinfo", FmodSystem::systemCallSoundFmodSystemGetRecordDriverInfo);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.getrecordposition", FmodSystem::systemCallSoundFmodSystemGetRecordPosition);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.recordstart", FmodSystem::systemCallSoundFmodSystemRecordStart);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.recordstop", FmodSystem::systemCallSoundFmodSystemRecordStop);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodsystem.isrecording", FmodSystem::systemCallSoundFmodSystemIsRecording);
    }

    private static QAMessage systemCallSoundFmodSystemSetOutput(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        FmodOutputType type = FmodOutputType.getEnumValue(message.getParameters().get(1).getInt());
        FmodResult result = system.setOutput(type);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()));
        }
    }
    private static QAMessage systemCallSoundFmodSystemGetOutput(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        Wrapper<FmodOutputType> type = new Wrapper<>();
        FmodResult result = system.getOutput(type);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()), Parameter.createInt(type.value.getValue()));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()), Parameter.createInt(-1));
        }
    }
    private static QAMessage systemCallSoundFmodSystemGetNumDrivers(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        Wrapper<Integer> numDrivers = new Wrapper<>();
        FmodResult result = system.getNumDrivers(numDrivers);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()), Parameter.createInt(numDrivers.value));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()), Parameter.createInt(-1));
        }
    }
    private static QAMessage systemCallSoundFmodSystemGetDriverInfo(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        int id = message.getParameters().get(0).getInt();
        Wrapper<String> name = new Wrapper<>();
        Wrapper<String> guid = new Wrapper<>();
        Wrapper<Integer> systemRate = new Wrapper<>();
        Wrapper<FmodSpeakerMode> speakerMode = new Wrapper<>();
        Wrapper<Integer> speakerModeChannels = new Wrapper<>();
        FmodResult result = system.getDriverInfo(id, name, guid, systemRate, speakerMode, speakerModeChannels);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()),
                    Parameter.createString(name.value),
                    Parameter.createString(guid.value),
                    Parameter.createInt(systemRate.value),
                    Parameter.createInt(speakerMode.value.getValue()),
                    Parameter.createInt(speakerModeChannels.value));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()),
                    Parameter.createString(""),
                    Parameter.createString(""),
                    Parameter.createInt(-1),
                    Parameter.createInt(-1),
                    Parameter.createInt(-1));
        }
    }
    private static QAMessage systemCallSoundFmodSystemSetDriver(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        int driver = message.getParameters().get(0).getInt();
        FmodResult result = system.setDriver(driver);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()));
        }
    }
    private static QAMessage systemCallSoundFmodSystemGetDriver(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        Wrapper<Integer> driver = new Wrapper<>();
        FmodResult result = system.getDriver(driver);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()), Parameter.createInt(driver.value));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()), Parameter.createInt(-1));
        }
    }

    private static QAMessage systemCallSoundFmodSystemCreateSound(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        String nameOrData = message.getParameters().get(0).getString();
        int mode = message.getParameters().get(1).getInt();
        int infoId = message.getParameters().get(2).getInt();
        int soundId = message.getParameters().get(3).getInt();
        FmodCreateSoundExInfo info = Utils.as(FmodCreateSoundExInfo.class, app.getRegisteredObject(infoId));
        FmodSound sound = Utils.as(FmodSound.class, app.getRegisteredObject(soundId));

        if(infoId != -1 && info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }
        if(sound == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = system.createSound(nameOrData, mode, info, sound);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()));
        }
    }
    private static QAMessage systemCallSoundFmodSystemCreateStream(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        String nameOrData = message.getParameters().get(0).getString();
        int mode = message.getParameters().get(1).getInt();
        int infoId = message.getParameters().get(2).getInt();
        int soundId = message.getParameters().get(3).getInt();
        FmodCreateSoundExInfo info = Utils.as(FmodCreateSoundExInfo.class, app.getRegisteredObject(infoId));
        FmodSound sound = Utils.as(FmodSound.class, app.getRegisteredObject(soundId));

        if(infoId != -1 && info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }
        if(sound == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = system.createStream(nameOrData, mode, info, sound);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()));
        }
    }

    private static QAMessage systemCallSoundFmodSystemPlaySound(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        int soundId = message.getParameters().get(0).getInt();
        boolean paused = message.getParameters().get(1).getBoolean();
        int channelId = message.getParameters().get(2).getInt();
        FmodSound sound = Utils.as(FmodSound.class, app.getRegisteredObject(soundId));
        FmodChannel channel = Utils.as(FmodChannel.class, app.getRegisteredObject(channelId));

        if(sound == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }
        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = system.playSound(sound, paused, channel);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()));
        }
    }

    private static QAMessage systemCallSoundFmodSystemGetRecordNumDrivers(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        Wrapper<Integer> numDrivers = new Wrapper<>();
        Wrapper<Integer> numConnected = new Wrapper<>();
        FmodResult result = system.getRecordNumDrivers(numDrivers, numConnected);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()),
                    Parameter.createInt(numDrivers.value), Parameter.createInt(numConnected.value));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()),
                    Parameter.createInt(-1), Parameter.createInt(-1));
        }
    }
    private static QAMessage systemCallSoundFmodSystemGetRecordDriverInfo(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        int id = message.getParameters().get(0).getInt();
        Wrapper<String> name = new Wrapper<>();
        Wrapper<String> guid = new Wrapper<>();
        Wrapper<Integer> systemRate = new Wrapper<>();
        Wrapper<FmodSpeakerMode> speakerMode = new Wrapper<>();
        Wrapper<Integer> speakerModeChannels = new Wrapper<>();
        Wrapper<Integer> state = new Wrapper<>();
        FmodResult result = system.getRectordDriverInfo(id, name, guid, systemRate, speakerMode, speakerModeChannels, state);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()),
                    Parameter.createString(name.value),
                    Parameter.createString(guid.value),
                    Parameter.createInt(systemRate.value),
                    Parameter.createInt(speakerMode.value.getValue()),
                    Parameter.createInt(speakerModeChannels.value),
                    Parameter.createInt(state.value));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()),
                    Parameter.createString(""),
                    Parameter.createString(""),
                    Parameter.createInt(-1),
                    Parameter.createInt(-1),
                    Parameter.createInt(-1),
                    Parameter.createInt(-1));
        }
    }
    private static QAMessage systemCallSoundFmodSystemGetRecordPosition(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        int id = message.getParameters().get(0).getInt();
        Wrapper<Integer> position = new Wrapper<>();
        FmodResult result = system.getRecordPosition(id, position);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()),
                    Parameter.createInt(position.value));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()),
                    Parameter.createInt(-1));
        }
    }
    private static QAMessage systemCallSoundFmodSystemRecordStart(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        int id = message.getParameters().get(0).getInt();
        int soundId = message.getParameters().get(1).getInt();
        boolean loop = message.getParameters().get(2).getBoolean();
        FmodSound sound = Utils.as(FmodSound.class, app.getRegisteredObject(soundId));

        FmodResult result = system.recordStart(id, sound, loop);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()));
        }
    }
    private static QAMessage systemCallSoundFmodSystemRecordStop(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        int id = message.getParameters().get(0).getInt();

        FmodResult result = system.recordStop(id);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()));
        }
    }
    private static QAMessage systemCallSoundFmodSystemIsRecording(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSystem system = de.silveryard.basesystem.sound.FmodSystem.getInstance();
        int id = message.getParameters().get(0).getInt();

        Wrapper<Boolean> recording = new Wrapper<>();
        FmodResult result = system.isRecording(id, recording);

        if(result == FmodResult.FMOD_OK) {
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                    Parameter.createInt(result.getValue()), Parameter.createBoolean(recording.value));
        }else{
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.UNKNOWN_ERROR.getValue(),
                    Parameter.createInt(result.getValue()), Parameter.createBoolean(false));
        }
    }
}