package de.silveryard.basesystem.driver.bluetoothphone.kernel;

/**
 * Created by silveryard on 29.05.17.
 */
public enum PhonebookType {
    CONTACTS(0),
    HISTORY_INCOMING(1),
    HISTORY_OUTGOING(2),
    HISTORY_MISSED(3);

    public static PhonebookType getEnumValue(int value){
        PhonebookType[] values = PhonebookType.values();
        for(int i = 0; i < values.length; i++) {
            if (values[i].getValue() == value) {
                return values[i];
            }
        }
        return null;
    }

    private int value;

    PhonebookType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
