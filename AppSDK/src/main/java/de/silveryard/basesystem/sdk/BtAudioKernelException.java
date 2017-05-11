package de.silveryard.basesystem.sdk;

import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.BtReturnCode;
import de.silveryard.basesystem.sdk.kernel.bluetooth.audio.BtAudioReturnCode;

/**
 * Created by silveryard on 11.05.17.
 */
public class BtAudioKernelException extends KernelException {
    private BtAudioReturnCode btAudioReturnCode;

    public BtAudioKernelException(BtAudioReturnCode btAudioReturnCode) {
        super(ReturnCode.ERROR);
        this.btAudioReturnCode = btAudioReturnCode;
    }
    public BtAudioKernelException(BtAudioReturnCode btAudioReturnCode, String message) {
        super(ReturnCode.ERROR, message);
        this.btAudioReturnCode = btAudioReturnCode;
    }
    public BtAudioKernelException(BtReturnCode btReturnCode, Throwable base) {
        super(ReturnCode.ERROR, base);
        this.btAudioReturnCode = btAudioReturnCode;
    }
    public BtAudioKernelException(BtAudioReturnCode btAudioReturnCode, String message, Throwable base) {
        super(ReturnCode.ERROR, message, base);
        this.btAudioReturnCode = btAudioReturnCode;
    }

    public BtAudioReturnCode getBtAudioReturnCode(){
        return btAudioReturnCode;
    }
}
