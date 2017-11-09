package de.silveryard.basesystem.gui.kernel;


/**
 * Created by Sebif on 13.03.2017.
 */
public abstract class GraphicsKernel {
    /**
     * Enables the graphics kernel
     */
    public static void enableKernel(){
        Screen.enableKernel();
        RenderObject.enableKernel();
        Font.enableKernel();
        Label.enableKernel();
        Texture.enableKernel();
        TextureSprite.enableKernel();
        NineSliceSprite.enableKernel();
        Sprite.enableKernel();
        Moveable.enableKernel();
        Sizeable.enableKernel();
        Fadeable.enableKernel();
        SimpleLine.enableKernel();
        SimpleRect.enableKernel();
    }
}
