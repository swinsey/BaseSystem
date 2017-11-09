package de.silveryard.basesystem.sdk.bluetooth.phone;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.phone.BtPhoneReturnCode;

/**
 * Created by silveryard on 29.05.17.
 */
public final class BtPhoneKernelException extends KernelException {
    private BtPhoneReturnCode btPhoneReturnCode;

    /**
     * Constructor
     * @param btPhoneReturnCode
     */
    public BtPhoneKernelException(BtPhoneReturnCode btPhoneReturnCode) {
        super(ReturnCode.ERROR, "BtAudioReturnCode: " + btPhoneReturnCode);
        this.btPhoneReturnCode = btPhoneReturnCode;
    }
    /**
     * Constructor
     * @param btPhoneReturnCode
     * @param message
     */
    public BtPhoneKernelException(BtPhoneReturnCode btPhoneReturnCode, String message) {
        super(ReturnCode.ERROR, message);
        this.btPhoneReturnCode = btPhoneReturnCode;
    }
    /**
     * Constructor
     * @param btPhoneReturnCode
     * @param base
     */
    public BtPhoneKernelException(BtPhoneReturnCode btPhoneReturnCode, Throwable base) {
        super(ReturnCode.ERROR, base);
        this.btPhoneReturnCode = btPhoneReturnCode;
    }
    /**
     * Constructor
     * @param btPhoneReturnCode
     * @param message
     * @param base
     */
    public BtPhoneKernelException(BtPhoneReturnCode btPhoneReturnCode, String message, Throwable base) {
        super(ReturnCode.ERROR, message, base);
        this.btPhoneReturnCode = btPhoneReturnCode;
    }

    /**
     * Returns the underlying return code
     * @return
     */
    public BtPhoneReturnCode getBtPhoneReturnCode(){
        return btPhoneReturnCode;
    }
}
