package de.silveryard.basesystem.util;

/**
 * Created by Sebif on 25.04.2017.
 */
@FunctionalInterface
public interface ActionP2<P1, P2> {
    /**
     * Called when the action gets invoked
     * @param param1 First parameter
     * @param param2 Second parameter
     */
    void invoke(P1 param1, P2 param2);
}
