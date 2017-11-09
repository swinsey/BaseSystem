package de.silveryard.basesystem.sdk.bluetooth;

import de.silveryard.basesystem.sdk.kernel.KernelException;
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

    /**
     * Returns the MAC address of this adapter
     * @return MAC address
     */
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
    /**
     * Returns the alias name of this adapter
     * @return Alias name
     */
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
    /**
     * Returns the modalias name of this adapter
     * @return Modalias name
     */
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
    /**
     * Returns the name of this adapter
     * @return Name value
     */
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
    /**
     * Returns the discoverable flag of this adapter
     * @return True if this adapter is set to discoverable. False otherwise
     */
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
    /**
     * Returns the discovering flag of this adapter
     * @return True if this adapter is currently discovering. False otherwise
     */
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
    /**
     * Returns the pariable flag of this adapter
     * @return True if this adapter is currently pairable. False otherwise
     */
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
    /**
     * Returns the powered flag of this adapter
     * @return True if this adapter is currently powered. False otherwise
     */
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

    /**
     * Starts the discovery
     */
    public static void startDiscovery(){
        systemcallDriverBTAdapterStartDiscovery(returnCodeWrapper, btReturnCodeWrapper);

        if(btReturnCodeWrapper.value != BtReturnCode.OK){
            throw new BtKernelException(btReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Stops the discovery
     */
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
