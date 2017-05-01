/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class de_silveryard_basesystem_gui_SDLTexture */

#ifndef _Included_de_silveryard_basesystem_gui_SDLTexture
#define _Included_de_silveryard_basesystem_gui_SDLTexture
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     de_silveryard_basesystem_gui_SDLTexture
 * Method:    textureLoad
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureLoad
  (JNIEnv *, jclass, jstring);

/*
 * Class:     de_silveryard_basesystem_gui_SDLTexture
 * Method:    textureUnload
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureUnload
  (JNIEnv *, jclass, jint);

/*
 * Class:     de_silveryard_basesystem_gui_SDLTexture
 * Method:    textureGetWidth
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureGetWidth
  (JNIEnv *, jclass, jint);

/*
 * Class:     de_silveryard_basesystem_gui_SDLTexture
 * Method:    textureGetHeight
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureGetHeight
  (JNIEnv *, jclass, jint);

/*
 * Class:     de_silveryard_basesystem_gui_SDLTexture
 * Method:    textureSetAlpha
 * Signature: (IB)I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLTexture_textureSetAlpha
  (JNIEnv *, jclass, jint, jbyte);

#ifdef __cplusplus
}
#endif
#endif
