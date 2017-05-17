package de.silveryard.basesystem.sdk.bluetooth;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.bluetooth.BtReturnCode;

import static de.silveryard.basesystem.sdk.kernel.bluetooth.Device.*;

/**
 * Created by silveryard on 01.05.17.
 */
public class Device {
    private final Wrapper<ReturnCode> returnCodeWrapper;
    private final Wrapper<BtReturnCode> btReturnCodeWrapper;
    private final Wrapper<String> stringWrapper;
    private final Wrapper<Boolean> booleanWrapper;

    private final int id;

    /**
     * Constructor
     * @param id Internal identifier
     */
    public Device(int id){
        this.id = id;

        returnCodeWrapper = new Wrapper<>();
        btReturnCodeWrapper = new Wrapper<>();
        stringWrapper = new Wrapper<>();
        booleanWrapper = new Wrapper<>();
    }

    /**
     * Returns the internal identifier
     * @return Internal id
     */
    public int getId(){
        return id;
    }

    /**
     * Returns the devices MAC address
     * @return MAC address
     */
    public String getAddress(){
        systemCallDriverBTDeviceGetAddress(id, returnCodeWrapper, btReturnCodeWrapper, stringWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Returns the devices alias name
     * @return Alias
     */
    public String getAlias(){
        systemCallDriverBTDeviceGetAlias(id, returnCodeWrapper, btReturnCodeWrapper, stringWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Returns the devices icon name
     * TODO: Change to 'getType' with enum return type
     * @return Icon type
     */
    public String getIcon(){
        systemCallDriverBTDeviceGetIcon(id, returnCodeWrapper, btReturnCodeWrapper, stringWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    /**
     * Returns the devices name
     * @return Device name
     */
    public String getName(){
        systemCallDriverBTDeviceGetName(id, returnCodeWrapper, btReturnCodeWrapper, stringWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }

    /**
     * Returns if this device is blocked
     * @return True if blocked. False otherwise
     */
    public boolean isBlocked(){
        systemCallDriverBTDeviceIsBlocked(id, returnCodeWrapper, btReturnCodeWrapper, booleanWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    /**
     * Returns if this device is connected
     * @return True if connected. False otherwise
     */
    public boolean isConnected(){
        systemCallDriverBTDeviceIsConnected(id, returnCodeWrapper, btReturnCodeWrapper, booleanWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    /**
     * Returns if this device is paired
     * @return True if paired. False otherwise
     */
    public boolean isPaired(){
        systemCallDriverBTDeviceIsPaired(id, returnCodeWrapper, btReturnCodeWrapper, booleanWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    /**
     * Returns if this device is trusted
     * @return True if trusted. False otherwise
     */
    public boolean isTrusted(){
        systemCallDriverBTDeviceIsTrusted(id, returnCodeWrapper, btReturnCodeWrapper, booleanWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }

    /**
     * Pairs with this device
     * @return
     */
    public void pair(){
        systemCallDriverBTDevicePair(id, returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Cancels an active pairing process
     */
    public void cancelPairing(){
        systemCallDriverBTDeviceCancelPairing(id, returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Connects to this device
     */
    public void connect(){
        systemCallDriverBTDeviceConnect(id, returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Disconnects from this device
     */
    public void disconnect(){
        systemCallDriverBTDeviceDisconnect(id, returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
}
