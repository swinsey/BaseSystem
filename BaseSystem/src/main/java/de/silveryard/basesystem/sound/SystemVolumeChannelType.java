package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 09.04.2017.
 */
public enum SystemVolumeChannelType {
    CHANNEL_TYPE_INVALID(-1),
    CHANNEL_TYPE_MONO(1),
    CHANNEL_TYPE_STEREO_LEFT(2),
    CHANNEL_TYPE_STEREO_RIGHT(3),
    CHANNEL_TYPE_QUAD_FRONT_LEFT(4),
    CHANNEL_TYPE_QUAD_FRONT_RIGHT(5),
    CHANNEL_TYPE_QUAD_REAR_LEFT(6),
    CHANNEL_TYPE_QUAD_REAR_RIGHT(7),
    CHANNEL_TYPE_5_1_FRONT_LEFT(8),
    CHANNEL_TYPE_5_1_FRONT_CENTER(9),
    CHANNEL_TYPE_5_1_FRONT_RIGHT(10),
    CHANNEL_TYPE_5_1_REAR_LEFT(11),
    CHANNEL_TYPE_5_1_REAR_RIGHT(12),
    CHANNEL_TYPE_5_1_SUB(13),
    CHANNEL_TYPE_7_1_FRONT_LEFT(14),
    CHANNEL_TYPE_7_1_FRONT_CENTER(15),
    CHANNEL_TYPE_7_1_FRONT_RIGHT(16),
    CHANNEL_TYPE_7_1_SIDE_LEFT(17),
    CHANNEL_TYPE_7_1_SIDE_RIGHT(18),
    CHANNEL_TYPE_7_1_REAR_LEFT(19),
    CHANNEL_TYPE_7_1_REAR_RIGHT(20),
    CHANNEL_TYPE_7_1_SUB(21);

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

    SystemVolumeChannelType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
