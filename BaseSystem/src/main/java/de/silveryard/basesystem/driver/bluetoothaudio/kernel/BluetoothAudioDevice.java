package de.silveryard.basesystem.driver.bluetoothaudio.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.driver.DriverManager;
import de.silveryard.basesystem.driver.bluetooth.kernel.BtReturnCode;
import de.silveryard.basesystem.driver.bluetoothaudio.AudioStatus;
import de.silveryard.basesystem.driver.bluetoothaudio.RepeatMode;
import de.silveryard.basesystem.driver.bluetoothaudio.ShuffleMode;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.List;

/**
 * Created by silveryard on 07.05.17.
 */
abstract class BluetoothAudioDevice {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getdevice", BluetoothAudioDevice::systemCallBluetoothAudioDeviceGetDevice);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getrepeat", BluetoothAudioDevice::systemCallBluetoothAudioDeviceGetRepeat);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.setrepeat", BluetoothAudioDevice::systemCallBluetoothAudioDeviceSetRepeat);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getshuffle", BluetoothAudioDevice::systemCallBluetoothAudioDeviceGetShuffle);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.setshuffle", BluetoothAudioDevice::systemCallBluetoothAudioDeviceSetShuffle);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getstatus", BluetoothAudioDevice::systemCallBluetoothAudioDeviceGetStatus);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttracktitle", BluetoothAudioDevice::systemCallBluetoothAudioDeviceGetCurrentTrackTitle);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttrackartist", BluetoothAudioDevice::systemCallBluetoothAudioDeviceGetCurrentTrackArtist);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttrackalbum", BluetoothAudioDevice::systemCallBluetoothAudioDeviceGetCurrentTrackAlbum);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttrackduration", BluetoothAudioDevice::systemCallBluetoothAudioDeviceGetCurrentTrackDuration);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttrackposition", BluetoothAudioDevice::systemCallBluetoothAudioDeviceGetCurrentTrackPosition);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.play", BluetoothAudioDevice::systemCallBluetoothAudioDevicePlay);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.pause", BluetoothAudioDevice::systemCallBluetoothAudioDevicePause);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.stop", BluetoothAudioDevice::systemCallBluetoothAudioDeviceStop);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.next", BluetoothAudioDevice::systemCallBluetoothAudioDeviceNext);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.previous", BluetoothAudioDevice::systemCallBluetoothAudioDevicePrevious);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.fastforward", BluetoothAudioDevice::systemCallBluetoothAudioDeviceFastForward);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.rewind", BluetoothAudioDevice::systemCallBluetoothAudioDeviceRewind);
    }

    private static de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice getDevice(int id){
        List<de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice> devices = DriverManager.getInstance().getDriver(de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDriver.class).getDevices();
        for(int i = 0; i < devices.size(); i++){
            de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = devices.get(i);
            if(device.getId() == id){
                return device;
            }
        }
        return null;
    }

    private static QAMessage systemCallBluetoothAudioDeviceGetDevice(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(0)
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue(),
                Parameter.createInt(device.getDevice().getId())
        );
    }

    private static QAMessage systemCallBluetoothAudioDeviceGetRepeat(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(RepeatMode.ERROR.getValue())
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue(),
                Parameter.createInt(device.getRepeat().getValue())
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceSetRepeat(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue()
            );
        }

        device.setRepeat(RepeatMode.getEnumValue(message.getParameters().get(1).getInt()));
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue()
        );
    }

    private static QAMessage systemCallBluetoothAudioDeviceGetShuffle(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(ShuffleMode.ERROR.getValue())
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtReturnCode.OK.getValue(),
                Parameter.createInt(device.getShuffle().getValue())
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceSetShuffle(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue()
            );
        }

        device.setShuffle(ShuffleMode.getEnumValue(message.getParameters().get(1).getInt()));
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue()
        );
    }

    private static QAMessage systemCallBluetoothAudioDeviceGetStatus(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue(),
                    Parameter.createInt(AudioStatus.ERROR.getValue())
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue(),
                Parameter.createInt(device.getStatus().getValue())
        );
    }

    private static QAMessage systemCallBluetoothAudioDeviceGetCurrentTrackTitle(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue(),
                    Parameter.createString("")
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue(),
                Parameter.createString(device.getCurrentTrackTitle())
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceGetCurrentTrackArtist(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue(),
                    Parameter.createString("")
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue(),
                Parameter.createString(device.getCurrentTrackArtist())
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceGetCurrentTrackAlbum(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue(),
                    Parameter.createString("")
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue(),
                Parameter.createString(device.getCurrentTrackAlbum())
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceGetCurrentTrackDuration(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue(),
                    Parameter.createLong(0)
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue(),
                Parameter.createLong(device.getCurrentTrackDuration())
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceGetCurrentTrackPosition(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue(),
                    Parameter.createLong(0)
            );
        }

        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue(),
                Parameter.createLong(device.getCurrentTrackPosition())
        );
    }

    private static QAMessage systemCallBluetoothAudioDevicePlay(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue()
            );
        }

        device.play();
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue()
        );
    }
    private static QAMessage systemCallBluetoothAudioDevicePause(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue()
            );
        }

        device.pause();
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue()
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceStop(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue()
            );
        }

        device.stop();
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue()
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceNext(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue()
            );
        }

        device.next();
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue()
        );
    }
    private static QAMessage systemCallBluetoothAudioDevicePrevious(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue()
            );
        }

        device.previous();
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue()
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceFastForward(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue()
            );
        }

        device.fastForward();
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue()
        );
    }
    private static QAMessage systemCallBluetoothAudioDeviceRewind(RunningApp app, QAMessage message){
        de.silveryard.basesystem.driver.bluetoothaudio.BluetoothAudioDevice device = getDevice(message.getParameters().get(0).getInt());

        if(device == null){
            return Kernel.getInstance().createResponse(
                    message,
                    ReturnCode.ERROR.getValue(),
                    BtAudioReturnCode.INVALID_ID.getValue()
            );
        }

        device.rewind();
        return Kernel.getInstance().createResponse(
                message,
                ReturnCode.OK.getValue(),
                BtAudioReturnCode.OK.getValue()
        );
    }
}
