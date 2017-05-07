package de.silveryard.basesystem.driver.bluetoothaudio.kernel;

/**
 * Created by silveryard on 07.05.17.
 */
public abstract class BluetoothAudioKernel {
    /**
     * Enables the bluetooth audio kernel and registers the corresponding systemcalls
     */
    public static void enableKernel(){
        BluetoothAudioDriver.enableKernel();
        BluetoothAudioDevice.enableKernel();
    }
}
