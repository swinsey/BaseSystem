package de.awesome.smarthome.system.kernel.gui;

/**
 * Created by Sebif on 19.03.2017.
 */
public enum VerticalAlignment {
    /**
     * Aligns the text at the top
     */
    TOP(1),
    /**
     * Aligns the text at the vertical center
     */
    CENTER(2),
    /**
     * Aligns the text at the bottom
     */
    BOTTOM(3);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static VerticalAlignment getEnumValue(int value){
        VerticalAlignment[] values = VerticalAlignment.values();
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
    VerticalAlignment(int value){
        this.value = value;
    }

    /**
     * @return Returns the enums integer value
     */
    public int getValue(){
        return value;
    }
}
