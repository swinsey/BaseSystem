package de.silveryard.basesystem.sdk.kernel.sound;

/**
 * Created by Sebif on 07.04.2017.
 */
public enum FmodOutputType {
    /**
     * FMOD_OUTPUTTYPE_AUTODETECT
     */
    AUTODETECT(0),

    /**
     * FMOD_OUTPUTTYPE_UNKNOWN
     */
    UNKNOWN(1),
    /**
     * FMOD_OUTPUTTYPE_NOSOUND
     */
    NOSOUND(2),
    /**
     * FMOD_OUTPUTTYPE_WAVWRITER
     */
    WAVWRITER(3),
    /**
     * FMOD_OUTPUTTYPE_NOSOUND_NRT
     */
    NOSOUND_NRT(4),
    /**
     * FMOD_OUTPUTTYPE_WAVWRITER_NRT
     */
    WAVWRITER_NRT(5),

    /**
     * FMOD_OUTPUTTYPE_DSOUND
     */
    DSOUND(6),
    /**
     * FMOD_OUTPUTTYPE_WINMM
     */
    WINMM(7),
    /**
     * FMOD_OUTPUTTYPE_WASAPI
     */
    WASAPI(8),
    /**
     * FMOD_OUTPUTTYPE_ASIO
     */
    ASIO(9),
    /**
     * FMOD_OUTPUTTYPE_PULSEAUDIO
     */
    PULSEAUDIO(10),
    /**
     * FMOD_OUTPUTTYPE_ALSA
     */
    ALSA(11),
    /**
     * FMOD_OUTPUTTYPE_COREAUDIO
     */
    COREAUDIO(12),
    /**
     * FMOD_OUTPUTTYPE_XAUDIO
     */
    XAUDIO(13),
    /**
     * FMOD_OUTPUTTYPE_PS3
     */
    PS3(14),
    /**
     * FMOD_OUTPUTTYPE_AUDIOTRACK
     */
    AUDIOTRACK(15),
    /**
     * FMOD_OUTPUTTYPE_OPENSL
     */
    OPENSL(16),
    /**
     * FMOD_OUTPUTTYPE_WIIU
     */
    WIIU(17),
    /**
     * FMOD_OUTPUTTYPE_AUDIOOUT
     */
    AUDIOOUT(18),
    /**
     * FMOD_OUTPUTTYPE_AUDIO3D
     */
    AUDIO3D(19),
    /**
     * FMOD_OUTPUTTYPE_ATMOS
     */
    ATMOS(20),
    /**
     * FMOD_OUTPUTTYPE_WEBAUDIO
     */
    WEBAUDIO(21),
    /**
     * FMOD_OUTPUTTYPE_NNAUDIO
     */
    NNAUDIO(22),

    /**
     * FMOD_OUTPUTTYPE_MAX
     */
    MAX(23),
    /**
     * FMOD_OUTPUTTYPE_FORCEINT
     */
    FORCEINT(65536);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static FmodOutputType getEnumValue(int value){
        FmodOutputType[] values = FmodOutputType.values();
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
    FmodOutputType(int value){
        this.value = value;
    }

    /**
     * @return Gets the enums integer value
     */
    public int getValue(){
        return value;
    }
}

