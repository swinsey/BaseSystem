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
     * Single speaker in a mono setup
     */
    CHANNEL_TYPE_MONO(1),
    /**
     * Left speaker in a stereo setup
     */
    CHANNEL_TYPE_STEREO_LEFT(2),
    /**
     * Right speaker in a stereo setup
     */
    CHANNEL_TYPE_STEREO_RIGHT(3),
    /**
     * Front left speaker in a 4-speaker setup
     */
    CHANNEL_TYPE_QUAD_FRONT_LEFT(4),
    /**
     * Front right speaker in a 4-speaker setup
     */
    CHANNEL_TYPE_QUAD_FRONT_RIGHT(5),
    /**
     * Rear left speaker in a 4-speaker setup
     */
    CHANNEL_TYPE_QUAD_REAR_LEFT(6),
    /**
     * Rear right speaker in a 4-speaker setup
     */
    CHANNEL_TYPE_QUAD_REAR_RIGHT(7),
    /**
     * Front left speaker in a 5.1 setup
     */
    CHANNEL_TYPE_5_1_FRONT_LEFT(8),
    /**
     * Front center speaker in a 5.1 setup
     */
    CHANNEL_TYPE_5_1_FRONT_CENTER(9),
    /**
     * Front right speaker in a 5.1 setup
     */
    CHANNEL_TYPE_5_1_FRONT_RIGHT(10),
    /**
     * Rear left speaker in a 5.1 setup
     */
    CHANNEL_TYPE_5_1_REAR_LEFT(11),
    /**
     * Rear right speaker in a 5.1 setup
     */
    CHANNEL_TYPE_5_1_REAR_RIGHT(12),
    /**
     * Subwoofer in a 5.1 setup
     */
    CHANNEL_TYPE_5_1_SUB(13),
    /**
     * Front left speaker in a 7.1 setup
     */
    CHANNEL_TYPE_7_1_FRONT_LEFT(14),
    /**
     * Front center speaker in a 7.1 setup
     */
    CHANNEL_TYPE_7_1_FRONT_CENTER(15),
    /**
     * Front right speaker in a 7.1 setup
     */
    CHANNEL_TYPE_7_1_FRONT_RIGHT(16),
    /**
     * Side left speaker in a 7.1 setup
     */
    CHANNEL_TYPE_7_1_SIDE_LEFT(17),
    /**
     * Side right speaker in a 7.1 setup
     */
    CHANNEL_TYPE_7_1_SIDE_RIGHT(18),
    /**
     * Rear left speaker in a 7.1 setup
     */
    CHANNEL_TYPE_7_1_REAR_LEFT(19),
    /**
     * Rear right speaker in a 7.1 setup
     */
    CHANNEL_TYPE_7_1_REAR_RIGHT(20),
    /**
     * Subwoofer in a 7.1 setup
     */
    CHANNEL_TYPE_7_1_SUB(21);

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
