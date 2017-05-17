package de.silveryard.basesystem.sdk.bluetooth.audio;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.BtReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.audio.BtAudioReturnCode;

/**
 * Created by silveryard on 11.05.17.
 */
public class BtAudioKernelException extends KernelException {
    private BtAudioReturnCode btAudioReturnCode;

    /**
     * Constructor
     * @param btAudioReturnCode
     */
    public BtAudioKernelException(BtAudioReturnCode btAudioReturnCode) {
        super(ReturnCode.ERROR);
        this.btAudioReturnCode = btAudioReturnCode;
    }
    /**
     * Constructor
     * @param btAudioReturnCode
     * @param message
     */
    public BtAudioKernelException(BtAudioReturnCode btAudioReturnCode, String message) {
        super(ReturnCode.ERROR, message);
        this.btAudioReturnCode = btAudioReturnCode;
    }
    /**
     * Constructor
     * @param btReturnCode
     * @param base
     */
    public BtAudioKernelException(BtReturnCode btReturnCode, Throwable base) {
        super(ReturnCode.ERROR, base);
        this.btAudioReturnCode = btAudioReturnCode;
    }
    /**
     * Constructor
     * @param btAudioReturnCode
     * @param message
     * @param base
     */
    public BtAudioKernelException(BtAudioReturnCode btAudioReturnCode, String message, Throwable base) {
        super(ReturnCode.ERROR, message, base);
        this.btAudioReturnCode = btAudioReturnCode;
    }

    /**
     * Returns the underlying return code
     * @return
     */
    public BtAudioReturnCode getBtAudioReturnCode(){
        return btAudioReturnCode;
    }
}
