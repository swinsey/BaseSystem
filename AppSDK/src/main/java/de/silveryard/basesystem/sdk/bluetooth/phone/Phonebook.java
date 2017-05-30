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

    public Phonebook(PhoneDevice device, PhonebookType type){
        this.device = device;
        this.type = type;

        returnCodeWrapper = new Wrapper<>();
        btPhoneReturnCodeWrapper = new Wrapper<>();
        integerWrapper = new Wrapper<>();
        stringWrapper = new Wrapper<>();
    }

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
