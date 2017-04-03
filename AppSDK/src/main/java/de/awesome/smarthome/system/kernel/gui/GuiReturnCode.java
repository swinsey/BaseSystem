package de.awesome.smarthome.system.kernel.gui;

/**
 * Created by Sebif on 13.03.2017.
 */
public enum GuiReturnCode {
    /**
     * Everything went fine
     */
    OK(1),
    /**
     * An unknown error occured
     */
    UNKNOWN_ERROR(2),
    /**
     * A given path does not exist
     */
    PATH_NOT_EXISTENT(3),
    /**
     * A given path is not a file. But it should be
     */
    PATH_NO_FILE(4),
    /**
     * A given ID is not valid
     */
    INVALID_ID(5),
    /**
     * A given ID does not point to a texture. But it should be
     */
    NOT_A_TEXTURE(6),
    /**
     * A given ID does not point to a font. But it should be
     */
    NOT_A_FONT(7),
    /**
     * A given ID does not point to a render object. But it should be
     */
    NOT_A_RENDEROBJECT(8),
    /**
     * A given ID does not point to a label. But it should be
     */
    NOT_A_LABEL(9),
    /**
     * A given ID does not point to a moveable render object. But it should be
     */
    NOT_A_MOVEABLE(10),
    /**
     * A given ID does not point to a sizeable render object. But it should be
     */
    NOT_A_SIZEABLE(11),
    /**
     * A given ID does not point to a fadeable render object. But it should be
     */
    NOT_A_FADEABLE(12),
    /**
     * A given ID does not point to a texturesprite. But it should be
     */
    NOT_A_TEXTURESPRITE(13),
    /**
     * A given ID does not point to a sprite. But it should be
     */
    NOT_A_SPRITE(14),
    /**
     * A given ID does not point to a simple line. But it should be
     */
    NOT_A_SIMPLELINE(15),
    /**
     * A given ID does not point to a simple rect. But it should be
     */
    NOT_A_SIMPLERECT(16);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static GuiReturnCode getEnumValue(int value){
        GuiReturnCode[] values = GuiReturnCode.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].getValue() == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    /**
     * Constructor
     * @param value Integer value
     */
    GuiReturnCode(int value){
        this.value = value;
    }

    /**
     * @return Returns the enums integer value
     */
    public int getValue(){
        return value;
    }
}
