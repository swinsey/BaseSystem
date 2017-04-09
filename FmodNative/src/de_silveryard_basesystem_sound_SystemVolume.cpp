#include "de_silveryard_basesystem_sound_SystemVolume.h"
#include "SystemVolume.h"

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeInit
(JNIEnv* env, jobject obj) {
	return sysvol_init();
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeDispose
(JNIEnv* env, jobject obj) {
	sysvol_dispose();
}

JNIEXPORT jboolean JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetMute
(JNIEnv* env, jobject obj) {
	return sysvol_get_mute();
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetMute
(JNIEnv* env, jobject obj, jboolean mute) {
	sysvol_set_mute(mute);
}

JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetMasterVolume
(JNIEnv* env, jobject obj) {
	return sysvol_get_master_volume();
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetMasterVolume
(JNIEnv* env, jobject obj, jfloat volume) {
	sysvol_set_master_volume(volume);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetNumOutputChannels
(JNIEnv* env, jobject obj) {
	return sysvol_get_num_output_channels();
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetOutputChannelType
(JNIEnv* env, jobject obj, jint index) {
	return sys_vol_get_output_channel_type(index);
}
JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetOutputChannelVolume
(JNIEnv* env, jobject obj, jint index) {
	return sysvol_get_output_volume(index);
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetOutputChannelVolume
(JNIEnv* env, jobject obj, jint index, jfloat volume) {
	sysvol_set_output_volume(index, volume);
}