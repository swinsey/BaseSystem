package de.silveryard.basesystem.sound.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 13.04.2017.
 */
abstract class SystemVolume {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getmute", SystemVolume::systemCallSoundSystemVolumeGetMute);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.setmute", SystemVolume::systemCallSoundSystemVolumeSetMute);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getmastervolume", SystemVolume::systemCallSoundSystemVolumeGetMasterVolume);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.setmastervolume", SystemVolume::systemCallSoundSystemVolumeSetMasterVolume);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getnumoutputchannels", SystemVolume::systemCallSoundSystemVolumeGetNumOutputChannels);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getoutputchanneltype", SystemVolume::systemCallSoundSystemVolumeGetOutputChannelType);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.getoutputchannelvolume", SystemVolume::systemCallSoundSystemVolumeGetOutputChannelVolume);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.systemvolume.setoutputchannelvolume", SystemVolume::systemCallSoundSystemVolumeSetOutputChannelVolume);
    }

    private static QAMessage systemCallSoundSystemVolumeGetMute(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createBoolean(de.silveryard.basesystem.sound.SystemVolume.getInstance().getMute())
        );
    }
    private static QAMessage systemCallSoundSystemVolumeSetMute(RunningApp app, QAMessage message){
        final boolean mute = message.getParameters().get(0).getBoolean();
        de.silveryard.basesystem.sound.SystemVolume.getInstance().setMute(mute);
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue()
        );
    }

    private static QAMessage systemCallSoundSystemVolumeGetMasterVolume(RunningApp app, QAMessage message){
       return Kernel.getInstance().createResponse(
               message,
               ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
               Parameter.createFloat(de.silveryard.basesystem.sound.SystemVolume.getInstance().getMasterVolume())
       );
    }
    private static QAMessage systemCallSoundSystemVolumeSetMasterVolume(RunningApp app, QAMessage message){
        final float volume = message.getParameters().get(0).getFloat();
        de.silveryard.basesystem.sound.SystemVolume.getInstance().setMasterVolume(volume);
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue()
        );
    }

    private static QAMessage systemCallSoundSystemVolumeGetNumOutputChannels(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(de.silveryard.basesystem.sound.SystemVolume.getInstance().getNumOutputChannels())
        );
    }
    private static QAMessage systemCallSoundSystemVolumeGetOutputChannelType(RunningApp app, QAMessage message){
        final int index = message.getParameters().get(0).getInt();
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(de.silveryard.basesystem.sound.SystemVolume.getInstance().getOutputChannelType(index).getValue())
        );
    }
    private static QAMessage systemCallSoundSystemVolumeGetOutputChannelVolume(RunningApp app, QAMessage message){
        final int index = message.getParameters().get(0).getInt();
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createFloat(de.silveryard.basesystem.sound.SystemVolume.getInstance().getOutputChannelVolume(index))
        );
    }
    private static QAMessage systemCallSoundSystemVolumeSetOutputChannelVolume(RunningApp app, QAMessage message){
        final int index = message.getParameters().get(0).getInt();
        final float volume = message.getParameters().get(1).getFloat();
        de.silveryard.basesystem.sound.SystemVolume.getInstance().setOutputChannelVolume(index, volume);
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue()
        );
    }
}
