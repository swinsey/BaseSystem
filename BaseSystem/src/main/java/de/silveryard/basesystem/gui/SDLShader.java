package de.silveryard.basesystem.gui;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Created by Sebif on 7/13/2017.
 */
abstract class SDLShader {


    public static final int GL_FRAGMENT_SHADER = 2;
    public static final int GL_VERTEX_SHADER = 1;

    private static SDLNativeLib lib;
    private static void init(){
        if (lib == null){
            lib = (SDLNativeLib) Native.loadLibrary("SDLNative", SDLNativeLib.class);
        }
    }

    public static int createShader(int shaderType){
        init();
        return lib.shader_create(shaderType);
    }
    public static void shaderSetSource(int shader, String source){
        init();
        lib.shader_set_source(shader, source);
    }
    public static int shaderCompile(int shader){
        init();
        return lib.shader_compile(shader);
    }
    public static void shaderDelete(int shader){
        init();
        lib.shader_delete(shader);
    }

    public static int shaderProgramCreate(){
        init();
        return lib.shader_program_create();
    }
    public static void shaderProgramAttach(int program, int shader){
        init();
        lib.shader_program_attach(program, shader);
    }
    public static int shaderProgramLink(int program){
        init();
        return lib.shader_program_link(program);
    }

    public static int shaderProgramGetUniformLocation(int program, String name){
        init();
        return lib.shader_program_get_uniform_location(program, name);
    }
    public static void shaderProgramSetFloat(int location, float value){
        init();
        lib.shader_program_set_float(location, value);
    }
    public static void shaderProgramSetInt(int location, int value){
        init();
        lib.shader_program_set_int(location, value);
    }
    public static void shaderProgramSetVector2f(int location, float x, float y){
        init();
        lib.shader_program_set_vector2f(location, x, y);
    }
    public static void shaderProgramSetVector3f(int location, float x, float y, float z){
        init();
        lib.shader_program_set_vector3f(location, x, y, z);
    }
    public static void shaderProgramSetVector4f(int location, float x, float y, float z, float w){
        init();
        lib.shader_program_set_vector4f(location, x, y, z, w);
    }

    public static void shaderProgramUse(int program){
        init();
        lib.shader_program_use(program);
    }

    private SDLShader(){}
}
