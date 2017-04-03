package de.silveryard.basesystem.gui;

/**
 * Created by beppo on 01/02/17.
 */
abstract class SDLWindow {
    public static native int windowInit(String title, int width, int height, boolean showCursor);
    public static native int windowDispose();

    public static native boolean windowIsInitialized();

    public static native int windowGetWidth();
    public static native int windowGetHeight();

    public static native void windowUpdateScreen();
    public static native void windowSetDrawColor(byte r, byte g, byte b, byte a);
    public static native void windowClear();
    public static native void windowDrawRect(int x, int y, int width, int height, boolean fill);
    public static native void windowDrawLine(int x, int y, int x2, int y2);
    public static native void windowDrawTexture(int tex,
                                                int srcX, int srcY, int srcWidth, int srcHeight,
                                                int dstX, int dstY, int dstWidth, int dstHeight,
                                                double angle);
    public static native void windowDrawLabel(int label, int x, int y);

    public static native int windowPollEvent();
    public static native int windowGetMouseX();
    public static native int windowGetMouseY();
}
