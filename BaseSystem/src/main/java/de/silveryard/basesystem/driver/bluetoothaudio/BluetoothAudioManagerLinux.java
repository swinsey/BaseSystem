package de.silveryard.basesystem.driver.bluetoothaudio;

import de.silveryard.basesystem.driver.Device;
import de.silveryard.basesystem.driver.DeviceHandler;
import de.silveryard.basesystem.driver.DriverManager;
import de.silveryard.basesystem.driver.bluetooth.BluetoothDevice;
import de.silveryard.basesystem.driver.bluetooth.BluetoothDriver;
import de.silveryard.basesystem.driver.bluetoothaudio.dbus.MediaPlayer;
import org.freedesktop.DBus;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.sqlite.core.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 07.05.17.
 */
final class BluetoothAudioManagerLinux extends BluetoothAudioManager {
    private static final long UPDATE_DELAY = 1000;
    private static final String EMPTY_OBJECT =
            "<!DOCTYPE node PUBLIC \"-//freedesktop//DTD D-BUS Object Introspection 1.0//EN\"\n" +
            "\"http://www.freedesktop.org/standards/dbus/1.0/introspect.dtd\">\n" +
            "<node>\n" +
            "</node>\n";

    private final DeviceHandler<BluetoothAudioDevice> onConnectedHandler;
    private final DeviceHandler<BluetoothAudioDevice> onDisconnectedHandler;
    private final DBusConnection connection;
    private final BluetoothDriver bluetoothDriver;
    private final List<BluetoothDevice> connectedDevices;

    private long lastCheck;

    public BluetoothAudioManagerLinux(DeviceHandler<BluetoothAudioDevice> onConnectedHandler, DeviceHandler<BluetoothAudioDevice> onDisconnectedHandler){
        this.onConnectedHandler = onConnectedHandler;
        this.onDisconnectedHandler = onDisconnectedHandler;

        bluetoothDriver = DriverManager.getInstance().getDriver(BluetoothDriver.class);

        if(bluetoothDriver == null){
            throw new RuntimeException("Bluetooth Driver not loaded!");
        }

        try {
            connection = DBusConnection.getConnection(DBusConnection.SYSTEM);
        } catch (DBusException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        connectedDevices = new ArrayList<>();
    }

    @Override
    public void update() {
        long timestamp = System.currentTimeMillis();
        if(timestamp - lastCheck < UPDATE_DELAY){
            return;
        }
        lastCheck = timestamp;
        connectedDevices.clear();

        List<BluetoothDevice> devices = bluetoothDriver.getDevices();
        for(int i = 0; i < devices.size(); i++){
            BluetoothDevice device = devices.get(i);

            if(device.isConnected()){
                connectedDevices.add(device);
            }
        }

        List<BluetoothAudioDevice> audioDevices = DriverManager.getInstance().getDriver(BluetoothAudioDriver.class).getDevices();
        for(int i = 0; i < connectedDevices.size(); i++){
            boolean alreadyConnected = false;
            String deviceAddress = connectedDevices.get(i).getAddress();

            for(int j = 0; j < audioDevices.size(); j++){
                String audioDeviceAddress = audioDevices.get(j).getDevice().getAddress();

                if(deviceAddress.equals(audioDeviceAddress)){
                    alreadyConnected = true;
                    break;
                }
            }

            if(!alreadyConnected){
                BluetoothDevice device = connectedDevices.get(i);
                BluetoothAudioDevice audioDevice = null;
                String path = "/org/bluez/hci0/dev_" + device.getAddress().replace(':', '_') + "/player0";
                try {
                    DBus.Introspectable introspectable = connection.getRemoteObject("org.bluez", path, DBus.Introspectable.class);
                    if(!introspectable.Introspect().equals(EMPTY_OBJECT)){
                        DBus.Properties properties = connection.getRemoteObject("org.bluez", path, DBus.Properties.class);
                        MediaPlayer player = connection.getRemoteObject("org.bluez", path, MediaPlayer.class);
                        audioDevice = new BluetoothAudioDeviceLinux(device, properties, player);
                        onConnectedHandler.handle(audioDevice);
                    }
                } catch (DBusException e) {
                    e.printStackTrace();
                }
            }
        }

        for(int i = 0; i < audioDevices.size(); i++){
            boolean stillConnected = false;
            String audioDeviceAddress = audioDevices.get(i).getDevice().getAddress();

            for(int j = 0; j < connectedDevices.size(); j++){
                String deviceAddress = connectedDevices.get(j).getAddress();

                if(deviceAddress.equals(audioDeviceAddress)){
                    stillConnected = true;
                    break;
                }
            }

            if(!stillConnected){
                onDisconnectedHandler.handle(audioDevices.get(i));
            }
        }
    }
}
