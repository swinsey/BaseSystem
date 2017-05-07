package de.silveryard.basesystem.driver.bluetoothaudio;

/**
 * Created by silveryard on 07.05.17.
 */
public enum ShuffleMode {
    OFF(0),
    ALL_TRACKS(1),
    GROUP(2),
    ERROR(3);

    private int value;

    ShuffleMode(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
