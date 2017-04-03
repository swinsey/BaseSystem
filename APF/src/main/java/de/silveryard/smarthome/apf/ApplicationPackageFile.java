package de.silveryard.smarthome.apf;

/**
 * Java Representation of an application package file (.apf)
 *
 * Created by Sebif on 22.02.2017.
 */
public class ApplicationPackageFile {
    //File Data
    private short fileVersion;

    //App Metadata
    private String appName;
    private String appIdentifier;
    private byte majorVersion;
    private byte minorVersion;

    //Icons
    private AppIcon[] icons;
    private SplashImage[] splashImages;

    //Data
    private byte[] binary;
    private File[] files;

    /**
     * Constructor
     * @param appName App Name
     * @param appIdentifier Unique App Identifier
     * @param majorVersion Apps Major Version
     * @param minorVersion Apps Minor version
     * @param icons Application Icons
     * @param splashImages Application Splashs
     * @param binary Application main binary
     * @param files Application Files
     */
    public ApplicationPackageFile(
            String appName, String appIdentifier,
            byte majorVersion, byte minorVersion,
            AppIcon[] icons, SplashImage[] splashImages,
            byte[] binary, File[] files){
        this((short)1,
                appName, appIdentifier,
                majorVersion, minorVersion,
                icons, splashImages,
                binary, files);
    }
    /**
     * Constructor
     * @param fileVersion File Version
     * @param appName App Name
     * @param appIdentifier Unique App Identifier
     * @param majorVersion Apps Major Version
     * @param minorVersion Apps Minor version
     * @param icons Application Icons
     * @param splashImages Application Splashs
     * @param binary Application main binary
     * @param files Application Files
     */
    public ApplicationPackageFile(
            short fileVersion,
            String appName, String appIdentifier,
            byte majorVersion, byte minorVersion,
            AppIcon[] icons, SplashImage[] splashImages,
            byte[] binary, File[] files){

        assert fileVersion > 0;
        assert appName != null;
        assert appName.length() > 0;
        assert appName.length() <= Util.BYTE_MAX;
        assert appIdentifier != null;
        assert appIdentifier.length() > 0;
        assert appIdentifier.length() <= Util.BYTE_MAX;
        assert majorVersion > 0;
        assert minorVersion > 0;
        assert icons != null;
        assert icons.length > 0;
        assert icons.length <= Util.BYTE_MAX;
        for(int i = 0; i < icons.length; i++){
            assert icons[i] != null;
        }
        assert splashImages != null;
        assert splashImages.length > 0;
        assert splashImages.length <= Util.BYTE_MAX;
        for(int i = 0; i < splashImages.length; i++){
            assert splashImages[i] != null;
        }
        assert binary != null;
        assert binary.length > 0;
        assert binary.length <= Util.INT_MAX;
        assert files != null;
        assert files.length > 0;
        assert files.length <= Util.SHORT_MAX;
        for(int i = 0; i < files.length; i++){
            assert files[i] != null;
        }

        this.fileVersion = fileVersion;
        this.appName = appName;
        this.appIdentifier = appIdentifier;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.icons = icons;
        this.splashImages = splashImages;
        this.binary = binary;
        this.files = files;
    }

    /**
     * @return File Version
     */
    public short getFileVersion(){
        return fileVersion;
    }

    /**
     * @return Application Name
     */
    public String getAppName(){
        return appName;
    }
    /**
     * @return Unique Application Identifier
     */
    public String getAppIdentifier(){
        return appIdentifier;
    }
    /**
     * @return Apps Major Version
     */
    public byte getMajorVersion(){
        return majorVersion;
    }
    /**
     * @return Apps Minor version
     */
    public byte getMinorVersion(){
        return minorVersion;
    }

    /**
     * @return Application Icons
     */
    public AppIcon[] getIcons(){
        return icons;
    }
    /**
     * @return Application Splashs
     */
    public SplashImage[] getSplashImages(){
        return splashImages;
    }

    /**
     * @return Applications main binary
     */
    public byte[] getBinary(){
        return binary;
    }
    /**
     * @return Applications files
     */
    public File[] getFiles(){
        return files;
    }
}
