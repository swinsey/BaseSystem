package de.silveryard.basesystem.gui;

/**
 * Created by beppo on 04/02/17.
 */
public class SimpleLine extends RenderObject implements IMoveable {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private byte r;
    private byte g;
    private byte b;

    /**
     * Constructor
     * @param x1 Start x position
     * @param y1 Start y position
     * @param x2 End x position
     * @param y2 End y position
     * @param r Color red value. 0-255
     * @param g Color green value. 0-255
     * @param b Color blue value. 0-255
     */
    public SimpleLine(int x1, int y1, int x2, int y2, byte r, byte g, byte b){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.r  = r;
        this.g  = g;
        this.b  = b;
    }

    /**
     * Sets the start x position
     * @param x1 X position
     */
    public void setX1(int x1){
        this.x1 = x1;
        setDirty();
    }
    /**
     * Sets the start y position
     * @param y1 Y position
     */
    public void setY1(int y1){
        this.y1 = y1;
        setDirty();
    }
    /**
     * Sets the start position
     * @param x1 X position
     * @param y1 Y position
     */
    public void setPoint1(int x1, int y1){
        this.x1 = x1;
        this.y1 = y1;
        setDirty();
    }
    /**
     * Returns the start x position
     * @return X position
     */
    public int getX1(){
        return x1;
    }
    /**
     * Returns the start y position
     * @return Y position
     */
    public int getY1(){
        return y1;
    }

    /**
     * Sets the end x position
     * @param x2 X position
     */
    public void setX2(int x2){
        this.x2 = x2;
        setDirty();
    }
    /**
     * Sets the end y position
     * @param y2 Y Position
     */
    public void setY2(int y2){
        this.y2 = y2;
        setDirty();
    }
    /**
     * Sets the end position
     * @param x2 X position
     * @param y2 Y position
     */
    public void setPoint2(int x2, int y2){
        this.x2 = x2;
        this.y2 = y2;
        setDirty();
    }
    /**
     * Returns the end x position
     * @return X position
     */
    public int getX2(){
        return x2;
    }
    /**
     * Returns the end y position
     * @return Y position
     */
    public int getY2(){
        return y2;
    }

    /**
     * Sets the color red value
     * @param r Red value. 0-255
     */
    public void setColorR(byte r){
        this.r = r;
        setDirty();
    }
    /**
     * Sets the color green value
     * @param g Green value. 0-255
     */
    public void setColorG(byte g){
        this.g = g;
        setDirty();
    }
    /**
     * Sets the color blue value
     * @param b Blue value. 0-255
     */
    public void setColorB(byte b){
        this.b = b;
        setDirty();
    }
    /**
     * Sets the color
     * @param r Red value. 0-255
     * @param g Green value. 0-255
     * @param b Blue value. 0-255
     */
    public void setColor(byte r, byte g, byte b){
        this.r = r;
        this.g = g;
        this.b = b;
        setDirty();
    }

    /**
     * Returns the color red value
     * @return Red value. 0-255
     */
    public byte getColorR(){
        return r;
    }
    /**
     * Returns the color green value
     * @return Green value. 0-255
     */
    public byte getColorG(){
        return g;
    }
    /**
     * Returns the color blue value
     * @return Blue value. 0-255
     */
    public byte getColorB(){
        return b;
    }

    @Override
    public int getPositionX() {
        return getX1();
    }
    @Override
    public int getPositionY() {
        return getY1();
    }

    @Override
    public void setPosition(int x, int y) {
        setPoint1(x, y);
    }
    @Override
    public void setPositionX(int x) {
        setX1(x);
    }
    @Override
    public void setPositionY(int y) {
        setY1(y);
    }

    /**
     * Draws this object
     */
    @Override
    public void draw() {
        SDLWindow.windowSetDrawColor(r, g, b, (byte)0xFF);
        SDLWindow.windowDrawLine(x1, y1, x2, y2);
    }
}
