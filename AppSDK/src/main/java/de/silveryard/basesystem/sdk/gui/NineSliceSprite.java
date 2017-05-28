package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

import static de.silveryard.basesystem.sdk.kernel.gui.Fadeable.systemCallFadeableGet;
import static de.silveryard.basesystem.sdk.kernel.gui.Fadeable.systemCallFadeableSet;
import static de.silveryard.basesystem.sdk.kernel.gui.Moveable.*;
import static de.silveryard.basesystem.sdk.kernel.gui.NineSliceSprite.systemCallNineSliceSpriteCreate;
import static de.silveryard.basesystem.sdk.kernel.gui.Sizeable.*;

/**
 * Created by silveryard on 28.05.17.
 */
public class NineSliceSprite extends RenderObject implements ISizeable, IMoveable, IFadeable {
    private final Wrapper<Integer> integerWrapper2 = new Wrapper<>();
    private final Wrapper<Byte> byteWrapper = new Wrapper<>();

    private final int id;
    private final TextureSprite textureSprite;

    public NineSliceSprite(
            TextureSprite textureSprite,
            int leftSlice, int rightSlice,
            int topSlice, int bottomSlice,
            int positionX, int positionY,
            int width, int height
    ){
        systemCallNineSliceSpriteCreate(
                textureSprite.getTextureSpriteId(),
                leftSlice, rightSlice,
                topSlice, bottomSlice,
                positionX, positionY,
                width, height,
                returnCodeWrapper, guiReturnCodeWrapper,
                integerWrapper
        );

        id = integerWrapper.value;
        this.textureSprite = textureSprite;
    }


    @Override
    public synchronized int getWidth() {
        systemCallSizeableGetWidth(id, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallSizeableGetHeight(id, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallSizeableSetSize(id, width, height, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setWidth(int width) {
        systemCallSizeableSetWidth(id, width, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setHeight(int height) {
        systemCallSizeableSetHeight(id, height, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    @Override
    public synchronized byte getAlpha() {
        systemCallFadeableGet(id, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

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
        systemCallFadeableSet(id, alpha, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    @Override
    public synchronized Position getPosition() {
        systemCallMoveableGetPosition(id, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper, integerWrapper2);

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
        systemCallMoveableGetPositionX(id, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallMoveableGetPositionY(id, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallMoveableSetPosition(id, x, y, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionX(int x) {
        systemCallMoveableSetPositionX(id, x, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionY(int y) {
        systemCallMoveableSetPositionY(id, y, returnCodeWrapper, guiReturnCodeWrapper);

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
        return id;
    }
}
