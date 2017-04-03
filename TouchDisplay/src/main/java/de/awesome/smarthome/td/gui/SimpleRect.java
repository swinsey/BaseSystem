package de.awesome.smarthome.td.gui;

import static de.awesome.smarthome.td.gui.SDLWindow.windowDrawRect;
import static de.awesome.smarthome.td.gui.SDLWindow.windowSetDrawColor;

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

    public void setFilled(boolean filled){
        this.filled = filled;
        setDirty();
    }
    public boolean getFilled(){
        return filled;
    }

    public void setColorR(byte r){
        this.r = r;
        setDirty();
    }
    public void setColorG(byte g){
        this.g = g;
        setDirty();
    }
    public void setColorB(byte b){
        this.b = b;
        setDirty();
    }
    public void setColor(byte r, byte g, byte b){
        this.r = r;
        this.g = g;
        this.b = b;
        setDirty();
    }
    public byte getColorR(){
        return r;
    }
    public byte getColorG(){
        return g;
    }
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

    @Override
    public void draw() {
        windowSetDrawColor(r, g, b, (byte)0xFF);
        windowDrawRect(posX, posY, width, height, filled);
    }
}
