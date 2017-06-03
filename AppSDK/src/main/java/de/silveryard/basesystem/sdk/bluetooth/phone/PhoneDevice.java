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

    public int getId(){
        return id;
    }

    //////
    ///General
    //////

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
    public synchronized void setVolumeSpeaker(float volume){
        systemCallBtPhoneDeviceSetVolumeSpeaker(id, volume, returnCodeWrapper, btPhoneReturnCodeWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

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
    public synchronized void setVolumeMicrophone(float volume){
        systemCallBtPhoneDeviceSetVolumeMicrophone(id, volume, returnCodeWrapper, btPhoneReturnCodeWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

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

    public Phonebook getPhonebookContacts(){
        return phonebookContacts;
    }
    public Phonebook getPhonebookIncomingHistory(){
        return phonebookIncomingHistory;
    }
    public Phonebook getPhonebookOutgoingHistory(){
        return phonebookOutgoingHistory;
    }
    public Phonebook getPhonebookMissedHistory(){
        return phonebookMissedHistory;
    }
}
