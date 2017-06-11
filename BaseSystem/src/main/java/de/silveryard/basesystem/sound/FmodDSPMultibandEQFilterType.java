package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 11.06.2017.
 */
public enum FmodDSPMultibandEQFilterType {
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_DISABLED
     */
    DISABLED(0),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_LOWPASS_12DB
     */
    LOWPASS_12DB(1),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_LOWPASS_24DB
     */
    LOWPASS_24DB(2),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_LOWPASS_48DB
     */
    LOWPASS_48DB(3),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_HIGHPASS_12DB
     */
    HIGHPASS_12DB(4),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_HIGHPASS_24DB
     */
    HIGHPASS_24DB(5),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_HIGHPASS_48DB
     */
    HIGHPASS_48DB(6),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_LOWSHELF
     */
    LOWSHELF(7),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_HIGHSHELF
     */
    HIGHSHELF(8),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_PEAKING
     */
    PEAKING(9),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_BANDPASS
     */
    BANDPASS(10),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_NOTCH
     */
    NOTCH(11),
    /**
     * FMOD_DSP_MULTIBAND_EQ_FILTER_ALLPASS
     */
    ALLPASS(2);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static FmodDSPMultibandEQFilterType getEnumValue(int value){
        FmodDSPMultibandEQFilterType[] values = FmodDSPMultibandEQFilterType.values();
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
    FmodDSPMultibandEQFilterType(int value){
        this.value = value;
    }

    /**
     * @return Gets the enums integer value
     */
    public int getValue(){
        return value;
    }
}
