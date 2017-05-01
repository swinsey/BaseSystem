package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.GuiKernelException;
import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.Color;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;
import de.silveryard.basesystem.sdk.kernel.gui.HorizontalAlignment;
import de.silveryard.basesystem.sdk.kernel.gui.VerticalAlignment;

import static de.silveryard.basesystem.sdk.kernel.gui.Fadeable.systemCallFadeableGet;
import static de.silveryard.basesystem.sdk.kernel.gui.Fadeable.systemCallFadeableSet;
import static de.silveryard.basesystem.sdk.kernel.gui.Label.*;
import static de.silveryard.basesystem.sdk.kernel.gui.Moveable.*;
import static de.silveryard.basesystem.sdk.kernel.gui.Sizeable.*;

/**
 * Created by Sebif on 08.04.2017.
 */
public class Label extends RenderObject implements IFadeable, IMoveable, ISizeable, IDisposable {
    private final Wrapper<Integer> integerWrapper2 = new Wrapper<>();
    private final Wrapper<Byte> byteWrapper = new Wrapper<>();
    private final Wrapper<String> stringWrapper = new Wrapper<>();
    private final Wrapper<VerticalAlignment> verticalAlignmentWrapper = new Wrapper<>();
    private final Wrapper<HorizontalAlignment> horizontalAlignmentWrapper = new Wrapper<>();
    private final Wrapper<Color> colorWrapper = new Wrapper<>();

    private final int labelId;
    private Font font;

    /**
     * Creates a new Label
     * @param font Font to use
     * @param text Initial Text of the label
     * @param horizontalAlignment Initial Horizontal Alignment respective to position, internal text size and width
     * @param verticalAlignment Initial Vertical Alignment respective to position, internal text size and height
     * @param positionX Initial X position
     * @param positionY Initial Y position
     * @param width Initial width
     * @param height Initial height
     * @param color Intial RGBA Color
     */
    public Label(
            Font font, String text,
            HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
            int positionX, int positionY,
            int width, int height,
            Color color
    ){
        systemCallLabelCreate(
                font.getFontId(), text,
                horizontalAlignment, verticalAlignment,
                positionX, positionY,
                width, height,
                color,
                returnCodeWrapper, guiReturnCodeWrapper, integerWrapper
        );

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        this.font = font;
        labelId = integerWrapper.value;
    }

    /**
     * @return Returns the labels internal id
     */
    public int getLabelId(){
        return labelId;
    }

    /**
     * @return Returns the used font
     */
    public synchronized Font getFont(){
        return font;
    }
    /**
     * Sets the used font
     * @param font Font to use
     */
    public synchronized void setFont(Font font){
        this.font = font;
        systemCallLabelSetFont(labelId, font.getFontId(), returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Sets the labels text
     * @param text New text value
     */
    public synchronized void setText(String text){
        systemCallLabelSetText(labelId, text, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Returns the labels text
     * @return Text value
     */
    public synchronized String getText(){
        systemCallLabelGetText(labelId, returnCodeWrapper, guiReturnCodeWrapper, stringWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return stringWrapper.value;
    }

    /**
     * Returns the labels horizontal alignment
     * @return Alignment
     */
    public synchronized HorizontalAlignment getHorizontalAlignment(){
        systemCallLabelGetHorizontalAlignment(labelId, returnCodeWrapper, guiReturnCodeWrapper, horizontalAlignmentWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return horizontalAlignmentWrapper.value;
    }
    /**
     * Sets the labels horizontal alignment
     * @param horizontalAlignment Alignment
     */
    public synchronized void setHorizontalAlignment(HorizontalAlignment horizontalAlignment){
        systemCallLabelSetHorizontalAlignment(labelId, horizontalAlignment, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Returns the labels vertical alignment
     * @return Alignment
     */
    public synchronized VerticalAlignment getVerticalAlignment(){
        systemCallLabelGetVerticalAlignment(labelId, returnCodeWrapper, guiReturnCodeWrapper, verticalAlignmentWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return verticalAlignmentWrapper.value;
    }
    /**
     * Sets the labels vertical alignment
     * @param verticalAlignment Alignment
     */
    public synchronized void setVerticalAlignment(VerticalAlignment verticalAlignment){
        systemCallLabelSetVerticalAlignment(labelId, verticalAlignment, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * Returns the labels color
     * @return Color value
     */
    public synchronized Color getColor(){
        systemCallLabelGetColor(labelId, returnCodeWrapper, guiReturnCodeWrapper, colorWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return colorWrapper.value;
    }
    /**
     * Returns the labels red value
     * @return Color value 0-255
     */
    public synchronized byte getColorR(){
        systemCallLabelGetColorR(labelId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }
    /**
     * Returns the labels green value
     * @return Color value 0-255
     */
    public synchronized byte getColorG(){
        systemCallLabelGetColorG(labelId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }
    /**
     * Returns the labels blue value
     * @return Color value 0-255
     */
    public synchronized byte getColorB(){
        systemCallLabelGetColorB(labelId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return byteWrapper.value;
    }

    /**
     * Sets the labels color value
     * @param color Color value
     */
    public synchronized void setColor(Color color){
        systemCallLabelSetColor(labelId, color, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the labels red value
     * @param red Color value 0-255
     */
    public synchronized void setColorR(byte red){
        systemCallLabelSetColorR(labelId, red, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the labels green value
     * @param green Color value 0-255
     */
    public synchronized void setColorG(byte green){
        systemCallLabelSetColorG(labelId, green, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Sets the labels blue value
     * @param blue Color value 0-255
     */
    public synchronized void setColorB(byte blue){
        systemCallLabelSetColorB(labelId, blue, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    /**
     * @return Returns the labels internal width
     */
    public synchronized int getInternalWidth(){
        systemCallLabelGetInternalHeight(labelId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * @return Returns the labels internal height
     */
    public synchronized int getInternalHeight(){
        systemCallLabelGetInternalHeight(labelId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }

    @Override
    public synchronized int getWidth() {
        systemCallSizeableGetWidth(labelId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallSizeableGetHeight(labelId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallSizeableSetSize(labelId, width, height, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setWidth(int width) {
        systemCallSizeableSetWidth(labelId, width, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setHeight(int height) {
        systemCallSizeableSetHeight(labelId, height, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    @Override
    public synchronized byte getAlpha() {
        systemCallFadeableGet(labelId, returnCodeWrapper, guiReturnCodeWrapper, byteWrapper);

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
        systemCallFadeableSet(labelId, alpha, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    @Override
    public synchronized Position getPosition() {
        systemCallMoveableGetPosition(labelId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper, integerWrapper2);

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
        systemCallMoveableGetPositionX(labelId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallMoveableGetPositionY(labelId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

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
        systemCallMoveableSetPosition(labelId, x, y, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionX(int x) {
        systemCallMoveableSetPositionX(labelId, x, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    @Override
    public synchronized void setPositionY(int y) {
        systemCallMoveableSetPositionY(labelId, y, returnCodeWrapper, guiReturnCodeWrapper);

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
        return labelId;
    }
}
