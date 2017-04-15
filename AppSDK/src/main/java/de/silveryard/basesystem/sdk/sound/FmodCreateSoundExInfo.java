package de.silveryard.basesystem.sdk.sound;

import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.SoundKernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.sound.FmodChannelOrder;
import de.silveryard.basesystem.sdk.kernel.sound.FmodSoundFormat;
import de.silveryard.basesystem.sdk.kernel.sound.FmodSoundType;
import de.silveryard.basesystem.sdk.kernel.sound.SoundReturnCode;

import static de.silveryard.basesystem.sdk.kernel.sound.FmodCreateSoundExInfo.*;

/**
 * Created by Sebif on 15.04.2017.
 */
public class FmodCreateSoundExInfo {
    private final Wrapper<ReturnCode> returnCodeWrapper;
    private final Wrapper<SoundReturnCode> soundReturnCodeWrapper;
    private final Wrapper<Integer> integerWrapper;
    private final Wrapper<FmodSoundFormat> fmodSoundFormatWrapper;
    private final Wrapper<Integer[]> integerArrayWrapper;
    private final Wrapper<String> stringWrapper;
    private final Wrapper<FmodSoundType> fmodSoundTypeWrapper;
    private final Wrapper<FmodChannelOrder> fmodChannelOrderWrapper;

    private final int id;

    public FmodCreateSoundExInfo(){
        returnCodeWrapper = new Wrapper<>();
        soundReturnCodeWrapper = new Wrapper<>();
        integerWrapper = new Wrapper<>();
        fmodSoundFormatWrapper = new Wrapper<>();
        integerArrayWrapper = new Wrapper<>();
        stringWrapper = new Wrapper<>();
        fmodSoundTypeWrapper = new Wrapper<>();
        fmodChannelOrderWrapper = new Wrapper<>();

        systemCallSoundFmodCreateSoundExInfoCreate(returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        id = integerWrapper.value;
    }

    /**
     * Returns the internal id
     * @return internal identifier
     */
    public int getId(){
        return id;
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::length
     * @return Result
     */
    public int getLength(){
        systemCallSoundFmodCreateSoundExInfoGetLength(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::length
     * @param length Value
     */
    public void setLength(int length){
        systemCallSoundFmodCreateSoundExInfoSetLength(id, length, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::fileOffset
     * @return Result
     */
    public int getFileOffset(){
        systemCallSoundFmodCreateSoundExInfoGetFileOffset(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::fileOffset
     * @param fileOffset Value
     */
    public void setFileOffset(int fileOffset){
        systemCallSoundFmodCreateSoundExInfoSetFileOffset(id, fileOffset, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::numChannels
     * @return Result
     */
    public int getNumChannels(){
        systemCallSoundFmodCreateSoundExInfoGetNumChannels(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::numChannels
     * @param numChannels Value
     */
    public void setNumChannels(int numChannels){
        systemCallSoundFmodCreateSoundExInfoSetNumChannels(id, numChannels, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::defaultFrequency
     * @return Result
     */
    public int getDefaultFrequency(){
        systemCallSoundFmodCreateSoundExInfoGetDefaultFrequency(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::defaultFrequency
     * @param defaultFrequency Value
     */
    public void setDefaultFrequency(int defaultFrequency){
        systemCallSoundFmodCreateSoundExInfoSetDefaultFrequency(id, defaultFrequency, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::format
     * @return Result
     */
    public FmodSoundFormat getFormat(){
        systemCallSoundFmodCreateSoundExInfoGetFormat(id, returnCodeWrapper, soundReturnCodeWrapper, fmodSoundFormatWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodSoundFormatWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::format
     * @param format Value
     */
    public void setFormat(FmodSoundFormat format){
        systemCallSoundFmodCreateSoundExInfoSetFormat(id, format, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::decodeBufferSize
     * @return Result
     */
    public int getDecodeBufferSize(){
        systemCallSoundFmodCreateSoundExInfoGetDecodeBufferSize(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::decodeBufferSize
     * @param decodeBufferSize Value
     */
    public void setDecodeBufferSize(int decodeBufferSize){
        systemCallSoundFmodCreateSoundExInfoSetDecodeBufferSize(id, decodeBufferSize,returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::initialSubSound
     * @return Result
     */
    public int getInitialSubSound(){
        systemCallSoundFmodCreateSoundExInfoGetInitialSubsound(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::initialSubSound
     * @param initialSubSound Value
     */
    public void setInitialSubSound(int initialSubSound){
        systemCallSoundFmodCreateSoundExInfoSetInitialSubsound(id, initialSubSound, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::numSubSounds
     * @return Result
     */
    public int getNumSubSounds(){
        systemCallSoundFmodCreateSoundExInfoGetNumSubSounds(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }

    /**
     * Sets FMOD_CREATESOUNDEXINFO::numSubSounds
     * @param numSubSounds Value
     */
    public void setNumSubSounds(int numSubSounds){
        systemCallSoundFmodCreateSoundExInfoSetNumSubSounds(id, numSubSounds, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::inclusionList
     * @return Result
     */
    public Integer[] getInclusionList(){
        systemCallSoundFmodCreateSoundExInfoGetInclusionList(id, returnCodeWrapper, soundReturnCodeWrapper, integerArrayWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerArrayWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::inclusionList
     * @param inclusionList Value
     */
    public void setInclusionList(int[] inclusionList){
        systemCallSoundFmodCreateSoundExInfoSetInclusionList(id, inclusionList, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::dlsName
     * @return Result
     */
    public String getDlsName(){
        systemCallSoundFmodCreateSoundExInfoGetDlsName(id, returnCodeWrapper, soundReturnCodeWrapper, stringWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::dlsName
     * @param dlsName Value
     */
    public void setDlsName(String dlsName){
        systemCallSoundFmodCreateSoundExInfoSetDlsName(id, dlsName, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::encryptionKey
     * @return Result
     */
    public String getEncryptionKey(){
        systemCallSoundFmodCreateSoundExInfoGetEncryptionKey(id, returnCodeWrapper, soundReturnCodeWrapper, stringWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::encryptionKey
     * @param encryptionKey Value
     */
    public void setEncryptionKey(String encryptionKey){
        systemCallSoundFmodCreateSoundExInfoSetEncryptionKey(id, encryptionKey, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::maxPolyphony
     * @return Result
     */
    public int getMaxPolyphony(){
        systemCallSoundFmodCreateSoundExInfoGetMaxPolypony(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::maxPolyphony
     * @param maxPolyphony Value
     */
    public void setMaxPolyphony(int maxPolyphony){
        systemCallSoundFmodCreateSoundExInfoSetMaxPolyphony(id, maxPolyphony, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::suggestedSoundType
     * @return Result
     */
    public FmodSoundType getSuggestedSoundType(){
        systemCallSoundFmodCreateSoundExInfoGetSuggestedSoundType(id, returnCodeWrapper, soundReturnCodeWrapper, fmodSoundTypeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodSoundTypeWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::suggestedSoundType
     * @param suggestedSoundType Value
     */
    public void setSuggestedSoundType(FmodSoundType suggestedSoundType){
        systemCallSoundFmodCreateSoundExInfoSetSuggestedSoundType(id, suggestedSoundType, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::fileBufferSize
     * @return Result
     */
    public int getFileBufferSize(){
        systemCallSoundFmodCreateSoundExInfoGetFileBufferSize(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::fileBufferSize
     * @param fileBufferSize Value
     */
    public void setFileBufferSize(int fileBufferSize){
        systemCallSoundFmodCreateSoundExInfoSetFileBufferSize(id, fileBufferSize, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::channelOrder
     * @return Result
     */
    public FmodChannelOrder getChannelOrder(){
        systemCallSoundFmodCreateSoundExInfoGetChannelOrder(id, returnCodeWrapper, soundReturnCodeWrapper, fmodChannelOrderWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return fmodChannelOrderWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::channelOrder
     * @param channelOrder Value
     */
    public void setChannelOrder(FmodChannelOrder channelOrder){
        systemCallSoundFmodCreateSoundExInfoSetChannelOrder(id, channelOrder, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::channelMask
     * @return Result
     */
    public int getChannelMask(){
        systemCallSoundFmodCreateSoundExInfoGetChannelMask(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::channelMask
     * @param channelMask Value
     */
    public void setChannelMask(int channelMask){
        systemCallSoundFmodCreateSoundExInfoSetChannelMask(id, channelMask, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::initialSeekPosition
     * @return Result
     */
    public int getInitialSeekPosition(){
        systemCallSoundFmodCreateSoundExInfoGetInitialSeekPosition(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::initialSeekPosition
     * @param initialSeekPosition Value
     */
    public void setInitialSeekPosition(int initialSeekPosition){
        systemCallSoundFmodCreateSoundExInfoSetInitialSeekPosType(id, initialSeekPosition, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::initialSeekPosType
     * @return Result
     */
    public int getInitialSeekPosType(){
        systemCallSoundFmodCreateSoundExInfoGetInitialSeekPosType(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::initialSeekPosType
     * @param initialSeekPosType Value
     */
    public void setInitialSeekPosType(int initialSeekPosType){
        systemCallSoundFmodCreateSoundExInfoSetInitialSeekPosType(id, initialSeekPosType, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::ignoreSetFilesystem
     * @return Result
     */
    public int getIgnoreSetFilesystem(){
        systemCallSoundFmodCreateSoundExInfoGetIgnoreSetFileSystem(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::ignoreSetFilesystem
     * @param ignoreSetFilesystem Value
     */
    public void setIgnoreSetFilesystem(int ignoreSetFilesystem){
        systemCallSoundFmodCreateSoundExInfoSetIgnoreSetFileSystem(id, ignoreSetFilesystem, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::audioQueuePolicy
     * @return Result
     */
    public int getAudioQueuePolicy(){
        systemCallSoundFmodCreateSoundExInfoGetAudioQueuePolicy(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::audioQueuePolicy
     * @param audioQueuePolicy Value
     */
    public void setAudioQueuePolicy(int audioQueuePolicy){
        systemCallSoundFmodCreateSoundExInfoSetAudioQueuePolicy(id, audioQueuePolicy, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::minMidiGranularity
     * @return Result
     */
    public int getMinMidiGranularity(){
        systemCallSoundFmodCreateSoundExInfoGetMinMidiGranularity(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::minMidiGranularity
     * @param minMidiGranularity Value
     */
    public void setMinMidiGranularity(int minMidiGranularity){
        systemCallSoundFmodCreateSoundExInfoSetMinMidiGranularity(id, minMidiGranularity, returnCodeWrapper,soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Gets FMOD_CREATESOUNDEXINFO::nonBlockThreadId
     * @return Result
     */
    public int getNonBlockThreadId(){
        systemCallSoundFmodCreateSoundExInfoGetNonBlockThreadId(id, returnCodeWrapper, soundReturnCodeWrapper, integerWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Sets FMOD_CREATESOUNDEXINFO::nonBlockThreadId
     * @param nonBlockThreadId Value
     */
    public void setNonBlockThreadId(int nonBlockThreadId){
        systemCallSoundFmodCreateSoundExInfoSetNonBlockThreadId(id, nonBlockThreadId, returnCodeWrapper, soundReturnCodeWrapper);

        if(soundReturnCodeWrapper.value != SoundReturnCode.OK){
            throw new SoundKernelException(soundReturnCodeWrapper.value);
        }
        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
}
