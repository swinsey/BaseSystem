package de.silveryard.basesystem.sdk.bluetooth.phone;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.bluetooth.phone.BtPhoneReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.phone.PhonebookType;

import static de.silveryard.basesystem.sdk.kernel.bluetooth.phone.BtPhoneDevice.*;

/**
 * Created by silveryard on 30.05.17.
 */
public final class Phonebook {
    private final PhoneDevice device;
    private final PhonebookType type;

    private final Wrapper<ReturnCode> returnCodeWrapper;
    private final Wrapper<BtPhoneReturnCode> btPhoneReturnCodeWrapper;
    private final Wrapper<Integer> integerWrapper;
    private final Wrapper<String> stringWrapper;

    /**
     * Constructor
     * @param device Device to fetch data from
     * @param type Phonebook to fetch data from
     */
    public Phonebook(PhoneDevice device, PhonebookType type){
        this.device = device;
        this.type = type;

        returnCodeWrapper = new Wrapper<>();
        btPhoneReturnCodeWrapper = new Wrapper<>();
        integerWrapper = new Wrapper<>();
        stringWrapper = new Wrapper<>();
    }

    /**
     * Returns the number of entries in this phonebook
     * @return Number of entries
     */
    public synchronized int getEntryCount(){
        systemCallBtPhoneDeviceGetPhoneBookNumEntries(device.getId(), type, returnCodeWrapper, btPhoneReturnCodeWrapper, integerWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Returns the name stored in an entry in this phonebook
     * @param entry Entry index
     * @return Name
     */
    public synchronized String getEntryName(int entry){
        systemCallBtPhoneDeviceGetPhoneBookEntryName(device.getId(), type, entry, returnCodeWrapper, btPhoneReturnCodeWrapper, stringWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Returns the amount of numbers stored in an entry in this phonebook
     * @param entry Entry index
     * @return Amount of numbers
     */
    public synchronized int getEntryNumberCount(int entry){
        systemCallBtPhoneDeviceGetPhoneBookEntryNumNumbers(device.getId(), type, entry, returnCodeWrapper, btPhoneReturnCodeWrapper, integerWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Returns the number stored in an entry in this phonebook
     * @param entry Entry index
     * @param number Number index
     * @return Phone number
     */
    public synchronized String getEntryNumber(int entry, int number){
        systemCallBtPhoneDeviceGetPhoneBookEntryNumber(device.getId(), type, entry, number, returnCodeWrapper, btPhoneReturnCodeWrapper, stringWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Returns the type of an number stored in an entry in this phonebook
     * @param entry Entry index
     * @param number Number index
     * @return Phone number type
     */
    public synchronized String getEntryNumberType(int entry, int number){
        systemCallBtPhoneDeviceGetPhoneBookEntryNumberType(device.getId(), type, entry, number, returnCodeWrapper, btPhoneReturnCodeWrapper, stringWrapper);

        if(btPhoneReturnCodeWrapper.value != BtPhoneReturnCode.OK){
            throw new BtPhoneKernelException(btPhoneReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
}
