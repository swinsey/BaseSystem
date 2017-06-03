package de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created based on documentation: https://github.com/intgr/ofono/blob/master/doc/voicecall-api.txt
 * Created by silveryard on 03.06.17.
 */
@DBusInterfaceName("org.ofono.VoiceCall")
public interface VoiceCall extends PropertyInterface {
    void Hangup();
    void Answer();
}
