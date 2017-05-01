package de.silveryard.basesystem.driver.bluetooth.kernel;

/**
 * Created by silveryard on 01.05.17.
 */
public enum BtReturnCode {
    OK(0),
    INVALID_ID(1);

    public static BtReturnCode getEnumValue(int value){
        BtReturnCode[] values = BtReturnCode.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].value == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    BtReturnCode(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
