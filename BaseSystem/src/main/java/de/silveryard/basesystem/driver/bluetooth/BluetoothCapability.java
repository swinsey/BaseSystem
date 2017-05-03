package de.silveryard.basesystem.driver.bluetooth;

/**
 * Created by silveryard on 02.05.17.
 */
public enum BluetoothCapability {
    DISPLAY_ONLY("DisplayOnly"),
    DISPLAY_YES_NO("DisplayYesNo"),
    KEYBOARD_DISPLAY("KeyboardDisplay"),
    KEYBOARD_ONLY("KeyboardOnly"),
    NO_INPUT_NO_OUTPUT("NoInputNoOutput");

    private String value;

    BluetoothCapability(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
