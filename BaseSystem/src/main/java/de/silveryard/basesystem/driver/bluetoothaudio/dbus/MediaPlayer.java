package de.silveryard.basesystem.driver.bluetoothaudio.dbus;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

/**
 * Created based on documentation: https://github.com/r10r/bluez/blob/master/doc/media-api.txt
 * Created by silveryard on 07.05.17.
 */
@DBusInterfaceName("org.bluez.MediaPlayer1")
public interface MediaPlayer extends DBusInterface {
    /**
     * Resume playback.
     */
    void Play();
    /**
     * Pause playback.
     */
    void Pause();
    /**
     * Stop playback.
     */
    void Stop();
    /**
     * Next item.
     */
    void Next();
    /**
     * Previous item.
     */
    void Previous();
    /**
     * Fast forward playback, this action is only stopped
     * when another method in this interface is called.
     */
    void FastForward();
    /**
     * Rewind playback, this action is only stopped
     * when another method in this interface is called.
     */
    void Rewind();
}
