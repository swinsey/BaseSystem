package de.silveryard.basesystem.sound.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.sound.FmodResult;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 06.04.2017.
 */
abstract class FmodChannel {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.create", FmodChannel::systemCallSoundFmodChannelCreate);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.stop", FmodChannel::systemCallSoundFmodChannelStop);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setpaused", FmodChannel::systemCallSoundFmodChannelSetPaused);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getpaused", FmodChannel::systemCallSoundFmodChannelGetPaused);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setvolume", FmodChannel::systemCallSoundFmodChannelSetVolume);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getvolume", FmodChannel::systemCallSoundFmodChannelGetVolume);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setvolumeramp", FmodChannel::systemCallSoundFmodChannelSetVolumeRamp);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getvolumeramp", FmodChannel::systemCallSoundFmodChannelGetVolumeRamp);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getaudibility", FmodChannel::systemCallSoundFmodChannelGetAudibility);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setpitch", FmodChannel::systemCallSoundFmodChannelSetPitch);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getpitch", FmodChannel::systemCallSoundFmodChannelGetPitch);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setmute", FmodChannel::systemCallSoundFmodChannelSetMute);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getmute", FmodChannel::systemCallSoundFmodChannelGetMute);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.isplaying", FmodChannel::systemCallSoundFmodChannelIsPlaying);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setfrequency", FmodChannel::systemCallSoundFmodChannelSetFrequency);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getfrequency", FmodChannel::systemCallSoundFmodChannelGetFrequency);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setpriority", FmodChannel::systemCallSoundFmodChannelSetPriority);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getpriority", FmodChannel::systemCallSoundFmodChannelGetPriority);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.setposition", FmodChannel::systemCallSoundFmodChannelSetPosition);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodchannel.getposition", FmodChannel::systemCallSoundFmodChannelGetPosition);

    }

    public static QAMessage systemCallSoundFmodChannelCreate(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodChannel channel = new de.silveryard.basesystem.sound.FmodChannel();
        int id = app.registerObject(channel);
        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(id));
    }

    public static QAMessage systemCallSoundFmodChannelStop(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = channel.stop();
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(result.getValue()));
    }
    public static QAMessage systemCallSoundFmodChannelSetPaused(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        boolean paused = message.getParameters().get(1).getBoolean();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = channel.setPaused(paused);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(result.getValue()));
    }
    public static QAMessage systemCallSoundFmodChannelGetPaused(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Boolean> paused = new Wrapper<>();
        FmodResult result = channel.getPaused(paused);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createBoolean(paused.value));
    }
    public static QAMessage systemCallSoundFmodChannelSetVolume(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        float volume = message.getParameters().get(1).getFloat();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = channel.setVolume(volume);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(result.getValue()));
    }
    public static QAMessage systemCallSoundFmodChannelGetVolume(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Float> paused = new Wrapper<>();
        FmodResult result = channel.getVolume(paused);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createFloat(paused.value));
    }
    public static QAMessage systemCallSoundFmodChannelSetVolumeRamp(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        boolean paused = message.getParameters().get(1).getBoolean();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = channel.setVolumeRamp(paused);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(result.getValue()));
    }
    public static QAMessage systemCallSoundFmodChannelGetVolumeRamp(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Boolean> paused = new Wrapper<>();
        FmodResult result = channel.getVolumeRamp(paused);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createBoolean(paused.value));
    }
    public static QAMessage systemCallSoundFmodChannelGetAudibility(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Float> audibility = new Wrapper<>();
        FmodResult result = channel.getAudibility(audibility);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createFloat(audibility.value));
    }
    public static QAMessage systemCallSoundFmodChannelSetPitch(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        float volume = message.getParameters().get(1).getFloat();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = channel.setPitch(volume);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(result.getValue()));
    }
    public static QAMessage systemCallSoundFmodChannelGetPitch(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Float> paused = new Wrapper<>();
        FmodResult result = channel.getPitch(paused);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createFloat(paused.value));
    }
    public static QAMessage systemCallSoundFmodChannelSetMute(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        boolean mute = message.getParameters().get(1).getBoolean();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = channel.setMute(mute);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(result.getValue()));
    }
    public static QAMessage systemCallSoundFmodChannelGetMute(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Boolean> mute = new Wrapper<>();
        FmodResult result = channel.getMute(mute);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createBoolean(mute.value));
    }

    public static QAMessage systemCallSoundFmodChannelIsPlaying(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Boolean> isPlaying = new Wrapper<>();
        FmodResult result = channel.isPlaying(isPlaying);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createBoolean(isPlaying.value));
    }

    public static QAMessage systemCallSoundFmodChannelSetFrequency(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        float frequency = message.getParameters().get(1).getFloat();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = channel.setFrequency(frequency);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(result.getValue()));
    }
    public static QAMessage systemCallSoundFmodChannelGetFrequency(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Float> frequency = new Wrapper<>();
        FmodResult result = channel.getFrequency(frequency);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createFloat(frequency.value));
    }
    public static QAMessage systemCallSoundFmodChannelSetPriority(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int priority = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = channel.setPriority(priority);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(result.getValue()));
    }
    public static QAMessage systemCallSoundFmodChannelGetPriority(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Integer> priority = new Wrapper<>();
        FmodResult result = channel.getPriority(priority);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createFloat(priority.value));
    }
    public static QAMessage systemCallSoundFmodChannelSetPosition(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int position = message.getParameters().get(1).getInt();
        int posType = message.getParameters().get(2).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        FmodResult result = channel.setPosition(position, posType);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(result.getValue()));
    }
    public static QAMessage systemCallSoundFmodChannelGetPosition(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int posType = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodChannel channel = Utils.as(de.silveryard.basesystem.sound.FmodChannel.class, app.getRegisteredObject(id));

        if(channel == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Integer> position = new Wrapper<>();
        FmodResult result = channel.getPosition(position, posType);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createFloat(position.value));
    }
}
