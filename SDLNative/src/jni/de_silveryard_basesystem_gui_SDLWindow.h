/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class de_silveryard_basesystem_gui_SDLWindow */

#ifndef _Included_de_silveryard_basesystem_gui_SDLWindow
#define _Included_de_silveryard_basesystem_gui_SDLWindow
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowInit
 * Signature: (Ljava/lang/String;IIZ)I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowInit
  (JNIEnv *, jclass, jstring, jint, jint, jboolean);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowDispose
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDispose
  (JNIEnv *, jclass);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowIsInitialized
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowIsInitialized
  (JNIEnv *, jclass);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowGetWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowGetWidth
  (JNIEnv *, jclass);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowGetHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowGetHeight
  (JNIEnv *, jclass);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowUpdateScreen
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowUpdateScreen
  (JNIEnv *, jclass);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowSetDrawColor
 * Signature: (BBBB)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowSetDrawColor
  (JNIEnv *, jclass, jbyte, jbyte, jbyte, jbyte);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowClear
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowClear
  (JNIEnv *, jclass);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowDrawRect
 * Signature: (IIIIZ)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDrawRect
  (JNIEnv *, jclass, jint, jint, jint, jint, jboolean);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowDrawLine
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDrawLine
  (JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowDrawTexture
 * Signature: (IIIIIIIIID)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDrawTexture
  (JNIEnv *, jclass, jint, jint, jint, jint, jint, jint, jint, jint, jint, jdouble);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowDrawLabel
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDrawLabel
  (JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowPollEvent
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowPollEvent
  (JNIEnv *, jclass);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowGetMouseX
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowGetMouseX
  (JNIEnv *, jclass);

/*
 * Class:     de_silveryard_basesystem_gui_SDLWindow
 * Method:    windowGetMouseY
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowGetMouseY
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif
