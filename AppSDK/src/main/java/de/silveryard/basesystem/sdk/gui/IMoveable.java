package de.silveryard.basesystem.sdk.gui;

/**
 * Created by Sebif on 08.04.2017.
 */
public interface IMoveable {
    /**
     * Returns the position of this object
     * @return Position value
     */
    Position getPosition();
    /**
     * Returns the positions x value of this object
     * @return X value
     */
    int getPositionX();
    /**
     * Returns the positions y value of this object
     * @return Y value
     */
    int getPositionY();

    /**
     * Sets the position of this object
     * @param x X value
     * @param y Y value
     */
    void setPosition(int x, int y);
    /**
     * Sets the positions x value of this object
     * @param x X value
     */
    void setPositionX(int x);
    /**
     * Sets the positions y value of this object
     * @param y Y value
     */
    void setPositionY(int y);
}
