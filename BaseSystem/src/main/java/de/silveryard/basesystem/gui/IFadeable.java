package de.silveryard.basesystem.gui;

import aurelienribon.tweenengine.Tween;

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

    default int fadeableGetTweenValues(IFadeable target, int tweenType, float[] returnValues){
        switch(tweenType){
            case TweenTable.IFADEABLE_ALPHA:
                returnValues[0] = (getAlpha() & 0xFF) / 255f;
                return 1;
        }

        return -1;
    }
    default void fadebleSetTweenValues(IFadeable target, int tweenType, float[] newValues){
        switch(tweenType){
            case TweenTable.IFADEABLE_ALPHA:
                setAlpha((byte)(newValues[0] * 255));
                break;
        }
    }
}
