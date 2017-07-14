package de.silveryard.basesystem.gui;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebif on 7/13/2017.
 */
public class Shader {
    public static Shader create(String vertexSource, String fragmentSource){
        int program = SDLShader.shaderProgramCreate();
        int vertexShader = SDLShader.createShader(SDLShader.GL_VERTEX_SHADER);
        int fragmentShader = SDLShader.createShader(SDLShader.GL_FRAGMENT_SHADER);

        SDLShader.shaderSetSource(vertexShader, vertexSource);
        SDLShader.shaderSetSource(fragmentShader, fragmentSource);

        int result = SDLShader.shaderCompile(vertexShader);
        if(result < 0)
            throw new RuntimeException("Failed to compile vertex shader!");

        result = SDLShader.shaderCompile(fragmentShader);
        if(result < 0)
            throw new RuntimeException("Failed to compile fragment shader!");

        SDLShader.shaderProgramAttach(program, vertexShader);
        SDLShader.shaderProgramAttach(program, fragmentShader);

        result = SDLShader.shaderProgramLink(program);
        if(result < 0)
            throw new RuntimeException("Failed to link shader program!");

        SDLShader.shaderDelete(vertexShader);
        SDLShader.shaderDelete(fragmentShader);

        return new Shader(program);
    }

    private int program;
    private Map<String, Integer> locations;

    private Shader(int program){
        this.program = program;
        locations = new HashMap<>();
    }

    private int getLocation(String name){
        Integer location = locations.get(name);

        if(location == null){
            location = SDLShader.shaderProgramGetUniformLocation(program, name);
            locations.put(name, location);
        }

        return location;
    }

    public void use(){
        SDLShader.shaderProgramUse(program);
    }

    public void setFloat(String name, float value){
        SDLShader.shaderProgramSetFloat(getLocation(name), value);
    }
    public void setInt(String name, int value){
        SDLShader.shaderProgramSetInt(getLocation(name), value);
    }
    public void setVector2f(String name, float x, float y){
        SDLShader.shaderProgramSetVector2f(getLocation(name), x, y);
    }
    public void setVector3f(String name, float x, float y, float z){
        SDLShader.shaderProgramSetVector3f(getLocation(name), x, y, z);
    }
    public void setVector4f(String name, float x, float y, float z, float w){
        SDLShader.shaderProgramSetVector4f(getLocation(name), x, y, z, w);
    }
}
