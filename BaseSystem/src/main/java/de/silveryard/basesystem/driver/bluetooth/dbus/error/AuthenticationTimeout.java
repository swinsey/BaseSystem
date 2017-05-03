package de.silveryard.basesystem.driver.bluetooth.dbus.error;

import org.freedesktop.dbus.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusExecutionException;

/**
 * Created by silveryard on 02.05.17.
 */
@DBusInterfaceName("org.bluez.Error.AuthenticationTimeout")
public class AuthenticationTimeout extends DBusExecutionException {
    public AuthenticationTimeout(String message) {
        super(message);
    }
}
