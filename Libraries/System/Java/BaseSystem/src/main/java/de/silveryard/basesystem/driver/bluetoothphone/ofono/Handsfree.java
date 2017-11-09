package de.silveryard.basesystem.driver.bluetoothphone.ofono;

/**
 * Created by silveryard on 28.05.17.
 */
public class Handsfree {
    private de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.Handsfree handsfree;

    public Handsfree(de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.Handsfree handsfree){
        this.handsfree = handsfree;
    }

    /**
     * The current charge level of the battery.  The value
     * can be between 0 and 5 respectively.
     *
     * @return
     */
    public byte getBatteryChargeLevel(){
        return (Byte)handsfree.GetProperties().get("BatteryChargeLevel").getValue();
    }
}
