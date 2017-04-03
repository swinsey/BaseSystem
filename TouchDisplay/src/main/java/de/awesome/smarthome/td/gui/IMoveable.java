package de.awesome.smarthome.td.gui;

/**
 * Created by Sebif on 10.02.2017.
 */
public interface IMoveable {
    int getPositionX();
    int getPositionY();

    void setPosition(int x, int y);
    void setPositionX(int x);
    void setPositionY(int y);
}
