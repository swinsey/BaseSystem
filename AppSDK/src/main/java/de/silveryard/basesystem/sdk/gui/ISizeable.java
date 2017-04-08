package de.silveryard.basesystem.sdk.gui;

/**
 * Created by Sebif on 08.04.2017.
 */
public interface ISizeable {
    /**
     * Gets the width of this object
     * @return Width value
     */
    int getWidth();
    /**
     * Gets the height of this object
     * @return Height value
     */
    int getHeight();

    /**
     * Sets the size of this object
     * @param width Width value
     * @param height Height value
     */
    void setSize(int width, int height);
    /**
     * Sets the sizes width value of this object
     * @param width Width value
     */
    void setWidth(int width);
    /**
     * Sets the sizes height value of this object
     * @param height Height value
     */
    void setHeight(int height);
}
