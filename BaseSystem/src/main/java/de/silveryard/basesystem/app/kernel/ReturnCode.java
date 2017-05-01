package de.silveryard.basesystem.app.kernel;

/**
 * Created by Sebif on 13.03.2017.
 */
public enum ReturnCode {
    /**
     * Everything went fine
     */
    OK(1),
    /**
     * An error occured
     */
    ERROR(2),
    /**
     * Given functionality is not included
     */
    NOT_IMPLEMENTED(3),
    /**
     * Response is not in the right format
     */
    INVALID_RESPONSE(4),
    /**
     * Message is not in the right format
     */
    INVALID_MESSAGE(5);

    /**
     * Converts an integer to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static ReturnCode getEnumValue(int value){
        ReturnCode[] values = ReturnCode.values();
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
    ReturnCode(int value){
        this.value = value;
    }

    /**
     * @return Returns the enums integer value
     */
    public int getValue(){
        return value;
    }
}
