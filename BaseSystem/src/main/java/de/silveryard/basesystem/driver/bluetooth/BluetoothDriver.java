package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.Driver;
import org.apache.commons.lang3.SystemUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 30.04.17.
 */
public final class BluetoothDriver extends Driver<BluetoothDevice> {
    private final BluetoothManager manager;

    /**
     * Constructor
     */
    public BluetoothDriver(){
        if(SystemUtils.IS_OS_LINUX){
            manager = new BluetoothManagerLinux(this::onDeviceConnected, this::onDeviceDisconnected);
        } else {
            manager = new BluetoothManagerFallback(this::onDeviceConnected, this::onDeviceDisconnected);
        }
    }

    @Override
    public void onUnload(){
        manager.dispose();
    }
    @Override
    public void update(){
        manager.update();
    }

    /**
     * Returns the default adapter
     * @return Adapter object
     */
    public Adapter getAdapter(){
        return manager.getAdapter();
    }

    /**
     * Returns the internal bluetooth manager
     *
     * TODO: Make this method unneccesary
     * @return Manager object
     */
    public BluetoothManager getManager(){
        return manager;
    }
}
