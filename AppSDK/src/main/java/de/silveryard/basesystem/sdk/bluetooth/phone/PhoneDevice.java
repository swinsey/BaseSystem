package de.silveryard.basesystem.sdk.bluetooth.phone;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.bluetooth.phone.BtPhoneReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.phone.NetworkStatus;
import de.silveryard.basesystem.sdk.kernel.bluetooth.phone.PhonebookType;

import static de.silveryard.basesystem.sdk.kernel.bluetooth.phone.BtPhoneDevice.*;

/**
 * Created by silveryard on 30.05.17.
 */
public final class PhoneDevice {
    private final int id;

    private final Phonebook phonebookContacts;
    private final Phonebook phonebookIncomingHistory;
    private final Phonebook phonebookOutgoingHistory;
    private final Phonebook phonebookMissedHistory;

    private final Wrapper<ReturnCode> returnCodeWrapper;
    private final Wrapper<BtPhoneReturnCode> btPhoneReturnCodeWrapper;
    private final Wrapper<String> stringWrapper;
    private final Wrapper<Boolean> booleanWrapper;
    private final Wrapper<Float> floatWrapper;
    private final Wrapper<NetworkStatus> networkStatusWrapper;

    /**
     * Constructor
     * @param id Internal phone id
     */
    public PhoneDevice(int id){
        this.id = id;

        phonebookContacts = new Phonebook(this, PhonebookType.CONTACTS);
        phonebookIncomingHistory = new Phonebook(this, PhonebookType.HISTORY_INCOMING);
        phonebookOutgoingHistory = new Phonebook(this, PhonebookType.HISTORY_OUTGOING);
        phonebookMissedHistory = new Phonebook(this, PhonebookType.HISTORY_MISSED);

        returnCodeWrapper = new Wrapper<>();
        btPhoneReturnCodeWrapper = new Wrapper<>();
        stringWrapper = new Wrapper<>();
        booleanWrapper = new Wrapper<>();
        floatWrapper = new Wrapper<>();
        networkStatusWrapper = new Wrapper<>();
    }

    /**
     * Returns the internal phone id
     * @return Phone id
     */
    public int getId(){
        return id;
    }

    //////
    ///General
    //////

    /**
     * Returns the devices name
     * @return Name
     */
    public synchronized String getName(){
        systemCallBtPhoneDeviceGetName(id, returnCodeWrapper, btPhoneReturnCodeWrapper, stringWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }

    /**
     * Returns the devices address
     * @return Address
     */
    public synchronized String getAddress(){
        systemCallBtPhoneDeviceGetAddress(id, returnCodeWrapper, btPhoneReturnCodeWrapper, stringWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }

    //////
    ///Volume Information
    //////

    /**
     * Returns if this device supports volume information
     * @return True if this device supports volume information
     */
    public synchronized boolean supportsVolumeInformation(){
        systemCallBtPhoneDeviceSupportsVolumeInformation(id, returnCodeWrapper, btPhoneReturnCodeWrapper, booleanWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }

    /**
     * Returns the devices speaker volume
     * @return Volume. Ranges from 0-1
     */
    public synchronized float getVolumeSpeaker(){
        systemCallBtPhoneDeviceGetVolumeSpeaker(id, returnCodeWrapper, btPhoneReturnCodeWrapper, floatWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return floatWrapper.value;
    }
    /**
     * Sets the devices speaker volume
     * @param volume Volume. ranges from 0-1
     */
    public synchronized void setVolumeSpeaker(float volume){
        systemCallBtPhoneDeviceSetVolumeSpeaker(id, volume, returnCodeWrapper, btPhoneReturnCodeWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Returns the devices microphone volume
     * @return Volume. Ranges from 0-1
     */
    public synchronized float getVolumeMicrophone(){
        systemCallBtPhoneDeviceGetVolumeMicrophone(id, returnCodeWrapper, btPhoneReturnCodeWrapper, floatWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return floatWrapper.value;
    }
    /**
     * Sets the devices microphone volume
     * @param volume Volume. Ranges from 0-1
     */
    public synchronized void setVolumeMicrophone(float volume){
        systemCallBtPhoneDeviceSetVolumeMicrophone(id, volume, returnCodeWrapper, btPhoneReturnCodeWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Returns if this device is muted
     * @return True if this device is muted
     */
    public synchronized boolean getVolumeMuted(){
        systemCallBtPhoneDeviceGetVolumeMuted(id, returnCodeWrapper, btPhoneReturnCodeWrapper, booleanWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    /**
     * Sets the mute flag of this device
     * @param muted Mute flag
     */
    public synchronized void setVolumeMuted(boolean muted){
        systemCallBtPhoneDeviceSetVolumeMuted(id, muted, returnCodeWrapper, btPhoneReturnCodeWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    //////
    ///Battery Level
    //////

    /**
     * Returns if this device supports battery level information
     * @return True if this device supports battery level information
     */
    public synchronized boolean supportsBatteryLevel(){
        systemCallBtPhoneDeviceSupportsBatteryLevel(id, returnCodeWrapper, btPhoneReturnCodeWrapper, booleanWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }

    /**
     * Returns the battery level of this device
     * @return Battery Lavel. Ranges from 0-1
     */
    public synchronized float getBatteryLevel(){
        systemCallBtPhoneDeviceGetBatteryLevel(id, returnCodeWrapper, btPhoneReturnCodeWrapper, floatWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return floatWrapper.value;
    }

    //////
    ///Network Information
    //////

    /**
     * Returns if this device supports network information
     * @return True if this device supports network information
     */
    public synchronized boolean supportsNetworkInformation(){
        systemCallBtPhoneDeviceSupportsNetworkInformation(id, returnCodeWrapper, btPhoneReturnCodeWrapper, booleanWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }

    /**
     * Returns the networks name
     * @return Network name
     */
    public synchronized String getNetworkName(){
        systemCallBtPhoneDeviceGetNetworkName(id, returnCodeWrapper, btPhoneReturnCodeWrapper, stringWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Returns the networks current connection strength
     * @return Strength. Ranges from 0-1
     */
    public synchronized float getNetworkStrength(){
        systemCallBtPhoneDeviceGetNetworkStrength(id, returnCodeWrapper, btPhoneReturnCodeWrapper, floatWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return floatWrapper.value;
    }
    /**
     * Returns the networks status
     * @return Network Status
     */
    public synchronized NetworkStatus getNetworkStatus(){
        systemCallBtPhoneDeviceGetNetworkStatus(id, returnCodeWrapper, btPhoneReturnCodeWrapper, networkStatusWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return networkStatusWrapper.value;
    }

    //////
    ///Phonebook
    //////

    /**
     * Returns if this device supports contacts phonebook information
     * @return True if this device supports contacts phonebook information
     */
    public synchronized boolean supportsContactsPhonebook(){
        systemCallBtPhoneDeviceSupportsContactsPhonebook(id, returnCodeWrapper, btPhoneReturnCodeWrapper, booleanWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    /**
     * Returns if this device supports incoming history information
     * @return True if this device supports incoming history information
     */
    public synchronized boolean supportsIncomingHistoryPhonebook(){
        systemCallBtPhoneDeviceSupportsIncomingHistoryPhonebook(id, returnCodeWrapper, btPhoneReturnCodeWrapper, booleanWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    /**
     * Returns if this device supports ougoing history information
     * @return True if this device supports outgoing history information
     */
    public synchronized boolean supportsOutgoingHistoryPhonebook(){
        systemCallBtPhoneDeviceSupportsOutgoingHistoryPhonebook(id, returnCodeWrapper, btPhoneReturnCodeWrapper, booleanWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    /**
     * Returns if this device supports missed history information
     * @return True if this device supports missed history information
     */
    public synchronized boolean supportsMissedHistoryPhonebook(){
        systemCallBtPhoneDeviceSupportsMissedHistoryPhonebook(id, returnCodeWrapper, btPhoneReturnCodeWrapper, booleanWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }

    /**
     * Returns the contacts phonebook
     * @return Phonebook that contains the devices contacts
     */
    public Phonebook getPhonebookContacts(){
        return phonebookContacts;
    }
    /**
     * Returns the incoming history phonebook
     * @return Phonebook that contains all incoming calls
     */
    public Phonebook getPhonebookIncomingHistory(){
        return phonebookIncomingHistory;
    }
    /**
     * Returns the outgoing history phonebook
     * @return Phonebook that contains all outgoing calls
     */
    public Phonebook getPhonebookOutgoingHistory(){
        return phonebookOutgoingHistory;
    }
    /**
     * Returns the missed history phonebook
     * @return Phonebook that contains all missed calls
     */
    public Phonebook getPhonebookMissedHistory(){
        return phonebookMissedHistory;
    }
}
