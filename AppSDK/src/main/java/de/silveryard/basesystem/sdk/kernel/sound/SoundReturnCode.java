package de.silveryard.basesystem.sdk.kernel.sound;

/**
 * Created by Sebif on 07.04.2017.
 */
public enum SoundReturnCode {
    OK(1),
    UNKNOWN_ERROR(2),
    PATH_NOT_EXISTENT(3),
    PATH_NO_FILE(4),
    INVALID_ID(5);

    public static SoundReturnCode getEnumValue(int value){
        SoundReturnCode[] values = SoundReturnCode.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].getValue() == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    SoundReturnCode(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
