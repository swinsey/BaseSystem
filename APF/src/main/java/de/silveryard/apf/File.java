package de.silveryard.apf;

/**
 * Wrapper for Files
 *
 * Created by Sebif on 22.02.2017.
 */
public class File {
    private String filename;
    private String[] subDirs;
    private byte[] data;

    /**
     * Constructor
     * @param filename Filename (including extension)
     * @param subDirs List of subdirs
     * @param data Binary data
     */
    public File(String filename, String[] subDirs, byte[] data){
        assert filename != null;
        assert filename.length() > 0;
        assert filename.length() <= Util.BYTE_MAX;
        assert subDirs != null;
        assert subDirs.length <= Util.BYTE_MAX;
        for(int i = 0; i < subDirs.length; i++){
            assert subDirs[i] != null;
            assert subDirs[i].length() > 0;
            assert subDirs[i].length() <= Util.BYTE_MAX;
        }
        assert data != null;
        assert data.length > 0;
        assert data.length <= Util.INT_MAX;

        this.filename = filename;
        this.subDirs = subDirs;
        this.data = data;
    }

    /**
     * @return Filename
     */
    public String getFilename(){
        return filename;
    }
    /**
     * @return List of subdirs
     */
    public String[] getSubDirs(){
        return subDirs;
    }
    /**
     * @return Binary data
     */
    public byte[] getData(){
        return data;
    }
}
