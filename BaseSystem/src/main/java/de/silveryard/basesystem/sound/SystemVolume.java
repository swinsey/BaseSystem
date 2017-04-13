package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.IDisposable;

/**
 * Created by Sebif on 09.04.2017.
 */
public class SystemVolume implements IDisposable {
    private static SystemVolume instance;

    /**
     * Initializes SystemVolume
     */
    public static void initialize(){
        instance = new SystemVolume();
    }
    /**
     * Singleton Getter
     * @return SystemVolume instance
     */
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

    @Override
    public void dispose(){
        nativeDispose();
    }

    /**
     * Fetches the mute flag of the systems output driver
     * @return Mute flag value
     */
    public boolean getMute(){
        return nativeGetMute();
    }
    /**
     * Sets the mute flag of the systems output driver
     * @param mute Mute flag value
     */
    public void setMute(boolean mute){
        nativeSetMute(mute);
    }

    /**
     * Fetches the master volume of the systems output driver
     * @return Master volume value
     */
    public float getMasterVolume(){
        return nativeGetMasterVolume();
    }
    /**
     * Sets the master volume of the systems output driver
     * @param volume Master volume value
     */
    public void setMasterVolume(float volume){
        nativeSetMasterVolume(volume);
    }

    /**
     * Fetches the number of channels of the systems output driver
     * @return Number of channels
     */
    public int getNumOutputChannels(){
        return nativeGetNumOutputChannels();
    }
    /**
     * Fetches the type of a given channel of the systems output driver
     * @param index Channel index
     * @return Channel type
     */
    public SystemVolumeChannelType getOutputChannelType(int index){
        int result = nativeGetOutputChannelType(index);
        SystemVolumeChannelType type = SystemVolumeChannelType.getEnumValue(result);
        return type;
    }
    /**
     * Fetches the volume of a given channel of the systems output driver
     * @param index Channel index
     * @return Volume value
     */
    public float getOutputChannelVolume(int index){
        return nativeGetOutputChannelVolume(index);
    }
    /**
     * Sets the volume of a given channel of the systems output driver
     * @param index Channel index
     * @param volume Volume value
     */
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
