package de.silveryard.basesystem.sdk.kernel.app;

/**
 * Created by silveryard on 16.05.17.
 */
public enum AppManagerResult {
    /**
     * Indicates that everything whent fine
     */
    OK(1),
    /**
     * An unknown error occured
     */
    UNKNOWN_ERROR(2),
    /**
     * A given app is not installed
     */
    NOT_INSTALLED(3),
    /**
     * A never version of a given app is aready installed
     */
    NEWER_VERSION_INSTALLED(4),
    /**
     * A given app is not running
     */
    NOT_RUNNING(5),
    /**
     * A given app is already running
     */
    ALREADY_RUNNING(6);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static AppManagerResult getEnumValue(int value){
        AppManagerResult[] values = AppManagerResult.values();
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
    AppManagerResult(int value){
        this.value = value;
    }

    /**
     * Returns the integer value of this enum value
     * @return Integer value
     */
    public int getValue(){
        return value;
    }
}

