package de.silveryard.basesystem.libdisplayjava;

/**
 * Created by Sebif on 21.06.2017.
 */
public enum DisplayResult {
    SUCCESS(0),

    //Fallback
    ERROR_UNKNOWN_PLATFORM(-1),

    //DispmanX
    ERROR_DISPMANX_NO_UPDATE_HANDLE(-2),
    ERROR_DISPMANX_NO_ELEMENT_HANDLE(-3);

    public static DisplayResult getEnumValue(int value){
        DisplayResult[] values = DisplayResult.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].getValue() == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    DisplayResult(int value){
        this.value = value;
    }

    public boolean isSuccess(){
        return Display.isSuccess(this);
    }
    public boolean isError(){
        return Display.isError(this);
    }

    public int getValue(){
        return value;
    }
}
