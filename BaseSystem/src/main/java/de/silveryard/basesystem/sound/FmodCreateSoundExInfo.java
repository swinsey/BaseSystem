package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.IDisposable;

/**
 * Created by Sebif on 27.03.2017.
 */
public class FmodCreateSoundExInfo implements IDisposable{
    private long handle;

    @Override
    public native void dispose();
    /**
     * Returns the internal handle
     * @return Internal handle
     */
    public native long getHandle();

    /**
     * Gets FMOD_CREATESOUNDEXINFO::length
     * @return Result
     */
    public native int getLength();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::length
     * @param length Value
     */
    public native void setLength(int length);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::fileOffset
     * @return Result
     */
    public native int getFileOffset();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::fileOffset
     * @param fileOffset Value
     */
    public native void setFileOffset(int fileOffset);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::numChannels
     * @return Result
     */
    public native int getNumChannels();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::numChannels
     * @param numChannels Value
     */
    public native void setNumChannels(int numChannels);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::defaultFrequency
     * @return Result
     */
    public native int getDefaultFrequency();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::defaultFrequency
     * @param defaultFrequency Value
     */
    public native void setDefaultFrequency(int defaultFrequency);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::format
     * @return Result
     */
    public native FmodSoundFormat getFormat();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::format
     * @param format Value
     */
    public native void setFormat(FmodSoundFormat format);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::decodeBufferSize
     * @return Result
     */
    public native int getDecodeBufferSize();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::decodeBufferSize
     * @param decodeBufferSize Value
     */
    public native void setDecodeBufferSize(int decodeBufferSize);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::initialSubSound
     * @return Result
     */
    public native int getInitialSubSound();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::initialSubSound
     * @param initialSubSound Value
     */
    public native void setInitialSubSound(int initialSubSound);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::numSubSounds
     * @return Result
     */
    public native int getNumSubSounds();

    /**
     * Sets FMOD_CREATESOUNDEXINFO::numSubSounds
     * @param numSubSounds Value
     */
    public native void setNumSubSounds(int numSubSounds);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::inclusionList
     * @return Result
     */
    public native int[] getInclusionList();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::inclusionList
     * @param inclusionList Value
     */
    public native void setInclusionList(int[] inclusionList);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::dlsName
     * @return Result
     */
    public native String getDlsName();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::dlsName
     * @param dlsName Value
     */
    public native void setDlsName(String dlsName);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::encryptionKey
     * @return Result
     */
    public native String getEncryptionKey();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::encryptionKey
     * @param encryptionKey Value
     */
    public native void setEncryptionKey(String encryptionKey);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::maxPolyphony
     * @return Result
     */
    public native int getMaxPolyphony();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::maxPolyphony
     * @param maxPolyphony Value
     */
    public native void setMaxPolyphony(int maxPolyphony);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::suggestedSoundType
     * @return Result
     */
    public native FmodSoundType getSuggestedSoundType();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::suggestedSoundType
     * @param suggestedSoundType Value
     */
    public native void setSuggestedSoundType(FmodSoundType suggestedSoundType);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::fileBufferSize
     * @return Result
     */
    public native int getFileBufferSize();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::fileBufferSize
     * @param fileBufferSize Value
     */
    public native void setFileBufferSize(int fileBufferSize);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::channelOrder
     * @return Result
     */
    public native FmodChannelOrder getChannelOrder();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::channelOrder
     * @param channelOrder Value
     */
    public native void setChannelOrder(FmodChannelOrder channelOrder);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::channelMask
     * @return Result
     */
    public native int getChannelMask();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::channelMask
     * @param channelMask Value
     */
    public native void setChannelMask(int channelMask);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::initialSeekPosition
     * @return Result
     */
    public native int getInitialSeekPosition();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::initialSeekPosition
     * @param initialSeekPosition Value
     */
    public native void setInitialSeekPosition(int initialSeekPosition);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::initialSeekPosType
     * @return Result
     */
    public native int getInitialSeekPosType();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::initialSeekPosType
     * @param initialSeekPosType Value
     */
    public native void setInitialSeekPosType(int initialSeekPosType);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::ignoreSetFilesystem
     * @return Result
     */
    public native int getIgnoreSetFilesystem();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::ignoreSetFilesystem
     * @param ignoreSetFilesystem Value
     */
    public native void setIgnoreSetFilesystem(int ignoreSetFilesystem);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::audioQueuePolicy
     * @return Result
     */
    public native int getAudioQueuePolicy();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::audioQueuePolicy
     * @param audioQueuePolicy Value
     */
    public native void setAudioQueuePolicy(int audioQueuePolicy);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::minMidiGranularity
     * @return Result
     */
    public native int getMinMidiGranularity();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::minMidiGranularity
     * @param minMidiGranularity Value
     */
    public native void setMinMidiGranularity(int minMidiGranularity);

    /**
     * Gets FMOD_CREATESOUNDEXINFO::nonBlockThreadId
     * @return Result
     */
    public native int getNonBlockThreadId();
    /**
     * Sets FMOD_CREATESOUNDEXINFO::nonBlockThreadId
     * @param nonBlockThreadId Value
     */
    public native void setNonBlockThreadId(int nonBlockThreadId);
}
