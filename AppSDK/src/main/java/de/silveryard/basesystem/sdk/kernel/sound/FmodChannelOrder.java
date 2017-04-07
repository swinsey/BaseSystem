package de.silveryard.basesystem.sdk.kernel.sound;

/**
 * Created by Sebif on 07.04.2017.
 */
public enum FmodChannelOrder {
    ORDER_DEFAULT(0),
    ORDER_WAVEFORMAT(1),
    ORDER_PROTOOLS(2),
    ORDER_ALLMONO(3),
    ORDER_ALLSTEREO(4),
    ORDER_ALSA(5),

    ORDER_MAX(6),
    ORDER_FORCEINT(65536);

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

    FmodChannelOrder(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
