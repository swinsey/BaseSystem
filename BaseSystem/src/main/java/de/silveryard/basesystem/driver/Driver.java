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

    /**
     * Constructor
     */
    public Driver(){
        loaded = false;
        devices = new ArrayList<>();
        connectedHandlers = new ArrayList<>();
        disconnectedHandlers = new ArrayList<>();
    }

    /**
     * Called when the driver gets loaded
     */
    public void onLoad(){
        loaded = true;
    }
    /**
     * Called when the driver gets unloaded
     */
    public void onUnload(){
        loaded = false;
        devices = new ArrayList<>();
        connectedHandlers = new ArrayList<>();
        disconnectedHandlers = new ArrayList<>();
    }

    /**
     * Updates the driver
     */
    public abstract void update();

    /**
     * Returns if this driver object is currently loaded
     * @return
     */
    public final boolean isLoaded(){
        return loaded;
    }
    /**
     * Returns a list of all connected devices managed by this driver
     * @return List of devices
     */
    public final List<TDev> getDevices(){
        return devices;
    }

    /**
     * Registers a connected handler.
     * @param handler Handler. Gets called when a new device connected to this driver
     */
    public final void registerConnectedHandler(DeviceHandler<TDev> handler){
        connectedHandlers.add(handler);
    }
    /**
     * Unregisters a connected handler
     * @param handler Handler. Will no longer be called when a new device connected to this driver
     */
    public final void unregisterConnectedHandler(DeviceHandler<TDev> handler){
        connectedHandlers.remove(handler);
    }

    /**
     * Registers a disconnected handler
     * @param handler Handler. Gets called when a device disconnected from the driver
     */
    public final void registerDisconnectedHandler(DeviceHandler<TDev> handler){
        disconnectedHandlers.add(handler);
    }
    /**
     * Unregisters a disconnected handler
     * @param handler Handler. Will no longer be called when a device disconnects from the drievr
     */
    public final void unregisterDisconnectedHandler(DeviceHandler<TDev> handler){
        disconnectedHandlers.remove(handler);
    }

    /**
     * Called when a device connected
     * @param device
     */
    protected final void onDeviceConnected(TDev device){
        devices.add(device);
        for(int i = 0; i < connectedHandlers.size(); i++){
            connectedHandlers.get(i).handle(device);
        }
    }
    /**
     * Called when a device disconnected
     * @param device
     */
    protected final void onDeviceDisconnected(TDev device){
        devices.remove(device);
        for(int i = 0; i < disconnectedHandlers.size(); i++){
            disconnectedHandlers.get(i).handle(device);
        }
    }
}
