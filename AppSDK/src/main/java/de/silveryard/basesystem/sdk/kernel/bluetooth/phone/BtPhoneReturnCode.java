package de.silveryard.basesystem.sdk.kernel.bluetooth.phone;

/**
 * Created by silveryard on 29.05.17.
 */
public enum BtPhoneReturnCode {
    OK(0),
    ERROR(1),
    INVALID_ID(2),
    INVALID_INDEX(3);

    public static BtPhoneReturnCode getEnumValue(int value){
        BtPhoneReturnCode[] values = BtPhoneReturnCode.values();
        for(int i = 0; i < values.length; i++) {
            if (values[i].getValue() == value) {
                return values[i];
            }
        }
        return null;
    }

    private int value;

    BtPhoneReturnCode(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

