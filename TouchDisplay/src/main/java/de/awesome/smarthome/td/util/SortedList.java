package de.awesome.smarthome.td.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beppo on 04/02/17.
 */
public class SortedList<K extends Comparable, V>{
    private List<K> keyList;
    private List<V> valueList;

    public SortedList(){
        keyList = new ArrayList<>();
        valueList = new ArrayList<>();
    }

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

    public V get(int index){
        return valueList.get(index);
    }

    public int size(){
        return keyList.size();
    }

    public void clear(){
        keyList.clear();
        valueList.clear();
    }
}
