package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 11.06.2017.
 */
public enum FmodDSPMultibandEQParam {
    /**
     * FMOD_DSP_MULTIBAND_EQ_A_FILTER
     */
    A_FILTER(0),
    /**
     * FMOD_DSP_MULTIBAND_EQ_A_FREQUENCY
     */
    A_FREQUENCY(1),
    /**
     * FMOD_DSP_MULTIBAND_EQ_A_Q
     */
    A_Q(2),
    /**
     * FMOD_DSP_MULTIBAND_EQ_A_GAIN
     */
    A_GAIN(3),
    /**
     * FMOD_DSP_MULTIBAND_EQ_B_FILTER
     */
    B_FILTER(4),
    /**
     * FMOD_DSP_MULTIBAND_EQ_B_FREQUENCY
     */
    B_FREQUENCY(5),
    /**
     * FMOD_DSP_MULTIBAND_EQ_B_Q
     */
    B_Q(6),
    /**
     * FMOD_DSP_MULTIBAND_EQ_B_GAIN
     */
    B_GAIN(7),
    /**
     * FMOD_DSP_MULTIBAND_EQ_C_FILTER
     */
    C_FILTER(8),
    /**
     * FMOD_DSP_MULTIBAND_EQ_C_FREQUENCY
     */
    C_FREQUENCY(9),
    /**
     * FMOD_DSP_MULTIBAND_EQ_C_Q
     */
    C_Q(10),
    /**
     * FMOD_DSP_MULTIBAND_EQ_C_GAIN
     */
    C_GAIN(11),
    /**
     * FMOD_DSP_MULTIBAND_EQ_D_FILTER
     */
    D_FILTER(12),
    /**
     * FMOD_DSP_MULTIBAND_EQ_D_FREQUENCY
     */
    D_FREQUENCY(13),
    /**
     * FMOD_DSP_MULTIBAND_EQ_D_Q
     */
    D_Q(14),
    /**
     * FMOD_DSP_MULTIBAND_EQ_D_GAIN
     */
    D_GAIN(15),
    /**
     * FMOD_DSP_MULTIBAND_EQ_E_FILTER
     */
    E_FILTER(16),
    /**
     * FMOD_DSP_MULTIBAND_EQ_E_FREQUENCY
     */
    E_FREQUENCY(17),
    /**
     * FMOD_DSP_MULTIBAND_EQ_E_Q
     */
    E_Q(18),
    /**
     * FMOD_DSP_MULTIBAND_EQ_E_GAIN
     */
    E_GAIN(19);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static FmodDSPMultibandEQParam getEnumValue(int value){
        FmodDSPMultibandEQParam[] values = FmodDSPMultibandEQParam.values();
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
    FmodDSPMultibandEQParam(int value){
        this.value = value;
    }

    /**
     * @return Gets the enums integer value
     */
    public int getValue(){
        return value;
    }
}
