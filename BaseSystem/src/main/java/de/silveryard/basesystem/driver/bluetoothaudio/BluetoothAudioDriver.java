package de.silveryard.basesystem.driver.bluetoothaudio;

import de.silveryard.basesystem.driver.Driver;
import org.apache.commons.lang3.SystemUtils;

/**
 * Created by silveryard on 07.05.17.
 */
public final class BluetoothAudioDriver extends Driver<BluetoothAudioDevice> {
    private BluetoothAudioManager manager;

    /**
     * Constructor
     */
    public BluetoothAudioDriver(){
        if(SystemUtils.IS_OS_LINUX){
            manager = new BluetoothAudioManagerLinux(this::onDeviceConnected, this::onDeviceDisconnected);
        }else{
            manager = new BluetoothAudioManagerFallback();
        }
    }

    @Override
    public void update() {
        manager.update();
    }
}
