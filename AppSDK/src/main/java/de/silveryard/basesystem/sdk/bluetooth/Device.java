package de.silveryard.basesystem.sdk.bluetooth;

import de.silveryard.basesystem.sdk.BtKernelException;
import de.silveryard.basesystem.sdk.KernelException;
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

    public Device(int id){
        this.id = id;

        returnCodeWrapper = new Wrapper<>();
        btReturnCodeWrapper = new Wrapper<>();
        stringWrapper = new Wrapper<>();
        booleanWrapper = new Wrapper<>();
    }

    public int getId(){
        return id;
    }

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

    public void pair(){
        systemCallDriverBTDevicePair(id, returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    public void cancelPairing(){
        systemCallDriverBTDeviceCancelPairing(id, returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    public void connect(){
        systemCallDriverBTDeviceConnect(id, returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
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
