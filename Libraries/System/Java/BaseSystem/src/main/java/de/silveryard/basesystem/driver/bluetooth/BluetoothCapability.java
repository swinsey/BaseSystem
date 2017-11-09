package de.silveryard.basesystem.driver.bluetooth;

/**
 * Created by silveryard on 02.05.17.
 */
public enum BluetoothCapability {
    /**
     * The device can only display information.
     * No input
     */
    DISPLAY_ONLY("DisplayOnly"),
    /**
     * The device can display information and
     * can either confirm or deny a connection request
     */
    DISPLAY_YES_NO("DisplayYesNo"),
    /**
     * The device can display information
     * and keyboard input can be made
     */
    KEYBOARD_DISPLAY("KeyboardDisplay"),
    /**
     * The device cannot display information but
     * keyboard input can be made
     */
    KEYBOARD_ONLY("KeyboardOnly"),
    /**
     * The device can neither display device
     * nor get input of any type
     */
    NO_INPUT_NO_OUTPUT("NoInputNoOutput");

    private String value;

    /**
     * Constructor
     * @param value String value
     */
    BluetoothCapability(String value){
        this.value = value;
    }

    /**
     * Returns the internal string value
     * @return String value
     */
    public String getValue(){
        return value;
    }
}
