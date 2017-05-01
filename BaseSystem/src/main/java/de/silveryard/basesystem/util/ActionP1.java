package de.silveryard.basesystem.util;

/**
 * Created by Sebif on 18.03.2017.
 */
@FunctionalInterface
public interface ActionP1<P1> {
    /**
     * Called when the action gets invoked
     * @param param Parameter
     */
    void invoke(P1 param);
}
