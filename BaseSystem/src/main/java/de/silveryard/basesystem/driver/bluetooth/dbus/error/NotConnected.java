package de.silveryard.basesystem.driver.bluetooth.dbus.error;

import org.freedesktop.dbus.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusExecutionException;

/**
 * Created by silveryard on 02.05.17.
 */
@DBusInterfaceName("org.bluez.Error.NotConnected")
public class NotConnected extends DBusExecutionException {
    public NotConnected(String message) {
        super(message);
    }
}
