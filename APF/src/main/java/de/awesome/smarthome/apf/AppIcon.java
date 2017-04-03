package de.awesome.smarthome.apf;

/**
 * A wrapper for holding icon data
 *
 * Created by Sebif on 22.02.2017.
 */
public class AppIcon {
    private short size;
    private byte[] data;

    /**
     * Constructor
     * @param size Width and height
     * @param data Binary data
     */
    public AppIcon(short size, byte[] data){
        assert size > 0;
        assert data != null;
        assert data.length > 0;
        assert data.length <= Util.INT_MAX;

        this.size = size;
        this.data = data;
    }

    /**
     * @return Size of the icon
     */
    public short getSize(){
        return size;
    }

    /**
     * @return Binary data
     */
    public byte[] getData(){
        return data;
    }
}
