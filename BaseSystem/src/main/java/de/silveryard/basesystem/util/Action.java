package de.silveryard.basesystem.util;

/**
 * Created by beppo on 04/02/17.
 */
@FunctionalInterface
public interface Action {
    /**
     * Called when the action gets invoked
     */
    void invoke();
}
