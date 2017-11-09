package de.silveryard.basesystem.driver.bluetooth.dbus;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;
import org.freedesktop.dbus.Variant;

import java.util.Map;

/**
 * Created by silveryard on 01.05.17.
 */
@DBusInterfaceName("org.freedesktop.DBus.ObjectManager")
public interface ObjectManager extends DBusInterface {
    /**
     *
     * @return
     */
    Map<DBusInterface, Map<String, Map<String, Variant>>> GetManagedObjects();
}
