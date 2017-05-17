package de.silveryard.basesystem.sdk.app;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.app.AppManagerResult;

/**
 * Created by silveryard on 17.05.17.
 */
public class AppException extends KernelException {
    private AppManagerResult appManagerResult;

    /**
     * Constructor
     * @param appManagerResult
     */
    public AppException(AppManagerResult appManagerResult) {
        super(ReturnCode.ERROR);
        this.appManagerResult = appManagerResult;
    }
    /**
     * Constructor
     * @param appManagerResult
     * @param message
     */
    public AppException(AppManagerResult appManagerResult, String message) {
        super(ReturnCode.ERROR, message);
        this.appManagerResult = appManagerResult;
    }
    /**
     * Constructor
     * @param appManagerResult
     * @param base
     */
    public AppException(AppManagerResult appManagerResult, Throwable base) {
        super(ReturnCode.ERROR, base);
        this.appManagerResult = appManagerResult;
    }
    /**
     * Constructor
     * @param appManagerResult
     * @param message
     * @param base
     */
    public AppException(AppManagerResult appManagerResult, String message, Throwable base) {
        super(ReturnCode.ERROR, message, base);
        this.appManagerResult = appManagerResult;
    }

    /**
     * Returns the underlying return code
     * @return
     */
    public AppManagerResult getAppManagerResult(){
        return appManagerResult;
    }
}
