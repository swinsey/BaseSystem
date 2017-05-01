package de.silveryard.basesystem.driver;

/**
 * Created by silveryard on 01.05.17.
 */
@FunctionalInterface
public interface DeviceHandler<TDev extends Device> {
    void handle(TDev device);
}
