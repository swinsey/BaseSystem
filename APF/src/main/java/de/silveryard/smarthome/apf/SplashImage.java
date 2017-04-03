package de.silveryard.smarthome.apf;

/**
 * A wrapper for holding splash images
 *
 * Created by Sebif on 22.02.2017.
 */
public class SplashImage {
    private short imageWidth;
    private short imageHeight;
    private byte[] data;

    /**
     * @param imageWidth Image width
     * @param imageHeight Image height
     * @param data Binary data
     */
    public SplashImage(short imageWidth, short imageHeight, byte[] data){
        assert imageWidth > 0;
        assert imageHeight > 0;
        assert data != null;
        assert data.length > 0;
        assert data.length <= Util.INT_MAX;

        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.data = data;
    }

    /**
     * @return Image width
     */
    public short getImageWidth(){
        return imageWidth;
    }
    /**
     * @return Image height
     */
    public short getImageHeight(){
        return imageHeight;
    }
    /**
     * @return Binary data
     */
    public byte[] getData(){
        return data;
    }
}
