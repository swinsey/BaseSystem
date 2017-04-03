package de.silveryard.basesystem.gui;

/**
 * Created by Sebif on 11.02.2017.
 */
public interface ITouchEventHandler {
    boolean handleTouchEvent(long id, TouchType type, int x, int y);
}
