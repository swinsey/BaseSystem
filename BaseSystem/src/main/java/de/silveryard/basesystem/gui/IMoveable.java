package de.silveryard.basesystem.gui;

/**
 * Created by Sebif on 10.02.2017.
 */
public interface IMoveable {
    /**
     * Returns the x position
     * @return X position value
     */
    int getPositionX();
    /**
     * Returns the y position
     * @return Y position value
     */
    int getPositionY();

    /**
     * Sets the position
     * @param x X position value
     * @param y Y position value
     */
    void setPosition(int x, int y);
    /**
     * Sets the x position
     * @param x X position value
     */
    void setPositionX(int x);
    /**
     * Sets the y position
     * @param y Y position value
     */
    void setPositionY(int y);
}
