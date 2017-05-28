package de.silveryard.basesystem.driver.bluetoothphone.ofono;

/**
 * Created by silveryard on 28.05.17.
 */
public class NetworkRegistration {
    private de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.NetworkRegistration networkRegistration;

    public NetworkRegistration(de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.NetworkRegistration networkRegistration){
        this.networkRegistration = networkRegistration;
    }

    /**
     * Contains the current operator name, suitable for
     * display on the idle screen or an empty string if
     * not registered to a network.
     *
     * @return
     */
    public String getName(){
        return (String)networkRegistration.GetProperties().get("Name").getValue();
    }

    /**
     * The current registration status of a modem.
     *
     * The possible values are:
     * "unregistered"  Not registered to any network
     * "registered"    Registered to home network
     * "searching"     Not registered, but searching
     * "denied"        Registration has been denied
     * "unknown"       Status is unknown
     * "roaming" Registered, but roaming
     *
     * @return
     */
    public String getStatus(){
       return (String)networkRegistration.GetProperties().get("Status").getValue();
    }

    /**
     * Contains the current signal strength as a percentage
     * between 0-100 percent.
     *
     * @return
     */
    public byte getStrength(){
        return (Byte)networkRegistration.GetProperties().get("Strength").getValue();
    }
}
