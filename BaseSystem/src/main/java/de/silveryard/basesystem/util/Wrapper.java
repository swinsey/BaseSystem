package de.silveryard.basesystem.util;

/**
 * Created by Sebif on 18.03.2017.
 */
public class Wrapper<T> {
    /**
     * The wrappers value
     */
    public T value;

    /**
     * Initializes the wrapper with null
     */
    public Wrapper(){
        value = null;
    }
    /**
     * Initializes the wrapper with a default value
     * @param value Default value
     */
    public Wrapper(T value){
        this.value = value;
    }
}
