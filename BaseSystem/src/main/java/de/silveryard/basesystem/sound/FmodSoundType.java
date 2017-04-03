package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 27.03.2017.
 */
public enum FmodSoundType {
    TYPE_UNKNOWN(0),
    TYPE_AIFF(1),
    TYPE_ASF(2),
    TYPE_DLS(3),
    TYPE_FLAC(4),
    TYPE_FSB(5),
    TYPE_IT(6),
    TYPE_MIDI(7),
    TYPE_MOD(8),
    TYPE_MPEG(9),
    TYPE_OGGVORBIS(10),
    TYPE_PLAYLIST(11),
    TYPE_RAW(12),
    TYPE_S3M(13),
    TYPE_USER(14),
    TYPE_WAV(15),
    TYPE_XM(16),
    TYPE_XMA(17),
    TYPE_AUDIOQUEUE(18),
    TYPE_AT9(19),
    TYPE_VORBIS(20),
    TYPE_MEDIA_FOUNDATION(21),
    TYPE_MEDIACODEC(22),
    TYPE_FADPCM(23),

    TYPE_MAX(24),
    TYPE_FORCEINT(65536);

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

    FmodSoundType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
