//
// Created by Beppo on 02/02/17.
//

#include <string>
#include "de_awesome_smarthome_td_gui_SDLLabel.h"
#include "../label.h"

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_gui_SDLLabel_labelCreate
        (JNIEnv* env, jclass obj, jint font, jstring text,
         jbyte r, jbyte g, jbyte b, jbyte a, jint width){
    const char* native_tmp_text = env->GetStringUTFChars(text, 0);
    const char* native_text = strdup(native_tmp_text);
    env->ReleaseStringUTFChars(text, native_tmp_text);
    //TODO leak

    return label_create(font, native_text, r, g, b, a, width);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_gui_SDLLabel_labelDestroy
        (JNIEnv* env, jclass obj, jint label){
    return label_destroy(label);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_gui_SDLLabel_labelSetText
        (JNIEnv* env, jclass obj, jint label, jstring text){
    const char* native_tmp_text = env->GetStringUTFChars(text, 0);
    const char* native_text = strdup(native_tmp_text);
    env->ReleaseStringUTFChars(text, native_tmp_text);
    //TODO leak

    return label_set_text(label, native_text);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_gui_SDLLabel_labelSetFont
        (JNIEnv* env, jclass obj, jint label, jint font){
    return label_set_font(label, font);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_gui_SDLLabel_labelSetColor
        (JNIEnv* env, jclass obj, jint label, jchar r, jchar g, jchar b, jchar a){
    return label_set_color(label, r, g, b, a);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_gui_SDLLabel_labelSetWidth
(JNIEnv* env, jclass clazz, jint label, jint width) {
	return label_set_width(label, width);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_gui_SDLLabel_labelGetWidth
        (JNIEnv* env, jclass obj, jint label){
    return label_get_width(label);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_gui_SDLLabel_labelGetHeight
        (JNIEnv* env, jclass obj, jint label){
    return label_get_height(label);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_gui_SDLLabel_labelSetAlpha
(JNIEnv* env, jclass clazz, jint label, jbyte alpha) {
	return label_set_alpha(label, alpha);
}