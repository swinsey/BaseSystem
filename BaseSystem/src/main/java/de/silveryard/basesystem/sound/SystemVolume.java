package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.IDisposable;

/**
 * Created by Sebif on 09.04.2017.
 */
public class SystemVolume {
    private static SystemVolume instance;

    public static void initialize(){
        instance = new SystemVolume();
    }
    public static SystemVolume getInstance(){
        return instance;
    }

    private SystemVolume(){
        int result = nativeInit();
        SystemVolumeResult enumResult = SystemVolumeResult.getEnumValue(result);
        if(enumResult != SystemVolumeResult.OK){
            throw new RuntimeException();
        }
    }

    public void dispose(){
        nativeDispose();
    }

    public boolean getMute(){
        return nativeGetMute();
    }
    public void setMute(boolean mute){
        nativeSetMute(mute);
    }

    public float getMasterVolume(){
        return nativeGetMasterVolume();
    }
    public void setMasterVolume(float volume){
        nativeSetMasterVolume(volume);
    }

    public int getNumOutputChannels(){
        return nativeGetNumOutputChannels();
    }
    public SystemVolumeChannelType getOutputChannelType(int index){
        int result = nativeGetOutputChannelType(index);
        SystemVolumeChannelType type = SystemVolumeChannelType.getEnumValue(result);
        return type;
    }
    public float getOutputChannelVolume(int index){
        return nativeGetOutputChannelVolume(index);
    }
    public void setOutputChannelVolume(int index, float volume){
        nativeSetOutputChannelVolume(index, volume);
    }

    private native int nativeInit();
    private native void nativeDispose();

    private native boolean nativeGetMute();
    private native void nativeSetMute(boolean mute);

    private native float nativeGetMasterVolume();
    private native void nativeSetMasterVolume(float volume);

    private native int nativeGetNumOutputChannels();
    private native int nativeGetOutputChannelType(int index);
    private native float nativeGetOutputChannelVolume(int index);
    private native void nativeSetOutputChannelVolume(int index, float volume);
}
