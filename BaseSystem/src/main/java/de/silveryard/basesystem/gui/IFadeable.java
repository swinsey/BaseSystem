package de.silveryard.basesystem.gui;

/**
 * Created by Sebif on 10.02.2017.
 */
public interface IFadeable {
    /**
     * Returns the alpha value
     * @return Alpha value. 0-255
     */
    byte getAlpha();

    /**
     * Sets the alpha value
     * @param alpha Alpha value. 0-255
     */
    void setAlpha(byte alpha);
}
