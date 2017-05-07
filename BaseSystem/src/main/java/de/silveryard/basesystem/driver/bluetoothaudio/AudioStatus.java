package de.silveryard.basesystem.driver.bluetoothaudio;

/**
 * Created by silveryard on 07.05.17.
 */
public enum AudioStatus {
    PLAYING(0),
    STOPPED(1),
    PAUSED(2),
    FORWARD_SEEK(3),
    REVERSE_SEEK(4),
    ERROR(5);

    private int value;

    AudioStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
