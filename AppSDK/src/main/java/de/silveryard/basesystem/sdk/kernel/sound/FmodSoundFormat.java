package de.silveryard.basesystem.sdk.kernel.sound;

/**
 * Created by Sebif on 07.04.2017.
 */
public enum FmodSoundFormat {
    FORMAT_NONE(0),
    FORMAT_PCM8(1),
    FORMAT_PCM16(2),
    FORMAT_PCM24(3),
    FORMAT_PCM32(4),
    FORMAT_PCMFLOAT(5),
    FORMAT_BITSTREAM(6),

    FORMAT_MAX(7),
    FORMAT_FORCEINT(65536);

    public static FmodSoundFormat getEnumValue(int value){
        FmodSoundFormat[] values = FmodSoundFormat.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].value == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    FmodSoundFormat(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

