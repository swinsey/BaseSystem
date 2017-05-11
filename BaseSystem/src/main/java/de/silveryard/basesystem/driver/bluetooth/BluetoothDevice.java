package de.silveryard.basesystem.driver.bluetooth;

import de.silveryard.basesystem.driver.Device;
import de.silveryard.basesystem.driver.DeviceHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silveryard on 30.04.17.
 */
public abstract class BluetoothDevice extends Device {
    private final List<DeviceHandler<BluetoothDevice>> pairedHandlers;
    private final List<DeviceHandler<BluetoothDevice>> failedPairingHandlers;
    private final List<DeviceHandler<BluetoothDevice>> connectedHandlers;
    private final List<DeviceHandler<BluetoothDevice>> failedConnectingHandlers;
    private final List<DeviceHandler<BluetoothDevice>> disconnectedHandlers;
    private final List<DeviceHandler<BluetoothDevice>> removedHandlers;

    private static int nextId = 1;

    private final int id;

    protected BluetoothDevice(){
        id = nextId;
        nextId++;

        pairedHandlers = new ArrayList<>();
        failedPairingHandlers = new ArrayList<>();
        connectedHandlers = new ArrayList<>();
        failedConnectingHandlers = new ArrayList<>();
        disconnectedHandlers = new ArrayList<>();
        removedHandlers = new ArrayList<>();
    }

    /**
     * Returns the internal identifier
     * @return Internal id
     */
    public final int getId(){
        return id;
    }
    /**
     * Returns if this device is blocked
     * @return True if blocked. False otherwise
     */
    public abstract boolean isBlocked();
    /**
     * Returns if this device is connected
     * @return True if connected. False otherwise
     */
    public abstract boolean isConnected();
    /**
     * Returns if this device is paired
     * @return True if paired. False otherwise
     */
    public abstract boolean isPaired();
    /**
     * Returns if this device is trusted
     * @return True if trusted. False otherwise
     */
    public abstract boolean isTrusted();

    /**
     * Returns the devices MAC address
     * @return MAC address
     */
    public abstract String getAddress();
    /**
     * Returns the devices alias name
     * @return Alias
     */
    public abstract String getAlias();
    /**
     * Returns the devices icon name
     * TODO: Change to 'getType' with enum return type
     * @return Icon type
     */
    public abstract String getIcon();
    /**
     * Returns the devices name
     * @return Device name
     */
    public abstract String getName();

    /**
     * Pairs with this device
     * @return
     */
    protected abstract boolean pairInternal();
    /**
     * Cancels an active pairing process
     */
    public abstract void cancelPairing();
    /**
     * Removes an already paired device
     */
    protected abstract void removeInternal();
    /**
     * Connects to this device
     */
    protected abstract boolean connectInternal();
    /**
     * Disconnects from this device
     */
    protected abstract void disconnectInternal();

    /**
     * Pairs with this device
     * @return
     */
    public final synchronized boolean pair(){
        boolean result = false;
        try{
            result = pairInternal();
        }catch(Throwable t){
            t.printStackTrace();
        }

        if(result){
            callEvent(pairedHandlers);
        }else{
            callEvent(failedPairingHandlers);
        }

        return result;
    }
    /**
     * Removes an already paired device
     */
    public final synchronized void remove(){
        removeInternal();
        callEvent(removedHandlers);
    }
    /**
     * Connects to this device
     */
    public final synchronized boolean connect(){
        boolean result = false;
        try{
            result = connectInternal();;
        }catch(Throwable t){
            t.printStackTrace();
        }

        if(result){
            callEvent(connectedHandlers);
        }else{
            callEvent(failedConnectingHandlers);
        }

        return result;
    }
    /**
     * Disconnects from this device
     */
    public final synchronized void disconnect(){
        disconnectInternal();
        callEvent(disconnectedHandlers);
    }

    /**
     * Registers a new handler for the event "paired". This handler will be called when pairing to this device succeeds
     * @param handler Handler to be registered
     */
    public final synchronized void registerPairedHandler(DeviceHandler<BluetoothDevice> handler){
        pairedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "paired". This handler will no longer be called when pairing to this device succeeds
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterPairedHandler(DeviceHandler<BluetoothDevice> handler){
        pairedHandlers.remove(handler);
    }
    /**
     * Registers a new handler to the event "failedPairing". This handler will be called when pairing to this device fails
     * @param handler Handler to be registered
     */
    public final synchronized void registerFailedPairingHandler(DeviceHandler<BluetoothDevice> handler){
        failedPairingHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "failedPairing". This handler will no longer be called when pairing to this device failed
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterFailedPairingHandler(DeviceHandler<BluetoothDevice> handler){
        failedPairingHandlers.remove(handler);
    }
    /**
     * Registers a new handler to the event "connected". This handler will be called when connecting to this device succeeds
     * @param handler Handler to be registered
     */
    public final synchronized void registerConnectedHandler(DeviceHandler<BluetoothDevice> handler){
        connectedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "connected". This handler will no longer be called when connecting to this device succeeds
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterConnectedHandler(DeviceHandler<BluetoothDevice> handler){
        connectedHandlers.remove(handler);
    }
    /**
     * Registers a new handler to the event "failedConnecting". This handler will be called when connecting to this device failed
     * @param handler Handler to be registered
     */
    public final synchronized void registerFailedConnectingHandler(DeviceHandler<BluetoothDevice> handler){
        failedConnectingHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "failedConnecting". This handler will no longer be called when connecting to this device failed
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterFailedConnectingHandler(DeviceHandler<BluetoothDevice> handler){
        failedConnectingHandlers.remove(handler);
    }
    /**
     * URegisters a new handler to the event "disconnected". This handler will be called when this device got disconnected
     * @param handler Handler to be registered
     */
    public final synchronized void registerDisconnectedHandler(DeviceHandler<BluetoothDevice> handler){
        disconnectedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "disconnected". This handler will no longer be called when this device git disconnected
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterDisconnectedHandler(DeviceHandler<BluetoothDevice> handler){
        disconnectedHandlers.remove(handler);
    }
    /**
     * Registers a new handler to the event "removed". This handler will be called when this device got removed
     * @param handler Handler to be registered
     */
    public final synchronized void registerRemovedHandler(DeviceHandler<BluetoothDevice> handler){
        removedHandlers.add(handler);
    }
    /**
     * Unregisters a handler from the event "removed". This handler will no longer be called when this device got removed
     * @param handler Handler to be unregistered
     */
    public final synchronized void unregisterRemovedHandler(DeviceHandler<BluetoothDevice> handler){
        removedHandlers.remove(handler);
    }

    private void callEvent(List<DeviceHandler<BluetoothDevice>> handlers){
        for(int i = 0; i < handlers.size(); i++){
            DeviceHandler handler = handlers.get(i);
            handler.handle(this);
        }
    }
}
