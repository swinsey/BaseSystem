package de.silveryard.basesystem.driver.bluetooth.kernel;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class BluetoothKernel {
    /**
     * Enables the kernel and its corresponding systemcalls
     */
    public static void enableKernel(){
        BluetoothDriver.enableKernel();
        Adapter.enableKernel();
        Device.enableKernel();
    }
}
