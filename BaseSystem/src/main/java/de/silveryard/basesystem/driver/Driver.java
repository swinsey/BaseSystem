package de.silveryard.basesystem.driver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 29.04.17.
 */
public abstract class Driver<TDev extends Device>  {
    private boolean loaded;
    private List<TDev> devices;
    private List<DeviceHandler<TDev>> connectedHandlers;
    private List<DeviceHandler<TDev>> disconnectedHandlers;

    public Driver(){
        loaded = false;
        devices = new ArrayList<>();
        connectedHandlers = new ArrayList<>();
        disconnectedHandlers = new ArrayList<>();
    }

    public void onLoad(){
        loaded = true;
    }
    public void onUnload(){
        loaded = false;
        devices = new ArrayList<>();
        connectedHandlers = new ArrayList<>();
        disconnectedHandlers = new ArrayList<>();
    }

    public abstract void update();

    public final boolean isLoaded(){
        return loaded;
    }
    public final List<TDev> getDevices(){
        return devices;
    }

    public final void registerConnectedHandler(DeviceHandler<TDev> handler){
        connectedHandlers.add(handler);
    }
    public final void unregisterConnectedHandler(DeviceHandler<TDev> handler){
        connectedHandlers.remove(handler);
    }

    public final void registerDisconnectedHandler(DeviceHandler<TDev> handler){
        disconnectedHandlers.add(handler);
    }
    public final void unregisterDisconnectedHandler(DeviceHandler<TDev> handler){
        disconnectedHandlers.remove(handler);
    }

    protected final void onDeviceConnected(TDev device){
        devices.add(device);
        for(int i = 0; i < connectedHandlers.size(); i++){
            connectedHandlers.get(i).handle(device);
        }
    }
    protected final void onDeviceDisconnected(TDev device){
        devices.remove(device);
        for(int i = 0; i < disconnectedHandlers.size(); i++){
            disconnectedHandlers.get(i).handle(device);
        }
    }
}
