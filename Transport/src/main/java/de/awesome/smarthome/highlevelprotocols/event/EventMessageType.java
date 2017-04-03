package de.awesome.smarthome.highlevelprotocols.event;

/**
 * Created by Sebif on 29.03.2017.
 */
public enum EventMessageType {
    /**
     * Registers a listener to an event server
     */
    REGISTER(1),
    /**
     * Unregisters a listener from an event server
     */
    UNREGISTER(2),
    /**
     * Sent from event server to listener when event is called
     */
    CALL(3);

    /**
     * Converts an integer to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static EventMessageType getEnumValue(int value){
        EventMessageType[] values = EventMessageType.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].value == value){
                return values[i];
            }
        }

        return null;
    }

    private int value;

    /**
     * Constructor
     * @param value Enum value
     */
    EventMessageType(int value){
        this.value = value;
    }

    /**
     * @return Returns the enums integer value
     */
    public int getValue(){
        return value;
    }
}
