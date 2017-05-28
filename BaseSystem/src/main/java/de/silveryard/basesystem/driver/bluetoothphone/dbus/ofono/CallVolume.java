package de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono;

import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created based on documentation: https://github.com/webOS-ports/ofono/blob/webOS-ports/master/doc/call-volume-api.txt#L31
 * Created by silveryard on 28.05.17.
 */
@DBusInterfaceName("org.ofono.CallVolume")
public interface CallVolume extends PropertyInterface {}
