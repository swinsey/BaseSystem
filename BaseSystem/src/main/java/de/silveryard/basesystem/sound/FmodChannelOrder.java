package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 27.03.2017.
 */
public enum FmodChannelOrder {
    /**
     * FMOD_CHANNELORDER_DEFAULT
     */
    ORDER_DEFAULT(0),
    /**
     * FMOD_CHANNELORDER_WAVEFORMAT
     */
    ORDER_WAVEFORMAT(1),
    /**
     * FMOD_CHANNELORDER_PROTOOLS
     */
    ORDER_PROTOOLS(2),
    /**
     * FMOD_CHANNELORDER_ALLMONO
     */
    ORDER_ALLMONO(3),
    /**
     * FMOD_CHANNELORDER_ALLSTEREO
     */
    ORDER_ALLSTEREO(4),
    /**
     * FMOD_CHANNELORDER_ALSA
     */
    ORDER_ALSA(5),

    /**
     * FMOD_CHANNELORDER_MAX
     */
    ORDER_MAX(6),
    /**
     * FMOD_CHANNELORDER_FORCEINT
     */
    ORDER_FORCEINT(65536);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static FmodChannelOrder getEnumValue(int value){
        FmodChannelOrder[] values = FmodChannelOrder.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].getValue() == value){
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
    FmodChannelOrder(int value){
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
