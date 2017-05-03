package de.silveryard.basesystem.driver.bluetooth.dbus;

import de.silveryard.basesystem.driver.bluetooth.dbus.error.*;
import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created based on documentation: https://github.com/hmallat/bluez5-5.18/blob/master/doc/device-api.txt
 * Created by silveryard on 01.05.17.
 */
@DBusInterfaceName("org.bluez.Device1")
public interface Device extends DBusInterface {
    /**
     * This is a generic method to connect any profiles
     * the remote device supports that can be connected
     * to and have been flagged as auto-connectable on
     * our side. If only subset of profiles is already
     * connected it will try to connect currently disconnected
     * ones.
     *
     * If at least one profile was connected successfully this
     * method will indicate success.
     *
     * Possible errors: org.bluez.NotReady
     * org.bluez.Failed
     * org.bluez.InProgress
     * org.bluez.AlreadyConnected
     * @throws NotReady
     * @throws Failed
     * @throws InProgress
     * @throws AlreadyConnected
     */
    void Connect() throws
            NotReady,
            Failed,
            InProgress,
            AlreadyConnected;

    /**
     * This method gracefully disconnects all connected
     * profiles and then terminates low-level ACL connection.
     *
     * ACL connection will be terminated even if some profiles
     * were not disconnected properly e.g. due to misbehaving
     * device.
     *
     * This method can be also used to cancel a preceding
     * Connect call before a reply to it has been received.
     *
     * Possible errors: org.bluez.NotConnected
     *
     * @throws NotConnected
     */
    void Disconnect() throws
            NotConnected;

    /**
     * This method connects a specific profile of this
     * device. The UUID provided is the remote service
     * UUID for the profile.
     *
     * Possible errors: org.bluez.DoesNotExist
     * org.bluez.AlreadyConnected
     * org.bluez.ConnectFailed
     *
     * @param uuid
     * @throws DoesNotExist
     * @throws AlreadyConnected
     * @throws ConnectFailed
     */
    void ConnectProfile(String uuid) throws
            DoesNotExist,
            AlreadyConnected,
            ConnectFailed;
    /**
     * This method disconnects a specific profile of
     * this device. The profile needs to be registered
     * client profile.
     *
     * There is no connection tracking for a profile, so
     * as long as the profile is registered this will always
     * succeed.
     *
     * Possible errors: org.bluez.DoesNotExist
     * org.bluez.Failed
     * org.bluez.NotConnected
     * org.bluez.NotSupported
     *
     *
     * @param uuid
     * @throws DoesNotExist
     * @throws Failed
     * @throws NotConnected
     * @throws NotSupported
     */
    void DisconnectProfile(String uuid) throws
            DoesNotExist,
            Failed,
            NotConnected,
            NotSupported;

    /**
     * This method will connect to the remote device,
     * initiate pairing and then retrieve all SDP records
     * (or GATT primary services).
     *
     * If the application has registered its own agent,
     * then that specific agent will be used. Otherwise
     * it will use the default agent.
     *
     * Only for applications like a pairing wizard it
     * would make sense to have its own agent. In almost
     * all other cases the default agent will handle
     * this just fine.
     *
     * In case there is no application agent and also
     * no default agent present, this method will fail.
     *
     * Possible errors: org.bluez.InvalidArguments
     * org.bluez.Error.Failed
     * org.bluez.Error.AlreadyExists
     * org.bluez.Error.AuthenticationCanceled
     * org.bluez.Error.AuthenticationFailed
     * org.bluez.Error.AuthenticationRejected
     * org.bluez.Error.AuthenticationTimeout
     * org.bluez.Error.ConnectionAttemptFailed
     *
     * @throws InvalidArguments
     * @throws Failed
     * @throws AlreadyExists
     * @throws AuthenticationCanceled
     * @throws AuthenticationFailed
     * @throws AuthenticationRejected
     * @throws AuthenticationTimeout
     * @throws ConnectionAttemptFailed
     */
    void Pair() throws
            InvalidArguments,
            Failed,
            AlreadyExists,
            AuthenticationCanceled,
            AuthenticationFailed,
            AuthenticationRejected,
            AuthenticationTimeout,
            ConnectionAttemptFailed;

    /**
     * This method can be used to cancel a pairing
     * operation initiated by the Pair method.
     *
     * Possible errors: org.bluez.DoesNotExist
     * org.bluez.Error.Failed
     *
     * @throws DoesNotExist
     * @throws Failed
     */
    void CancelPairing() throws
            DoesNotExist,
            Failed;
}
