//
// Created by Beppo on 02/02/17.
//

#include "de_silveryard_basesystem_gui_SDLTexture.h"
#include "../texture.h"

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureLoad
        (JNIEnv* env, jclass obj, jstring path){
    const char* native_path = env->GetStringUTFChars(path, 0);
    int result = texture_load(native_path);
    env->ReleaseStringUTFChars(path, native_path);
    return result;
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureUnload
        (JNIEnv* env, jclass obj, jint tex){
    return texture_unload(tex);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureGetWidth
        (JNIEnv* env, jclass obj, jint tex){
    return texture_get_width(tex);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureGetHeight
        (JNIEnv* env, jclass obj, jint tex){
    return texture_get_height(tex);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureSetAlpha
(JNIEnv* env, jclass clazz, jint tex, jbyte alpha) {
	return texture_set_alpha(tex, alpha);
}
