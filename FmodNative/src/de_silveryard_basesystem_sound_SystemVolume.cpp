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

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetNumOutputDevices
(JNIEnv* env, jobject obj) {
	return sysvol_get_num_output_devices();
}
JNIEXPORT jstring JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetOutputDeviceName
(JNIEnv* env, jobject obj, jint index) {
	const char* name = sysvol_get_output_device_name(index);
	jstring jname = env->NewStringUTF(name);
	return jname;
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

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetNumInputDevices
(JNIEnv* env, jobject obj) {
	return sysvol_get_num_input_devices();
}
JNIEXPORT jstring JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputDeviceName
(JNIEnv* env, jobject obj, jint index) {
	const char* name = sysvol_get_input_device_name(index);
	jstring jname = env->NewStringUTF(name);
	return jname;
}

JNIEXPORT jboolean JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputMute
(JNIEnv* env, jobject obj) {
	return sysvol_get_input_mute();
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetInputMute
(JNIEnv* env, jobject obj, jboolean mute) {
	sysvol_set_input_mute(mute);
}

JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputMasterVolume
(JNIEnv* env, jobject obj) {
	return sysvol_get_input_master_volume();
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetInputMasterVolume
(JNIEnv* env, jobject obj, jfloat volume) {
	sysvol_set_input_master_volume(volume);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetNumInputChannels
(JNIEnv* env, jobject obj) {
	return sysvol_get_num_input_channels();
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputChannelType
(JNIEnv* env, jobject obj, jint index) {
	return sysvol_get_input_channel_type(index);
}
JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeGetInputChannelVolume
(JNIEnv* env, jobject obj, jint index) {
	return sysvol_get_input_volume(index);
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_SystemVolume_nativeSetInputChannelVolume
(JNIEnv* env, jobject obj, jint index, jfloat volume) {
	sysvol_set_input_volume(index, volume);
}