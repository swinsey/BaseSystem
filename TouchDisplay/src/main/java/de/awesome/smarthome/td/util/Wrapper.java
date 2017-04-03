package de.awesome.smarthome.td.util;

/**
 * Created by Sebif on 18.03.2017.
 */
public class Wrapper<T> {
    public T value;

    public Wrapper(){
        value = null;
    }
    public Wrapper(T value){
        this.value = value;
    }
}
