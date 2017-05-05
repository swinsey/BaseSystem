package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.DriverManager;
import de.silveryard.basesystem.driver.bluetooth.dbus.Device;
import org.freedesktop.DBus;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;

/**
 * Created by silveryard on 01.05.17.
 */
final class BluetoothDeviceLinux extends BluetoothDevice {
    private static final String INTERFACE = "org.bluez.Device1";

    private final String objectPath;
    private final DBus.Properties properties;
    private final Device device;
    private final BluetoothAgent agent;

    public BluetoothDeviceLinux(DBusConnection connection, String objectPath){
        this.objectPath = objectPath;
        try {
            this.properties = connection.getRemoteObject("org.bluez", objectPath, DBus.Properties.class);
            this.device = connection.getRemoteObject("org.bluez", objectPath, Device.class);
        } catch (DBusException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        agent = ((BluetoothManagerLinux)DriverManager.getInstance().getDriver(BluetoothDriver.class).getManager()).getBluetoothAgent();
    }

    public String getObjectPath(){
        return objectPath;
    }

    @Override
    public boolean isBlocked() {
        return properties.Get(INTERFACE, "Blocked");
    }
    @Override
    public boolean isConnected() {
        return properties.Get(INTERFACE, "Connected");
    }
    @Override
    public boolean isPaired() {
        return properties.Get(INTERFACE, "Paired");
    }
    @Override
    public boolean isTrusted() {
        return properties.Get(INTERFACE, "Trusted");
    }

    @Override
    public String getAddress() {
        return properties.Get(INTERFACE, "Address");
    }
    @Override
    public String getAlias() {
        return properties.Get(INTERFACE, "Alias");
    }
    @Override
    public String getIcon() {
        return properties.Get(INTERFACE, "Icon");
    }
    @Override
    public String getName() {
        return properties.Get(INTERFACE, "Name");
    }

    @Override
    public boolean pair() {
        boolean success = false;

        try {
            device.Pair();
            success = true;
        }catch(DBusExecutionException e){
            System.out.println("Failed to pair with bluetooth device: " + e.getMessage());
            e.printStackTrace();
            success = false;
        }

        if(success){
            System.out.println("Paired with device: " + getName());
        }

        if(agent != null) {
            if (success) {
                agent.onDevicePaired(this);
            }else{
                agent.onDevicePairingFailed(this);
            }
        }
        return success;
    }
    @Override
    public void cancelPairing() {
        device.CancelPairing();
    }

    @Override
    public void remove() {
        Adapter adapter = DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter();
        ((AdapterLinux) adapter).removeDevice(objectPath);
    }

    @Override
    public void connect() {
        device.Connect();
    }
    @Override
    public void disconnect() {
        device.Disconnect();
    }
}
