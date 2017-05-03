package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.DriverManager;
import de.silveryard.basesystem.driver.bluetooth.dbus.Agent;
import de.silveryard.basesystem.driver.bluetooth.dbus.error.Canceled;
import de.silveryard.basesystem.driver.bluetooth.dbus.error.Rejected;
import org.freedesktop.dbus.Path;
import org.freedesktop.dbus.UInt32;

import java.util.List;
import java.util.UUID;

/**
 * Created by silveryard on 03.05.17.
 */
public abstract class BluetoothAgent implements Agent {
    private String objectPath;
    private BluetoothDriver driver;
    private BluetoothCapability capability;

    public BluetoothAgent(BluetoothCapability capability){
        this.capability = capability;

        objectPath = "/de/silveryard/basesystem/bluetooth/agent/" + UUID.randomUUID().toString().replace('-', '_');
        driver = DriverManager.getInstance().getDriver(BluetoothDriver.class);
    }

    public final BluetoothCapability getCapability(){
        return capability;
    }

    protected abstract String requestPinCode(BluetoothDevice device) throws Rejected, Canceled;
    protected abstract void displayPinCode(BluetoothDevice device, String pinCode) throws Rejected, Canceled;

    protected abstract UInt32 requestPasskey(BluetoothDevice device) throws Rejected, Canceled;
    protected abstract void displayPasskey(BluetoothDevice device, UInt32 passKey, byte entered);

    protected abstract void requestConfirmation(BluetoothDevice device, UInt32 passKey) throws Rejected, Canceled;
    protected abstract void requestAuthorization(BluetoothDevice device) throws Rejected, Canceled;

    protected abstract void authorizeService(BluetoothDevice device, String uuid) throws Rejected, Canceled;

    public abstract void onDevicePaired(BluetoothDevice device);
    public abstract void onDevicePairingFailed(BluetoothDevice device);

    private final BluetoothDevice getDevice(Path path){
        List<BluetoothDevice> devices = driver.getDevices();
        for(int i = 0; i < devices.size(); i++){
            BluetoothDeviceLinux device = (BluetoothDeviceLinux) devices.get(i);
            if(device.getObjectPath().equals(path.getPath())){
                return device;
            }
        }

        return null;
    }

    @Override
    public final String RequestPinCode(Path device) throws Rejected, Canceled {
        BluetoothDevice dev = getDevice(device);
        return requestPinCode(dev);
    }
    @Override
    public final void DisplayPinCode(Path device, String pinCode) throws Rejected, Canceled {
        BluetoothDevice dev = getDevice(device);
        displayPinCode(dev, pinCode);
    }

    @Override
    public final UInt32 RequestPasskey(Path device) throws Rejected, Canceled {
        BluetoothDevice dev = getDevice(device);
        return requestPasskey(dev);
    }
    @Override
    public final void DisplayPasskey(Path device, UInt32 passKey, byte entered) {
        BluetoothDevice dev = getDevice(device);
        displayPasskey(dev, passKey, entered);
    }

    @Override
    public final void RequestConfirmation(Path device, UInt32 passKey) throws Rejected, Canceled {
        BluetoothDevice dev = getDevice(device);
        requestConfirmation(dev, passKey);
    }
    @Override
    public final void RequestAuthorization(Path device) throws Rejected, Canceled {
        BluetoothDevice dev = getDevice(device);
        requestAuthorization(dev);
    }

    @Override
    public final void AuthorizeService(Path device, String uuid) throws Rejected, Canceled {
        BluetoothDevice dev = getDevice(device);
        authorizeService(dev, uuid);
    }
    @Override
    public final void Release() {}
    @Override
    public final void Cancel() {}
    @Override
    public final boolean isRemote() {
        return false;
    }
    @Override
    public final String getObjectPath() {
        return objectPath;
    }
}
