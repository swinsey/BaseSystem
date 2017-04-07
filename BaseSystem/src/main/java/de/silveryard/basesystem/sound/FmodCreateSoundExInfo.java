package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.IDisposable;

/**
 * Created by Sebif on 27.03.2017.
 */
public class FmodCreateSoundExInfo implements IDisposable{
    private long handle;

    @Override
    public native void dispose();
    public native long getHandle();

    public native int getLength();
    public native void setLength(int length);

    public native int getFileOffset();
    public native void setFileOffset(int fileOffset);

    public native int getNumChannels();
    public native void setNumChannels(int numChannels);

    public native int getDefaultFrequency();
    public native void setDefaultFrequency(int defaultFrequency);

    public native FmodSoundFormat getFormat();
    public native void setFormat(FmodSoundFormat format);

    public native int getDecodeBufferSize();
    public native void setDecodeBufferSize(int decodeBufferSize);

    public native int getInitialSubSound();
    public native void setInitialSubSound(int initialSubSound);

    public native int getNumSubSounds();
    public native void setNumSubSounds(int numSubSounds);

    public native int[] getInclusionList();
    public native void setInclusionList(int[] inclusionList);

    public native String getDlsName();
    public native void setDlsName(String dlsName);

    public native String getEncryptionKey();
    public native void setEncryptionKey(String encryptionKey);

    public native int getMaxPolyphony();
    public native void setMaxPolyphony(int maxPolyphony);

    public native FmodSoundType getSuggestedSoundType();
    public native void setSuggestedSoundType(FmodSoundType suggestedSoundType);

    public native int getFileBufferSize();
    public native void setFileBufferSize(int fileBufferSize);

    public native FmodChannelOrder getChannelOrder();
    public native void setChannelOrder(FmodChannelOrder channelOrder);

    public native int getChannelMask();
    public native void setChannelMask(int channelMask);

    public native int getInitialSeekPosition();
    public native void setInitialSeekPosition(int initialSeekPosition);

    public native int getInitialSeekPosType();
    public native void setInitialSeekPosType(int initialSeekPosType);

    public native int getIgnoreSetFilesystem();
    public native void setIgnoreSetFilesystem(int ignoreSetFilesystem);

    public native int getAudioQueuePolicy();
    public native void setAudioQueuePolicy(int audioQueuePolicy);

    public native int getMinMidiGranularity();
    public native void setMinMidiGranularity(int minMidiGranularity);

    public native int getNonBlockThreadId();
    public native void setNonBlockThreadId(int nonBlockThreadId);
}
