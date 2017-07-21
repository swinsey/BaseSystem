package de.silveryard.apf.profiles;

import java.util.List;

/**
 * Created by Sebif on 7/21/2017.
 */
public class AppProfile {
    private final int fileVersion;
    private final String name;
    private final List<Section> sections;

    public AppProfile(int fileVersion, String name, List<Section> sections) {
        this.fileVersion = fileVersion;
        this.name = name;
        this.sections = sections;
    }

    public int getFileVersion() {
        return fileVersion;
    }
    public String getName() {
        return name;
    }
    public List<Section> getSections() {
        return sections;
    }
}
