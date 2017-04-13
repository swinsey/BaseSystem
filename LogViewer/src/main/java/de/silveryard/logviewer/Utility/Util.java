package de.silveryard.logviewer.Utility;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by Sebif on 11.04.2017.
 */
public abstract class Util {
    public static String generateMd5(String string){
        return generateMd5(string.getBytes());
    }
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
}
