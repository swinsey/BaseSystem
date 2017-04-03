package de.awesome.smarthome.apfcreator;

import de.awesome.smarthome.apf.AppIcon;
import de.awesome.smarthome.apf.ApplicationPackageFile;
import de.awesome.smarthome.apf.File;
import de.awesome.smarthome.apf.SplashImage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static de.awesome.smarthome.apfcreator.Assert.*;

/**
 * Created by Sebif on 24.02.2017.
 */
public class ApplicationPackageFileTemplate {
    private String appName;
    private String appIdentifier;

    private byte majorVersion;
    private byte minorVersion;

    private byte[] binary;

    private List<AppIconTemplate> appIcons;
    private List<SplashImageTemplate> splashImages;
    private List<FileTemplate> files;

    public ApplicationPackageFileTemplate(){
        appIcons = new ArrayList<>();
        splashImages = new ArrayList<>();
        files = new ArrayList<>();
    }

    public ApplicationPackageFile create(){
        validate();
        AppIcon[] tmpIcons = new AppIcon[appIcons.size()];
        for(int i = 0; i < appIcons.size(); i++){
            tmpIcons[i] = appIcons.get(i).create();
        }
        SplashImage[] tmpSplashs = new SplashImage[splashImages.size()];
        for(int i = 0; i < splashImages.size(); i++){
            tmpSplashs[i] = splashImages.get(i).create();
        }
        File[] tmpFiles = new File[files.size()];
        for(int i = 0; i < files.size(); i++){
            tmpFiles[i] = files.get(i).create();
        }
        return new ApplicationPackageFile(appName, appIdentifier, majorVersion, minorVersion, tmpIcons, tmpSplashs, binary, tmpFiles);
    }

    private void validate(){
        assertNotNull(appName, "App Name cannot be null");
        assertGreater(appName.length(), 0, "App Name has to be at least 1 character long");
        assertLowerEquals(appName.length(), 255, "App Name can be at max 255 characters long");

        assertNotNull(appIdentifier, "App Identifier cannot be null");
        assertGreater(appIdentifier.length(), 0, "App Identifier has to be at least 1 character long");
        assertLowerEquals(appIdentifier.length(), 255, "App Identifier can be at max 255 characters long");

        assertFalse(majorVersion == 0 && minorVersion == 0, "Major Version and Minor Version cannot be both 0");

        assertNotNull(binary, "Binary cannot be null");
        assertGreater(binary.length, 0, "Binary cannot be 0 bytes long");
        assertLowerEquals(binary.length, (4 * 1024 * 1024), "Binary cannot be larger than 4MiB. If you want to use resources, use the files feature");

        assertGreater(appIcons.size(), 0, "You have to specify at least 1 App Icon");
        assertLowerEquals(appIcons.size(), 255, "You cannot specify more than 255 icons");
        for(int i = 0; i < appIcons.size(); i++){
            for(int j = 0; j < appIcons.size(); j++){
                if(i == j){
                    continue;
                }

                assertNotEqual(appIcons.get(i).getSize(), appIcons.get(j).getSize(), "There cannot be 2 images with same size");
            }
        }

        assertGreater(splashImages.size(), 0, "You have to specify at least 1 Splash Image");
        assertLowerEquals(splashImages.size(), 255, "You cannot specify more than 255 splash images");
        for(int i = 0; i < splashImages.size(); i++){
            for(int j = 0; j < splashImages.size(); j++){
                if(i == j){
                    continue;
                }

                assertFalse(splashImages.get(i).getWidth() == splashImages.get(j).getWidth() &&
                            splashImages.get(i).getHeight() == splashImages.get(j).getHeight(), "There cannot be 2 splash images with same size");
            }
        }

        assertLowerEquals(files.size(), 65535, "There cannot be more than 65535 files");
    }

    public void setAppName(String appName){
        this.appName = appName;
    }
    public void setAppIdentifier(String appIdentifier){
        this.appIdentifier = appIdentifier;
    }

    public void setMajorVersion(byte majorVersion){
        this.majorVersion = majorVersion;
    }
    public void setMinorVersion(byte minorVersion){
        this.minorVersion = minorVersion;
    }

    public void setBinary(byte[] binary){
        this.binary = binary;
    }

    public void addIcon(Path path){
        appIcons.add(new AppIconTemplate(path));
    }
    public void addSplashImage(Path path){
        splashImages.add(new SplashImageTemplate(path));
    }
    public void addFile(Path filePath, Path basePath){
        files.add(new FileTemplate(filePath, basePath));
    }
}
