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

    default int moveableGetTweenValues(IFadeable target, int tweenType, float[] returnValues){
        switch(tweenType){
            case TweenTable.IMOVEABLE_POS_X:
                returnValues[0] = (float)getPositionX();
                return 1;
            case TweenTable.IMOVEABLE_POS_Y:
                returnValues[0] = (float)getPositionY();
                return 1;
            case TweenTable.IMOVEABLE_POS_XY:
                returnValues[0] = (float)getPositionX();
                returnValues[1] = (float)getPositionY();
                return 2;
        }

        return -1;
    }
    default void moveableSetTweenValues(IFadeable target, int tweenType, float[] newValues){
        switch(tweenType){
            case TweenTable.IMOVEABLE_POS_X:
                setPositionX((int)newValues[0]);
                break;
            case TweenTable.IMOVEABLE_POS_Y:
                setPositionY((int)newValues[0]);
                break;
            case TweenTable.IMOVEABLE_POS_XY:
                setPosition((int)newValues[0], (int)newValues[1]);
                break;
        }
    }
}
