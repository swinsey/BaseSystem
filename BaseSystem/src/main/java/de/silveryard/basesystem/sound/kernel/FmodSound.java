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
abstract class FmodSound {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodsound.create", FmodSound::systemCallSoundFmodSoundCreate);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.kernel.sound.fmodsound.getlength", FmodSound::systemCallSoundFmodSoundGetLength);
    }

    public static QAMessage systemCallSoundFmodSoundCreate(RunningApp app, QAMessage message){
        de.silveryard.basesystem.sound.FmodSound sound = new de.silveryard.basesystem.sound.FmodSound();
        int id = app.registerObject(sound);
        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(), Parameter.createInt(id));
    }

    public static QAMessage systemCallSoundFmodSoundGetLength(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        int lengthType = message.getParameters().get(1).getInt();
        de.silveryard.basesystem.sound.FmodSound sound = Utils.as(de.silveryard.basesystem.sound.FmodSound.class, app.getRegisteredObject(id));

        if(sound == null){
            return Kernel.getInstance().createResponse(message,
                    ReturnCode.ERROR.getValue(), SoundReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(-1));
        }

        Wrapper<Integer> length = new Wrapper<>();
        FmodResult result = sound.getLength(length, lengthType);
        return Kernel.getInstance().createResponse(message,
                ReturnCode.OK.getValue(), SoundReturnCode.OK.getValue(),
                Parameter.createInt(result.getValue()), Parameter.createFloat(length.value));
    }
}
