package de.awesome.smarthome.td.gui;

/**
 * Created by beppo on 02/02/17.
 */
abstract class SDLFont {
    public static native int fontLoad(String path, int size);
    public static native int fontUnload(int font);
}
