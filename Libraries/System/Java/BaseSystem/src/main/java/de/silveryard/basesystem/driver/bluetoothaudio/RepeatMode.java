package de.silveryard.basesystem.driver.bluetoothaudio;

/**
 * Created by silveryard on 07.05.17.
 */
public enum RepeatMode {
    /**
     * There is no repeating
     */
    OFF(0),
    /**
     * Repeats a single track
     */
    SINGLE_TRACK(1),
    /**
     * Repeats all tracks
     */
    ALL_TRACKS(2),
    /**
     * Repeats the current group
     */
    GROUP(3),
    /**
     * Some kind of error occured
     */
    ERROR(4);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static RepeatMode getEnumValue(int value){
        RepeatMode[] values = RepeatMode.values();
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
    RepeatMode(int value){
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
