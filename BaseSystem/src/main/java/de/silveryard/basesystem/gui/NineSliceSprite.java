package de.silveryard.basesystem.gui;

import static de.silveryard.basesystem.gui.SDLWindow.windowDrawTexture;

/**
 * Created by beppo on 04/02/17.
 */
public class NineSliceSprite extends RenderObject implements IMoveable, ISizeable, IFadeable {
    private TextureSprite sprite;

    private int leftSlice;
    private int rightSlice;
    private int topSlice;
    private int bottomSlice;

    private int x;
    private int y;
    private int width;
    private int height;
    private byte alpha;

    public NineSliceSprite(
            TextureSprite sprite,
            int leftSlice, int rightSlice, int topSlice, int bottomSlice,
            int x, int y, int width, int height) {
        this.sprite = sprite;

        this.leftSlice   = leftSlice;
        this.rightSlice  = rightSlice;
        this.topSlice    = topSlice;
        this.bottomSlice = bottomSlice;

        this.x      = x;
        this.y      = y;
        this.width  = width;
        this.height = height;
        this.alpha  = (byte)255;
    }

    @Override
    public int getPositionX(){
        return x;
    }
    @Override
    public int getPositionY(){
        return x;
    }
    @Override
    public void setPositionX(int x){
        this.x = x;
        setDirty();
    }
    @Override
    public void setPositionY(int y){
        this.y = y;
        setDirty();
    }
    @Override
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        setDirty();
    }

    @Override
    public int getWidth(){
        return width;
    }
    @Override
    public int getHeight(){
        return height;
    }
    @Override
    public void setWidth(int width){
        this.width = width;
        setDirty();
    }
    @Override
    public void setHeight(int height){
        this.height = height;
        setDirty();
    }
    @Override
    public void setSize(int width, int height){
        this.width  = width;
        this.height = height;
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

    @Override
    public void draw(){
        sprite.getTexture().setAlpha(alpha);

        int xLeft = x;
        int xCenter = x + leftSlice;
        int xRight = Math.max(x + width - rightSlice, xCenter);
        int yTop = y;
        int yCenter = y + topSlice;
        int yBottom = Math.max(y + height - bottomSlice, yCenter);

        //Top Left
        windowDrawTexture(
                sprite.getTexture().getId(),
                sprite.getTextureX(), sprite.getTextureY(),
                leftSlice, topSlice,
                xLeft, yTop,
                leftSlice, topSlice,
                0
        );
        //Top Right
        windowDrawTexture(
                sprite.getTexture().getId(),
                sprite.getTextureX() + sprite.getTextureWidth() - rightSlice, sprite.getTextureY(),
                rightSlice, topSlice,
                xRight, yTop,
                rightSlice, topSlice,
                0
        );
        //Bottom Left
        windowDrawTexture(
                sprite.getTexture().getId(),
                sprite.getTextureX(), sprite.getTextureY() + sprite.getTextureHeight() - bottomSlice,
                leftSlice, bottomSlice,
                xLeft, yBottom,
                leftSlice, bottomSlice,
                0
        );
        //Bottom Right
        windowDrawTexture(
                sprite.getTexture().getId(),
                sprite.getTextureX() + sprite.getTextureWidth() - rightSlice, sprite.getTextureY() + sprite.getTextureHeight() - bottomSlice,
                rightSlice, bottomSlice,
                xRight, yBottom,
                rightSlice, bottomSlice,
                0
        );

        //Top
        windowDrawTexture(
                sprite.getTexture().getId(),
                sprite.getTextureX() + leftSlice, sprite.getTextureY(),
                sprite.getTextureWidth() - rightSlice - leftSlice, topSlice,
                xCenter, yTop,
                width - rightSlice - leftSlice, topSlice,
                0
        );
        //Bottom
        windowDrawTexture(
                sprite.getTexture().getId(),
                sprite.getTextureX() + leftSlice, sprite.getTextureY() + sprite.getTextureHeight() - bottomSlice,
                sprite.getTextureWidth() - rightSlice - leftSlice, bottomSlice,
                xCenter, yBottom,
                width - rightSlice - leftSlice, bottomSlice,
                0
        );
        //Left
        windowDrawTexture(
                sprite.getTexture().getId(),
                sprite.getTextureX(), sprite.getTextureY() + topSlice,
                leftSlice, sprite.getTextureHeight() - bottomSlice - topSlice,
                xLeft, yCenter,
                leftSlice, height - bottomSlice - topSlice,
                0
        );
        //Right
        windowDrawTexture(
                sprite.getTexture().getId(),
                sprite.getTextureX() + sprite.getTextureWidth() - rightSlice, sprite.getTextureY() + topSlice,
                rightSlice, sprite.getTextureHeight() - bottomSlice - topSlice,
                xRight, yCenter,
                rightSlice, height - bottomSlice - topSlice,
                0
        );

        //Center
        windowDrawTexture(
                sprite.getTexture().getId(),
                sprite.getTextureX() + leftSlice, sprite.getTextureY() + topSlice,
                sprite.getTextureWidth() - leftSlice - rightSlice, sprite.getTextureHeight() - topSlice - bottomSlice,
                xCenter, yCenter,
                width - leftSlice - rightSlice, height - topSlice - bottomSlice,
                0
        );
    }
}
