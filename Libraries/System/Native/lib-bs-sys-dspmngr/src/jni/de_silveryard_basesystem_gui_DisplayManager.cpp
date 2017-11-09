#include <string>

#include <bs/system/logger.h>

#include "de_silveryard_basesystem_gui_DisplayManager.h"

using namespace std;

namespace {

}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_initNative
  (JNIEnv* env, jclass clazz) {

    bs::system::log("DisplayManager initialized");
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeRegisterHandle
  (JNIEnv *, jobject, jobject) {
    return 0;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeUnregisterHandle
  (JNIEnv *, jobject, jobject) {
    return 0;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeSetWindowPosition
  (JNIEnv *, jobject, jobject, jint, jint) {
    return 0;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeGetWindowPosition
  (JNIEnv *, jobject, jobject, jobject, jobject) {
    return 0;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeSetWindowAlpha
  (JNIEnv *, jobject, jobject, jbyte) {
    return 0;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeGetWindowAlpha
  (JNIEnv *, jobject, jobject, jobject) {
    return 0;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeSetWindowActive
  (JNIEnv *, jobject, jobject, jboolean) {
    return 0;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeGetWindowActive
  (JNIEnv *, jobject, jobject, jobject) {
    return 0;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeSetWindowLayer
  (JNIEnv *, jobject, jobject, jbyte) {
    return 0;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeGetWindowLayer
  (JNIEnv *, jobject, jobject, jobject){
    return 0;
}
