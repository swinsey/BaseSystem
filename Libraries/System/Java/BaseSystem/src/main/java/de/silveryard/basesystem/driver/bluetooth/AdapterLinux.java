package de.silveryard.basesystem.driver.bluetooth;

import org.freedesktop.DBus;
import org.freedesktop.dbus.Path;

/**
 * Created by silveryard on 01.05.17.
 */
final class AdapterLinux extends Adapter {
    private static final String INTERFACE = "org.bluez.Adapter1";

    private final DBus.Properties properties;
    private final de.silveryard.basesystem.driver.bluetooth.dbus.Adapter adapter;

    public AdapterLinux(DBus.Properties properties, de.silveryard.basesystem.driver.bluetooth.dbus.Adapter adapter){
        this.properties = properties;
        this.adapter = adapter;
    }

    @Override
    public String getAddress(){
        return properties.Get(INTERFACE, "Address");
    }
    @Override
    public String getAlias(){
        return properties.Get(INTERFACE, "Alias");
    }
    @Override
    public String getModalias(){
        return properties.Get(INTERFACE, "Modalias");
    }
    @Override
    public String getName(){
        return properties.Get(INTERFACE, "Name");
    }

    @Override
    public boolean isDiscoverable(){
        return properties.Get(INTERFACE, "Discoverable");
    }
    @Override
    public boolean isDiscovering(){
        return properties.Get(INTERFACE, "Discovering");
    }
    @Override
    public boolean isPairable(){
        return properties.Get(INTERFACE, "Pairable");
    }
    @Override
    public boolean isPowered(){
        return properties.Get(INTERFACE, "Powered");
    }

    @Override
    public void startDiscovery() {
        adapter.StartDiscovery();
    }
    @Override
    public void stopDiscovery() {
        adapter.StopDiscovery();
    }

    public void removeDevice(String path){
        adapter.RemoveDevice(new Path(path));
    }
}
