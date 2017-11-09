package de.silveryard.basesystem.driver.bluetoothphone;

/**
 * Created by silveryard on 03.06.17.
 */
public enum CallState {
    DIALING(0),
    INCOMING(1),
    ACTIVE(2),
    UNKNOWN(3);

    public static CallState getEnumValue(int value){
        CallState[] values = CallState.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].getValue() == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    CallState(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
