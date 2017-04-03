package de.awesome.smarthome.td.gui;

/**
 * Created by beppo on 04/02/17.
 */
public enum HorizontalAlignment {
    LEFT(1),
    CENTER(2),
    RIGHT(3);

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

    HorizontalAlignment(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
