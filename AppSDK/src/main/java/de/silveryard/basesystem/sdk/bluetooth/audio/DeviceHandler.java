package de.silveryard.basesystem.sdk.bluetooth.audio;

/**
 * Created by silveryard on 11.05.17.
 */
@FunctionalInterface
public interface DeviceHandler {
    void handle(AudioDevice device);
}
