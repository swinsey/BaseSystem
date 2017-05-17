package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

import static de.silveryard.basesystem.sdk.kernel.gui.TextureSprite.*;

/**
 * Created by Sebif on 08.04.2017.
 */
public class TextureSprite implements IDisposable {
    private final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private final Wrapper<GuiReturnCode> guiReturnCodeWrapper = new Wrapper<>();
    private final Wrapper<Integer> integerWrapper = new Wrapper<>();

    private final int textureSpriteId;

    /**
     * Creates a new texture sprite
     * @param texture Texture to use
     * @param sourceX X Position on the source texture
     * @param sourceY Y Position on the source texture
     * @param sourceWidth Width on the source texture
     * @param sourceHeight Height on the source texture
     */
    public TextureSprite(
        Texture texture,
        int sourceX, int sourceY,
        int sourceWidth, int sourceHeight
    ){
        systemCallTextureSpriteCreate(
            texture.getTextureId(),
            sourceX, sourceY,
            sourceWidth, sourceHeight,
            returnCodeWrapper, guiReturnCodeWrapper, integerWrapper
        );

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        this.textureSpriteId = integerWrapper.value;
    }
    /**
     * Creates a new texture sprite. Uses the whole source texture
     * @param texture Texture to use
     */
    public TextureSprite(
            Texture texture
    ){
        this(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    /**
     * Returns the id of this texture sprite
     * @return ID value
     */
    public int getTextureSpriteId(){
        return textureSpriteId;
    }

    /**
     * Returns the x position of this texture sprite
     * @return X Position value
     */
    public int getSourceX(){
        systemCallTextureSpriteGetSourceX(textureSpriteId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Returns the y position of this texture sprite
     * @return Y Position value
     */
    public int getSourceY(){
        systemCallTextureSpriteGetSourceY(textureSpriteId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }

    /**
     * Returns the height of this texture sprite
     * @return Width value
     */
    public int getSourceWidth(){
        systemCallTextureSpriteGetSourceWidth(textureSpriteId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Returns the height of this texture sprite
     * @return Height value
     */
    public int getSourceHeight(){
        systemCallTextureSpriteGetSourceHeight(textureSpriteId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }

    @Override
    public void dispose() {
        //TODO
    }
}
