package de.silveryard.basesystem.gui;

/**
 * Created by Sebif on 7/10/2017.
 */
public interface IColorizable {
    byte getColorR();
    byte getColorG();
    byte getColorB();

    void setColorR(byte r);
    void setColorG(byte g);
    void setColorB(byte b);
    void setColor(byte r, byte g, byte b);

    default int colorizableGetTweenValues(IFadeable target, int tweenType, float[] returnValues){
        switch(tweenType){
            case TweenTable.ICOLORIZABLE_R:
                returnValues[0] = getColorR() / 255f;
                return 1;
            case TweenTable.ICOLORIZABLE_G:
                returnValues[0] = getColorG() / 255f;
                return 1;
            case TweenTable.ICOLORIZABLE_B:
                returnValues[0] = getColorB() / 255f;
                return 1;
            case TweenTable.ICOLORIZABLE_RGB:
                returnValues[0] = getColorR() / 255f;
                returnValues[1] = getColorG() / 255f;
                returnValues[2] = getColorB() / 255f;
                return 2;
        }

        return -1;
    }
    default void colorizableSetTweenValues(IFadeable target, int tweenType, float[] newValues){
        switch(tweenType){
            case TweenTable.ICOLORIZABLE_R:
                setColorR((byte)(newValues[0] * 255));
                break;
            case TweenTable.ICOLORIZABLE_G:
                setColorG((byte)(newValues[0] * 255));
                break;
            case TweenTable.ICOLORIZABLE_B:
                setColorB((byte)(newValues[0] * 255));
                break;
            case TweenTable.ISIZEABLE_WIDTH_HEIGHT:
                setColor((byte)(newValues[0] * 255), (byte)(newValues[1] * 255), (byte)(newValues[2] * 255));
                break;
        }
    }
}
