package de.silveryard.basesystem.sdk.kernel;

/**
 * Created by Sebif on 20.02.2017.
 */
public abstract class App {
    /**
     * Called when the app is successfully loaded
     */
    public abstract void onAppLoaded();

    /**
     * Called approx 30 times per seconds
     */
    public abstract void onTick();
}
