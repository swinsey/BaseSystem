package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.Device;

/**
 * Created by silveryard on 30.04.17.
 */
public final class BluetoothDevice extends Device {
    private long id;

    public BluetoothDevice(long id){
        this.id = id;
    }

    public long getId(){
        if(!isValid()){
            return -1;
        }

        return id;
    }
}
