package de.silveryard.basesystem.driver.bluetooth.dbus;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created based on documentation: https://github.com/r10r/bluez/blob/master/doc/adapter-api.txt
 * Created by silveryard on 01.05.17.
 */
@DBusInterfaceName("org.bluez.Adapter1")
public interface Adapter extends DBusInterface {
    /**
     * This method starts the device discovery session. This
     * includes an inquiry procedure and remote device name
     * resolving. Use StopDiscovery to release the sessions
     * acquired.
     *
     * This process will start creating Device objects as
     * new devices are discovered.
     *
     * Possible errors: org.bluez.Error.NotReady
     * org.bluez.Error.Failed
     */
    void StartDiscovery();
    /**
     * This method will cancel any previous StartDiscovery
     * transaction.
     *
     * Note that a discovery procedure is shared between all
     * discovery sessions thus calling StopDiscovery will only
     * release a single session.
     *
     * Possible errors: org.bluez.Error.NotReady
     * org.bluez.Error.Failed
     * org.bluez.Error.NotAuthorized
     */
    void StopDiscovery();
    /**
     * This removes the remote device object at the given
     * path. It will remove also the pairing information.
     *
     * Possible errors: org.bluez.Error.InvalidArguments
     * org.bluez.Error.Failed
     * @param device
     */
    void RemoveDevice(String device);
}
