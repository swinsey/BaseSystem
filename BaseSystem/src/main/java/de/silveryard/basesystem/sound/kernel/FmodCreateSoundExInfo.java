package de.silveryard.basesystem.sound.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.sound.FmodChannelOrder;
import de.silveryard.basesystem.sound.FmodSoundFormat;
import de.silveryard.basesystem.sound.FmodSoundType;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 06.04.2017.
 */
abstract class FmodCreateSoundExInfo {
    public static void enableKernel() {
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.create", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoCreate);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getlength", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetLength);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setlength", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetLength);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getfileoffset", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetFileOffset);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setfileoffset", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetFileOffset);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getnumchannels", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetNumChannels);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setnumchannels", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetNumChannels);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getdefaultfrequency", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetDefaultFrequency);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setdefaultfrequency", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetDefaultFrequency);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getformat", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetFormat);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setformat", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetFormat);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getdecodebuffersize", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetDecodeBufferSize);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setdecodebuffersize", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetDecodeBufferSize);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getinitialsubsound", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetInitialSubsound);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setinitialsubsound", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetInitialSubsound);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getnumsubsounds", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetNumSubSounds);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setnumsubsounds", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetNumSubSounds);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getinclusionlist", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetInclusionList);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setinclusionlist", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetInclusionList);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getdlsname", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetDlsName);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setdlsname", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetDlsName);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getencryptionkey", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetEncryptionKey);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setencryptionkey", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetEncryptionKey);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getmaxpolyphony", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetMaxPolyphony);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setmaxpolyphony", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetMaxPolyphony);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getsuggestedsoundtype", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetSuggestedSoundType);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setsuggestedsoundtype", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetSuggestedSoundType);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getfilebuffersize", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetFileBufferSize);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setfilebuffersize", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetFileBufferSize);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getchannelorder", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetChannelOrder);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setchannelorder", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetChannelOrder);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getchannelmask", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetChannelMask);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setchannelmask", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetChannelMask);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getinitialseekposition", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetInitialSeekPosition);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setinitialseekposition", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetInitialSeekPosition);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getseekpostype", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetInitialSeekPosType);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setseekpostype", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetInitialSeekPosType);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getignoresetfilesystem", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetIgnoreSetFilesystem);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setignoresetfilesystem", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetIgnoreSetFilesystem);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getaudioqueuepolicy", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetAudioQueuePolicy);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setaudioqueuepolicy", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetAudioQueuePolicy);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getminmidigranularity", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetMinMidiGranularity);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setminmidigranularity", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetMinMidiGranularity);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.getnonblockthreadid", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoGetNonBlockThreadId);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.setnonblockthreadid", FmodCreateSoundExInfo::systemCallSoundFmodCreateSoundExInfoSetNonBlockThreadId);
    }


    public static QAMessage systemCallSoundFmodCreateSoundExInfoCreate(RunningApp app, QAMessage message){
        ReturnCode returnCode = ReturnCode.OK;
        SoundReturnCode soundReturnCode = SoundReturnCode.OK;
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo obj = new de.silveryard.basesystem.sound.FmodCreateSoundExInfo();
        int id = app.registerObject(obj);

        return Kernel.getInstance().createResponse(message, returnCode.getValue(), soundReturnCode.getValue(), Parameter.createInt(id));
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetLength(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getLength()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetLength(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int length = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setLength(length);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetFileOffset(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getFileOffset()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetFileOffset(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int offset = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setFileOffset(offset);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetNumChannels(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getNumChannels()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetNumChannels(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int num = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setNumChannels(num);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetDefaultFrequency(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getDefaultFrequency()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetDefaultFrequency(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int frequency = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setDefaultFrequency(frequency);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetFormat(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getFormat().getValue()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetFormat(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        FmodSoundFormat format = FmodSoundFormat.getEnumValue(message.getParameters().get(1).getInt());
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setFormat(format);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetDecodeBufferSize(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getDecodeBufferSize()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetDecodeBufferSize(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int size = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setDecodeBufferSize(size);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetInitialSubsound(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getInitialSubSound()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetInitialSubsound(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int initialSubsound = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setInitialSubSound(initialSubsound);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetNumSubSounds(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getNumSubSounds()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetNumSubSounds(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int num = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setNumSubSounds(num);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetInclusionList(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        int[] inclusionList = info.getInclusionList();
        Parameter[] params = new Parameter[inclusionList.length + 1];
        params[0] = Parameter.createInt(inclusionList.length);
        for(int i = 0; i < inclusionList.length; i++){
            params[i + 1] = Parameter.createInt(inclusionList[i]);
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), params);
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetInclusionList(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int size = message.getParameters().get(1).getInt();
        int[] inclusionList = new int[size];
        for(int i = 0; i < size; i++){
            inclusionList[i] = message.getParameters().get(i + 2).getInt();
        }

        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setInclusionList(inclusionList);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetDlsName(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createString(info.getDlsName()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetDlsName(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        String dlsName = message.getParameters().get(1).getString();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setDlsName(dlsName);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetEncryptionKey(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createString(info.getEncryptionKey()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetEncryptionKey(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        String key = message.getParameters().get(1).getString();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setEncryptionKey(key);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetMaxPolyphony(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getMaxPolyphony()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetMaxPolyphony(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int polyphony = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setMaxPolyphony(polyphony);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetSuggestedSoundType(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getSuggestedSoundType().getValue()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetSuggestedSoundType(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        FmodSoundType type = FmodSoundType.getEnumValue(message.getParameters().get(1).getInt());
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setSuggestedSoundType(type);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetFileBufferSize(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getFileBufferSize()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetFileBufferSize(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int size = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setFileBufferSize(size);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetChannelOrder(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getChannelOrder().getValue()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetChannelOrder(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        FmodChannelOrder order = FmodChannelOrder.getEnumValue(message.getParameters().get(1).getInt());
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setChannelOrder(order);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetChannelMask(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getChannelMask()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetChannelMask(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int mask = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setChannelMask(mask);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetInitialSeekPosition(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getInitialSeekPosition()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetInitialSeekPosition(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int position = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setInitialSeekPosition(position);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetInitialSeekPosType(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getInitialSeekPosType()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetInitialSeekPosType(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int type = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setInitialSeekPosType(type);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetIgnoreSetFilesystem(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getIgnoreSetFilesystem()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetIgnoreSetFilesystem(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int ignore = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setIgnoreSetFilesystem(ignore);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetAudioQueuePolicy(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getAudioQueuePolicy()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetAudioQueuePolicy(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int policy = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setAudioQueuePolicy(policy);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetMinMidiGranularity(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getMinMidiGranularity()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetMinMidiGranularity(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int granularity = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setMinMidiGranularity(granularity);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }

    public static QAMessage systemCallSoundFmodCreateSoundExInfoGetNonBlockThreadId(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(info.getNonBlockThreadId()));
    }
    public static QAMessage systemCallSoundFmodCreateSoundExInfoSetNonBlockThreadId(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int threadId = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodCreateSoundExInfo info = Utils.as(de.silveryard.basesystem.sound.FmodCreateSoundExInfo.class, app.getRegisteredObject(id));

        if(info == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        info.setNonBlockThreadId(threadId);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue());
    }
}
