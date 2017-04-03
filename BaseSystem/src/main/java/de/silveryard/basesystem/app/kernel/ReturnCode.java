package de.silveryard.basesystem.app.kernel;

/**
 * Created by Sebif on 13.03.2017.
 */
public enum ReturnCode {
    OK(1),
    ERROR(2),
    NOT_IMPLEMENTED(3),
    INVALID_RESPONSE(4),
    INVALID_MESSAGE(5);

    private int value;

    ReturnCode(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
