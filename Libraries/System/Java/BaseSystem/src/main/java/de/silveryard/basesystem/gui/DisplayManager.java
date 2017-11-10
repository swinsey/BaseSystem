package de.silveryard.basesystem.gui;

import de.silveryard.basesystem.util.Wrapper;

/**
 * Created by Beppo on 09.11.2017.
 */
public final class DisplayManager {
    private static DisplayManager instance;

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

    public native int nativeGetProcessHandle(long processId, DisplayHandle processHandle);

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
