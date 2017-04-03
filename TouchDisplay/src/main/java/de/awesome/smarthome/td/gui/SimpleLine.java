package de.awesome.smarthome.td.gui;

import static de.awesome.smarthome.td.gui.SDLWindow.windowDrawLine;
import static de.awesome.smarthome.td.gui.SDLWindow.windowSetDrawColor;

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

    public SimpleLine(int x1, int y1, int x2, int y2, byte r, byte g, byte b){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.r  = r;
        this.g  = g;
        this.b  = b;
    }

    public void setX1(int x1){
        this.x1 = x1;
        setDirty();
    }
    public void setY1(int y1){
        this.y1 = y1;
        setDirty();
    }
    public void setPoint1(int x1, int y1){
        this.x1 = x1;
        this.y1 = y1;
        setDirty();
    }
    public int getX1(){
        return x1;
    }
    public int getY1(){
        return y1;
    }

    public void setX2(int x2){
        this.x2 = x2;
        setDirty();
    }
    public void setY2(int y2){
        this.y2 = y2;
        setDirty();
    }
    public void setPoint2(int x2, int y2){
        this.x2 = x2;
        this.y2 = y2;
        setDirty();
    }
    public int getX2(){
        return x2;
    }
    public int getY2(){
        return y2;
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

    @Override
    public void draw() {
        windowSetDrawColor(r, g, b, (byte)0xFF);
        windowDrawLine(x1, y1, x2, y2);
    }
}
