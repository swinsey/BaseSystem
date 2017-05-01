package de.silveryard.basesystem.gui;

/**
 * Created by beppo on 02/02/17.
 */
abstract class SDLTexture {
    public static native int textureLoad(String path);
    public static native int textureUnload(int texture);

    public static native int textureGetWidth(int texture);
    public static native int textureGetHeight(int texture);

    public static native int textureSetAlpha(int texture, byte alpha);
}
