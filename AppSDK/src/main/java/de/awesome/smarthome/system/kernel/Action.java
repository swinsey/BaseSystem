package de.awesome.smarthome.system.kernel;

/**
 * Created by Sebif on 10.03.2017.
 */
@FunctionalInterface
public interface Action {
    /**
     * Invokable method
     */
    void invoke();
}
