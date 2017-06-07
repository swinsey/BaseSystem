#include <fmod.hpp>
#include "de_silveryard_basesystem_sound_FmodDSPMeteringInfo.h"
#include "helper.h"

using namespace FMOD;

namespace {
	bool _initialized = false;

	jclass _class;
	jfieldID _field_handle;


	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/silveryard/basesystem/sound/FmodDSPMeteringInfo");
		_field_handle = env->GetFieldID(_class, "handle", "J");
	}
}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_FmodDSPMeteringInfo_init
(JNIEnv* env, jobject obj) {
	_init(env);

	FMOD_DSP_METERING_INFO* info = new FMOD_DSP_METERING_INFO();
	set_handle<FMOD_DSP_METERING_INFO>(env, _field_handle, obj, info);
}
JNIEXPORT jlong JNICALL Java_de_silveryard_basesystem_sound_FmodDSPMeteringInfo_getHandle
(JNIEnv* env, jobject obj) {
	_init(env);

	return env->GetLongField(obj, _field_handle);
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_sound_FmodDSPMeteringInfo_getNumSamples
(JNIEnv* env, jobject obj) {
	_init(env);

	FMOD_DSP_METERING_INFO* info = get_handle<FMOD_DSP_METERING_INFO>(env, _field_handle, obj);
	return info->numsamples;
}
JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_FmodDSPMeteringInfo_getPeakLevel
(JNIEnv* env, jobject obj, jint index) {
	_init(env);

	if (index < 0 || index > 31) {
		return -1.0f;
	}

	FMOD_DSP_METERING_INFO* info = get_handle<FMOD_DSP_METERING_INFO>(env, _field_handle, obj);
	return info->peaklevel[index];
}
JNIEXPORT jfloat JNICALL Java_de_silveryard_basesystem_sound_FmodDSPMeteringInfo_getRmsLevel
(JNIEnv* env, jobject obj, jint index) {
	_init(env);

	if (index < 0 || index > 31) {
		return -1.0f;
	}

	FMOD_DSP_METERING_INFO* info = get_handle<FMOD_DSP_METERING_INFO>(env, _field_handle, obj);
	return info->rmslevel[index];
}
JNIEXPORT jshort JNICALL Java_de_silveryard_basesystem_sound_FmodDSPMeteringInfo_getNumChannels
(JNIEnv* env, jobject obj) {
	_init(env);

	FMOD_DSP_METERING_INFO* info = get_handle<FMOD_DSP_METERING_INFO>(env, _field_handle, obj);
	return info->numchannels;
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_FmodDSPMeteringInfo_dispose
(JNIEnv* env, jobject obj) {
	_init(env);

	FMOD_DSP_METERING_INFO* info = get_handle<FMOD_DSP_METERING_INFO>(env, _field_handle, obj);
	delete info;
	set_handle<FMOD_DSP_METERING_INFO>(env, _field_handle, obj, 0);
}
