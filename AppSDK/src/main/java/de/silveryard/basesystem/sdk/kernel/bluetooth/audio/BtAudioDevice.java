package de.silveryard.basesystem.sdk.kernel.bluetooth.audio;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 11.05.17.
 */
public abstract class BtAudioDevice {
    /**
     * Returns the internal bluetooth device
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     * @param outBtDeviceID Bluetooth device ID
     */
    public static void systemCallBluetoothAudioDeviceGetDevice(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<Integer> outBtDeviceID
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getdevice", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outBtDeviceID.value = response.getParameters().get(2).getInt();
    }

    /**
     * Returns the repeat mode
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     * @param outRepeatMode Repeat mode
     */
    public static void systemCallBluetoothAudioDeviceGetRepeat(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<RepeatMode> outRepeatMode
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getrepeat", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outRepeatMode.value = RepeatMode.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Sets the repeat mode
     * @param audioDeviceID ID of the AudioDevice in question
     * @param repeatMode Repeat mode
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     */
    public static void systemCallBluetoothAudioSetRepeat(
            int audioDeviceID,
            RepeatMode repeatMode,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode
    ){
        List<Parameter> params = new ArrayList<>(2);
        params.add(Parameter.createInt(audioDeviceID));
        params.add(Parameter.createInt(repeatMode.getValue()));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.setrepeat", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Returns the shuffle mode
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     * @param outShuffleMode Shuffle mode
     */
    public static void systemCallBluetoothAudioDeviceGetShuffle(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<ShuffleMode> outShuffleMode
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getshuffle", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outShuffleMode.value = ShuffleMode.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Sets the shuffle mode
     * @param audioDeviceID ID of the AudioDevice in question
     * @param shuffleMode Shuffle mode
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     */
    public static void systemCallBluetoothAudioDeviceSetShuffle(
            int audioDeviceID,
            ShuffleMode shuffleMode,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode
    ){
        List<Parameter> params = new ArrayList<>(2);
        params.add(Parameter.createInt(audioDeviceID));
        params.add(Parameter.createInt(shuffleMode.getValue()));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.setshuffle", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Returns the playback status
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     * @param outAudioStatus Playback status
     */
    public static void systemCallBluetoothAudioDeviceGetStatus(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<AudioStatus> outAudioStatus
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getstatus", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outAudioStatus.value = AudioStatus.getEnumValue(response.getParameters().get(2).getInt());
    }

    /**
     * Returns the current tracks title
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     * @param outTrackTitle Title of the current track played back
     */
    public static void systemCallBluetoothAudioDeviceGetCurrentTrackTitle(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<String> outTrackTitle
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttracktitle", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outTrackTitle.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the current tracks artist
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     * @param outTrackArtist Artist of the current track played back
     */
    public static void systemCallBluetoothAudioDeviceGetCurrentTrackArtist(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<String> outTrackArtist
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttrackartist", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outTrackArtist.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the current tracks album
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     * @param outTrackAlbum Album of the current track played back
     */
    public static void systemCallBluetoothAudioDeviceGetCurrentTrackAlbum(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<String> outTrackAlbum
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttrackalbum", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outTrackAlbum.value = response.getParameters().get(2).getString();
    }
    /**
     * Returns the current tracks duration
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     * @param outTrackDuration Duration of the current track played back
     */
    public static void systemCallBluetoothAudioDeviceGetCurrentTrackDuration(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<Long> outTrackDuration
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttrackduration", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outTrackDuration.value = response.getParameters().get(2).getLong();
    }
    /**
     * Returns the current tracks position in time
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode Audio Return Code
     * @param outTrackPosition Position of the current track played back
     */
    public static void systemCallBluetoothAudioDeviceGetCurrentTrackPosition(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode,
            Wrapper<Long> outTrackPosition
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.getcurrenttrackposition", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outTrackPosition.value = response.getParameters().get(2).getLong();
    }

    /**
     * Resumes playback
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     */
    public static void systemCallBluetoothAudioDevicePlay(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.play", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Pauses playback
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     */
    public static void systemCallBluetoothAudioDevicePause(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.pause", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Stops playback
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     */
    public static void systemCallBluetoothAudioDeviceStop(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.stop", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Plays the next title
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     */
    public static void systemCallBluetoothAudioDeviceNext(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.next", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Plays the previous title
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     */
    public static void systemCallBluetoothAudioDevicePrevious(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.previous", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Starts fast forward
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     */
    public static void systemCallBluetoothAudioDeviceFastForward(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.fastforward", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Starts rewind
     * @param audioDeviceID ID of the AudioDevice in question
     * @param outReturnCode General Return Code
     * @param outBtAudioReturnCode BtAudio Return Code
     */
    public static void systemCallBluetoothAudioDeviceRewind(
            int audioDeviceID,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<BtAudioReturnCode> outBtAudioReturnCode
    ){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(audioDeviceID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.driver.btaudio.bluetoothaudiodevice.rewind", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outBtAudioReturnCode.value = BtAudioReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
