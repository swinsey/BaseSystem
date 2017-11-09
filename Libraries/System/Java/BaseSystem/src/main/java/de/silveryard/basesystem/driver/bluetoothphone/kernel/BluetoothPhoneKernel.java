package de.silveryard.basesystem.driver.bluetoothphone.kernel;

/**
 * Created by silveryard on 29.05.17.
 */
public abstract class BluetoothPhoneKernel {
    public static void enableKernel(){
        Manager.enableKernel();
        Device.enableKernel();
    }

    private BluetoothPhoneKernel(){}
}
