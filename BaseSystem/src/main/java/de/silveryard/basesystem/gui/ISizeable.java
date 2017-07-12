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

    default int sizeableGetTweenValues(IFadeable target, int tweenType, float[] returnValues){
        switch(tweenType){
            case TweenTable.ISIZEABLE_WIDTH:
                returnValues[0] = (float)getWidth();
                return 1;
            case TweenTable.ISIZEABLE_HEIGHT:
                returnValues[0] = (float)getHeight();
                return 1;
            case TweenTable.ISIZEABLE_WIDTH_HEIGHT:
                returnValues[0] = (float)getWidth();
                returnValues[1] = (float)getHeight();
                return 2;
        }

        return -1;
    }
    default void sizeableSetTweenValues(IFadeable target, int tweenType, float[] newValues){
        switch(tweenType){
            case TweenTable.ISIZEABLE_WIDTH:
                setWidth((int)newValues[0]);
                break;
            case TweenTable.ISIZEABLE_HEIGHT:
                setHeight((int)newValues[0]);
                break;
            case TweenTable.ISIZEABLE_WIDTH_HEIGHT:
                setSize((int)newValues[0], (int)newValues[1]);
                break;
        }
    }
}
