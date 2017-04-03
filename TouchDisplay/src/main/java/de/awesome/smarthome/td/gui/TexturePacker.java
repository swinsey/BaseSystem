package de.awesome.smarthome.td.gui;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beppo on 04/02/17.
 */
public class TexturePacker {
    public static TextureAtlas createSprites(Texture texture, Path jsonPath){
        Map<String, TextureSprite> sprites = new HashMap<>();

        String data = readFile(jsonPath);
        JSONObject obj = new JSONObject(data);

        JSONArray frames = obj.getJSONArray("frames");
        for(int i = 0; i < frames.length(); i++){
            String name = frames.getJSONObject(i).getString("filename");
            JSONObject frame = frames.getJSONObject(i).getJSONObject("frame");
            int x = frame.getInt("x");
            int y = frame.getInt("y");
            int w = frame.getInt("w");
            int h = frame.getInt("h");
            TextureSprite sprite = new TextureSprite(texture, x, y, w, h);
            sprites.put(name, sprite);
        }

        return new TextureAtlas(sprites);
    }

    private static String readFile(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            StringBuilder builder = new StringBuilder();
            for(String line : lines){
                builder.append(line).append('\n');
            }
            return builder.toString();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
