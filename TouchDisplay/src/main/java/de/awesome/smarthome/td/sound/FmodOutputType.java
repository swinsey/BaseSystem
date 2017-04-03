package de.awesome.smarthome.td.sound;

/**
 * Created by Sebif on 25.03.2017.
 */
public enum FmodOutputType {
    AUTODETECT(0),

    UNKNOWN(1),
    NOSOUND(2),
    WAVWRITER(3),
    NOSOUND_NRT(4),
    WAVWRITER_NRT(5),

    DSOUND(6),
    WINMM(7),
    WASAPI(8),
    ASIO(9),
    PULSEAUDIO(10),
    ALSA(11),
    COREAUDIO(12),
    XAUDIO(13),
    PS3(14),
    AUDIOTRACK(15),
    OPENSL(16),
    WIIU(17),
    AUDIOOUT(18),
    AUDIO3D(19),
    ATMOS(20),
    WEBAUDIO(21),
    NNAUDIO(22),

    MAX(23),
    FORCEINT(65536);

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

    FmodOutputType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
