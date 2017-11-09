package de.silveryard.logviewer.Model;

/**
 * Created by Sebif on 13.04.2017.
 */
public enum LogMessageType {
    OUT(1),
    ERROR(2);

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

    LogMessageType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
