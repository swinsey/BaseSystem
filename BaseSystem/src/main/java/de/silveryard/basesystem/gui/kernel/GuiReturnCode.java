package de.silveryard.basesystem.gui.kernel;

/**
 * Created by Sebif on 13.03.2017.
 */
public enum GuiReturnCode {
    OK(1),
    UNKNOWN_ERROR(2),
    PATH_NOT_EXISTENT(3),
    PATH_NO_FILE(4),
    INVALID_ID(5),
    NOT_A_TEXTURE(6),
    NOT_A_FONT(7),
    NOT_A_RENDEROBJECT(8),
    NOT_A_LABEL(9),
    NOT_A_MOVEABLE(10),
    NOT_A_SIZEABLE(11),
    NOT_A_FADEABLE(12),
    NOT_A_TEXTURESPRITE(13),
    NOT_A_SPRITE(14),
    NOT_A_SIMPLELINE(15),
    NOT_A_SIMPLERECT(16);

    private int value;

    GuiReturnCode(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
