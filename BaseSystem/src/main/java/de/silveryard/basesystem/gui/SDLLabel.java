package de.silveryard.basesystem.gui;

/**
 * Created by beppo on 02/02/17.
 */
abstract class SDLLabel {
    public static native int labelCreate(int font, String text, byte r, byte g, byte b, byte a, int label);
    public static native int labelDestroy(int label);

    public static native int labelSetText(int label, String text);
    public static native int labelSetFont(int label, int font);
    public static native int labelSetColor(int label, byte r, byte g, byte b, byte a);
    public static native int labelSetWidth(int label, int width);

    public static native int labelGetWidth(int label);
    public static native int labelGetHeight(int label);

    public static native int labelSetAlpha(int label, byte a);
}
