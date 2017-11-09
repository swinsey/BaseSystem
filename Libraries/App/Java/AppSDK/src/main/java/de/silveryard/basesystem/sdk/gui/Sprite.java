package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

import static de.silveryard.basesystem.sdk.kernel.gui.Fadeable.systemCallFadeableGet;
import static de.silveryard.basesystem.sdk.kernel.gui.Fadeable.systemCallFadeableSet;
import static de.silveryard.basesystem.sdk.kernel.gui.Moveable.*;
import static de.silveryard.basesystem.sdk.kernel.gui.Sizeable.*;
import static de.silveryard.basesystem.sdk.kernel.gui.Sprite.systemCallSpriteCreate;

/**
 * Created by Sebif on 08.04.2017.
 */
public class Sprite extends RenderObject implements IFadeable, IMoveable, ISizeable {
    private final Wrapper<Integer> integerWrapper2 = new Wrapper<>();
    private final Wrapper<Byte> byteWrapper = new Wrapper<>();

    private final int spriteId;
    private final TextureSprite textureSprite;

    /**
     * Creates a sprite
     * @param textureSprite Texture sprite to use
     * @param positionX X Position Value
     * @param positionY Y Position Value
     * @param width Width Value
     * @param height Height Value
     */
    public Sprite(
        TextureSprite textureSprite,
        int positionX, int positionY,
        int width, int height
    ){
        systemCallSpriteCreate(
            textureSprite.getTextureSpriteId(),
            positionX, positionY,
            width, height,
            returnCodeWrapper, guiReturnCodeWrapper, integerWrapper
        );

        spriteId = integerWrapper.value;
        this.textureSprite = textureSprite;
    }

    /**
     * Returns the texture sprite of this texture
     * @return
     */
    public TextureSprite getTextureSprite(){
        return textureSprite;
    }

    @Override
    public synchronized int getWidth() {
        systemCallSizeableGetWidth(spriteId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    @Override
    public synchronized int getHeight() {
        systemCallSizeableGetHeight(spriteId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    @Override
    public synchronized void setSize(int width, int height) {
        systemCallSizeableSetSize(spriteId, width, height, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setWidth(int width) {
        systemCallSizeableSetWidth(spriteId, width, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setHeight(int height) {
        systemCallSizeableSetHeight(spriteId, height, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    @Override
    public synchronized byte getAlpha() {
        systemCallFadeableGet(spriteId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }
    @Override
    public synchronized void setAlpha(byte alpha) {
        systemCallFadeableSet(spriteId, alpha, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    @Override
    public synchronized Position getPosition() {
        systemCallMoveableGetPosition(spriteId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper, integerWrapper2);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return new Position(integerWrapper.value, integerWrapper2.value);
    }
    @Override
    public synchronized int getPositionX() {
        systemCallMoveableGetPositionX(spriteId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    @Override
    public synchronized int getPositionY() {
        systemCallMoveableGetPositionY(spriteId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    @Override
    public synchronized void setPosition(int x, int y) {
        systemCallMoveableSetPosition(spriteId, x, y, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionX(int x) {
        systemCallMoveableSetPositionX(spriteId, x, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionY(int y) {
        systemCallMoveableSetPositionY(spriteId, y, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Returns the renderobjects internal id
     * @return Identifier that identifies this renderobject
     */
    @Override
    public int getId() {
        return spriteId;
    }
}
