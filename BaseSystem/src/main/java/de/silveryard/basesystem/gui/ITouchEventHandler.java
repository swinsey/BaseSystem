package de.silveryard.basesystem.gui;

/**
 * Created by Sebif on 11.02.2017.
 */
public interface ITouchEventHandler {
    /**
     * Handles a touch
     * @param id Touch ID
     * @param type Touch Type
     * @param x X Position
     * @param y Y Position
     * @return True if this handler blocks the input for lower layers. False otherwise
     */
    boolean handleTouchEvent(long id, TouchType type, int x, int y);
}
