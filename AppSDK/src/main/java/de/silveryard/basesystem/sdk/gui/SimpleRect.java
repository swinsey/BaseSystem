package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.Color;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

import static de.silveryard.basesystem.sdk.kernel.gui.Moveable.*;
import static de.silveryard.basesystem.sdk.kernel.gui.SimpleRect.*;
import static de.silveryard.basesystem.sdk.kernel.gui.Sizeable.*;

/**
 * Created by Sebif on 08.04.2017.
 */
public class SimpleRect extends RenderObject implements IMoveable, ISizeable {
    private final Wrapper<Integer> integerWrapper2 = new Wrapper<>();
    private final Wrapper<Byte> byteWrapper = new Wrapper<>();
    private final Wrapper<Boolean> booleanWrapper = new Wrapper<>();

    private final int simpleRectId;

    /**
     * Creates a new simple rect. Simple rects cannot have alpha value. They will always be fully visible
     * @param positionX X Position Value
     * @param positionY Y Position Value
     * @param width Width Value
     * @param height Height Value
     * @param filled True if the rect is filled
     * @param color RGB Color Value. Alpha will be discarded
     */
    public SimpleRect(
            int positionX, int positionY,
            int width, int height,
            boolean filled, Color color
    ){

        systemCallSimpleRectCreate(
                positionX, positionY,
                width, height,
                filled, color,
                returnCodeWrapper, guiReturnCodeWrapper, integerWrapper
        );

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        simpleRectId = integerWrapper.value;
    }

    /**
     * Returns the filled flag for this simple rect
     * @return Filled flag value
     */
    public synchronized boolean getFilled(){
        systemCallSimpleRectGetFilled(simpleRectId, returnCodeWrapper, guiReturnCodeWrapper, booleanWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return booleanWrapper.value;
    }
    /**
     * Sets the filled flag of this simple rect
     * @param filled Filled flag value
     */
    public synchronized void setFilled(boolean filled){
        systemCallSimpleRectSetFilled(simpleRectId, filled, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Returns the red value of this simple rect
     * @return Color value 0-255
     */
    public synchronized byte getColorR(){
        systemCallSimpleRectGetColorR(simpleRectId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }
    /**
     * Returns the green value of this simple rect
     * @return Color value 0-255
     */
    public synchronized byte getColorG(){
        systemCallSimpleRectGetColorG(simpleRectId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }
    /**
     * Returns the blue value of this simple rect
     * @return Color value 0-255
     */
    public synchronized byte getColorB(){
        systemCallSimpleRectGetColorB(simpleRectId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }

    /**
     * Sets the RGB color of this simple rect
     * @param color RGB Color Value. Alpha will be discarded
     */
    public synchronized void setColor(Color color){
        systemCallSimpleRectSetColor(simpleRectId, color, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the red value of this simple rect
     * @param red Color value 0-255
     */
    public synchronized void setColorR(byte red){
        systemCallSimpleRectSetColorR(simpleRectId, red, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the green value of this simple rect
     * @param green Color value 0-255
     */
    public synchronized void setColorG(byte green){
        systemCallSimpleRectSetColorG(simpleRectId, green, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the blue value of this simple rect
     * @param blue Color value 0-255
     */
    public synchronized void setColorB(byte blue){
        systemCallSimpleRectSetColorB(simpleRectId, blue, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    
    @Override
    public synchronized int getWidth() {
        systemCallSizeableGetWidth(simpleRectId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallSizeableGetHeight(simpleRectId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallSizeableSetSize(simpleRectId, width, height, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setWidth(int width) {
        systemCallSizeableSetWidth(simpleRectId, width, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setHeight(int height) {
        systemCallSizeableSetHeight(simpleRectId, height, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    @Override
    public synchronized Position getPosition() {
        systemCallMoveableGetPosition(simpleRectId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper, integerWrapper2);

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
        systemCallMoveableGetPositionX(simpleRectId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallMoveableGetPositionY(simpleRectId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallMoveableSetPosition(simpleRectId, x, y, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionX(int x) {
        systemCallMoveableSetPositionX(simpleRectId, x, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionY(int y) {
        systemCallMoveableSetPositionY(simpleRectId, y, returnCodeWrapper, guiReturnCodeWrapper);

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
        return simpleRectId;
    }
}
