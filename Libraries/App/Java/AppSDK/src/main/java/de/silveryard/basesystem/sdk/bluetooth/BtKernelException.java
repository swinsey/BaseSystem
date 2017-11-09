package de.silveryard.basesystem.sdk.bluetooth;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.BtReturnCode;

/**
 * Created by silveryard on 01.05.17.
 */
public class BtKernelException extends KernelException {
    private BtReturnCode btReturnCode;

    /**
     * Constructor
     * @param btReturnCode
     */
    public BtKernelException(BtReturnCode btReturnCode) {
        super(ReturnCode.ERROR, "BtReturnCode: " + btReturnCode);
        this.btReturnCode = btReturnCode;
    }
    /**
     * Constructor
     * @param btReturnCode
     * @param message
     */
    public BtKernelException(BtReturnCode btReturnCode, String message) {
        super(ReturnCode.ERROR, message);
        this.btReturnCode = btReturnCode;
    }
    /**
     * Constructor
     * @param btReturnCode
     * @param base
     */
    public BtKernelException(BtReturnCode btReturnCode, Throwable base) {
        super(ReturnCode.ERROR, base);
        this.btReturnCode = btReturnCode;
    }
    /**
     * Constructor
     * @param btReturnCode
     * @param message
     * @param base
     */
    public BtKernelException(BtReturnCode btReturnCode, String message, Throwable base) {
        super(ReturnCode.ERROR, message, base);
        this.btReturnCode = btReturnCode;
    }

    /**
     * Returns the underlying return code
     * @return
     */
    public BtReturnCode getBtReturnCode(){
        return btReturnCode;
    }
}
