package de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created by silveryard on 03.06.17.
 */
@DBusInterfaceName("org.ofono.VoiceCallManager")
public interface VoiceCallManager extends DBusInterface {
    Object Dial(String number, String hideCallerId);
    Object GetCalls();
}
