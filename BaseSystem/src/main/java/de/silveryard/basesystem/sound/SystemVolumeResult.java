package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 09.04.2017.
 */
public enum SystemVolumeResult {
    OK(0),
    ERROR(1);

    public static SystemVolumeResult getEnumValue(int value){
        SystemVolumeResult[] values = SystemVolumeResult.values();
        for(int i = 0; i < values.length; i++) {
            if (values[i].getValue() == value) {
                return values[i];
            }
        }
        return null;
    }

    private int value;

    SystemVolumeResult(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
