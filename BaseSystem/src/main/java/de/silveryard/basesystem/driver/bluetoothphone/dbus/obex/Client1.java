package de.silveryard.basesystem.driver.bluetoothphone.dbus.obex;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;
import org.freedesktop.dbus.Path;
import org.freedesktop.dbus.Variant;

import java.util.Map;

/**
 * Created based on documentation: https://github.com/r10r/bluez/blob/master/doc/obex-api.txt
 * Created by silveryard on 28.05.17.
 */
@DBusInterfaceName("org.bluez.obex.Client1")
public interface Client1 extends DBusInterface {
    /**
     * Create a new OBEX session for the given remote address.
     *
     * The last parameter is a dictionary to hold optional or
     * type-specific parameters. Typical parameters that can
     * be set in this dictionary include the following:
     *
     * string "Target" : type of session to be created
     * string "Source" : local address to be used
     * byte "Channel"
     *
     * The currently supported targets are the following:
     *
     * "ftp"
     * "map"
     * "opp"
     * "pbap"
     * "sync"
     *
     * Possible errors: org.bluez.obex.Error.InvalidArguments
     * org.bluez.obex.Error.Failed
     *
     * @param destination
     * @param arguments
     * @return
     */
    Path CreateSession(String destination, Map<String, Variant<?>> arguments);
    /**
     * Unregister session and abort pending transfers.
     *
     * Possible errors: org.bluez.obex.Error.InvalidArguments
     * org.bluez.obex.Error.NotAuthorized
     *
     * @param session
     */
    void RemoveSession(Path session);
}
