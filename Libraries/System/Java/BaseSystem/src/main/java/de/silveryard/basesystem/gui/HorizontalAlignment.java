package de.silveryard.basesystem.gui;

/**
 * Created by beppo on 04/02/17.
 */
public enum HorizontalAlignment {
    /**
     * Aligns the text at the left side
     */
    LEFT(1),
    /**
     * Aligns the text at the horizontal center
     */
    CENTER(2),
    /**
     * Aligns the text at the right side
     */
    RIGHT(3);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static HorizontalAlignment getEnumValue(int value){
        HorizontalAlignment[] values = HorizontalAlignment.values();
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
    HorizontalAlignment(int value){
        this.value = value;
    }

    /**
     * @return Returns the enums integer value
     */
    public int getValue(){
        return value;
    }
}
