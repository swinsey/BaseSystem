package de.silveryard.apf;

/**
 * The builder class is responsible to convert ApplicationPackageFile objects to binary form
 *
 * Created by Sebif on 22.02.2017.
 */
public abstract class Builder {
    /**
     * Generates a binary representation of the file
     * @param file Java Wrapper
     * @return Binary data
     */
    public static byte[] build(ApplicationPackageFile file){
        assert file != null;

        int size = getSize(file);
        byte[] data = new byte[size];
        int index = 0;

        //File Data
        index += writeByte(data, (byte)'.', index);
        index += writeByte(data, (byte)'a', index);
        index += writeByte(data, (byte)'p', index);
        index += writeByte(data, (byte)'f', index);
        index += writeShort(data, file.getFileVersion(), index);

        //App Metadata
        index += writeString(data, file.getAppName(), index);
        index += writeString(data, file.getAppIdentifier(), index);
        index += writeByte(data, file.getMajorVersion(), index);
        index += writeByte(data, file.getMinorVersion(), index);

        //Icons
        index += writeByte(data, (byte)file.getIcons().length, index);
        for(int i = 0; i < file.getIcons().length; i++){
            index += writeShort(data, file.getIcons()[i].getSize(), index);
            index += writeInt(data, file.getIcons()[i].getData().length, index);
            index += writeArray(data, file.getIcons()[i].getData(), index);
        }
        index += writeByte(data, (byte)file.getSplashImages().length, index);
        for(int i = 0; i < file.getSplashImages().length; i++){
            index += writeShort(data, file.getSplashImages()[i].getImageWidth(), index);
            index += writeShort(data, file.getSplashImages()[i].getImageHeight(), index);
            index += writeInt(data, file.getSplashImages()[i].getData().length, index);
            index += writeArray(data, file.getSplashImages()[i].getData(), index);
        }

        //Data
        index += writeInt(data, file.getBinary().length, index);
        index += writeArray(data, file.getBinary(), index);
        index += writeShort(data, (short)file.getFiles().length, index);
        for(int i = 0; i < file.getFiles().length; i++){
            index += writeString(data, file.getFiles()[i].getFilename(), index);
            index += writeByte(data, (byte)file.getFiles()[i].getSubDirs().length, index);
            for(int j = 0; j < file.getFiles()[i].getSubDirs().length; j++){
                index += writeString(data, file.getFiles()[i].getSubDirs()[j], index);
            }
            index += writeInt(data, file.getFiles()[i].getData().length, index);
            index += writeArray(data, file.getFiles()[i].getData(), index);
        }

        assert index == data.length;

        return data;
    }

    private static int getSize(ApplicationPackageFile file){
        assert file != null;

        int size = 0;

        size += 4; //magicNumbers
        size += 2; //fileVersion

        size += 1; //appNameLength
        size += file.getAppName().length();
        size += 1; //appIdentifierLength
        size += file.getAppIdentifier().length();
        size += 1; //appMajorVersion
        size += 1; //

        size += 1; //appIconCount
        for(int i = 0; i < file.getIcons().length; i++){
            size += 2; //iconSize
            size += 4; //dataLength
            size += file.getIcons()[i].getData().length;
        }
        size += 1; //splashImageCount
        for(int i = 0; i < file.getSplashImages().length; i++){
            size += 2; //imageWidth
            size += 2; //imageHeight
            size += 4; //dataLength
            size += file.getSplashImages()[i].getData().length;
        }

        size += 4; //binaryLength
        size += file.getBinary().length;
        size += 2; //fileCount
        size += file.getFiles().length;

        size += 2;//fileCount
        for(int i = 0; i < file.getFiles().length; i++){
            size += 1;//fileNameLength
            size += file.getFiles()[i].getFilename().length();
            size += 1;//subDirCount
            size += file.getFiles()[i].getSubDirs().length;
            for(int j = 0; j < file.getFiles()[i].getSubDirs().length; j++){
                size += 1;//subDirLength
                size += file.getFiles()[i].getSubDirs()[j].length();
            }
            size += 4;//dataLength
            size += file.getFiles()[i].getData().length;
        }

        return size;
    }

    private static int writeByte(byte[] data, byte b, int offset){
        assert offset >= 0;
        assert data != null;
        assert data.length >= offset + 1;

        data[offset] = b;
        return 1;
    }
    private static int writeShort(byte[] data, short s, int offset){
        assert offset >= 0;
        assert data != null;
        assert data.length >= offset + 2;

        data[offset + 0] = (byte)((s >> 8) & 0xFF);
        data[offset + 1] = (byte)((s >> 0) & 0xFF);
        return 2;
    }
    private static int writeInt(byte[] data, int i, int offset){
        assert offset >= 0;
        assert data != null;
        assert data.length >= offset + 4;

        data[offset + 0] = (byte)((i >> 24) & 0xFF);
        data[offset + 1] = (byte)((i >> 16) & 0xFF);
        data[offset + 2] = (byte)((i >>  8) & 0xFF);
        data[offset + 3] = (byte)((i >>  0) & 0xFF);
        return 4;
    }

    private static int writeArray(byte[] data, byte[] array, int offset){
        assert offset >= 0;
        assert array != null;
        assert array.length > 0;
        assert data != null;
        assert data.length >= offset + array.length;

        for(int i = 0; i < array.length; i++){
            data[offset + i] = array[i];
        }
        return array.length;
    }
    private static int writeString(byte[] data, String s, int offset){
        assert offset >= 0;
        assert s != null;
        assert s.length() < Util.BYTE_MAX;
        assert data != null;
        assert data.length >= offset + s.length();

        byte[] tmp = s.getBytes();
        int tmpOffset = writeByte(data, (byte)tmp.length, offset);
        tmpOffset += writeArray(data, tmp, offset + tmpOffset);
        return tmpOffset;
    }
}
