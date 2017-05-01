package de.silveryard.basesystem.gui;

import static de.silveryard.basesystem.gui.SDLWindow.windowDrawRect;
import static de.silveryard.basesystem.gui.SDLWindow.windowSetDrawColor;

/**
 * Created by beppo on 04/02/17.
 */
public class SimpleRect extends RenderObject implements IMoveable, ISizeable {
    private int     posX;
    private int     posY;
    private int     width;
    private int     height;
    private boolean filled;
    private byte    r;
    private byte    g;
    private byte    b;

    /**
     * Constructor
     * @param posX X position
     * @param posY Y position
     * @param width Width
     * @param height Height
     * @param filled True if the rect should be filled. If false a 1 pixel frame will be drawn
     * @param r Color red value. 0-255
     * @param g Color green value. 0-255
     * @param b Color blue value. 0-255
     */
    public SimpleRect(int posX, int posY, int width, int height, boolean filled,
                      byte r, byte g, byte b){

        this.posX   = posX;
        this.posY   = posY;
        this.width  = width;
        this.height = height;
        this.filled = filled;
        this.r      = r;
        this.g      = g;
        this.b      = b;
    }

    /**
     * Sets the filled flag.
     * @param filled Filled flag value. If false only a 1 pixel frame will be drawn
     */
    public void setFilled(boolean filled){
        this.filled = filled;
        setDirty();
    }
    /**
     * Returns the filled flag
     * @return Filled flag value. If false only a 1 pixel frame will be drawn
     */
    public boolean getFilled(){
        return filled;
    }

    /**
     * Sets the red color value
     * @param r Red value. 0-255
     */
    public void setColorR(byte r){
        this.r = r;
        setDirty();
    }
    /**
     * Sets the green color value
     * @param g Green value. 0-255
     */
    public void setColorG(byte g){
        this.g = g;
        setDirty();
    }
    /**
     * Sets the blue color value
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
     * Returns the red color value
     * @return Red value. 0-255
     */
    public byte getColorR(){
        return r;
    }
    /**
     * Returns the green color value
     * @return Green value. 0-255
     */
    public byte getColorG(){
        return g;
    }
    /**
     * Returns the blue color value
     * @return Blue value. 0-255
     */
    public byte getColorB(){
        return b;
    }

    @Override
    public int getPositionX(){
        return posX;
    }
    @Override
    public int getPositionY(){
        return posY;
    }

    @Override
    public void setPosition(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        setDirty();
    }
    @Override
    public void setPositionX(int posX){
        this.posX = posX;
        setDirty();
    }
    @Override
    public void setPositionY(int posY){
        this.posY = posY;
        setDirty();
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
        this.width  = width;
        this.height = height;
        setDirty();
    }
    @Override
    public void setWidth(int width){
        this.width = width;
        setDirty();
    }
    @Override
    public void setHeight(int height){
        this.height = height;
        setDirty();
    }

    /**
     * Draws this object
     */
    @Override
    public void draw() {
        windowSetDrawColor(r, g, b, (byte)0xFF);
        windowDrawRect(posX, posY, width, height, filled);
    }
}
