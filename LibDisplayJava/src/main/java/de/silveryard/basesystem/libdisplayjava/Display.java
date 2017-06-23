package de.silveryard.basesystem.libdisplayjava;

/**
 * Created by Sebif on 21.06.2017.
 */
public abstract class Display {
    private static boolean initialized = false;

    public static void initialize(){
        if(initialized){
            return;
        }
        initialized = true;

        System.loadLibrary("Display");
    }

    public static boolean compareHandles(Handle h1, Handle h2){
        initialize();
        return compareHandlesNative(h1, h2);
    }
    public static DisplayResult getHandle(Handle handle){
        initialize();

        int result = getHandleNative(handle);
        return DisplayResult.getEnumValue(result);
    }
    public static DisplayResult getScreenSize(Handle handle, ScreenSize screenSize){
        initialize();

        int result = getWindowSizeNative(handle, screenSize);
        return DisplayResult.getEnumValue(result);
    }

    public static boolean isError(DisplayResult result){
        initialize();
        return isErrorNative(result.getValue());
    }
    public static boolean isSuccess(DisplayResult result){
        initialize();
        return isSuccessNative(result.getValue());
    }

    private static native boolean compareHandlesNative(Handle h1, Handle h2);
    private static native int getHandleNative(Handle handle);
    private static native int getWindowSizeNative(Handle handle, ScreenSize size);

    private static native boolean isSuccessNative(int result);
    private static native boolean isErrorNative(int result);

    private Display(){}
}
