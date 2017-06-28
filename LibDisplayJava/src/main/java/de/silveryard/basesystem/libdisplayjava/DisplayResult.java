package de.silveryard.basesystem.libdisplayjava;

/**
 * Created by Sebif on 21.06.2017.
 */
public enum DisplayResult {
    SUCCESS_NOOP(50662977),
    SUCCESS(50462976),
    ERROR_UNKNOWN(-50462977),
    ERROR_INVALID_HANDLE(-50462978),

    //Fallback
    ERROR_UNKNOWN_PLATFORM(-50462986),

    //DispmanX
    ERROR_DISPMANX_NO_UPDATE_HANDLE(-50462996),
    ERROR_DISPMANX_NO_ELEMENT_HANDLE(-50462997),

    //Windows
    ERROR_WINDOWS_NO_HWND(-50463006);

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
        return Display.isSuccess(this.getValue());
    }
    public boolean isError(){
        return Display.isError(this.getValue());
    }

    public int getValue(){
        return value;
    }
}
