package de.silveryard.basesystem.sdk.kernel.bluetooth.phone;

/**
 * Created by silveryard on 29.05.17.
 */
public enum NetworkStatus {
    /**
     * The device is currently not registered in any network
     */
    UNREGISTERED(0),
    /**
     * The device is registered in a network
     */
    REGISTERED(1),
    /**
     * The device is currently searching for a network
     */
    SEARCHING(2),
    /**
     * The devices connection has been denied
     */
    DENIED(3),
    /**
     * The devices status is unknown
     */
    UNKNOWN(4),
    /**
     * The device is connected but uses roaming
     */
    ROAMING(5);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static NetworkStatus getEnumValue(int value){
        NetworkStatus[] values = NetworkStatus.values();
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
    NetworkStatus(int value){
        this.value = value;
    }

    /**
     * Returns the internal integer value
     * @return Integer value
     */
    public int getValue(){
        return value;
    }
}

