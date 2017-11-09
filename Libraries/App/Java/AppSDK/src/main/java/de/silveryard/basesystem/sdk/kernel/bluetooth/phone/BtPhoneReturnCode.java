package de.silveryard.basesystem.sdk.kernel.bluetooth.phone;

/**
 * Created by silveryard on 29.05.17.
 */
public enum BtPhoneReturnCode {
    /**
     * Everything went fine
     */
    OK(0),
    /**
     * Some error occured
     */
    ERROR(1),
    /**
     * An invalid id has been supplied
     */
    INVALID_ID(2),
    /**
     * An invalid index has been supplied
     */
    INVALID_INDEX(3);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
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

    /**
     * Constructor
     * @param value Integer value
     */
    BtPhoneReturnCode(int value){
        this.value = value;
    }

    /**
     * Returns the integers internal value
     * @return Integer value
     */
    public int getValue(){
        return value;
    }
}

