package de.silveryard.basesystem.driver.bluetoothphone.kernel;

/**
 * Created by silveryard on 29.05.17.
 */
public enum PhonebookType {
    /**
     * Defines the phonebook where all contacts are stored
     */
    CONTACTS(0),
    /**
     * Defines the phonebook that contains all incoming calls
     */
    HISTORY_INCOMING(1),
    /**
     * Defines the phonebook that contains all outgoing calls
     */
    HISTORY_OUTGOING(2),
    /**
     * Defines the phonebook that contains all missed calls
     */
    HISTORY_MISSED(3);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
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

    /**
     * Constructor
     * @param value Integer value
     */
    PhonebookType(int value){
        this.value = value;
    }

    /**
     * Returns the enums internal integer value
     * @return Internal integer value
     */
    public int getValue(){
        return value;
    }
}
