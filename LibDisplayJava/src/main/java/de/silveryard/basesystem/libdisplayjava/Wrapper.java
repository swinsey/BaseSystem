package de.silveryard.basesystem.libdisplayjava;

/**
 * Created by Sebif on 25.06.2017.
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

