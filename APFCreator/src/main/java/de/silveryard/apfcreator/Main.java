package de.silveryard.apfcreator;

import de.silveryard.apf.ApplicationPackageFile;

import java.math.BigDecimal;

import static de.silveryard.apfcreator.Assert.*;

/**
 * Created by Sebif on 23.02.2017.
 */
public class Main {
    public static void main(String[] args){
        assertNotNull(args, "Args cannot benull. This should not happen");
        assertGreaterEquals(args.length, 1, "Invalid argument count");

        if(args[0].equals("create")){
            Creator.create(args);
        }else if(args[0].equals("validate")){
            Validator.validate(args);
        }else{
            assertTrue(false, "Invalid first parameter");
        }
    }

    public static void print(ApplicationPackageFile apf, byte[] parsedData){
        System.out.println("App Name: " + apf.getAppName());
        System.out.println("App Identifier: " + apf.getAppIdentifier());
        System.out.println("Major Version: " + (0xFF&apf.getMajorVersion()));
        System.out.println("Minor Version: " + (0xFF&apf.getMinorVersion()));
        System.out.println("");
        System.out.println("Icons(" + apf.getIcons().length + "): ");
        for(int i = 0; i < apf.getIcons().length; i++){
            System.out.println("    " + apf.getIcons()[i].getSize() + "px");
        }
        System.out.println("");
        System.out.println("Splash Images(" + apf.getSplashImages().length + "): ");
        for(int i = 0; i < apf.getSplashImages().length; i++){
            System.out.println("    " + apf.getSplashImages()[i].getImageWidth() + "x" + apf.getSplashImages()[i].getImageHeight());
        }
        System.out.println("");
        System.out.println("Files(" + apf.getFiles().length + "): ");
        for(int i = 0; i < apf.getFiles().length; i++){
            System.out.print("    ");
            for(int j = 0; j < apf.getFiles()[i].getSubDirs().length; j++){
                System.out.print(apf.getFiles()[i].getSubDirs()[j] + "/");
            }
            System.out.println(apf.getFiles()[i].getFilename() + " (" + getLength(apf.getFiles()[i].getData()) + ")");
        }
        System.out.println("");
        System.out.println("Parsed Size: " + getLength(parsedData));
    }
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    private static String getLength(byte[] data){
        String ending = " Bytes";
        float length = data.length;
        if(length > 1024){
            ending = "KiB";
            length /= 1024;
        }
        if(length > 1024){
            ending = "MiB";
            length /= 1024;
        }
        if(length > 1024){
            ending = "GiB";
            length /= 1024;
        }

        return round(length, 2) + ending;
    }
}
