package de.silveryard.basesystem.driver.bluetooth;


import de.silveryard.basesystem.driver.DeviceHandler;
import de.silveryard.basesystem.driver.bluetooth.dbus.AgentManager;
import de.silveryard.basesystem.driver.bluetooth.dbus.ObjectManager;
import org.freedesktop.DBus;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by silveryard on 01.05.17.
 */
final class BluetoothManagerLinux extends BluetoothManager {
    private static final long REFRESH_DELAY = 1000;
    private static final Pattern PATTERN_DEVICE = Pattern.compile("^\\/org\\/bluez\\/hci0\\/dev(_[0-9A-F]{2}){6}$", Pattern.CASE_INSENSITIVE);

    private final DBusConnection connection;
    private final ObjectManager objectManager;
    private final AgentManager agentManager;
    private final AdapterLinux adapter;
    private final List<BluetoothDeviceLinux> devices;
    private final List<DBusInterface> tmpDevices;
    private long lastTimestamp;

    /**
     * Constructor
     * @param connectedHandler
     * @param disconnectedHandler
     */
    public BluetoothManagerLinux(DeviceHandler<BluetoothDevice> connectedHandler, DeviceHandler<BluetoothDevice> disconnectedHandler) {
        super(connectedHandler, disconnectedHandler);

        try {
            connection = DBusConnection.getConnection(DBusConnection.SYSTEM);
            objectManager = connection.getRemoteObject("org.bluez", "/", ObjectManager.class);
            agentManager = connection.getRemoteObject("org.bluez", "/org/bluez", AgentManager.class);
            adapter = new AdapterLinux(
                    connection.getRemoteObject("org.bluez", "/org/bluez/hci0", DBus.Properties.class),
                    connection.getRemoteObject("org.bluez", "/org/bluez/hci0", de.silveryard.basesystem.driver.bluetooth.dbus.Adapter.class)
            );
        } catch (DBusException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        devices = new ArrayList<>();
        tmpDevices = new ArrayList<>();
    }

    @Override
    public Adapter getAdapter(){
        return adapter;
    }

    @Override
    public void update() {
        long timestamp = System.currentTimeMillis();
        if(timestamp - lastTimestamp < REFRESH_DELAY){
            return;
        }
        lastTimestamp = timestamp;

        tmpDevices.clear();
        Map<DBusInterface, Map<String, Map<String, Variant>>> objs = objectManager.GetManagedObjects();
        objs.forEach(new BiConsumer<DBusInterface, Map<String, Map<String, Variant>>>() {
            public void accept(DBusInterface obj, Map<String, Map<String, Variant>> stringMapMap) {
                String path = obj.getObjectPath();
                Matcher m = PATTERN_DEVICE.matcher(path);

                if(m.find()){
                    tmpDevices.add(obj);
                }
            }
        });

        for(int i = 0; i < tmpDevices.size(); i++){
            boolean alreadyConnected = false;
            String tmpPath = tmpDevices.get(i).getObjectPath();

            for(int j = 0; j < devices.size(); j++){
                String devPath = devices.get(j).getObjectPath();

                if(devPath.equals(tmpPath)){
                    alreadyConnected = true;
                    break;
                }
            }

            if(!alreadyConnected){
                BluetoothDeviceLinux device = new BluetoothDeviceLinux(connection, tmpPath);
                devices.add(device);
                onDeviceConnected(device);
            }
        }

        for(int i = 0; i < devices.size(); i++){
            boolean stillConnected = false;
            String devPath = devices.get(i).getObjectPath();

            for(int j = 0; j < tmpDevices.size(); j++){
                String tmpPath = tmpDevices.get(j).getObjectPath();

                if(devPath.equals(tmpPath)){
                    stillConnected = true;
                    break;
                }
            }

            if(!stillConnected){
                BluetoothDeviceLinux device = devices.get(i);
                devices.remove(device);
                device.invalidate();
                onDeviceDisconnected(device);
            }
        }
    }
    @Override
    public void dispose(){
        connection.disconnect();
    }
}
