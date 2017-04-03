package de.awesome.smarthome.system.kernel.gui;

/**
 * Created by Sebif on 19.03.2017.
 */
public class Color {
    private byte r;
    private byte g;
    private byte b;
    private byte a;

    /**
     * Constructor. Alpha will be set to 255
     * @param r Red value 0-255
     * @param g Green value 0-255
     * @param b Blue value 0-255
     */
    public Color(byte r, byte g, byte b){
        this(r, g, b, (byte)255);
    }

    /**
     * Constructor
     * @param r Red value 0-255
     * @param g Green value 0-255
     * @param b Blue value 0-255
     * @param a Alpha value 0-255
     */
    public Color(byte r, byte g, byte b, byte a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * @return Red value
     */
    public byte getR(){
        return r;
    }
    /**
     * @return Green value
     */
    public byte getG(){
        return g;
    }
    /**
     * @return Blue value
     */
    public byte getB(){
        return b;
    }
    /**
     * @return Alpha value
     */
    public byte getA(){
        return a;
    }
}
