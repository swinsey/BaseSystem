package de.silveryard.basesystem.driver;

/**
 * Created by silveryard on 01.05.17.
 */
@FunctionalInterface
public interface DeviceHandler<T extends Device> {
    /**
     * Called when a device event has happened
     * @param device Affected device
     */
    void handle(T device);
}
