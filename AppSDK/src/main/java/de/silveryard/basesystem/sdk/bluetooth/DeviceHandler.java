package de.silveryard.basesystem.sdk.bluetooth;

/**
 * Created by silveryard on 01.05.17.
 */
@FunctionalInterface
public interface DeviceHandler {
    void handle(Device device);
}
