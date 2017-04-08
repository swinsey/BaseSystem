package de.silveryard.basesystem.sdk;

import de.silveryard.basesystem.sdk.kernel.ReturnCode;

/**
 * Created by Sebif on 08.04.2017.
 */
public class KernelException extends RuntimeException {
    private ReturnCode returnCode;

    public KernelException(ReturnCode returnCode){
        super();
        this.returnCode = returnCode;
    }
    public KernelException(ReturnCode returnCode, String message){
        super(message);
        this.returnCode = returnCode;
    }
    public KernelException(ReturnCode returnCode, Throwable base){
        super(base);
        this.returnCode = returnCode;
    }
    public KernelException(ReturnCode returnCode, String message, Throwable base){
        super(message, base);
        this.returnCode = returnCode;
    }

    public ReturnCode getReturnCode(){
        return returnCode;
    }
}
