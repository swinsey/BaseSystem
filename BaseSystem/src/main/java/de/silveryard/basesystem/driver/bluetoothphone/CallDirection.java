package de.silveryard.basesystem.driver.bluetoothphone;

/**
 * Created by silveryard on 03.06.17.
 */
public enum  CallDirection {
    INCOMING(0),
    OUTGOING(1),
    UNKNOWN(2);

    public static CallDirection getEnumValue(int value){
        CallDirection[] values = CallDirection.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].value == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    CallDirection(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
