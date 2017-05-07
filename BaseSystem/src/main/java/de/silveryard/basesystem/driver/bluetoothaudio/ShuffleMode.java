package de.silveryard.basesystem.driver.bluetoothaudio;

/**
 * Created by silveryard on 07.05.17.
 */
public enum ShuffleMode {
    /**
     * No shuffling
     */
    OFF(0),
    /**
     * Shuffle all tracks
     */
    ALL_TRACKS(1),
    /**
     * No shuffling
     */
    GROUP(2),
    /**
     * Some kind of error occured
     */
    ERROR(3);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static ShuffleMode getEnumValue(int value){
        ShuffleMode[] values = ShuffleMode.values();
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
    ShuffleMode(int value){
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
