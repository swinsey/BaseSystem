package de.silveryard.basesystem.sdk.bluetooth.audio;

import de.silveryard.basesystem.sdk.bluetooth.BluetoothDriver;
import de.silveryard.basesystem.sdk.bluetooth.Device;
import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.bluetooth.audio.AudioStatus;
import de.silveryard.basesystem.sdk.kernel.bluetooth.audio.BtAudioReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.audio.RepeatMode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.audio.ShuffleMode;

import java.util.List;

import static de.silveryard.basesystem.sdk.kernel.bluetooth.audio.BtAudioDevice.*;

/**
 * Created by silveryard on 11.05.17.
 */
public class AudioDevice {
    private final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private final Wrapper<BtAudioReturnCode> btAudioReturnCodeWrapper = new Wrapper<>();
    private final Wrapper<Integer> integerWrapper = new Wrapper<>();
    private final Wrapper<Long> longWrapper = new Wrapper<>();
    private final Wrapper<String> stringWrapper = new Wrapper<>();
    private final Wrapper<RepeatMode> repeatModeWrapper = new Wrapper<>();
    private final Wrapper<ShuffleMode> shuffleModeWrapper = new Wrapper<>();
    private final Wrapper<AudioStatus> audioStatusWrapper = new Wrapper<>();

    private final int id;

    /**
     * Constructor
     * @param id Internal identifier
     */
    public AudioDevice(int id){
        this.id = id;
    }

    /**
     * Returns the devices internal identifier
     * @return Internal id
     */
    public int getId(){
        return id;
    }

    /**
     * Returns the internal bluetooth device
     * @return Bluetooth device
     */
    public synchronized Device getDevice(){
        systemCallBluetoothAudioDeviceGetDevice(id, returnCodeWrapper, btAudioReturnCodeWrapper, integerWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        List<Device> devices = BluetoothDriver.getDevices();
        for(int i = 0; i < devices.size(); i++){
            Device device = devices.get(i);

            if(device.getId() == integerWrapper.value){
                return device;
            }
        }

        return null;
    }

    /**
     * Returns the repeat mode
     * @return Repeat mode
     */
    public synchronized RepeatMode getRepeat(){
        systemCallBluetoothAudioDeviceGetRepeat(id, returnCodeWrapper, btAudioReturnCodeWrapper, repeatModeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return repeatModeWrapper.value;
    }
    /**
     * Sets the repeat mode
     * @param repeat Repeat mode
     */
    public synchronized void setRepeat(RepeatMode repeat){
        systemCallBluetoothAudioSetRepeat(id, repeat, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Returns the shuffle mode
     * @return Shuffle mode
     */
    public synchronized ShuffleMode getShuffle(){
        systemCallBluetoothAudioDeviceGetShuffle(id, returnCodeWrapper, btAudioReturnCodeWrapper, shuffleModeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return shuffleModeWrapper.value;
    }
    /**
     * Sets the shuffle mode
     * @param shuffle Shuffle mode
     */
    public synchronized void setShuffle(ShuffleMode shuffle){
        systemCallBluetoothAudioDeviceSetShuffle(id, shuffle, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Returns the playback status
     * @return Playback status
     */
    public synchronized AudioStatus getStatus(){
        systemCallBluetoothAudioDeviceGetStatus(id, returnCodeWrapper, btAudioReturnCodeWrapper, audioStatusWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return audioStatusWrapper.value;
    }

    /**
     * Returns the current tracks title
     * @return Title of the current track played back
     */
    public synchronized String getCurrentTrackTitle(){
        systemCallBluetoothAudioDeviceGetCurrentTrackTitle(id, returnCodeWrapper, btAudioReturnCodeWrapper, stringWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Returns the current tracks artist
     * @return Artist of the current track played back
     */
    public synchronized String getCurrentTrackArtist(){
        systemCallBluetoothAudioDeviceGetCurrentTrackArtist(id, returnCodeWrapper, btAudioReturnCodeWrapper, stringWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Returns the current tracks album
     * @return Album of the current track played back
     */
    public synchronized String getCurrentTrackAlbum(){
        systemCallBluetoothAudioDeviceGetCurrentTrackAlbum(id, returnCodeWrapper, btAudioReturnCodeWrapper, stringWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Returns the current tracks duration
     * @return Duration of the current track played back
     */
    public synchronized long getCurrentTrackDuration(){
        systemCallBluetoothAudioDeviceGetCurrentTrackDuration(id, returnCodeWrapper, btAudioReturnCodeWrapper, longWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return longWrapper.value;
    }
    /**
     * Returns the current tacks playback position
     * @return Position in tame of the current track played back
     */
    public synchronized long getCurrentTrackPosition(){
        systemCallBluetoothAudioDeviceGetCurrentTrackPosition(id, returnCodeWrapper, btAudioReturnCodeWrapper, longWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return longWrapper.value;
    }

    /**
     * Resumes playback
     */
    public synchronized void play(){
        systemCallBluetoothAudioDevicePlay(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Pauses playback
     */
    public synchronized void pause(){
        systemCallBluetoothAudioDevicePause(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Stops playback
     */
    public synchronized void stop(){
        systemCallBluetoothAudioDeviceStop(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Plays the next title
     */
    public synchronized void next(){
        systemCallBluetoothAudioDeviceNext(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Plays the previous title
     */
    public synchronized void previous(){
        systemCallBluetoothAudioDevicePrevious(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Starts fast forward
     */
    public synchronized void fastForward(){
        systemCallBluetoothAudioDeviceFastForward(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Starts rewind
     */
    public synchronized void rewind(){
        systemCallBluetoothAudioDeviceRewind(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
}
