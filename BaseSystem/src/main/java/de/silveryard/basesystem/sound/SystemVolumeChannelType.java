package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 09.04.2017.
 */
public enum SystemVolumeChannelType {
    /**
     * Indicates some kind of error
     */
    CHANNEL_TYPE_INVALID(-1),
    /**
     * Front left speaker
     */
    CHANNEL_TYPE_FRONT_LEFT(1),
    /**
     * Front center speaker
     */
    CHANNEL_TYPE_FRONT_CENTER(2),
    /**
     * Front right speaker
     */
    CHANNEL_TYPE_FRONT_RIGHT(3),
    /**
     * Side left speaker
     */
    CHANNEL_TYPE_SIDE_LEFT(4),
    /**
     * Side right speaker
     */
    CHANNEL_TYPE_SIDE_RIGHT(5),
    /**
     * Rear left speaker
     */
    CHANNEL_TYPE_REAR_LEFT(6),
    /**
     * Rear center speaker
     */
    CHANNEL_TYPE_REAR_CENTER(7),
    /**
     * Rear right speaker
     */
    CHANNEL_TYPE_REAR_RIGHT(8),
    /**
     * Subwoofer
     */
    CHANNEL_TYPE_SUB(9);


    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static SystemVolumeChannelType getEnumValue(int value){
        SystemVolumeChannelType[] values = SystemVolumeChannelType.values();
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
    SystemVolumeChannelType(int value){
        this.value = value;
    }

    /**
     * @return Returns the integer value of this enum value
     */
    public int getValue(){
        return value;
    }
}
