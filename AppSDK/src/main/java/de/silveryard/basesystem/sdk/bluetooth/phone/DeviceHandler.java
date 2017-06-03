package de.silveryard.basesystem.sdk.bluetooth.phone;

/**
 * Created by silveryard on 30.05.17.
 */
@FunctionalInterface
public interface DeviceHandler {
    /**
     * Handler
     * @param device
     */
    void handle(PhoneDevice device);
}

