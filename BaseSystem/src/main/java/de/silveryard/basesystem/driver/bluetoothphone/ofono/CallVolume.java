package de.silveryard.basesystem.driver.bluetoothphone.ofono;

/**
 * Created by silveryard on 28.05.17.
 */
public class CallVolume {
    private de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.CallVolume callVolume;

    public CallVolume(de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.CallVolume callVolume){
        this.callVolume = callVolume;
    }

    /**
     * Boolean representing whether the microphone is muted.
     *
     * @return
     */
    public boolean getMuted(){
        return (Boolean)callVolume.GetProperties().get("Muted").getValue();
    }
    /**
     * Boolean representing whether the microphone is muted.
     *
     * @param muted
     */
    public void setMuted(boolean muted){
        callVolume.SetProperty("Muted", muted);
    }

    /**
     * Represents the current volume of the speaker in
     * percentage points. Valid values are 0-100.
     *
     * @return
     */
    public byte getSpeakerVolume(){
        return (Byte)callVolume.GetProperties().get("SpeakerVolume").getValue();
    }
    /**
     * Represents the current volume of the speaker in
     * percentage points. Valid values are 0-100.
     *
     * @param volume
     */
    public void setSpeakerVolume(byte volume){
        callVolume.SetProperty("SpeakerVolume", volume);
    }

    /**
     * Represents the current volume of the microphone in
     * percentage points. Valid values are 0-100.
     *
     * @return
     */
    public byte getMicrophoneVolume(){
        return (Byte)callVolume.GetProperties().get("MicrophoneVolume").getValue();
    }
    /**
     * Represents the current volume of the microphone in
     * percentage points. Valid values are 0-100.
     *
     * @param volume
     */
    public void setMicrophoneVolume(byte volume){
        callVolume.SetProperty("MicrophoneVolume", volume);
    }
}
