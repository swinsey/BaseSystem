package de.silveryard.basesystem.sdk.kernel.sound;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 06.04.2017.
 */
public abstract class FmodSound {
    /**
     * Creates a new FmodSound object
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outchannelID ID of the created sound
     */
    public static void systemCallSoundFmodSoundCreate(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outchannelID
    ){

        List<Parameter> params = new ArrayList<>();
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsound.create", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outchannelID.value = message.getParameters().get(2).getInt();
    }

    /**
     * Fetches the length of a given sound
     * @param channelID ID of the FmodSound
     * @param lengthType Format to store length in
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFmodResult Fmod Result
     * @param outLength Length of the sound
     */
    public static void systemCallSoundFmodSoundGetLength(
            int channelID, int lengthType,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<FmodResult> outFmodResult, Wrapper<Integer> outLength
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(channelID));
        params.add(Parameter.createInt(lengthType));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodsound.getlength", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outFmodResult.value = FmodResult.getEnumValue(message.getParameters().get(2).getInt());
        outLength.value = message.getParameters().get(3).getInt();
    }
}
