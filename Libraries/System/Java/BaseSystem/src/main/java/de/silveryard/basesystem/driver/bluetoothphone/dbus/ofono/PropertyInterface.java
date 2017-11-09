package de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.Variant;

import java.util.Map;

/**
 * Created by silveryard on 28.05.17.
 */
public interface PropertyInterface extends DBusInterface {
    /**
     * Returns properties for the modem object.
     * @return
     */
    Map<String, Variant<?>> GetProperties();

    /**
     * Changes the value of the specified property. Only
     * properties that are listed as readwrite are
     * changeable. On success a PropertyChanged signal
     * will be emitted.
     *
     * Possible Errors: org.ofono.Error.InProgress
     * org.ofono.Error.NotImplemented
     * org.ofono.Error.InvalidArguments
     * org.ofono.Error.NotAvailable
     * org.ofono.Error.AccessDenied
     * org.ofono.Error.Failed
     *
     * @param property
     * @param value
     */
    void SetProperty(String property, Object value);
}
