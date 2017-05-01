package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.GuiKernelException;
import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

import static de.silveryard.basesystem.sdk.kernel.gui.Moveable.*;
import static de.silveryard.basesystem.sdk.kernel.gui.SimpleLine.*;

/**
 * Created by Sebif on 08.04.2017.
 */
public class SimpleLine extends RenderObject implements IDisposable, IMoveable {
    private final Wrapper<Integer> integerWrapper2 = new Wrapper<>();
    private final Wrapper<Byte> byteWrapper = new Wrapper<>();

    private final int simpleLineId;

    /**
     * Creates a new simple line. Simple lines cannot have alpha value. They wil always be fully visible
     * @param positionX1 Start X Position. Can be requested and changed by moveable systemcalls
     * @param positionY1 Start Y Position. Can be requested and changed by moveable systemcalls
     * @param positionX2 End X Position. Can be requested and changed by endpoint systemcalls
     * @param positionY2 End Y Position. Can be requested and changed by enpoint systemcalls
     * @param colorR Red Value 0-255
     * @param colorG Green value 0-255
     * @param colorB Blue Value 0-255
     */
    public SimpleLine(
            int positionX1, int positionY1,
            int positionX2, int positionY2,
            byte colorR, byte colorG, byte colorB
    ){
        systemCallSimpleLineCreate(
                positionX1, positionY1,
                positionX2, positionY2,
                colorR, colorG, colorB,
                returnCodeWrapper, guiReturnCodeWrapper, integerWrapper
        );

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        simpleLineId = integerWrapper.value;
    }

    /**
     * Returns the end x position of this simple line
     * @return End x position
     */
    public synchronized int getEndpointX(){
        systemCallSimpleLineGetEndpointX(simpleLineId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Returns the end y position of this simple line
     * @return End y position
     */
    public synchronized int getEndpointY(){
        systemCallSimpleLineGetEndpointY(simpleLineId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }

    /**
     * Sets the end position of this simple line
     * @param x X Position Value
     * @param y Y Position Value
     */
    public synchronized void setEndpoint(int x, int y){
        systemCallSimpleLineSetEndpoint(simpleLineId, x, y, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the end x position of this simple line
     * @param x X Position Value
     */
    public synchronized void setEndpointX(int x){
        systemCallSimpleLineSetEndpointX(simpleLineId, x, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the end y position of this simple line
     * @param y Y Position Value
     */
    public synchronized void setEndpointY(int y){
        systemCallSimpleLineSetEndpointY(simpleLineId, y, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Returns the red value of a simple line
     * @return Color value 0-255
     */
    public synchronized byte getColorR(){
        systemCallSimpleLineGetColorR(simpleLineId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }
    /**
     * Returns the green value of a simple line
     * @return Color value 0-255
     */
    public synchronized byte getColorG(){
        systemCallSimpleLineGetColorG(simpleLineId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }
    /**
     * Returns the blue value of a simple line
     * @return Color value 0-255
     */
    public synchronized byte getColorB(){
        systemCallSimpleLineGetColorB(simpleLineId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }

    /**
     * Sets the color value of this simple line
     * @param colorR Red value 0-255
     * @param colorG Green value 0-255
     * @param colorB Blue value 0-255
     */
    public synchronized void setColor(byte colorR, byte colorG, byte colorB){
        systemCallSimpleLineSetColor(simpleLineId, colorR, colorG, colorB, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the red value of this simple line
     * @param red Red value 0-255
     */
    public synchronized void setColorR(byte red){
        systemCallSimpleLineSetColorR(simpleLineId, red, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the green value of this simple line
     * @param green Green value 0-255
     */
    public synchronized void setColorG(byte green){
        systemCallSimpleLineSetColorG(simpleLineId, green, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the blue value of this simple line
     * @param blue Blue value 0-255
     */
    public synchronized void setColorB(byte blue){
        systemCallSimpleLineSetColorB(simpleLineId, blue, returnCodeWrapper, guiReturnCodeWrapper);

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
        return simpleLineId;
    }

    @Override
    public synchronized Position getPosition() {
        systemCallMoveableGetPosition(simpleLineId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper, integerWrapper2);

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
        systemCallMoveableGetPositionX(simpleLineId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallMoveableGetPositionY(simpleLineId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallMoveableSetPosition(simpleLineId, x, y, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionX(int x) {
        systemCallMoveableSetPositionX(simpleLineId, x, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionY(int y) {
        systemCallMoveableSetPositionY(simpleLineId, y, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
}
