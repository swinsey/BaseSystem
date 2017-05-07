package de.silveryard.basesystem.driver.bluetoothaudio;

/**
 * Created by silveryard on 07.05.17.
 */
public enum RepeatMode {
    OFF(0),
    SINGLE_TRACK(1),
    ALL_TRACKS(2),
    GROUP(3),
    ERROR(4);

    private int value;

    RepeatMode(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
