package de.silveryard.basesystem.sdk.app;

/**
 * Created by silveryard on 17.05.17.
 */
@FunctionalInterface
public interface AppHandler {
    /**
     * Invoke
     * @param app Affected application
     */
    void invoke(App app);
}
