package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.DriverManager;
import de.silveryard.basesystem.driver.bluetooth.dbus.Agent;
import org.freedesktop.dbus.Path;
import org.freedesktop.dbus.UInt32;

import java.util.List;
import java.util.UUID;

/**
 * Created by silveryard on 03.05.17.
 */
public abstract class BluetoothAgent {
    /**
     * Called when a new device has been paired
     * @param device
     */
    public abstract void onDevicePaired(BluetoothDevice device);
    /**
     * Called when pairing to a device has failed
     * @param device
     */
    public abstract void onDevicePairingFailed(BluetoothDevice device);
}
