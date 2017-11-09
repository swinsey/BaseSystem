package de.silveryard.basesystem.sdk.kernel.sound;

/**
 * Created by Sebif on 07.04.2017.
 */
public enum FmodSoundFormat {
    /**
     * FMOD_SOUND_FORMAT_NONE
     */
    FORMAT_NONE(0),
    /**
     * FMOD_SOUND_FORMAT_PCM8
     */
    FORMAT_PCM8(1),
    /**
     * FMOD_SOUND_FORMAT_PCM16
     */
    FORMAT_PCM16(2),
    /**
     * FMOD_SOUND_FORMAT_PCM24
     */
    FORMAT_PCM24(3),
    /**
     * FMOD_SOUND_FORMAT_PCM32
     */
    FORMAT_PCM32(4),
    /**
     * FMOD_SOUND_FORMAT_PCMFLOAT
     */
    FORMAT_PCMFLOAT(5),
    /**
     * FMOD_SOUND_FORMAT_BITSTREAM
     */
    FORMAT_BITSTREAM(6),

    /**
     * FMOD_SOUND_FORMAT_MAX
     */
    FORMAT_MAX(7),
    /**
     * FMOD_SOUND_FORMAT_FORCEINT
     */
    FORMAT_FORCEINT(65536);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
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

    /**
     * Constructor
     * @param value Integer value
     */
    FmodSoundFormat(int value){
        this.value = value;
    }

    /**
     * @return Gets the enums integer value
     */
    public int getValue(){
        return value;
    }
}

