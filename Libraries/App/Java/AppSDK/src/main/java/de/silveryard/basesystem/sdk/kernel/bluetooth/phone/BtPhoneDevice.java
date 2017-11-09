package de.silveryard.basesystem.sdk.kernel.bluetooth.phone;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 29.05.17.
 */
public class BtPhoneDevice {
    /**
     * Returns the name of a given phone device
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outName Device Name
     */
    public static void systemCallBtPhoneDeviceGetName(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<String> outName
    ) {

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getname", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outName.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the address of a given phone device
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outAddress Devices Address
     */
    public static void systemCallBtPhoneDeviceGetAddress(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<String> outAddress
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getaddress", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outAddress.value = message.getParameters().get(2).getString();
    }

    /**
     * Returns the flag that indicates if the given phone device supports volume information
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outSupported True if volume information is supported
     */
    public static void systemCallBtPhoneDeviceSupportsVolumeInformation(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Boolean> outSupported
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsvolumeinformation", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outSupported.value = message.getParameters().get(2).getBoolean();
    }
    /**
     * Returns the volume of a given phones speaker
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outVolume Speaker volume. Ranges from 0-1
     */
    public static void systemCallBtPhoneDeviceGetVolumeSpeaker(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Float> outVolume
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getvolumespeaker", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outVolume.value = message.getParameters().get(2).getFloat();
    }
    /**
     * Sets the volume of a given phones speaker
     * @param deviceId ID of the device in question
     * @param volume Volume. Ranges from 0-1
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     */
    public static void systemCallBtPhoneDeviceSetVolumeSpeaker(
            int deviceId, float volume,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode
    ){

        List<Parameter> params = new ArrayList<>(2);
        params.add(Parameter.createInt(deviceId));
        params.add(Parameter.createFloat(volume));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.setvolumespeaker", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
    }
    /**
     * Returns the volume of a given phones microphone
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outVolume Microphone Volume. Ranges from 0-1
     */
    public static void systemCallBtPhoneDeviceGetVolumeMicrophone(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Float> outVolume
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getvolumemicrophone", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outVolume.value = message.getParameters().get(2).getFloat();
    }
    /**
     * Sets the volume of a given phones microphone
     * @param deviceId ID of the device in question
     * @param volume Volume. Ranges from 0-1
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     */
    public static void systemCallBtPhoneDeviceSetVolumeMicrophone(
            int deviceId, float volume,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode
    ){

        List<Parameter> params = new ArrayList<>(2);
        params.add(Parameter.createInt(deviceId));
        params.add(Parameter.createFloat(volume));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.setvolumemicrophone", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
    }
    /**
     * Returns the flag that indicates if a given device is muted
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outMuted True if the device is muted
     */
    public static void systemCallBtPhoneDeviceGetVolumeMuted(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Boolean> outMuted
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getvolumemuted", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outMuted.value = message.getParameters().get(2).getBoolean();
    }
    /**
     * Sets the flag that indicates if a given device is muted
     * @param deviceId ID of the device in question
     * @param muted Muted flag value
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     */
    public static void systemCallBtPhoneDeviceSetVolumeMuted(
            int deviceId, boolean muted,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode
    ){

        List<Parameter> params = new ArrayList<>(2);
        params.add(Parameter.createInt(deviceId));
        params.add(Parameter.createBoolean(muted));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.setvolumemuted", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
    }

    /**
     * Returns the flag that indicates if a given device supports battery level information
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outSupported True if the phone supports battery level information
     */
    public static void systemCallBtPhoneDeviceSupportsBatteryLevel(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Boolean> outSupported
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsbatterylevel", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outSupported.value = message.getParameters().get(2).getBoolean();
    }
    /**
     * Returns the phones battery level
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outBatteryLevel Battery level. Ranges from 0-1
     */
    public static void systemCallBtPhoneDeviceGetBatteryLevel(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Float> outBatteryLevel
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getbatterylevel", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outBatteryLevel.value = message.getParameters().get(2).getFloat();
    }

    /**
     * Returns the flag that indicates if network information is supported
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outSupported True if network information is supported
     */
    public static void systemCallBtPhoneDeviceSupportsNetworkInformation(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Boolean> outSupported
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsnetworkinformation", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outSupported.value = message.getParameters().get(2).getBoolean();
    }
    /**
     * Returns the phones network name
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outName Name of the network
     */
    public static void systemCallBtPhoneDeviceGetNetworkName(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<String> outName
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getnetworkname", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outName.value = message.getParameters().get(2).getString();
    }
    /**
     * Returns the phones network strength
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outStrength
     */
    public static void systemCallBtPhoneDeviceGetNetworkStrength(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Float> outStrength
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getnetworkstrength", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outStrength.value = message.getParameters().get(2).getFloat();
    }
    /**
     * Returns the phones network status
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outStatus Network Status
     */
    public static void systemCallBtPhoneDeviceGetNetworkStatus(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<NetworkStatus> outStatus
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getnetworkstatus", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outStatus.value = NetworkStatus.getEnumValue(message.getParameters().get(2).getInt());
    }

    /**
     * Returns the flag that indicates if the device supports contacts phonebook information
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outSupported True if contacts phonebook information is supported
     */
    public static void systemCallBtPhoneDeviceSupportsContactsPhonebook(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Boolean> outSupported
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportscontactsphonebook", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outSupported.value = message.getParameters().get(2).getBoolean();
    }
    /**
     * Returns the flag that indicates if the device supports incoming history phonebook information
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outSupported True if incoming history phonebook information is supported
     */
    public static void systemCallBtPhoneDeviceSupportsIncomingHistoryPhonebook(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Boolean> outSupported
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsincominghistoryphonebook", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outSupported.value = message.getParameters().get(2).getBoolean();
    }
    /**
     * Returns the flag that indicates if the device supports outgoing history phonebook information
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outSupported True if outgoing history phonebook information is supported
     */
    public static void systemCallBtPhoneDeviceSupportsOutgoingHistoryPhonebook(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Boolean> outSupported
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsoutgoinghistoryphonebook", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outSupported.value = message.getParameters().get(2).getBoolean();
    }
    /**
     * Returns the flag that indicates if the device supports missed history phonebook information
     * @param deviceId ID of the device in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outSupported True if missed history phonebook information is supported
     */
    public static void systemCallBtPhoneDeviceSupportsMissedHistoryPhonebook(
            int deviceId,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Boolean> outSupported
    ){

        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(deviceId));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsmissedhistoryphonebook", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outSupported.value = message.getParameters().get(2).getBoolean();
    }

    /**
     * Returns number of entries in a given phonebook
     * @param deviceId ID of the device in question
     * @param type Type of the phonebook in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outCount Number of entries
     */
    public static void systemCallBtPhoneDeviceGetPhoneBookNumEntries(
            int deviceId, PhonebookType type,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Integer> outCount
    ){

        List<Parameter> params = new ArrayList<>(2);
        params.add(Parameter.createInt(deviceId));
        params.add(Parameter.createInt(type.getValue()));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebooknumentries", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outCount.value = message.getParameters().get(2).getInt();
    }
    /**
     * Returns the name of an entry of a given phonebook
     * @param deviceId ID of the device in question
     * @param type Type of the phonebook in question
     * @param entry Index of the entry in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outName Name of the entry
     */
    public static void systemCallBtPhoneDeviceGetPhoneBookEntryName(
            int deviceId, PhonebookType type, int entry,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<String> outName
    ){

        List<Parameter> params = new ArrayList<>(3);
        params.add(Parameter.createInt(deviceId));
        params.add(Parameter.createInt(type.getValue()));
        params.add(Parameter.createInt(entry));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebookentryname", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outName.value = message.getParameters().get(2).getString();
    }
    /**
     * Returns the amount of numbers stored in an entry of a given phonebook
     * @param deviceId ID of the device in question
     * @param type Type of the phonebook in question
     * @param entry Index of the entry in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outCount Number of phone numbers
     */
    public static void systemCallBtPhoneDeviceGetPhoneBookEntryNumNumbers(
            int deviceId, PhonebookType type, int entry,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<Integer> outCount
    ){

        List<Parameter> params = new ArrayList<>(3);
        params.add(Parameter.createInt(deviceId));
        params.add(Parameter.createInt(type.getValue()));
        params.add(Parameter.createInt(entry));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebooknumnumbers", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outCount.value = message.getParameters().get(2).getInt();
    }
    /**
     * Returns a specifig number of an entry in a given phonebook
     * @param deviceId ID of the device in question
     * @param type Type of the phonebook in question
     * @param entry Index of the entry in question
     * @param number Index of the number in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outNumber Phone number
     */
    public static void systemCallBtPhoneDeviceGetPhoneBookEntryNumber(
            int deviceId, PhonebookType type, int entry, int number,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<String> outNumber
    ){

        List<Parameter> params = new ArrayList<>(4);
        params.add(Parameter.createInt(deviceId));
        params.add(Parameter.createInt(type.getValue()));
        params.add(Parameter.createInt(entry));
        params.add(Parameter.createInt(number));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebooknumber", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outNumber.value = message.getParameters().get(2).getString();
    }
    /**
     * Returns the type of a specific number of an entry in a given phonebook
     * @param deviceId ID of the device in question
     * @param type Type of phonebook in question
     * @param entry Index of the entry in question
     * @param number Index of the number in question
     * @param outReturnCode General Return Code
     * @param outBtPhoneReturnCode Bt Phone Return Code
     * @param outType Type of entries number
     */
    public static void systemCallBtPhoneDeviceGetPhoneBookEntryNumberType(
            int deviceId, PhonebookType type, int entry, int number,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtPhoneReturnCode> outBtPhoneReturnCode,
            Wrapper<String> outType
    ){

        List<Parameter> params = new ArrayList<>(4);
        params.add(Parameter.createInt(deviceId));
        params.add(Parameter.createInt(type.getValue()));
        params.add(Parameter.createInt(entry));
        params.add(Parameter.createInt(number));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebooknumbertype", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outBtPhoneReturnCode.value = BtPhoneReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outType.value = message.getParameters().get(2).getString();
    }
}
