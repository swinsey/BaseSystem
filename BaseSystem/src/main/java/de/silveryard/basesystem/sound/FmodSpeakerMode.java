package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 25.03.2017.
 */
public enum FmodSpeakerMode {
    /**
     * FMOD_SPEAKERMODE_DEFAULT
     */
    FMOD_SPEAKERMODE_DEFAULT(0),
    /**
     * FMOD_SPEAKERMODE_RAW
     */
    FMOD_SPEAKERMODE_RAW(1),
    /**
     * FMOD_SPEAKERMODE_MONO
     */
    FMOD_SPEAKERMODE_MONO(2),
    /**
     * FMOD_SPEAKERMODE_STEREO
     */
    FMOD_SPEAKERMODE_STEREO(3),
    /**
     * FMOD_SPEAKERMODE_QUAD
     */
    FMOD_SPEAKERMODE_QUAD(4),
    /**
     * FMOD_SPEAKERMODE_SURROUND
     */
    FMOD_SPEAKERMODE_SURROUND(5),
    /**
     * FMOD_SPEAKERMODE_5POINT1
     */
    FMOD_SPEAKERMODE_5POINT1(6),
    /**
     * FMOD_SPEAKERMODE_7POINT1
     */
    FMOD_SPEAKERMODE_7POINT1(7),

    /**
     * FMOD_SPEAKERMODE_MAX
     */
    FMOD_SPEAKERMODE_MAX(8),
    /**
     * FMOD_SPEAKERMODE_FORCEINT
     */
    FMOD_SPEAKERMODE_FORCEINT(65536);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static FmodSpeakerMode getEnumValue(int value){
        FmodSpeakerMode[] values = FmodSpeakerMode.values();
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
    FmodSpeakerMode(int value){
        this.value = value;
    }

    /**
     * @return Gets the enums integer value
     */
    public int getValue(){
        return value;
    }
}
