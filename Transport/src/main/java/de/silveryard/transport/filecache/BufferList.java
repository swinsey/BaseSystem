package de.silveryard.transport.filecache;

import java.util.*;

/**
 * Created by CHofm on 18.01.2017.
 */
public class BufferList {

    private byte[] data;
    private int size;

    /**
     * Constructor
     */
    public BufferList(){
        this(10);
    }

    /**
     * Constructor
     * @param capacity Initial capacity
     */
    public BufferList(int capacity){
        data = new byte[capacity];
        size = 0;
    }

    /**
     * Constructor
     * @param arr Intial data
     */
    public BufferList(byte[] arr){
        data = arr;
        size = arr.length;
    }

    /**
     * @return Returns the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * @return True if the list does not contain elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return Lists content as array
     */
    public byte[] toArray() {
        return Arrays.copyOfRange(data, 0, size);
    }

    /**
     * Adds a new value to the back of the list
     * @param b Value
     */
    public void add(byte b) {
        if(size == data.length){
            grow(1);
        }
        data[size++] = b;
    }

    /**
     * Adds an array of values to the back of the list
     * @param arr Array of values
     */
    public void addAll(byte[] arr){
        addRange(arr, 0, arr.length);
    }

    /**
     * Adds a range of values to the back of the list
     * @param arr Array to add values from
     * @param from First index
     * @param length Number of values
     */
    public void addRange(byte[] arr, int from, int length){
        if(data.length - size < length){
            grow(length);
        }
        System.arraycopy(arr, from, data, size, length);
        size += length;
    }

    /**
     * Remove an element from a specific inded
     * @param index Element index
     */
    public void removeAt(int index) {
        if(index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        removeRange(index, 1);
    }

    /**
     * Removes a range of values
     * @param from First element index
     * @param length Number of elements
     */
    public void removeRange(int from, int length){
        if(from + length > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(data, from + length, data, from, size - (from + length));
        size -= length;
    }

    /**
     * Clears the list
     */
    public void clear(){
        size = 0;
    }

    /**
     * Returns a value at a specific index
     * @param index Element index
     * @return Element value
     */
    public byte get(int index) {
        if(index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        return data[index];
    }

    /**
     * Sets an element value at a specific index
     * @param index Element index
     * @param element New value
     */
    public void set(int index, byte element) {
        if(index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        data[index] = element;
    }

    /**
     * Creates a range of elements as new list
     * @param fromIndex First element index
     * @param length Number of elements
     * @return New List
     */
    public BufferList subList(int fromIndex, int length) {
        if(fromIndex + length > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        return new BufferList(Arrays.copyOfRange(data, fromIndex, fromIndex + length));
    }

    private void grow(int minGrow){
        byte[] newData = new byte[(int)((data.length + minGrow) * 1.5f)];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
