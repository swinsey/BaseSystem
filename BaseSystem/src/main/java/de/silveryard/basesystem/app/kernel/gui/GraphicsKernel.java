package de.silveryard.basesystem.app.kernel.gui;


/**
 * Created by Sebif on 13.03.2017.
 */
public abstract class GraphicsKernel {
    public static void enableKernel(){
        Screen.enableKernel();
        RenderObject.enableKernel();
        Font.enableKernel();
        Label.enableKernel();
        Texture.enableKernel();
        TextureSprite.enableKernel();
        Sprite.enableKernel();
        Moveable.enableKernel();
        Sizeable.enableKernel();
        Fadeable.enableKernel();
        SimpleLine.enableKernel();
        SimpleRect.enableKernel();
    }
}
