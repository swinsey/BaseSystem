package de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono;

/**
 * Created by silveryard on 28.05.17.
 */

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created based on documentation: https://github.com/intgr/ofono/blob/master/doc/manager-api.txt
 * Created by silveryard on 28.05.17.
 */
@DBusInterfaceName("org.ofono.Manager")
public interface Manager extends DBusInterface {
    /**
     * Get an array of modem objects and properties
     * that represents the currently attached modems.
     *
     * This method call should only be used once when an
     * application starts up.  Further modem additions
     * and removal shall be monitored via ModemAdded and
     * ModemRemoved signals.
     * @return
     */
    Object GetModems();
    //TODO: Determine right type. Using anything than Objects results in a freeze
    //Map<DBusInterface, Map<String, Variant<?>>> GetModems();
}
