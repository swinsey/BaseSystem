package de.silveryard.basesystem.driver.bluetoothphone;

import de.silveryard.basesystem.driver.Driver;
import org.apache.commons.lang3.SystemUtils;

/**
 * Created by silveryard on 28.05.17.
 */
public final class BluetoothPhoneDriver extends Driver<BluetoothPhoneDevice> {
    private final BluetoothPhoneManager manager;

    public BluetoothPhoneDriver(){
        if(SystemUtils.IS_OS_LINUX){
            manager = new BluetoothPhoneManagerLinux(this::onDeviceConnected, this::onDeviceDisconnected);
        }else{
            manager = new BluetoothPhoneManagerFallback();
        }
    }

    @Override
    public void update() {
        manager.update();
    }
}
