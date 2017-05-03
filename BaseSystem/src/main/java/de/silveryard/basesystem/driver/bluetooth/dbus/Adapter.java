package de.silveryard.basesystem.driver.bluetooth.dbus;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created by silveryard on 01.05.17.
 */
@DBusInterfaceName("org.bluez.Adapter1")
public interface Adapter extends DBusInterface {
    void StartDiscovery();
    void StopDiscovery();
    void RemoveDevice(String device);
}
