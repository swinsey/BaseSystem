package de.silveryard.transport;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by CHofm on 08.01.2017.
 */
public class Parameter {
    /**
     * Maximum length in bytes a parameter can have
     */
    public static final int PARAM_DATA_MAX_LENGTH = 255;

    private int size;
    private byte[] data;

    /**
     * Creates a new parameter that contains a boolean value
     * @param b Value
     * @return Created parameter
     */
    public static Parameter createBoolean(boolean b){
        byte[] data = new byte[1];
        data[0] = b ? (byte)1 : (byte)0;
        return new Parameter(data);
    }

    /**
     * Creates a new parameter that contains an integer value
     * @param i Value
     * @return Created parameter
     */
    public static Parameter createInt(int i){
        byte[] data = new byte[4];
        data[0] = (byte)((i >> 24) & 0xFF);
        data[1] = (byte)((i >> 16) & 0xFF);
        data[2] = (byte)((i >> 8) & 0xFF);
        data[3]= (byte)(i & 0xFF);

        return new Parameter(data);
    }

    /**
     * Creates a new parameter that contains a long value
     * @param l Value
     * @return Created parameter
     */
    public static Parameter createLong(long l){
        byte[] data = new byte[8];
        data[0] = (byte)((l >> 56) & 0xFF);
        data[1] = (byte)((l >> 48) & 0xFF);
        data[2] = (byte)((l >> 40) & 0xFF);
        data[3] = (byte)((l >> 32) & 0xFF);
        data[4] = (byte)((l >> 24) & 0xFF);
        data[5] = (byte)((l >> 16) & 0xFF);
        data[6] = (byte)((l >> 8) & 0xFF);
        data[7] = (byte)(l & 0xFF);

        return new Parameter(data);
    }

    /**
     * Creates a new parameter that contains a float value
     * @param f Value
     * @return Created parameter
     */
    public static Parameter createFloat(float f){
        int representation = Float.floatToIntBits(f);
        return createInt(representation);
    }

    /**
     * Creates a new parameter that contains a string value
     * @param str Value
     * @return Created parameter
     */
    public static Parameter createString(String str){
        Objects.requireNonNull(str);
        byte[] data = str.getBytes();

        if(data.length > PARAM_DATA_MAX_LENGTH){
            throw new IllegalArgumentException("Data array too large (max: " + PARAM_DATA_MAX_LENGTH + ", actual: " + data.length + ")");
        }else{
            return new Parameter(data);
        }
    }

    /**
     * Creates a new parameter that contains a byte array value
     * @param arr Value
     * @return Creates parameter
     */
    public static Parameter createByteArray(byte[] arr){
        Objects.requireNonNull(arr);
        if(arr.length > PARAM_DATA_MAX_LENGTH){
            throw new IllegalArgumentException("Data array too large (max: " + PARAM_DATA_MAX_LENGTH + ", actual: " + arr.length + ")");
        } else {
            return new Parameter(arr);
        }
    }

    /**
     * Constructor
     * @param data Raw data
     */
    protected Parameter(byte[] data){
        Objects.requireNonNull(data);
        this.size = data.length;
        this.data = data;
    }

    /**
     * @return Returns the parameters size in bytes
     */
    public int getSize(){
        return size;
    }

    /**
     * @return Returns the parameters raw byte array
     */
    public byte[] getData(){
        return data;
    }

    /**
     * @return Returns the parameter value as boolean
     */
    public Boolean getBoolean(){
        if(size != 1 || data.length != 1){
            return null;
        }
        if(data[0] != 0 && data[0] != 1){
            return null;
        }

        return data[0] == 1;
    }

    /**
     * @return Returns the parameter as integer value
     */
    public Integer getInt(){
        if(size != 4 || data.length != 4){
            return null;
        }
        return ByteBuffer.wrap(data).getInt();
    }

    /**
     * @return Returns the parameter as long value
     */
    public Long getLong(){
        if(size != 8 || data.length != 8){
            return null;
        }

        return ByteBuffer.wrap(data).getLong();
    }

    /**
     * @return Returns the parameter as float value
     */
    public Float getFloat(){
        if(size != 4 || data.length != 4){
            return null;
        }

        Integer representation = getInt();
        if(representation == null){
            return null;
        }

        return Float.intBitsToFloat(representation);
    }

    /**
     * @return Returns the parameter as string value
     */
    public String getString(){
        return new String(data);
    }

    /**
     * Checks for equality
     * @param o Other object to compare
     * @return True if equal
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Parameter){
            Parameter p = (Parameter)o;

            boolean boolEqual = false;
            boolean intEqual = false;

            boolEqual = getBoolean() != null && p.getBoolean() != null
                        ? getBoolean().equals(p.getBoolean())
                            : getBoolean() != null ^ p.getBoolean() != null
                                ? false
                                : true;

            intEqual = getInt() != null && p.getInt() != null
                        ? getInt().equals(p.getInt())
                            : getInt() == null ^ p.getInt() == null
                                ? false
                                : true;

            return boolEqual &&
                   intEqual &&
                   getString().equals(p.getString()) &&
                   getSize() == getSize() &&
                   Arrays.equals(data, p.getData());
        }
        return false;
    }

    /**
     * Converts the parameter to a string
     * @return String representation
     */
    @Override
    public String toString(){
        return "Parameter:["
             + "Int:" + getInt()
             + ", Boolean:" + getBoolean()
             + ", String:"  + getString()
             + ", Data:" + Arrays.toString(data)
             + "]";
    }
}
