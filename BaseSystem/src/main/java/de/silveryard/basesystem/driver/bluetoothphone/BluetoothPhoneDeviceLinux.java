package de.silveryard.basesystem.driver.bluetoothphone;

import de.silveryard.basesystem.driver.bluetoothphone.dbus.obex.Client1;
import de.silveryard.basesystem.driver.bluetoothphone.dbus.obex.PhonebookAccess1;
import de.silveryard.basesystem.driver.bluetoothphone.ofono.CallVolume;
import de.silveryard.basesystem.driver.bluetoothphone.ofono.Handsfree;
import de.silveryard.basesystem.driver.bluetoothphone.ofono.Modem;
import de.silveryard.basesystem.driver.bluetoothphone.ofono.NetworkRegistration;
import org.apache.commons.lang3.SystemUtils;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.Path;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by silveryard on 28.05.17.
 */
final class BluetoothPhoneDeviceLinux extends BluetoothPhoneDevice {
    private static final String BUS_OFONO = "org.ofono";
    private static final String BUS_OBEX = "org.bluez.obex";

    private final DBusConnection systemConnection;
    private final DBusConnection sessionsConnection;

    private final String ofonoObjectPath;
    private final Modem modem;
    private final CallVolume callVolume;
    private final Handsfree handsfree;
    private final NetworkRegistration networkRegistration;

    private final Client1 obexClient;
    private final String obexObjectPath;

    private final Phonebook phonebookContacts;
    private final Phonebook phonebookIncomingHistory;
    private final Phonebook phoneBookOutgoingHistory;
    private final Phonebook phoneBookMissedHistory;

    public BluetoothPhoneDeviceLinux(DBusConnection systemConnection, DBusConnection sessionsConnection, Client1 obexClient, String ofonoObjectPath){
        this.systemConnection = systemConnection;
        this.sessionsConnection = sessionsConnection;
        this.obexClient = obexClient;
        this.ofonoObjectPath = ofonoObjectPath;

        try {
            modem = new Modem(systemConnection.getRemoteObject(BUS_OFONO, ofonoObjectPath, de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.Modem.class));
        }catch(DBusException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Vector<String> interfaces = modem.getInterfaces();

        //Check for CallVolume Interface
        if(interfaces.contains("org.ofono.CallVolume")){
            try{
                callVolume = new CallVolume(systemConnection.getRemoteObject(BUS_OFONO, ofonoObjectPath, de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.CallVolume.class));
            }catch(DBusException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else{
            callVolume = null;
        }

        //Check for Handsfree Interface
        if(interfaces.contains("org.ofono.Handsfree")){
            try{
                handsfree = new Handsfree(systemConnection.getRemoteObject(BUS_OFONO, ofonoObjectPath, de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.Handsfree.class));
            }catch(DBusException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else{
            handsfree = null;
        }

        //Check for NetworkRegistrationInterface
        if(interfaces.contains("org.ofono.NetworkRegistration")){
            try{
                networkRegistration = new NetworkRegistration(systemConnection.getRemoteObject(BUS_OFONO, ofonoObjectPath, de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.NetworkRegistration.class));
            }catch(DBusException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else{
            networkRegistration = null;
        }

        //Try to fetch phonebook data
        String tmpObexObjectPath = null;
        Phonebook tmpPhonebookContacts = null;
        Phonebook tmpPhonebookIncomingHistory = null;
        Phonebook tmpPhoneBookOutgoingHistory = null;
        Phonebook tmpPhoneBookMissedHistory = null;
        try{
            //Create new obex session
            Map<String, Variant<?>> options = new HashMap<>(1);
            options.put("Target", new Variant<String>("pbap"));
            tmpObexObjectPath = obexClient.CreateSession(getAddress(), options).getPath();

            PhonebookAccess1 phonebookAccess = sessionsConnection.getRemoteObject(BUS_OBEX, tmpObexObjectPath, PhonebookAccess1.class);
            String tmpDir = SystemUtils.JAVA_IO_TMPDIR;

            //Import phonebook
            phonebookAccess.Select("int", "pb");
            String tmpFileContacts = tmpDir + "/phonebook_contacts.txt";
            phonebookAccess.PullAll(tmpFileContacts, new HashMap<>(0));

            //Import Incoming Call History
            phonebookAccess.Select("int", "ich");
            String tmpFileIncoming = tmpDir + "/phonebook_incoming.txt";

            //Import Outgoing Call History
            phonebookAccess.Select("int", "och");
            String tmpFileOutgoing = tmpDir + "/phonebook_outgoing.txt";
            phonebookAccess.PullAll(tmpFileOutgoing, new HashMap<>(0));

            //Import Missing Call History
            phonebookAccess.Select("int", "mch");
            String tmpFileMissing = tmpDir + "/phonebook_missing.txt";
            phonebookAccess.PullAll(tmpFileMissing, new HashMap<>(0));

            Thread.sleep(5000); //TODO find a better way to determine if operation has finished

            tmpPhonebookContacts = new ObexPhonebook(tmpFileContacts);
            tmpPhonebookIncomingHistory = new ObexPhonebook(tmpFileIncoming);
            tmpPhoneBookOutgoingHistory = new ObexPhonebook(tmpFileOutgoing);
            tmpPhoneBookMissedHistory = new ObexPhonebook(tmpFileMissing);
        }catch(Exception e){
            e.printStackTrace();
        }
        obexObjectPath = tmpObexObjectPath;
        phonebookContacts = tmpPhonebookContacts;
        phonebookIncomingHistory = tmpPhonebookIncomingHistory;
        phoneBookOutgoingHistory = tmpPhoneBookOutgoingHistory;
        phoneBookMissedHistory = tmpPhoneBookMissedHistory;
    }

    @Override
    protected void onInvalidate(){
        try{
            obexClient.RemoveSession(new Path(obexObjectPath));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getOfonoObjectPath(){
        return ofonoObjectPath;
    }

    //////
    ///General Data
    //////

    @Override
    public String getName() {
        return modem.getName();
    }
    @Override
    public String getAddress() {
        return modem.getSerial();
    }

    //////
    ///Volume
    //////

    @Override
    public boolean supportsVolumeInformation() {
        return callVolume != null;
    }
    @Override
    protected float getVolumeSpeakerInternal() {
        return callVolume.getSpeakerVolume() / 100.0f;
    }
    @Override
    protected void setVolumeSpeakerInternal(float volume) {
        callVolume.setSpeakerVolume((byte)(volume * 100));
    }
    @Override
    protected float getVolumeMicrophoneInternal() {
        return callVolume.getMicrophoneVolume() / 100.0f;
    }
    @Override
    protected void setVolumeMicrophoneInternal(float volume) {
        callVolume.setMicrophoneVolume((byte)(volume * 100));
    }
    @Override
    protected boolean getVolumeMutedInternal() {
        return callVolume.getMuted();
    }
    @Override
    protected void setVolumeMutedInternal(boolean muted) {
        callVolume.setMuted(muted);
    }

    //////
    ///Battery
    //////

    @Override
    public boolean supportsBatteryChargeLevel() {
        return handsfree != null;
    }
    @Override
    protected float getBatteryChargeLevelInternal() {
        return handsfree.getBatteryChargeLevel() / 5.0f;
    }

    //////
    ///Network
    //////

    @Override
    public boolean supportsNetworkInformation() {
        return networkRegistration != null;
    }
    @Override
    protected String getNetworkNameInternal() {
        return networkRegistration.getName();
    }
    @Override
    protected float getNetworkStrengthInternal() {
        return networkRegistration.getStrength() / 100.0f;
    }
    @Override
    protected NetworkStatus getNetworkStatusInternal() {
        String status = networkRegistration.getStatus();

        switch(status){
            case "unregistered":
                return NetworkStatus.UNREGISTERED;
            case "registered":
                return NetworkStatus.REGISTERED;
            case "searching":
                return NetworkStatus.SEARCHING;
            case "denied":
                return NetworkStatus.DENIED;
            case "unknown":
                return NetworkStatus.UNKNOWN;
            case "roaming":
                return NetworkStatus.ROAMING;
            default:
                System.out.println("BT Phone: Unknown network status");
                return NetworkStatus.UNKNOWN;
        }
    }

    //////
    ///Phonebook
    //////

    @Override
    public boolean supportsContactsPhonebook() {
        return phonebookContacts != null;
    }
    @Override
    public boolean supportsIncomingHistoryPhonebook() {
        return phonebookIncomingHistory != null;
    }
    @Override
    public boolean supportsOutgoingHistoryPhonebook() {
        return phoneBookOutgoingHistory != null;
    }
    @Override
    public boolean supportsMissedHistoryPhonebook() {
        return phoneBookMissedHistory != null;
    }

    @Override
    protected Phonebook getContactsPhonebookInternal() {
        return phonebookContacts;
    }
    @Override
    protected Phonebook getIncomingHistoryPhonebookInternal() {
        return phonebookIncomingHistory;
    }
    @Override
    protected Phonebook getOutgoingHistoryPhonebookInternal() {
        return phoneBookOutgoingHistory;
    }
    @Override
    protected Phonebook getMissedHistoryPhonebookInternal() {
        return phoneBookMissedHistory;
    }
}
