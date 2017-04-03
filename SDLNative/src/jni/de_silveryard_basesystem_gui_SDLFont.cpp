//
// Created by Beppo on 02/02/17.
//

#include <string>
#include "de_silveryard_basesystem_gui_SDLFont.h"
#include "../font.h"

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLFont_fontLoad
        (JNIEnv* env, jclass obj, jstring path, jint size){
    const char* native_path = env->GetStringUTFChars(path, 0);
    int res = font_load(native_path, size);
    env->ReleaseStringUTFChars(path, native_path);

    return res;
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLFont_fontUnload
        (JNIEnv* env, jclass obj, jint font){
    return font_unload(font);
}