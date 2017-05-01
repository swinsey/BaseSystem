package de.silveryard.basesystem.gui;

/**
 * Created by Sebif on 10.02.2017.
 */
public interface ISizeable {
    /**
     * Returns the width
     * @return Width value
     */
    int getWidth();
    /**
     * Returns the height
     * @return Height value
     */
    int getHeight();

    /**
     * Sets the size
     * @param width Width value
     * @param height Height value
     */
    void setSize(int width, int height);
    /**
     * Sets the width
     * @param width Width value
     */
    void setWidth(int width);
    /**
     * Sets the height
     * @param height Height value
     */
    void setHeight(int height);
}
