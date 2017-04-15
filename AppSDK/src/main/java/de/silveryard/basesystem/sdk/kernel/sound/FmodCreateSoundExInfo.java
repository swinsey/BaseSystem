package de.silveryard.basesystem.sdk.kernel.sound;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 15.04.2017.
 */
public abstract class FmodCreateSoundExInfo {
    /**
     * Creates a new object of type FmodCreateSoundExInfo
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outInfoId Info Identifier
     */
    public static void systemCallSoundFmodCreateSoundExInfoCreate(
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outInfoId
    ){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.sound.fmodscreatesoundexinfo.create", new ArrayList<>());

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outInfoId.value = response.getParameters().get(2).getInt();
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::length
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outLength Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetLength(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outLength
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outLength.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::length
     * @param id Object info identifier
     * @param length Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetLength(
            int id, int length,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(length));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::fileOffset
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFileOffset Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetFileOffset(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outFileOffset
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFileOffset.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::fileOffset
     * @param id Object info identifier
     * @param fileOffset Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetFileOffset(
            int id, int fileOffset,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(fileOffset));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::numChannels
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outNumChannels Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetNumChannels(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outNumChannels
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outNumChannels.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::numChannels
     * @param id Object info identifier
     * @param numChannels Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetNumChannels(
            int id, int numChannels,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(numChannels));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::defaultFrequency
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outDefaultFrequency Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetDefaultFrequency(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outDefaultFrequency
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outDefaultFrequency.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::defaultFrequency
     * @param id Object info identifier
     * @param defaultFrequency Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetDefaultFrequency(
            int id, int defaultFrequency,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(defaultFrequency));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::format
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFormat Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetFormat(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<FmodSoundFormat> outFormat
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFormat.value = FmodSoundFormat.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::format
     * @param id Object info identifier
     * @param format Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetFormat(
            int id, FmodSoundFormat format,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(format.getValue()));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::decodeBufferSize
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outDecodeBufferSize Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetDecodeBufferSize(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outDecodeBufferSize
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outDecodeBufferSize.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::decodeBufferSize
     * @param id Object info identifier
     * @param decodeBufferSize Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetDecodeBufferSize(
            int id, int decodeBufferSize,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(decodeBufferSize));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::initialSubSound
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outInitialSubSound Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetInitialSubsound(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outInitialSubSound
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outInitialSubSound.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::initialSubSound
     * @param id Object info identifier
     * @param initialSubSound Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetInitialSubsound(
            int id, int initialSubSound,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(initialSubSound));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::numSubSounds
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outNumSubSounds Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetNumSubSounds(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outNumSubSounds
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outNumSubSounds.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::numSubSounds
     * @param id Object info identifier
     * @param numSubSounds Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetNumSubSounds(
            int id, int numSubSounds,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(numSubSounds));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::inclusionList
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outInclusionList Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetInclusionList(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer[]> outInclusionList
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());

        int count = response.getParameters().get(2).getInt();
        outInclusionList.value = new Integer[count];
        for(int i = 0; i < count; i++){
            outInclusionList.value[i] = response.getParameters().get(3 + i).getInt();
        }
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::inclusionList
     * @param id Object info identifier
     * @param inclusionList Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetInclusionList(
            int id, int[] inclusionList,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(inclusionList.length));
        for(int i = 0; i < inclusionList.length; i++){
            params.add(Parameter.createInt(inclusionList[i]));
        }
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::dlsName
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outDlsName Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetDlsName(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<String> outDlsName
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outDlsName.value = response.getParameters().get(2).getString();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::dlsName
     * @param id Object info identifier
     * @param dlsName Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetDlsName(
            int id, String dlsName,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createString(dlsName));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::encryptionKey
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outEncryptionKey Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetEncryptionKey(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<String> outEncryptionKey
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outEncryptionKey.value = response.getParameters().get(2).getString();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::encryptionKey
     * @param id Object info identifier
     * @param encryptionKey Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetEncryptionKey(
            int id, String encryptionKey,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createString(encryptionKey));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::maxPolyphony
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outMaxPolyphony Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetMaxPolypony(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outMaxPolyphony
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outMaxPolyphony.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::maxPolyphony
     * @param id Object info identifier
     * @param maxPolyphony Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetMaxPolyphony(
            int id, int maxPolyphony,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(maxPolyphony));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::suggestedSoundType
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outSuggestedSoundType Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetSuggestedSoundType(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<FmodSoundType> outSuggestedSoundType
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outSuggestedSoundType.value = FmodSoundType.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::suggestedSoundType
     * @param id Object info identifier
     * @param suggestedSoundType Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetSuggestedSoundType(
            int id, FmodSoundType suggestedSoundType,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(suggestedSoundType.getValue()));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::fileBufferSize
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outFileBufferSize Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetFileBufferSize(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outFileBufferSize
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFileBufferSize.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::fileBufferSize
     * @param id Object info identifier
     * @param fileBufferSize Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetFileBufferSize(
            int id, int fileBufferSize,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(fileBufferSize));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::channelOrder
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outChannelOrder Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetChannelOrder(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<FmodChannelOrder> outChannelOrder
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outChannelOrder.value = FmodChannelOrder.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::channelOrder
     * @param id Object info identifier
     * @param channelOrder Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetChannelOrder(
            int id, FmodChannelOrder channelOrder,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(channelOrder.getValue()));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::channelMask
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outChannelMask Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetChannelMask(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outChannelMask
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outChannelMask.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::channelMask
     * @param id Object info identifier
     * @param channelMask Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetChannelMask(
            int id, int channelMask,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(channelMask));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::initialSeekPosition
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outInitialSeekPosition Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetInitialSeekPosition(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outInitialSeekPosition
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outInitialSeekPosition.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::initialSeekPosition
     * @param id Object info identifier
     * @param initialSeekPosition Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetInitialSeekPosition(
            int id, int initialSeekPosition,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(initialSeekPosition));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::initialSeekPosType
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outInitialSeekPosType Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetInitialSeekPosType(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outInitialSeekPosType
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outInitialSeekPosType.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::initialSeekPosType
     * @param id Object info identifier
     * @param initialSeekPosType Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetInitialSeekPosType(
            int id, int initialSeekPosType,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(initialSeekPosType));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::ignoreSetFilesystem
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outIgnoreSetFileSystem Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetIgnoreSetFileSystem(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outIgnoreSetFileSystem
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outIgnoreSetFileSystem.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::ignoreSetFilesystem
     * @param id Object info identifier
     * @param ignoreSetFilesystem Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetIgnoreSetFileSystem(
            int id, int ignoreSetFilesystem,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(ignoreSetFilesystem));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::audioQueuePolicy
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outAudioQueuePolicy Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetAudioQueuePolicy(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outAudioQueuePolicy
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outAudioQueuePolicy.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::audioQueuePolicy
     * @param id Object info identifier
     * @param audioQueuePolicy Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetAudioQueuePolicy(
            int id, int audioQueuePolicy,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(audioQueuePolicy));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::minMidiGranularity
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outMinMidiGranularity Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetMinMidiGranularity(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outMinMidiGranularity
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outMinMidiGranularity.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::minMidiGranularity
     * @param id Object info identifier
     * @param minMidiGranularity Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetMinMidiGranularity(
            int id, int minMidiGranularity,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(minMidiGranularity));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::nonBlockThreadId
     * @param id Object info identifier
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     * @param outNonBlockThreadId Value
     */
    public static void systemCallSoundFmodCreateSoundExInfoGetNonBlockThreadId(
            int id,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode,
            Wrapper<Integer> outNonBlockThreadId
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outNonBlockThreadId.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::nonBlockThreadId
     * @param id Object info identifier
     * @param nonBlockThreadId Value
     * @param outReturnCode General Return Code
     * @param outSoundReturnCode Sound Return Code
     */
    public static void systemCallSoundFmodCreateSoundExInfoSetNonBlockThreadId(
            int id, int nonBlockThreadId,
            Wrapper<ReturnCode> outReturnCode, Wrapper<SoundReturnCode> outSoundReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(id));
        params.add(Parameter.createInt(nonBlockThreadId));
        QAMessage response = Kernel.systemCall("", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outSoundReturnCode.value = SoundReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
