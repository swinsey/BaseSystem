package de.silveryard.basesystem.sdk.kernel.bluetooth.audio;

/**
 * Created by silveryard on 11.05.17.
 */
public enum BtAudioReturnCode {
    /**
     * Everything went fine
     */
    OK(0),
    /**
     * The spcified id is not valid
     */
    INVALID_ID(1);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static BtAudioReturnCode getEnumValue(int value){
        BtAudioReturnCode[] values = BtAudioReturnCode.values();
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
    BtAudioReturnCode(int value){
        this.value = value;
    }

    /**
     * Returns the enums integer value
     * @return Integer value
     */
    public int getValue(){
        return value;
    }
}