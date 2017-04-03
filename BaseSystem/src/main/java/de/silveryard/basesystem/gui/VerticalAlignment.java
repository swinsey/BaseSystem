package de.silveryard.basesystem.gui;

/**
 * Created by beppo on 04/02/17.
 */
public enum VerticalAlignment {
    TOP(1),
    CENTER(2),
    BOTTOM(3);

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

    VerticalAlignment(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
