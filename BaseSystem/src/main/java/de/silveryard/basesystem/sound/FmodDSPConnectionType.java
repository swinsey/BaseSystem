package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 06.06.2017.
 */
public enum FmodDSPConnectionType {
    /**
     * Default connection type.         Audio is mixed from the input to the output DSP's audible buffer.
     */
    FMOD_DSPCONNECTION_TYPE_STANDARD(0),
    /**
     * Sidechain connection type.       Audio is mixed from the input to the output DSP's sidechain buffer.
     */
    FMOD_DSPCONNECTION_TYPE_SIDECHAIN(1),
    /**
     * Send connection type.            Audio is mixed from the input to the output DSP's audible buffer, but the input is NOT executed, only copied from.  A standard connection or sidechain needs to make an input execute to generate data.
     */
    FMOD_DSPCONNECTION_TYPE_SEND(2),
    /**
     * Send sidechain connection type.  Audio is mixed from the input to the output DSP's sidechain buffer, but the input is NOT executed, only copied from.  A standard connection or sidechain needs to make an input execute to generate data.
     */
    FMOD_DSPCONNECTION_TYPE_SEND_SIDECHAIN(3),

    /**
     * Maximum number of DSP connection types supported.
     */
    FMOD_DSPCONNECTION_TYPE_MAX(4),
    /**
     * Makes sure this enum is signed 32bit.
     */
    FMOD_DSPCONNECTION_TYPE_FORCEINT(65536);

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
    FmodDSPConnectionType(int value){
        this.value = value;
    }

    /**
     * @return Gets the enums integer value
     */
    public int getValue(){
        return value;
    }
}
