package de.silveryard.basesystem.gui;

/**
 * Created by Sebif on 10.02.2017.
 */
public interface ISizeable {
    int getWidth();
    int getHeight();

    void setSize(int width, int height);
    void setWidth(int width);
    void setHeight(int height);
}
