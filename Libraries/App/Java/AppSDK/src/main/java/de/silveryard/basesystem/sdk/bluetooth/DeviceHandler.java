package de.silveryard.basesystem.sdk.bluetooth;

/**
 * Created by silveryard on 01.05.17.
 */
@FunctionalInterface
public interface DeviceHandler {
    /**
     * Handler
     * @param device
     */
    void handle(Device device);
}
