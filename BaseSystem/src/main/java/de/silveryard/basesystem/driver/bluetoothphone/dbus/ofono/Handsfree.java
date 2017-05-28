package de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono;

import org.freedesktop.dbus.DBusInterfaceName;

/**
 * https://github.com/webOS-ports/ofono/blob/webOS-ports/master/doc/handsfree-api.txt
 * Created by silveryard on 28.05.17.
 */
@DBusInterfaceName("org.ofono.Handsfree")
public interface Handsfree extends PropertyInterface {}
