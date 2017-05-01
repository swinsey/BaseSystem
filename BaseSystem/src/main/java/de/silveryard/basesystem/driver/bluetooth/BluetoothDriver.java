package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.Driver;
import org.freedesktop.DBus;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.Message;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by silveryard on 30.04.17.
 */
public final class BluetoothDriver extends Driver<BluetoothDevice> {
    private final List<BluetoothDevice> devices;
    private final DBusConnection connection;
    private final ObjectManager objectManager;

    public BluetoothDriver(){
        devices = new ArrayList<>();
        try {
            connection = DBusConnection.getConnection(DBusConnection.SYSTEM);
            objectManager = connection.getRemoteObject("org.bluez", "/", ObjectManager.class);;
        } catch (DBusException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onLoad(){
        Map<DBusInterface, Map<String, Map<String, Variant>>> objs = objectManager.GetManagedObjects();

        System.out.println("Output:");
        objs.forEach(new BiConsumer<DBusInterface, Map<String, Map<String, Variant>>>() {
            public void accept(DBusInterface dBusInterface, Map<String, Map<String, Variant>> stringMapMap) {
                System.out.println(dBusInterface.getObjectPath());

                for(String k1 : stringMapMap.keySet()){
                    System.out.println("  " + k1);

                    for(String k2 : stringMapMap.get(k1).keySet()){
                        System.out.println("    " + k2);
                        Variant v = stringMapMap.get(k1).get(k2);
                        System.out.println("      " + v.toString());
                    }
                }
            }
        });
    }
    @Override
    public void onUnload(){
    }
    @Override
    public void update(){

    }

    @Override
    public List<BluetoothDevice> getDevices() {
        return new ArrayList<>(devices);
    }
}
