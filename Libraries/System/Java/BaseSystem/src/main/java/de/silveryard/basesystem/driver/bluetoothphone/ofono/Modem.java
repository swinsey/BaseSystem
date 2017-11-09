package de.silveryard.basesystem.driver.bluetoothphone.ofono;

import java.util.Vector;

/**
 * Created by silveryard on 28.05.17.
 */
public class Modem {
    private de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.Modem modem;

    public Modem(de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.Modem modem){
        this.modem = modem;
    }

    /**
     * Boolean representing the power state of the modem device.
     * @return
     */
    public boolean getPowered(){
        return (Boolean)modem.GetProperties().get("Powered").getValue();
    }
    /**
     * Boolean representing the rf state of the modem.
     * Online is false in flight mode.
     * @return
     */
    public boolean getOnline(){
        return (Boolean)modem.GetProperties().get("Online").getValue();
    }

    /**
     * Boolean representing the lock state of the modem.
     * Setting it to true, makes the calling application hold
     * the modem lock and power it down. Setting to false
     * makes the it release the modem lock. Only the
     * application that holds the lock can power up the modem.
     * If the the application exits Lockdown is set to false.
     *
     * @return
     */
    public boolean getLockdown(){
        return (Boolean)modem.GetProperties().get("Lockdown").getValue();
    }

    /**
     * Friendly name of the modem device.  In the case of
     * Bluetooth devices (e.g. Type="sap" or "hfp") this
     * corresponds to the remote device name or it's alias.
     *
     * @return
     */
    public String getName(){
        return (String)modem.GetProperties().get("Name").getValue();
    }

    /**
     * String represeting the serial number of the modem
     * device.
     *
     * This is usually obtained by using the +CGSN AT command.
     *
     * In the case of Bluetooth devices (e.g. Type="sap" or
     * "hfp") this corresponds to the Bluetooth Device
     * Address of the remote device.
     *
     * @return
     */
    public String getSerial(){
        return (String)modem.GetProperties().get("Serial").getValue();
    }

    /**
     * Set of interfaces currently supported by the mode
     * device. The set depends on the state of the device
     * (registration status, SIM inserted status,
     * network capabilities, device capabilities, etc.)
     *
     * Each string in the array is an interface from the
     * set supported by oFono by modem objects.  The set
     * includes:
     * org.ofono.AssistedSatelliteNavigation
     * org.ofono.AudioSettings
     * org.ofono.CallBarring
     * org.ofono.CallForwarding
     * org.ofono.CallMeter
     * org.ofono.CallSettings
     * org.ofono.CallVolume
     * org.ofono.CellBroadcast
     * org.ofono.Handsfree
     * org.ofono.LocationReporting
     * org.ofono.MessageManager
     * org.ofono.MessageWaiting
     * org.ofono.NetworkRegistration
     * org.ofono.Phonebook
     * org.ofono.PushNotification
     * org.ofono.RadioSettings
     * org.ofono.SimManager
     * org.ofono.SmartMessaging
     * org.ofono.SimToolkit
     * org.ofono.SupplementaryServices
     * org.ofono.TextTelephony
     * org.ofono.VoiceCallManager
     *
     * It is possible for extension interfaces (e.g. APIs
     * that are not part of the oFono standard API) to be
     * available in this list.  Also note that child object
     * interfaces, such as org.ofono.Message,
     * org.ofono.VoiceCall, org.ofono.NetworkOperator,
     * will never be part of this list.
     *
     * Please note that the set of Interfaces can and does
     * change frequently (e.g. due to change in Powered
     * and Online properties.)  If a given interface is no
     * longer available, then the application should assume
     * that all data for that interface has become invalid,
     * e.g. calls have been dropped, network registration
     * lost, etc.
     *
     * The set of possible interfaces supported is also
     * dependent on the modem hardware and driver support.
     * For example, HFP devices only support
     * org.ofono.VoiceCallManager,
     * org.ofono.NetworkRegistration, org.ofono.Handsfree and
     * org.ofono.CallVolume interfaces.
     *
     * @return
     */
    public Vector<String> getInterfaces(){
        return (Vector<String>)modem.GetProperties().get("Interfaces").getValue();
    }
}
