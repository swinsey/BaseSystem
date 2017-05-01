package de.silveryard.basesystem.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beppo on 04/02/17.
 */
public class SortedList<K extends Comparable, V>{
    private List<K> keyList;
    private List<V> valueList;

    /**
     * Constructor
     */
    public SortedList(){
        keyList = new ArrayList<>();
        valueList = new ArrayList<>();
    }

    /**
     * Adds a new value to the list
     * @param key Key to sort
     * @param value Value
     */
    public void add(K key, V value){
        for(int i = 0; i < keyList.size(); i++){
            K k = keyList.get(i);
            if(k.compareTo(key) >= 0){
                keyList.add(i, key);
                valueList.add(i, value);
                return;
            }
        }
        keyList.add(key);
        valueList.add(value);
    }

    /**
     * Returns a value at a given index
     * @param index Index
     * @return Value of type V
     */
    public V get(int index){
        return valueList.get(index);
    }

    /**
     * Returns the lists size
     * @return Number of elements in this list
     */
    public int size(){
        return keyList.size();
    }

    /**
     * Clears the list
     */
    public void clear(){
        keyList.clear();
        valueList.clear();
    }
}
