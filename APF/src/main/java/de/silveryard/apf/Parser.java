package de.silveryard.apf;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser is responsible to convert binary representation to java representation
 *
 * Created by Sebif on 25.02.2017.
 */
public class Parser {
    private byte[] data;
    private int index;

    /**
     * Constructor
     * @param data Binary representation
     */
    public Parser(byte[] data){
        this.data = data;
        this.index = 0;
    }

    /**
     * Parse the file
     * @return Java representation
     */
    public ApplicationPackageFile parse(){
        index = 0;
        checkMagicNumbers();

        short fileVersion = getShort();
        if(fileVersion != 1){
            throw new RuntimeException("Unknown file version");
        }

        String appName = getString();
        String appIdentifier = getString();
        byte majorVersion = getByte();
        byte minorVersion = getByte();

        List<AppIcon> icons = new ArrayList<>();
        int iconCount = getByte();
        for(int i = 0; i < iconCount; i++){
            icons.add(readIcon());
        }

        List<SplashImage> splashs = new ArrayList<>();
        int splashCount = getByte();
        for(int i = 0; i < splashCount; i++){
            splashs.add(getSplash());
        }

        byte[] binary = getByteArray();

        List<File> files = new ArrayList<>();
        int fileCount = getShort();
        for(int i = 0; i < fileCount; i++){
            files.add(getFile());
        }

        AppIcon[] iconArray = icons.toArray(new AppIcon[icons.size()]);
        SplashImage[] splashArray = splashs.toArray(new SplashImage[splashs.size()]);
        File[] fileArray = files.toArray(new File[files.size()]);

        return new ApplicationPackageFile(appName, appIdentifier, majorVersion, minorVersion, iconArray, splashArray, binary, fileArray);
    }

    private AppIcon readIcon(){
        short size = getShort();
        byte[] bytes = getByteArray();

        return new AppIcon(size, bytes);
    }
    private SplashImage getSplash(){
        short width = getShort();
        short height = getShort();
        byte[] bytes = getByteArray();

        return new SplashImage(width, height, bytes);
    }
    private File getFile(){
        String fileName = getString();
        List<String> subDirs = new ArrayList<>();
        int subDirCount = getByte();
        for(int i = 0; i < subDirCount; i++){
            subDirs.add(getString());
        }
        byte[] bytes = getByteArray();
        String[] subDirArray = subDirs.toArray(new String[subDirs.size()]);
        return new File(fileName, subDirArray, bytes);
    }

    private void checkMagicNumbers(){
        if(data.length < 4){
            throw new ParseException("No Application Package File");
        }

        byte b1 = data[index++];
        byte b2 = data[index++];
        byte b3 = data[index++];
        byte b4 = data[index++];

        if(b1 != '.' ||
            b2 != 'a' ||
            b3 != 'p' ||
            b4 != 'f'){
            throw new ParseException("No Application Package File");
        }
    }
    private byte[] getByteArray(){
        if(data.length < index + 4) {
            throwMalformed();
        }

        int len = getInt();
        byte[] bytes = new byte[len];

        for(int i = 0; i < len; i++){
            bytes[i] = data[index++];
        }

        return bytes;
    }
    private byte getByte(){
        if(data.length < index + 1){
            throwMalformed();
        }

        return data[index++];
    }
    private short getShort(){
        if(data.length < index + 2){
            throw new RuntimeException("Malformed File");
        }

        return (short)(((data[index++]&0xFF) << 8) | (data[index++]&0xFF));
    }
    private int getInt(){
        if(data.length < index + 4){
            throwMalformed();
        }

        int i = 0;
        i |= (data[index++]&0xFF) << 24;
        i |= (data[index++]&0xFF) << 16;
        i |= (data[index++]&0xFF) <<  8;
        i |= (data[index++]&0xFF) <<  0;
        return i;
    }
    private String getString(){
        if(data.length < index + 1){
            throwMalformed();
        }

        int length = data[index++];

        if(data.length < index + length){
            throwMalformed();
        }

        String str = new String(data, index, length);
        index += length;
        return str;
    }

    private void throwMalformed(){
        throw new RuntimeException("Malformed File");
    }
}
