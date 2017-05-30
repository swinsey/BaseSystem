package de.silveryard.basesystem.driver.bluetoothphone.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.driver.bluetoothphone.BluetoothPhoneDevice;
import de.silveryard.basesystem.driver.bluetoothphone.NetworkStatus;
import de.silveryard.basesystem.driver.bluetoothphone.Phonebook;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by silveryard on 29.05.17.
 */
abstract class Device {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getname", Device::systemCallBtPhoneDeviceGetName);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getaddress", Device::systemCallBtPhoneDeviceGetAddress);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsvolumeinformation", Device::systemCallBtPhoneDeviceSupportsVolumeInformation);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getvolumespeaker", Device::systemCallBtPhoneDeviceGetVolumeSpeaker);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.setvolumespeaker", Device::systemCallBtPhoneDeviceSetVolumeSpeaker);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getvolumemicrophone", Device::systemCallBtPhoneDeviceGetVolumeMicrophone);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.setvolumemicrophone", Device::systemCallBtPhoneDeviceSetVolumeMicrophone);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getvolumemuted", Device::systemCallBtPhoneDeviceGetVolumeMuted);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.setvolumemuted", Device::systemCallBtPhoneDeviceSetVolumeMuted);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsbatterylevel", Device::systemCallBtPhoneDeviceSupportsBatteryLevel);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getbatterylevel", Device::systemCallBtPhoneDeviceGetBatteryLevel);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsnetworkinformation", Device::systemCallBtPhoneDeviceSupportsNetworkInformation);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getnetworkname", Device::systemCallBtPhoneDeviceGetNetworkName);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getnetworkstrength", Device::systemCallBtPhoneDeviceGetNetworkStrength);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getnetworkstatus", Device::systemCallBtPhoneDeviceGetNetworkStatus);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportscontactsphonebook", Device::systemCallBtPhoneDeviceSupportsContactsPhonebook);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsincominghistoryphonebook", Device::systemCallBtPhoneDeviceSupportsIncomingHistoryPhonebook);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsoutgoinghistoryphonebook", Device::systemCallBtPhoneDeviceSupportsOutgoingHistoryPhonebook);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.supportsmissedhistoryphonebook", Device::systemCallBtPhoneDeviceSupportsMissedHistoryPhonebook);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebooknumentries", Device::systemCallBtPhoneDeviceGetPhoneBookNumEntries);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebookentryname", Device::systemCallBtPhoneDeviceGetPhoneBookEntryName);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebooknumnumbers", Device::systemCallBtPhoneDeviceGetPhoneBookEntryNumNumbers);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebooknumber", Device::systemCallBtPhoneDeviceGetPhoneBookEntryNumber);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btphone.device.getphonebooknumbertype", Device::systemCallBtPhoneDeviceGetPhoneBookEntryNumberType);
    }

    private static QAMessage systemCallBtPhoneDeviceGetName(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createString("")
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createString(device.getName())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceGetAddress(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createString("")
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createString(device.getAddress())
            );
        }
    }

    private static QAMessage systemCallBtPhoneDeviceSupportsVolumeInformation(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createBoolean(false)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createBoolean(device.supportsVolumeInformation())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceGetVolumeSpeaker(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createFloat(0)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createFloat(device.getVolumeSpeaker())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceSetVolumeSpeaker(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        float volume = message.getParameters().get(1).getFloat();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue()
            );
        }else{
            device.setVolumeSpeaker(volume);

            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue()
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceGetVolumeMicrophone(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createFloat(0)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createFloat(device.getVolumeMicrophone())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceSetVolumeMicrophone(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        float volume = message.getParameters().get(1).getFloat();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue()
            );
        }else{
            device.setVolumeMicrophone(volume);

            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue()
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceGetVolumeMuted(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createBoolean(false)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createBoolean(device.getVolumeMuted())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceSetVolumeMuted(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        boolean muted = message.getParameters().get(1).getBoolean();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue()
            );
        }else{
            device.setVolumeMuted(muted);

            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue()
            );
        }
    }

    private static QAMessage systemCallBtPhoneDeviceSupportsBatteryLevel(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createBoolean(false)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createBoolean(device.supportsBatteryChargeLevel())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceGetBatteryLevel(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createFloat(0)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createFloat(device.getBatteryChargeLevel())
            );
        }
    }

    private static QAMessage systemCallBtPhoneDeviceSupportsNetworkInformation(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createBoolean(false)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createBoolean(device.supportsNetworkInformation())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceGetNetworkName(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createString("")
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createString(device.getNetworkName())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceGetNetworkStrength(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createFloat(0)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createFloat(device.getNetworkStrength())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceGetNetworkStatus(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(NetworkStatus.UNKNOWN.getValue())
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createInt(device.getNetworkStatus().getValue())
            );
        }
    }

    private static QAMessage systemCallBtPhoneDeviceSupportsContactsPhonebook(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createBoolean(false)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createBoolean(device.supportsContactsPhonebook())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceSupportsIncomingHistoryPhonebook(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createBoolean(false)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createBoolean(device.supportsIncomingHistoryPhonebook())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceSupportsOutgoingHistoryPhonebook(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createBoolean(false)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createBoolean(device.supportsOutgoingHistoryPhonebook())
            );
        }
    }
    private static QAMessage systemCallBtPhoneDeviceSupportsMissedHistoryPhonebook(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createBoolean(false)
            );
        }else{
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.OK.getValue(),
                    BtPhoneReturnCode.OK.getValue(),
                    Parameter.createBoolean(device.supportsMissedHistoryPhonebook())
            );
        }
    }

    private static QAMessage systemCallBtPhoneDeviceGetPhoneBookNumEntries(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        PhonebookType type = PhonebookType.getEnumValue(message.getParameters().get(1).getInt());

        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(0)
            );
        }

        Phonebook phonebook = getPhonebook(device, type);
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtPhoneReturnCode.INVALID_ID.getValue(),
                Parameter.createInt(phonebook.getNumEntries())
        );
    }
    private static QAMessage systemCallBtPhoneDeviceGetPhoneBookEntryName(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        PhonebookType type = PhonebookType.getEnumValue(message.getParameters().get(1).getInt());
        int entry = message.getParameters().get(2).getInt();

        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createString("")
            );
        }

        Phonebook phonebook = getPhonebook(device, type);

        if(entry < 0 || entry >= phonebook.getNumEntries()){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_INDEX.getValue(),
                    Parameter.createString("")
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtPhoneReturnCode.INVALID_ID.getValue(),
                Parameter.createString(phonebook.getEntryName(entry))
        );
    }
    private static QAMessage systemCallBtPhoneDeviceGetPhoneBookEntryNumNumbers(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        PhonebookType type = PhonebookType.getEnumValue(message.getParameters().get(1).getInt());
        int entry = message.getParameters().get(2).getInt();

        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(0)
            );
        }

        Phonebook phonebook = getPhonebook(device, type);

        if(entry < 0 || entry >= phonebook.getNumEntries()){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_INDEX.getValue(),
                    Parameter.createInt(0)
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtPhoneReturnCode.INVALID_ID.getValue(),
                Parameter.createInt(phonebook.getEntryNumNumbers(entry))
        );
    }
    private static QAMessage systemCallBtPhoneDeviceGetPhoneBookEntryNumber(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        PhonebookType type = PhonebookType.getEnumValue(message.getParameters().get(1).getInt());
        int entry = message.getParameters().get(2).getInt();
        int number = message.getParameters().get(3).getInt();

        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createString("")
            );
        }

        Phonebook phonebook = getPhonebook(device, type);

        if(entry < 0 || entry >= phonebook.getNumEntries()){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_INDEX.getValue(),
                    Parameter.createString("")
            );
        }
        if(number < 0 || number >= phonebook.getEntryNumNumbers(entry)){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_INDEX.getValue(),
                    Parameter.createString("")
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtPhoneReturnCode.INVALID_ID.getValue(),
                Parameter.createString(phonebook.getEntryNumber(entry, number))
        );
    }
    private static QAMessage systemCallBtPhoneDeviceGetPhoneBookEntryNumberType(RunningApp app, QAMessage message){
        int id = message.getParameters().get(0).getInt();
        PhonebookType type = PhonebookType.getEnumValue(message.getParameters().get(1).getInt());
        int entry = message.getParameters().get(2).getInt();
        int number = message.getParameters().get(3).getInt();

        BluetoothPhoneDevice device = BluetoothPhoneDevice.getDevice(id);

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_ID.getValue(),
                    Parameter.createString("")
            );
        }

        Phonebook phonebook = getPhonebook(device, type);

        if(entry < 0 || entry >= phonebook.getNumEntries()){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_INDEX.getValue(),
                    Parameter.createString("")
            );
        }
        if(number < 0 || number >= phonebook.getEntryNumNumbers(entry)){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtPhoneReturnCode.INVALID_INDEX.getValue(),
                    Parameter.createString("")
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtPhoneReturnCode.INVALID_ID.getValue(),
                Parameter.createString(phonebook.getEntryNumberType(entry, number))
        );
    }


    private static Phonebook getPhonebook(BluetoothPhoneDevice device, PhonebookType type){
        switch(type){
            case CONTACTS:
                return device.getContactsPhonebook();
            case HISTORY_INCOMING:
                return device.getIncomingHistoryPhonebook();
            case HISTORY_OUTGOING:
                return device.getOutgoingHistoryPhonebook();
            case HISTORY_MISSED:
                return device.getMissedHistoryPhonebook();
        }

        return null;
    }

    private Device(){}
}
