package de.silveryard.basesystem.util;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URLDecoder;
import java.security.MessageDigest;

/**
 * Created by beppo on 05/02/17.
 */
public class Utils {
    private static String startupPath;

    /**
     * Returns the path to the directory where the executable is located
     * @return Path to the executables directory
     */
    public static String getStartupPath(){
        if(startupPath != null)
            return startupPath;

        String path = Utils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File file = new File(path);

        if(!file.isDirectory()){
            path = file.getParentFile().getAbsolutePath();
        }

        String decodedPath = "";

        try {
            decodedPath = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        startupPath = decodedPath;

        return decodedPath;
    }

    /**
     * Generates a MD5 from a given string
     * @param string String value
     * @return Generated MD5
     */
    public static String generateMd5(String string){
        return generateMd5(string.getBytes());
    }
    /**
     * Generates a MD5 from a given byte array
     * @param data Data value
     * @return Generated MD5
     */
    public static String generateMd5(byte[] data){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(data);
            String hash = DatatypeConverter.printHexBinary(bytes).toLowerCase();
            return hash;
        }catch(Exception e){
            //TODO do error logging
            return null;
        }
    }
    /**
     * Generates a MD5 from a given File from the FileSystem
     * @param file File Path
     * @return Generated MD5
     */
    public static String generateMd5(File file){
        if(!file.exists()){
            return null;
        }
        if(file.isDirectory()){
            return null;
        }

        try {
            InputStream fis =  new FileInputStream(file);
            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead;

            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);

            fis.close();
            return DatatypeConverter.printHexBinary(complete.digest()).toLowerCase();
        }catch(Exception e){
            //TODO do error logging
            return null;
        }
    }

    /**
     * Recursively deletes a directory
     * @param f Path to a directory
     * @throws IOException Thrown when subsequent calls fail
     */
    public static void deleteDirectory(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles())
                deleteDirectory(c);
        }
        if (!f.delete())
            throw new FileNotFoundException("Failed to delete file: " + f);
    }

    /**
     * Java implementation of C#´s 'as' operator
     * Converts an object to a given class safely
     * @param clazz Class to cast the object to
     * @param o Object to cast
     * @return The casted object on success. Null otherwise
     */
    public static <T> T as(Class<T> clazz, Object o){
        if(o == null){
            return null;
        }
        if(clazz.isInstance(o)){
            return clazz.cast(o);
        }
        return null;
    }

    /**
     * Waits until a wrapper value becomes non-null.
     * Requires another thread, that sets the wrappers value while this thread is waiting
     * @param wrapper Wrapper to wait for
     */
    public static void waitForWrapper(Wrapper<?> wrapper){
        while(wrapper.value == null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
