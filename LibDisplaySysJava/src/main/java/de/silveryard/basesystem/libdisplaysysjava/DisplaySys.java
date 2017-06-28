package de.silveryard.basesystem.libdisplaysysjava;

import de.silveryard.basesystem.libdisplayjava.Display;
import de.silveryard.basesystem.libdisplayjava.DisplayResult;
import de.silveryard.basesystem.libdisplayjava.Handle;
import de.silveryard.basesystem.libdisplayjava.Wrapper;

/**
 * Created by Sebif on 23.06.2017.
 */
public abstract class DisplaySys {
    private static boolean initialized;

    private static native int initNative();

    private static native int showWindowNative(Handle handle);
    private static native int hideWindowNative(Handle handle);

    private static native int getWindowLayerNative(Handle handle, Wrapper<Integer> layer);
    private static native int setWindowLayerNative(Handle handle, int layer);

    private static native int getWindowPositionNative(Handle handle, Wrapper<Integer> posX, Wrapper<Integer> posY);
    private static native int setWindowPositionNative(Handle handle, int posX, int posY);

    private static native int getWindowOpacityNative(Handle handle, Wrapper<Integer> opacity);
    private static native int setWindowOpacityNative(Handle handle, int opacity);

    public static int initialize(){
        if(initialized){
            return DisplayResult.SUCCESS.getValue();
        }
        initialized = true;

        System.loadLibrary("DisplaySys");

        return initNative();
    }

    public static int showWindow(Handle handle){
        checkNull(handle, 0);

        int result = initialize();
        if(Display.isError(result)){
            return result;
        }

        return showWindowNative(handle);
    }
    public static int hideWindow(Handle handle){
        checkNull(handle, 0);

        int result = initialize();
        if(Display.isError(result)){
            return result;
        }

        return hideWindowNative(handle);
    }

    public static int getWindowLayer(Handle handle, Wrapper<Integer> layer){
        checkNull(handle, 0);
        checkNull(layer, 0);

        int result = initialize();
        if(Display.isError(result)){
            return result;
        }

        return getWindowLayerNative(handle, layer);
    }
    public static int setWindowLayer(Handle handle, int layer){
        checkNull(handle, 0);

        int result = initialize();
        if(Display.isError(result)){
            return result;
        }

        return setWindowLayerNative(handle, layer);
    }

    public static int getWindowPosition(Handle handle, Wrapper<Integer> posX, Wrapper<Integer> posY){
        checkNull(handle, 0);
        checkNull(posX, 1);
        checkNull(posY, 2);

        int result = initialize();
        if(Display.isError(result)){
            return result;
        }

        return getWindowPositionNative(handle, posX, posY);
    }
    public static int setWindowPosition(Handle handle, int posX, int posY){
        checkNull(handle, 0);
        checkRange(posX, Short.MIN_VALUE, Short.MAX_VALUE, 1);
        checkRange(posY, Short.MIN_VALUE, Short.MAX_VALUE, 2);


        int result = initialize();
        if(Display.isError(result)){
            return result;
        }

        return setWindowPositionNative(handle, posX, posY);
    }

    public static int getWindowOpacity(Handle handle, Wrapper<Integer> opacity){
        checkNull(handle, 0);
        checkNull(opacity, 1);

        int result = initialize();
        if(Display.isError(result)){
            return result;
        }

        return getWindowOpacityNative(handle, opacity);
    }
    public static int setWindowOpacity(Handle handle, int opacity){
        checkNull(handle, 0);
        checkRange(opacity, 0, 255, 1);

        int result = initialize();
        if(Display.isError(result)){
            return result;
        }

        return setWindowOpacityNative(handle, opacity);
    }

    private static void checkNull(Object obj, int index){
        if(obj == null){
            throw new IllegalArgumentException("Method parameter with index " + index + " cannot be null");
        }
    }
    private static void checkRange(int i, int min, int max, int index){
        if(i < min || i > max){
            throw new IllegalArgumentException("Method parameter with index " +  index + " cannot be lower than " +  min + " or higher than " + max);
        }
    }

    private DisplaySys(){}
}
