package de.silveryard.basesystem.sdk.gui;

/**
 * Created by Sebif on 08.04.2017.
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructor
     * @param x Positions x value
     * @param y Positions y value
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the positions x value
     * @return X value
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the positions y value
     * @return Y value
     */
    public int getY(){
        return y;
    }
}
