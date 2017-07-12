package de.silveryard.basesystem.gui;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by beppo on 03/02/17.
 */
public class Sprite extends RenderObject implements
        TweenAccessor<Sprite>,
        IMoveable, ISizeable, IFadeable {
    private TextureSprite textureSprite;

    private int targetX;
    private int targetY;
    private int targetWidth;
    private int targetHeight;
    private double angle;
    private byte alpha;

    /**
     * Constructor
     * @param textureSprite TextureSprite to render
     * @param targetX X position
     * @param targetY Y position
     * @param targetWidth Width
     * @param targetHeight Height
     * @param angle Rotation angle
     */
    public Sprite(TextureSprite textureSprite, int targetX, int targetY, int targetWidth, int targetHeight, double angle){
        this.textureSprite = textureSprite;
        this.targetX       = targetX;
        this.targetY       = targetY;
        this.targetWidth   = targetWidth;
        this.targetHeight  = targetHeight;
        this.angle         = angle;
        this.alpha         = (byte)255;
    }

    /**
     * Returns the used texture sprite
     * @return TextureSprite instance
     */
    public TextureSprite getTextureSprite(){
        return textureSprite;
    }

    /**
     * Returns the sprites angle
     * @return Rotation angle
     */
    public double getAngle() {
        return angle;
    }
    /**
     * Sets the sprites angle
     * @param angle Rotation angle
     */
    public void setAngle(double angle){
        this.angle = angle;
    }

    @Override
    public int getPositionX(){
        return targetX;
    }
    @Override
    public int getPositionY(){
        return targetY;
    }

    @Override
    public void setPosition(int x, int y){
        targetX = x;
        targetY = y;
        setDirty();
    }
    @Override
    public void setPositionX(int x){
        targetX = x;
        setDirty();
    }
    @Override
    public void setPositionY(int y){
        targetY = y;
        setDirty();
    }

    @Override
    public int getWidth(){
        return targetWidth;
    }
    @Override
    public int getHeight(){
        return targetHeight;
    }

    @Override
    public void setSize(int width, int height){
        targetWidth  = width;
        targetHeight = height;
        setDirty();
    }
    @Override
    public void setWidth(int width){
        targetWidth = width;
        setDirty();
    }
    @Override
    public void setHeight(int height){
        targetHeight = height;
        setDirty();
    }

    @Override
    public byte getAlpha() {
        return alpha;
    }
    @Override
    public void setAlpha(byte alpha) {
        this.alpha = alpha;
        setDirty();
    }

    /**
     * Draws this object
     */
    @Override
    public void draw() {
        textureSprite.getTexture().setAlpha(alpha);
        SDLWindow.windowDrawTexture(
                textureSprite.getTexture().getId(),
                textureSprite.getTextureX(), textureSprite.getTextureY(), textureSprite.getTextureWidth(), textureSprite.getTextureHeight(),
                targetX, targetY, targetWidth, targetHeight,
                angle
        );
    }

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        int i;

        if((i = fadeableGetTweenValues(target, tweenType, returnValues)) != -1)
            return i;
        if((i = moveableGetTweenValues(target, tweenType, returnValues)) != -1)
            return i;
        if((i = sizeableGetTweenValues(target, tweenType, returnValues)) != -1)
            return i;

        switch (tweenType){
            case TweenTable.SPRITE_ANGLE:
                returnValues[0] = (float)getAngle();
                return 1;
        }

        return -1;
    }
    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        fadebleSetTweenValues(target, tweenType, newValues);
        moveableSetTweenValues(target, tweenType, newValues);
        sizeableSetTweenValues(target, tweenType, newValues);

        switch(tweenType){
            case TweenTable.SPRITE_ANGLE:
                setAngle(newValues[0]);
                break;
        }
    }
}
