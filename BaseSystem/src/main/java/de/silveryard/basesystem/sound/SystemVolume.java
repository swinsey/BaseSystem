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
     * OUTPUT
     **/

    /**
     * Returns the number of output devices
     * @return Number of output devices
     */
    public int getNumOutputDevices(){
        return nativeGetNumOutputDevices();
    }
    /**
     * Returns the device name of a specific output device
     * @param index Output device index
     * @return Device name
     */
    public String getOutputDeviceName(int index){
        return nativeGetOutputDeviceName(index);
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

    /**
     * INPUT
     **/

    /**
     * Returns the number of input devices
     * @return Number of input devices
     */
    public int getNumInputDevices(){
        return nativeGetNumInputDevices();
    }
    /**
     * Returns the device name of a specific input device
     * @param index Input device index
     * @return Device name
     */
    public String getInputDeviceName(int index){
        return nativeGetInputDeviceName(index);
    }

    /**
     * Fetches the mute flag of the systems input driver
     * @return Mute flag value
     */
    public boolean getInputMute(){
        return nativeGetInputMute();
    }
    /**
     * Sets the mute flag of the systems input driver
     * @param mute Mute flag value
     */
    public void setInputMute(boolean mute){
        nativeSetInputMute(mute);
    }

    /**
     * Fetches the master volume of the systems input driver
     * @return Master volume value
     */
    public float getInputMasterVolume(){
        return nativeGetInputMasterVolume();
    }
    /**
     * Sets the master volume of the systems input driver
     * @param volume Master volume value
     */
    public void setInputMasterVolume(float volume){
        nativeSetInputMasterVolume(volume);
    }

    /**
     * Fetches the number of channels of the systems input driver
     * @return Number of channels
     */
    public int getNumInputChannels(){
        return nativeGetNumInputChannels();
    }
    /**
     * Fetches the type of a given channel of the systems input driver
     * @param index Channel index
     * @return Channel type
     */
    public SystemVolumeChannelType getInputChannelType(int index){
        int value = nativeGetInputChannelType(index);
        SystemVolumeChannelType type = SystemVolumeChannelType.getEnumValue(value);
        return type;
    }
    /**
     * Fetches the volume of a given channel of the systems input driver
     * @param index Channel index
     * @return Volume value
     */
    public float getInputChannelVolume(int index){
        return nativeGetInputChannelVolume(index);
    }
    /**
     * Sets the volume of a given channel of the systems input driver
     * @param index Channel index
     * @param volume Volume value
     */
    public void setInputChannelVolume(int index, float volume){
        nativeSetInputChannelVolume(index, volume);
    }

    /**
     * NATIVE OUTPUT
     **/

    private native int nativeGetNumOutputDevices();
    private native String nativeGetOutputDeviceName(int index);

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

    /**
     * NATIVE INPUT
     **/

    private native int nativeGetNumInputDevices();
    private native String nativeGetInputDeviceName(int index);

    private native boolean nativeGetInputMute();
    private native void nativeSetInputMute(boolean mute);

    private native float nativeGetInputMasterVolume();
    private native void nativeSetInputMasterVolume(float volume);

    private native int nativeGetNumInputChannels();
    private native int nativeGetInputChannelType(int index);
    private native float nativeGetInputChannelVolume(int index);
    private native void nativeSetInputChannelVolume(int index, float volume);
}
