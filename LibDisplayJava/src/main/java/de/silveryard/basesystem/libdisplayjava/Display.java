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
    public static int getHandle(Wrapper<Handle> handle){
        Handle h = new Handle();
        int r = getHandle(h);

        handle.value = h;
        return r;
    }
    public static int getHandle(Handle handle){
        initialize();

        int result = getHandleNative(handle);
        return result;
    }
    public static int getScreenSize(Handle handle, Wrapper<Integer> width, Wrapper<Integer> height){
        initialize();

        int result = getWindowSizeNative(handle, width, height);
        return result;
    }

    public static boolean isError(int result){
        initialize();
        return isErrorNative(result);
    }
    public static boolean isSuccess(int result){
        initialize();
        return isSuccessNative(result);
    }

    private static native boolean compareHandlesNative(Handle h1, Handle h2);
    private static native int getHandleNative(Handle handle);
    private static native int getWindowSizeNative(Handle handle, Wrapper<Integer> width, Wrapper<Integer> height);

    private static native boolean isSuccessNative(int result);
    private static native boolean isErrorNative(int result);

    private Display(){}
}
