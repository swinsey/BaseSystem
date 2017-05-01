package de.silveryard.basesystem.logging;

/**
 * Created by Sebif on 12.04.2017.
 */
public enum LogMessageType {
    /**
     * The message got logged via System.out
     */
    OUT(1),
    /**
     * The message got logged via System.err
     */
    ERROR(2);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static LogMessageType getEnumValue(int value){
        LogMessageType[] values = LogMessageType.values();
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
    LogMessageType(int value){
        this.value = value;
    }

    /**
     * Returns the integer value of this enum value
     * @return Integer value
     */
    public int getValue(){
        return value;
    }
}
