package de.silveryard.basesystem.driver.bluetoothphone;

import de.silveryard.basesystem.driver.DeviceHandler;
import de.silveryard.basesystem.driver.bluetoothphone.dbus.obex.Client1;
import de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.Manager;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by silveryard on 28.05.17.
 */
final class BluetoothPhoneManagerLinux extends BluetoothPhoneManager {
    private static final int UPDATE_DELAY = 2000;

    private final DeviceHandler<BluetoothPhoneDevice> onConnected;
    private final DeviceHandler<BluetoothPhoneDevice> onDisconnected;

    private final List<BluetoothPhoneDeviceLinux> devices;
    private final List<String> tmpDevices;

    private final DBusConnection systemConnection;
    private final DBusConnection sessionConnection;
    private final Manager ofonoManager;
    private final Client1 obexClient;

    private long lastTimestamp;

    public BluetoothPhoneManagerLinux(DeviceHandler<BluetoothPhoneDevice> connectedHandler, DeviceHandler<BluetoothPhoneDevice> disconnectedHandler){
        onConnected = connectedHandler;
        onDisconnected = disconnectedHandler;

        devices = new ArrayList<>();
        tmpDevices = new ArrayList<>();

        try {
            systemConnection = DBusConnection.getConnection(DBusConnection.SYSTEM);
            sessionConnection = DBusConnection.getConnection(DBusConnection.SESSION);
            ofonoManager = systemConnection.getRemoteObject("org.ofono", "/", Manager.class);
            obexClient = sessionConnection.getRemoteObject("org.bluez.obex", "/org/bluez/obex", Client1.class);
        } catch (DBusException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        long timestamp = System.currentTimeMillis();
        if(timestamp - lastTimestamp < UPDATE_DELAY){
            return;
        }
        lastTimestamp = timestamp;

        tmpDevices.clear();
        Vector<Object[]> modems = (Vector<Object[]>)ofonoManager.GetModems();
        for(int i = 0; i < modems.size(); i++){
            String path = modems.get(0)[0].toString();
            Map<String, Variant<?>> data = (Map<String, Variant<?>>)modems.get(0)[1];

            boolean online = (Boolean)data.get("Online").getValue();
            boolean powered = (Boolean)data.get("Powered").getValue();

            if(online && powered){
                tmpDevices.add(path);
            }
        }

        for(int i = 0; i < tmpDevices.size(); i++){
            boolean alreadyConnected = false;
            String tmpPath = tmpDevices.get(i);

            for(int j = 0; j < devices.size(); j++){
                String devPath = devices.get(j).getOfonoObjectPath();

                if(devPath.equals(tmpPath)){
                    alreadyConnected = true;
                    break;
                }
            }

            if(!alreadyConnected){
                BluetoothPhoneDeviceLinux device = new BluetoothPhoneDeviceLinux(systemConnection, sessionConnection, obexClient, tmpPath);
                devices.add(device);
                onConnected.handle(device);
            }
        }

        for(int i = 0; i < devices.size(); i++){
            boolean stillConnected = false;
            String devPath = devices.get(i).getOfonoObjectPath();

            for(int j = 0; j < tmpDevices.size(); j++){
                String tmpPath = tmpDevices.get(j);

                if(devPath.equals(tmpPath)){
                    stillConnected = true;
                    break;
                }
            }

            if(!stillConnected){
                BluetoothPhoneDeviceLinux device = devices.get(i);
                devices.remove(device);
                device.invalidate();
                onDisconnected.handle(device);
            }
        }

        for(int i = 0; i < devices.size(); i++){
            devices.get(i).update();
            System.out.println("Num Calls: " + devices.get(i).getNumberCalls());
            for(int j = 0; j < devices.get(i).getNumberCalls(); j++){
                Call call = devices.get(i).getCall(j);
                System.out.println("Call " + j + " start: " + call.getStartTime() + " Sys: " + System.currentTimeMillis());
            }
        }
    }
}
