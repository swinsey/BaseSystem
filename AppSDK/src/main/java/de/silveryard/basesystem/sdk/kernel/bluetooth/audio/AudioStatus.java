package de.silveryard.basesystem.sdk.kernel.bluetooth.audio;

/**
 * Created by silveryard on 11.05.17.
 */
public enum AudioStatus {
    /**
     * Currently playing audio
     */
    PLAYING(0),
    /**
     * Playback is currently stopped
     */
    STOPPED(1),
    /**
     * Playback is currently paused
     */
    PAUSED(2),
    /**
     * Currently in forward seek mode
     */
    FORWARD_SEEK(3),
    /**
     * Currently in reverse seek mode
     */
    REVERSE_SEEK(4),
    /**
     * Some kind of error occured
     */
    ERROR(5);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static AudioStatus getEnumValue(int value){
        AudioStatus[] values = AudioStatus.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].value == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    /**
     * Constructor
     * @param value Integer value
     */
    AudioStatus(int value){
        this.value = value;
    }

    /**
     * Returns the enums integer value
     * @return Integer value
     */
    public int getValue(){
        return value;
    }
}
