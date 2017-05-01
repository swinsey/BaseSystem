package de.silveryard.basesystem.sdk.kernel.sound;

/**
 * Created by Sebif on 07.04.2017.
 */
public enum FmodSoundType {
    /**
     * FMOD_SOUNDTYPE_UNKNOWN
     */
    TYPE_UNKNOWN(0),
    /**
     * FMOD_SOUNDTYPE_AIFF
     */
    TYPE_AIFF(1),
    /**
     * FMOD_SOUNDTYPE_ASF
     */
    TYPE_ASF(2),
    /**
     * FMOD_SOUNDTYPE_DLS
     */
    TYPE_DLS(3),
    /**
     * FMOD_SOUNDTYPE_FLAC
     */
    TYPE_FLAC(4),
    /**
     * FMOD_SOUNDTYPE_FSB
     */
    TYPE_FSB(5),
    /**
     * FMOD_SOUNDTYPE_IT
     */
    TYPE_IT(6),
    /**
     * FMOD_SOUNDTYPE_MIDI
     */
    TYPE_MIDI(7),
    /**
     * FMOD_SOUNDTYPE_MOD
     */
    TYPE_MOD(8),
    /**
     * FMOD_SOUNDTYPE_MPEG
     */
    TYPE_MPEG(9),
    /**
     * FMOD_SOUNDTYPE_OGGVORBIS
     */
    TYPE_OGGVORBIS(10),
    /**
     * FMOD_SOUNDTYPE_PLAYLIST
     */
    TYPE_PLAYLIST(11),
    /**
     * FMOD_SOUNDTYPE_RAW
     */
    TYPE_RAW(12),
    /**
     * FMOD_SOUNDTYPE_S3M
     */
    TYPE_S3M(13),
    /**
     * FMOD_SOUNDTYPE_USER
     */
    TYPE_USER(14),
    /**
     * FMOD_SOUNDTYPE_WAV
     */
    TYPE_WAV(15),
    /**
     * FMOD_SOUNDTYPE_XM
     */
    TYPE_XM(16),
    /**
     * FMOD_SOUNDTYPE_XMA
     */
    TYPE_XMA(17),
    /**
     * FMOD_SOUNDTYPE_AUDIOQUEUE
     */
    TYPE_AUDIOQUEUE(18),
    /**
     * FMOD_SOUNDTYPE_AT9
     */
    TYPE_AT9(19),
    /**
     * FMOD_SOUNDTYPE_VORBIS
     */
    TYPE_VORBIS(20),
    /**
     * FMOD_SOUNDTYPE_MEDIA_FOUNDATION
     */
    TYPE_MEDIA_FOUNDATION(21),
    /**
     * FMOD_SOUNDTYPE_MEDIACODEC
     */
    TYPE_MEDIACODEC(22),
    /**
     * FMOD_SOUNDTYPE_FADPCM
     */
    TYPE_FADPCM(23),

    /**
     * FMOD_SOUNDTYPE_MAX
     */
    TYPE_MAX(24),
    /**
     * FMOD_SOUNDTYPE_FORCEINT
     */
    TYPE_FORCEINT(65536);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static FmodSoundType getEnumValue(int value){
        FmodSoundType[] values = FmodSoundType.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].getValue() == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    /**
     * Constuctor
     * @param value Integer value
     */
    FmodSoundType(int value){
        this.value = value;
    }

    /**
     * @return Gets the enums integer value
     */
    public int getValue(){
        return value;
    }
}

