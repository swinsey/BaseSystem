package de.silveryard.apf.profiles;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Sebif on 7/21/2017.
 */
public abstract class AppProfileFactory {
    public static AppProfile createAppProfile(File file) throws IOException {
        return createAppProfile(file.toPath());
    }
    public static AppProfile createAppProfile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < lines.size(); i++){
            builder.append(lines.get(i)).append('\n');
        }

        return createAppProfile(builder.toString());
    }
    public static AppProfile createAppProfile(String json){
        throw new NotImplementedException();
    }

    private AppProfileFactory(){}
}
