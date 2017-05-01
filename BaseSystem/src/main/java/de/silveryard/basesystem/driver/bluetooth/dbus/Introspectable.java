package de.silveryard.basesystem.driver.bluetooth.dbus;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created by silveryard on 01.05.17.
 */
@DBusInterfaceName("org.freedesktop.DBus.Introspectable")
public interface Introspectable extends DBusInterface {
    String Introspect();
}
