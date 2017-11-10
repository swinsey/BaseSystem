package de.silveryard.basesystem.util;

/**
 * Created by Beppo on 10.11.2017.
 */
public final class Result {
    public static boolean isSuccess(int errorCode){
        return errorCode > 0;
    }
    public static boolean isError(int errorCode){
        return errorCode < 0;
    }

    private Result(){}
}
