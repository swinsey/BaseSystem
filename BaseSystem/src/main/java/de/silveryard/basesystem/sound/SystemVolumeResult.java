package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 09.04.2017.
 */
public enum SystemVolumeResult {
    /**
     * Everything went fine
     */
    OK(0),
    /**
     * Some Error occured
     */
    ERROR(1);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static SystemVolumeResult getEnumValue(int value){
        SystemVolumeResult[] values = SystemVolumeResult.values();
        for(int i = 0; i < values.length; i++) {
            if (values[i].getValue() == value) {
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
    SystemVolumeResult(int value){
        this.value = value;
    }

    /**
     * Returns the integer value of this enum value
     * @return Integer value
     */
    public int getValue(){
        return value;
    }
}
