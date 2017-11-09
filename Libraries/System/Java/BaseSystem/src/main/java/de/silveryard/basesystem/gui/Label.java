package de.silveryard.basesystem.gui;

import aurelienribon.tweenengine.TweenAccessor;
import de.silveryard.basesystem.util.IDisposable;

import java.util.List;

import static de.silveryard.basesystem.gui.SDLLabel.*;
import static de.silveryard.basesystem.gui.SDLWindow.windowDrawLabel;

/**
 * Created by beppo on 04/02/17.
 */
public class Label extends RenderObject implements
        IDisposable, TweenAccessor<Label>,
        IMoveable, ISizeable, IFadeable, IColorizable {
    /**
     * Creates a new label. You should call Frame.createLabel instead
     * @param font Font to use for rendering this label
     * @param text Text. Cannot be empty
     * @param horizontalAlignment Horizontal alignment of this label
     * @param verticalAlignment Vertical alignment of this label
     * @param x X position
     * @param y Y position
     * @param width Label width
     * @param height Label height
     * @param r Color red value. 0-255
     * @param g Color green value. 0-255
     * @param b Color blue value. 0-255
     * @param a Color alpha value. 0-255
     * @return Label instance
     */
    public static Label create(Font font, String text,
                        HorizontalAlignment horizontalAlignment,
                        VerticalAlignment verticalAlignment,
                        int x, int y, int width, int height,
                        byte r, byte g, byte b, byte a){
        int result;
        if(text.trim().isEmpty()){
            result = labelCreate(font.getId(), "<no text>", r, g, b, a, width);
        }else{
            result = labelCreate(font.getId(), text, r, g, b, a, width);
        }

        if(result < 0){
            //TODO do error reporting
            return null;
        }

        return new Label(result, font, text,
                horizontalAlignment, verticalAlignment,
                x, y, width, height,
                r, g, b, a);
    }

    private static void rebuildLabel(Label label, List<String> words, List<Integer> newlines){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < words.size(); i++){
            builder.append(words.get(i));

            if(newlines.contains(i)){
                builder.append("\n");
            }else{
                builder.append(" ");
            }
        }

        label.setText(builder.toString());
    }

    private int     id;
    private Font    font;
    private String  text;

    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment   verticalAlignment;

    private int x;
    private int y;
    private int width;
    private int height;

    private byte r;
    private byte g;
    private byte b;
    private byte a;

    private Label(int id, Font font, String text,
                 HorizontalAlignment horizontalAlignment,
                 VerticalAlignment verticalAlignment,
                 int x, int y, int width, int height,
                 byte r, byte g, byte b, byte a){

        this.id   = id;
        this.font = font;
        this.text = text;

        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment   = verticalAlignment;

        this.x      = x;
        this.y      = y;
        this.width  = width;
        this.height = height;

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Returns the internal id of this label
     * @return Identifier
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the font to render this label
     * @param font New font
     */
    public void setFont(Font font){
        this.font = font;
        labelSetFont(id, font.getId());
        setDirty();
    }
    /**
     * Returns the font that is used to render this label
     * @return Font instance
     */
    public Font getFont(){
        return font;
    }

    /**
     * Sets the text of this label. Cannot be empty
     * @param text New text value
     */
    public void setText(String text){
        this.text = text;
        if(text.trim().isEmpty()) {
            this.text = "";
        }else{
            labelSetText(id, text);
        }
        setDirty();
    }
    /**
     * Returns the text of this label
     * @return Current text
     */
    public String getText(){
        return text;
    }

    /**
     * Sets the labels horizontal alignment
     * @param alignment Alignment value
     */
    public void setHorizontalAlignment(HorizontalAlignment alignment){
        horizontalAlignment = alignment;
        setDirty();
    }
    /**
     * Returns the labels horizontal alignment
     * @return Alignment value
     */
    public HorizontalAlignment getHorizontalAlignment(){
        return horizontalAlignment;
    }
    /**
     * Sets the labels vertical alignment
     * @param alignment Alignment value
     */
    public void setVerticalAlignment(VerticalAlignment alignment){
        verticalAlignment = alignment;
        setDirty();
    }
    /**
     * Returns the labels horizontal alignment
     * @return Alignment value
     */
    public VerticalAlignment getVerticalAlignment(){
        return verticalAlignment;
    }

    /**
     * Sets the labels red value
     * @param r Red value. 0-255
     */
    @Override
    public void setColorR(byte r){
        this.r = r;
        labelSetColor(id, r, g, b, a);
        setDirty();
    }
    /**
     * Sets the labels green value
     * @param g Green value. 0-255
     */
    @Override
    public void setColorG(byte g){
        this.g = g;
        labelSetColor(id, r, g, b, a);
        setDirty();
    }
    /**
     * Sets the labels blue value
     * @param b Blue value. 0-255
     */
    @Override
    public void setColorB(byte b){
        this.b = b;
        labelSetColor(id, r, g, b, a);
        setDirty();
    }
    /**
     * Sets the labels color
     * @param r Red value. 0-255
     * @param g Green value. 0-255
     * @param b Blue value. 0-255
     */
    @Override
    public void setColor(byte r, byte g, byte b){
        this.r = r;
        this.g = g;
        this.b = b;
        labelSetColor(id, r, g, b, a);
        setDirty();
    }
    /**
     * Returns the labels red value
     * @return Red value. 0-255
     */
    @Override
    public byte getColorR(){
        return r;
    }
    /**
     * Returns the labels green value
     * @return Green value. 0-255
     */
    @Override
    public byte getColorG(){
        return g;
    }
    /**
     * Returns the labels blue value
     * @return Blue value. 0-255
     */
    @Override
    public byte getColorB(){
        return b;
    }

    /**
     * Returns the labels internal width
     * @return Width the label consumes to render
     */
    public int getInternalWidth(){
        return labelGetWidth(id);
    }
    /**
     * Returns the labels internal height
     * @return Height the label consumes to render
     */
    public int getInternalHeight(){
        return labelGetHeight(id);
    }

    @Override
    public int getWidth(){
        return width;
    }
    @Override
    public int getHeight(){
        return height;
    }
    @Override
    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
        labelSetWidth(id, width);
        setDirty();
    }
    @Override
    public void setWidth(int width){
        this.width = width;
        labelSetWidth(id, width);
        setDirty();
    }
    @Override
    public void setHeight(int height) {
        this.height = height;
        setDirty();
    }

    @Override
    public int getPositionX() {
        return x;
    }
    @Override
    public int getPositionY() {
        return y;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        setDirty();
    }
    @Override
    public void setPositionX(int x) {
        this.x = x;
        setDirty();
    }
    @Override
    public void setPositionY(int y) {
        this.y = y;
        setDirty();
    }

    @Override
    public byte getAlpha() {
        return a;
    }
    @Override
    public void setAlpha(byte alpha) {
        a = alpha;
        labelSetAlpha(id, a);
        setDirty();
    }

    /**
     * Draws this object
     */
    @Override
    public void draw() {
        if(text.isEmpty()){
            return;
        }

        int internalWidth   = labelGetWidth(id);
        int internalHeight  = labelGetHeight(id);

        int drawX = 0;
        int drawY = 0;

        switch(horizontalAlignment){
            case LEFT:{
                drawX = 0;
                break;
            }
            case CENTER:{
                drawX = (width / 2) - (internalWidth / 2);
                break;
            }
            case RIGHT:{
                drawX = width - internalWidth;
                break;
            }
        }
        switch(verticalAlignment){
            case TOP:{
                drawY = 0;
                break;
            }
            case CENTER:{
                drawY = (height / 2) - (internalHeight / 2);
                break;
            }
            case BOTTOM:{
                drawY = height - internalHeight;
                break;
            }
        }

        drawX += x;
        drawY += y;

        windowDrawLabel(id, drawX, drawY);
    }

    @Override
    public void dispose(){
        labelDestroy(id);
    }

    @Override
    public int getValues(Label target, int tweenType, float[] returnValues) {
        int i;

        if((i = moveableGetTweenValues(target, tweenType, returnValues)) != -1)
            return i;
        if((i = sizeableGetTweenValues(target, tweenType, returnValues)) != -1)
            return i;
        if((i = fadeableGetTweenValues(target, tweenType, returnValues)) != -1)
            return i;
        if((i = colorizableGetTweenValues(target, tweenType, returnValues)) != -1)
            return i;

        return -1;
    }
    @Override
    public void setValues(Label target, int tweenType, float[] newValues) {
        moveableSetTweenValues(target, tweenType, newValues);
        sizeableSetTweenValues(target, tweenType, newValues);
        fadebleSetTweenValues(target, tweenType, newValues);
        colorizableSetTweenValues(target, tweenType, newValues);
    }
}
