package de.silveryard.basesystem.driver.bluetooth.dbus;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created by silveryard on 01.05.17.
 */
@DBusInterfaceName("org.bluez.Device1")
public interface Device extends DBusInterface {
    void CancelPairing();
    void Connect();
    void ConnectProfile(String uuid);
    void Disconnect();
    void DisconnectProfile(String uuid);
    void Pair();
}
