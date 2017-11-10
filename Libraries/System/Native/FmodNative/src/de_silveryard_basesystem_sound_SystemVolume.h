/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class de_silveryard_basesystem_sound_SystemVolume */

#ifndef _Included_de_silveryard_basesystem_sound_SystemVolume
#define _Included_de_silveryard_basesystem_sound_SystemVolume
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetNumOutputDevices
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetNumOutputDevices
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetOutputDeviceName
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetOutputDeviceName
  (JNIEnv *, jobject, jint);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeInit
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeInit
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeDispose
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeDispose
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetMute
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetMute
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeSetMute
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetMute
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetMasterVolume
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetMasterVolume
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeSetMasterVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetMasterVolume
  (JNIEnv *, jobject, jfloat);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetNumOutputChannels
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetNumOutputChannels
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetOutputChannelType
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetOutputChannelType
  (JNIEnv *, jobject, jint);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetOutputChannelVolume
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetOutputChannelVolume
  (JNIEnv *, jobject, jint);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeSetOutputChannelVolume
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetOutputChannelVolume
  (JNIEnv *, jobject, jint, jfloat);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetNumInputDevices
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetNumInputDevices
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetInputDeviceName
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputDeviceName
  (JNIEnv *, jobject, jint);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetInputMute
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputMute
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeSetInputMute
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetInputMute
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetInputMasterVolume
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputMasterVolume
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeSetInputMasterVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetInputMasterVolume
  (JNIEnv *, jobject, jfloat);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetNumInputChannels
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetNumInputChannels
  (JNIEnv *, jobject);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetInputChannelType
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputChannelType
  (JNIEnv *, jobject, jint);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeGetInputChannelVolume
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputChannelVolume
  (JNIEnv *, jobject, jint);

/*
 * Class:     de_silveryard_basesystem_sound_SystemVolume
 * Method:    nativeSetInputChannelVolume
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetInputChannelVolume
  (JNIEnv *, jobject, jint, jfloat);

#ifdef __cplusplus
}
#endif
#endif