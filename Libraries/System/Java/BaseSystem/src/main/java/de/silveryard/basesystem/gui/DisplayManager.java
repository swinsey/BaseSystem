package de.silveryard.basesystem.gui;

import de.silveryard.basesystem.util.APIMethodFailedException;
import de.silveryard.basesystem.util.Result;
import de.silveryard.basesystem.util.Wrapper;

/**
 * Created by Beppo on 09.11.2017.
 */
public final class DisplayManager {
    private static DisplayManager instance;

    /**
     * Initializes the displaymanager. Needs to be called before getInstance can be used
     */
    public static void initialize(){
        instance = new DisplayManager();

        System.loadLibrary("lib-bs-sys-dspmngr-1-0-0");
        initNative();
    }
    private static native void initNative();
    public static DisplayManager getInstance(){
        return instance;
    }

    private DisplayManager(){}

    /**
     * Returns a DisplayHandle obtained of a given processId
     * @param processId
     * @return
     */
    public DisplayHandle getProcessHandle(long processId){
        DisplayHandle handle = new DisplayHandle();
        int result = nativeGetProcessHandle(processId, handle);

        if(Result.isError(result)){
            throw new APIMethodFailedException("dspmngr", "getProcessHandle", result);
        }

        return handle;
    }

    /**
     * Registers a new DisplayHandle to be managed by the system
     * @param handle
     */
    public void registerHandle(DisplayHandle handle) {
        if (handle == null) {
            throw new IllegalArgumentException();
        }

        int result = nativeRegisterHandle(handle);
        if (Result.isError(result)) {
            throw new APIMethodFailedException("dspmngr", "registerHandle", result);
        }
    }
    /**
     * Unregisters a DisplayHandle from being managed by the system
     * @param handle
     */
    public void unregisterHandle(DisplayHandle handle){
        if (handle == null) {
            throw new IllegalArgumentException();
        }

        int result = nativeUnregisterHandle(handle);
        if (Result.isError(result)) {
            throw new APIMethodFailedException("dspmngr", "unregisterHandle", result);
        }
    }

    /**
     * Sets the position of a given window
     * @param handle
     * @param x
     * @param y
     */
    public void setWindowPosition(DisplayHandle handle, int x, int y){
        if(handle == null){
            throw new IllegalArgumentException();
        }

        int result = nativeSetWindowPosition(handle, x, y);
        if(Result.isError(result)) {
            throw new APIMethodFailedException("dspmngr", "setWindowPosition", result);
        }
    }
    /**
     * Fetches the current x position of a given window
     * @param handle
     * @return
     */
    public int getWindowPositionX(DisplayHandle handle){
        if(handle == null){
            throw new IllegalArgumentException();
        }

        Wrapper<Integer> wrapperX = new Wrapper<>();
        Wrapper<Integer> wrapperY = new Wrapper<>();
        int result = nativeGetWindowPosition(handle, wrapperX, wrapperY);

        if(Result.isError(result)){
            throw new APIMethodFailedException("dspmngr", "getWindowPosition", result);
        }

        return wrapperX.value;
    }
    /**
     * Fetches the current y position of a given window
     * @param handle
     * @return
     */
    public int getWindowPositionY(DisplayHandle handle){
        if(handle == null){
            throw new IllegalArgumentException();
        }

        Wrapper<Integer> wrapperX = new Wrapper<>();
        Wrapper<Integer> wrapperY = new Wrapper<>();
        int result = nativeGetWindowPosition(handle, wrapperX, wrapperY);

        if(Result.isError(result)){
            throw new APIMethodFailedException("dspmngr", "getWindowPosition", result);
        }

        return wrapperY.value;
    }

    /**
     * Sets the alpha value of a given window
     * @param handle
     * @param alpha
     */
    public void setWindowAlpha(DisplayHandle handle, byte alpha){
        if(handle == null){
            throw new IllegalArgumentException();
        }

        int result = nativeSetWindowAlpha(handle, alpha);

        if(Result.isError(result)){
            throw new APIMethodFailedException("dspmngr", "setWindowAlpha", result);
        }
    }
    /**
     * Fatches the current alpha value of a given window
     * @param handle
     * @return
     */
    public byte getWindowAlpha(DisplayHandle handle){
        if(handle == null){
            throw new IllegalArgumentException();
        }

        Wrapper<Byte> wrapperAlpha = new Wrapper<>();
        int result = nativeGetWindowAlpha(handle, wrapperAlpha);

        if(Result.isError(result)){
            throw new APIMethodFailedException("dspmngr", "getWindowWalpha", result);
        }

        return wrapperAlpha.value;
    }

    /**
     * Sets the active flag of a given window
     * @param handle
     * @param active
     */
    public void setWindowActive(DisplayHandle handle, boolean active){
        if(handle == null){
            throw new IllegalArgumentException();
        }

        int result = nativeSetWindowActive(handle, active);

        if(Result.isError(result)){
            throw new APIMethodFailedException("dspmngr", "getWindowWalpha", result);
        }
    }
    /**
     * Fetches the active flag of a given window
     * @param handle
     * @return
     */
    public boolean getWindowActive(DisplayHandle handle){
        if(handle == null){
            throw new IllegalArgumentException();
        }

        Wrapper<Boolean> wrapperActive = new Wrapper<>();
        int result = nativeGetWindowActive(handle, wrapperActive);

        if(Result.isError(result)){
            throw new APIMethodFailedException("dspmngr", "getWindowWalpha", result);
        }

        return wrapperActive.value;
    }

    /**
     * Sets the layer of a given window
     * @param handle
     * @param layer
     */
    public void setWindowLayer(DisplayHandle handle, byte layer){
        if(handle == null){
            throw new IllegalArgumentException();
        }

        int result = nativeSetWindowLayer(handle, layer);

        if(Result.isError(result)){
            throw new APIMethodFailedException("dspmngr", "setWindowAlpha", result);
        }
    }
    /**
     * Fetches the current layer of a given window
     * @param handle
     * @return
     */
    public byte getWindowLayer(DisplayHandle handle){
        if(handle == null){
            throw new IllegalArgumentException();
        }

        Wrapper<Byte> wrapperLayer = new Wrapper<>();
        int result = nativeGetWindowLayer(handle, wrapperLayer);

        if(Result.isError(result)){
            throw new APIMethodFailedException("dspmngr", "getWindowWalpha", result);
        }

        return wrapperLayer.value;
    }

    private native int nativeGetProcessHandle(long processId, DisplayHandle processHandle);

    private native int nativeRegisterHandle(DisplayHandle handle);
    private native int nativeUnregisterHandle(DisplayHandle handle);

    private native int nativeSetWindowPosition(DisplayHandle handle, int x, int y);
    private native int nativeGetWindowPosition(DisplayHandle handle, Wrapper<Integer> x, Wrapper<Integer> y);

    private native int nativeSetWindowAlpha(DisplayHandle handle, byte alpha);
    private native int nativeGetWindowAlpha(DisplayHandle handle, Wrapper<Byte> alpha);

    private native int nativeSetWindowActive(DisplayHandle handle, boolean active);
    private native int nativeGetWindowActive(DisplayHandle handle, Wrapper<Boolean> active);

    private native int nativeSetWindowLayer(DisplayHandle handle, byte layer);
    private native int nativeGetWindowLayer(DisplayHandle handle, Wrapper<Byte> layer);
}
