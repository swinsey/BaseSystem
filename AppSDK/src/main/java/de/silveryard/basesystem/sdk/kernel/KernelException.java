package de.silveryard.basesystem.sdk.kernel;

/**
 * Created by Sebif on 08.04.2017.
 */
public class KernelException extends RuntimeException {
    private ReturnCode returnCode;

    /**
     * Constructor
     * @param returnCode ReturnCode enum value
     */
    public KernelException(ReturnCode returnCode){
        super();
        this.returnCode = returnCode;
    }
    /**
     * Constructor
     * @param returnCode ReturnCpde enum value
     * @param message Exception message
     */
    public KernelException(ReturnCode returnCode, String message){
        super(message);
        this.returnCode = returnCode;
    }
    /**
     * Constructor
     * @param returnCode ReturnCode enum value
     * @param base Base exception
     */
    public KernelException(ReturnCode returnCode, Throwable base){
        super(base);
        this.returnCode = returnCode;
    }
    /**
     * Constructor
     * @param returnCode ReturnCode enum value
     * @param message Exception message
     * @param base Base exception
     */
    public KernelException(ReturnCode returnCode, String message, Throwable base){
        super(message, base);
        this.returnCode = returnCode;
    }

    /**
     * Returns the ReturnCode value of the exception
     * @return ReturnCode enum value
     */
    public ReturnCode getReturnCode(){
        return returnCode;
    }
}
