package de.silveryard.basesystem.sdk.bluetooth;

import de.silveryard.basesystem.sdk.BtKernelException;
import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.bluetooth.BtReturnCode;

import static de.silveryard.basesystem.sdk.kernel.bluetooth.Adapter.*;

/**
 * Created by silveryard on 01.05.17.
 */
public abstract class Adapter {
    private static final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private static final Wrapper<BtReturnCode> btReturnCodeWrapper = new Wrapper<>();
    private static final Wrapper<String> stringWrapper = new Wrapper<>();
    private static final Wrapper<Boolean> booleanWrapper = new Wrapper<>();

    public static String getAddress(){
        systemcallDriverBTAdapterGetAddress(returnCodeWrapper, btReturnCodeWrapper, stringWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    public static String getAlias(){
        systemcallDriverBTAdapterGetAlias(returnCodeWrapper, btReturnCodeWrapper, stringWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    public static String getModalias(){
        systemcallDriverBTAdapterGetModalias(returnCodeWrapper, btReturnCodeWrapper, stringWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }
    public static String getName(){
        systemcallDriverBTAdapterGetName(returnCodeWrapper, btReturnCodeWrapper, stringWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }

    public static boolean isDiscoverable(){
        systemcallDriverBTAdapterIsDiscoverable(returnCodeWrapper, btReturnCodeWrapper, booleanWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    public static boolean isDiscovering(){
        systemcallDriverBTAdapterIsDiscovering(returnCodeWrapper, btReturnCodeWrapper, booleanWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    public static boolean isPairable(){
        systemcallDriverBTAdapterIsPairable(returnCodeWrapper, btReturnCodeWrapper, booleanWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    public static boolean isPowered(){
        systemcallDriverBTAdapterIsPowered(returnCodeWrapper, btReturnCodeWrapper, booleanWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }

    public static void startDiscovery(){
        systemcallDriverBTAdapterStartDiscovery(returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    public static void stopDiscovery(){
        systemcallDriverBTAdapterStopDiscovery(returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
}
