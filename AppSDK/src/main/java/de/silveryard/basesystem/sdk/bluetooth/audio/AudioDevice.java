package de.silveryard.basesystem.sdk.bluetooth.audio;

import de.silveryard.basesystem.sdk.BtAudioKernelException;
import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.bluetooth.BluetoothDriver;
import de.silveryard.basesystem.sdk.bluetooth.Device;
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

    public AudioDevice(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

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
    public synchronized void setRepeat(RepeatMode repeat){
        systemCallBluetoothAudioSetRepeat(id, repeat, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

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
    public synchronized void setShuffle(ShuffleMode shuffle){
        systemCallBluetoothAudioDeviceSetShuffle(id, shuffle, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

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

    public synchronized void play(){
        systemCallBluetoothAudioDevicePlay(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    public synchronized void pause(){
        systemCallBluetoothAudioDevicePause(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    public synchronized void stop(){
        systemCallBluetoothAudioDeviceStop(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    public synchronized void next(){
        systemCallBluetoothAudioDeviceNext(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    public synchronized void previous(){
        systemCallBluetoothAudioDevicePrevious(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    public synchronized void fastForward(){
        systemCallBluetoothAudioDeviceFastForward(id, returnCodeWrapper, btAudioReturnCodeWrapper);

        if(btAudioReturnCodeWrapper.value != BtAudioReturnCode.OK){
            throw new BtAudioKernelException(btAudioReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
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
