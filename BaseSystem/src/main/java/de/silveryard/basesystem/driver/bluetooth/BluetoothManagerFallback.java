package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.DeviceHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 01.05.17.
 */
public final class BluetoothManagerFallback extends BluetoothManager {
    private AdapterFallback adapter;
    private List<BluetoothDevice> devices;

    public BluetoothManagerFallback(DeviceHandler<BluetoothDevice> connectedHandler, DeviceHandler<BluetoothDevice> disconnectedHandler) {
        super(connectedHandler, disconnectedHandler);

        System.out.println("Unsupported bluetooth platform. Using dummy fallback driver");
        adapter = new AdapterFallback();
        devices = new ArrayList<>(0);
    }

    @Override
    public Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}
