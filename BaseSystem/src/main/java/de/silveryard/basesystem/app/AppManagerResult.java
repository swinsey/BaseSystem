package de.silveryard.basesystem.app;

/**
 * Created by Sebif on 02.03.2017.
 */
public enum AppManagerResult {
    OK(1),
    UNKNOWN_ERROR(2),
    NOT_INSTALLED(3),
    NEWER_VERSION_INSTALLED(4),
    NOT_RUNNING(5),
    ALREADY_RUNNING(6);

    private int value;

    AppManagerResult(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
