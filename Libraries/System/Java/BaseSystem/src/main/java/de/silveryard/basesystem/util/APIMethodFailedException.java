package de.silveryard.basesystem.util;

/**
 * Created by Beppo on 10.11.2017.
 */
public final class APIMethodFailedException extends RuntimeException {
    public APIMethodFailedException(String library, String method, int errorCode){
        super("Library: " + library + " Method: " + method + " ErrorCode: " + errorCode);
    }
}
