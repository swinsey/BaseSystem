package de.silveryard.basesystem.sdk.kernel.bluetooth.phone;

/**
 * Created by silveryard on 29.05.17.
 */
public enum NetworkStatus {
    UNREGISTERED(0),
    REGISTERED(1),
    SEARCHING(2),
    DENIED(3),
    UNKNOWN(4),
    ROAMING(5);

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

    NetworkStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}

