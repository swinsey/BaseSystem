package de.silveryard.basesystem.app.kernel;

/**
 * Created by silveryard on 15.05.17.
 */
public abstract class AppKernel {
    /**
     * Enables the kernel and its corresponding systemcalls
     */
    public static void enableKernel(){
        AppManager.enableKernel();
        InstalledApp.enableKernel();
    }

    private AppKernel(){

    }
}
