package de.silveryard.basesystem.sdk;

import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.BtReturnCode;

/**
 * Created by silveryard on 01.05.17.
 */
public class BtKernelException extends KernelException {
    private BtReturnCode btReturnCode;

    public BtKernelException(BtReturnCode btReturnCode) {
        super(ReturnCode.ERROR);
        this.btReturnCode = btReturnCode;
    }
    public BtKernelException(BtReturnCode btReturnCode, String message) {
        super(ReturnCode.ERROR, message);
        this.btReturnCode = btReturnCode;
    }
    public BtKernelException(BtReturnCode btReturnCode, Throwable base) {
        super(ReturnCode.ERROR, base);
        this.btReturnCode = btReturnCode;
    }
    public BtKernelException(BtReturnCode btReturnCode, String message, Throwable base) {
        super(ReturnCode.ERROR, message, base);
        this.btReturnCode = btReturnCode;
    }

    public BtReturnCode getBtReturnCode(){
        return btReturnCode;
    }
}
