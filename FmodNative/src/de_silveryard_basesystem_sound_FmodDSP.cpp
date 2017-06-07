#include <fmod.hpp>
#include "helper.h"
#include "Boolean.h"
#include "Float.h"
#include "Integer.h"
#include "Wrapper.h"
#include "FmodResult.h"
#include "FmodDSPType.h"
#include "de_silveryard_basesystem_sound_FmodDSPMeteringInfo.h"
#include "de_silveryard_basesystem_sound_FmodDSP.h"

namespace {
	bool _initialized = false;

	jclass _class;
	jfieldID _field_handle;


	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/silveryard/basesystem/sound/FmodDSP");
		_field_handle = env->GetFieldID(_class, "handle", "J");
	}
}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_setHandle
(JNIEnv* env, jobject obj, jlong handle) {
	_init(env);

	env->SetLongField(obj, _field_handle, handle);
}
JNIEXPORT jlong JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getHandle
(JNIEnv* env, jobject obj) {
	_init(env);

	return env->GetLongField(obj, _field_handle);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_release
(JNIEnv* env, jobject obj) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	FMOD_RESULT result = dsp->release();

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_setActive
(JNIEnv* env, jobject obj, jboolean active) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	FMOD_RESULT result = dsp->setActive(active);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getActive
(JNIEnv* env, jobject obj, jobject active) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	bool native_active;
	FMOD_RESULT result = dsp->getActive(&native_active);

	jobject active_boxed = boolean_create(env, native_active);
	wrapper_set_value(env, active, active_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_setBypass
(JNIEnv* env, jobject obj, jboolean bypass) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	FMOD_RESULT result = dsp->setBypass(bypass);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getBypass
(JNIEnv* env, jobject obj, jobject bypass) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	bool native_bypass;
	FMOD_RESULT result = dsp->getBypass(&native_bypass);

	jobject bypass_boxed = boolean_create(env, native_bypass);
	wrapper_set_value(env, bypass, bypass_boxed);

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_setParameterFloat
(JNIEnv* env, jobject obj, jint index, jfloat value) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	FMOD_RESULT result = dsp->setParameterFloat(index, value);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_setParameterInt
(JNIEnv* env, jobject obj, jint index, jint value) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	FMOD_RESULT result = dsp->setParameterInt(index, value);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_setParameterBool
(JNIEnv* env, jobject obj, jint index, jboolean value) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	FMOD_RESULT result = dsp->setParameterBool(index, value);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getParameterFloat
(JNIEnv* env, jobject obj, jint index, jobject value) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	float native_value;
	FMOD_RESULT result = dsp->getParameterFloat(index, &native_value, nullptr, 0);

	jobject value_boxed = float_create(env, native_value);
	wrapper_set_value(env, value, value_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getParameterInt
(JNIEnv* env, jobject obj, jint index, jobject value) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	int native_value;
	FMOD_RESULT result = dsp->getParameterInt(index, &native_value, nullptr, 0);

	jobject value_boxed = integer_create(env, native_value);
	wrapper_set_value(env, value, value_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getParameterBool
(JNIEnv* env, jobject obj, jint index, jobject value) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	bool native_value;
	FMOD_RESULT result = dsp->getParameterBool(index, &native_value, nullptr, 0);

	jobject value_boxed = integer_create(env, native_value);
	wrapper_set_value(env, value, value_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getNumParameters
(JNIEnv* env, jobject obj, jobject numParams) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	int native_numparams;
	FMOD_RESULT result = dsp->getNumParameters(&native_numparams);

	jobject numparams_boxed = integer_create(env, native_numparams);
	wrapper_set_value(env, numParams, numparams_boxed);

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getInfo
(JNIEnv* env, jobject obj, jobject name, jobject version, jobject channels, jobject configWidth, jobject configHeight) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	char native_name[32];
	unsigned int native_version;
	int native_channels;
	int native_configwidth;
	int native_configheight;
	FMOD_RESULT result = dsp->getInfo(native_name, &native_version, &native_channels, &native_configwidth, &native_configheight);

	jstring name_boxed = env->NewStringUTF(native_name);
	wrapper_set_value(env, name, name_boxed);

	jobject version_boxed = integer_create(env, (int)native_version);
	wrapper_set_value(env, version, version_boxed);

	jobject channels_boxed = integer_create(env, native_channels);
	wrapper_set_value(env, channels, channels_boxed);

	jobject configwidth_boxed = integer_create(env, native_configwidth);
	wrapper_set_value(env, configWidth, configwidth_boxed);

	jobject configheight_boxed = integer_create(env, native_configheight);
	wrapper_set_value(env, configHeight, configheight_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getType
(JNIEnv* env, jobject obj, jobject type) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	FMOD_DSP_TYPE native_type;
	FMOD_RESULT result = dsp->getType(&native_type);

	jobject type_boxed = fmoddsptype_get_enum_value(env, (int)native_type);
	wrapper_set_value(env, type, type_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getIdle
(JNIEnv* env, jobject obj, jobject idle) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	bool native_idle;
	FMOD_RESULT result = dsp->getIdle(&native_idle);

	jobject idle_boxed = boolean_create(env, native_idle);
	wrapper_set_value(env, idle, idle_boxed);

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_setMeteringEnabled
(JNIEnv* env, jobject obj, jboolean inputEnabled, jboolean outputEnabled) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	FMOD_RESULT result = dsp->setMeteringEnabled(inputEnabled, outputEnabled);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getMeteringEnabled
(JNIEnv* env, jobject obj, jobject inputEnabled, jobject outputEnabled) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	bool native_inputenabled;
	bool native_outputenabled;
	FMOD_RESULT result = dsp->getMeteringEnabled(&native_inputenabled, &native_outputenabled);

	jobject inputenabled_boxed = boolean_create(env, native_inputenabled);
	wrapper_set_value(env, inputEnabled, inputenabled_boxed);

	jobject outputenabled_boxed = boolean_create(env, native_outputenabled);
	wrapper_set_value(env, outputEnabled, outputenabled_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSP_getMeteringInfo
(JNIEnv* env, jobject obj, jobject inputInfo, jobject outputInfo) {
	_init(env);

	FMOD::DSP* dsp = get_handle<FMOD::DSP>(env, _field_handle, obj);
	jlong input_handle = Java_de_silveryard_basesystem_sound_FmodDSPMeteringInfo_getHandle(env, inputInfo);
	jlong output_handle = Java_de_silveryard_basesystem_sound_FmodDSPMeteringInfo_getHandle(env, outputInfo);
	FMOD_DSP_METERING_INFO* native_inputinfo = reinterpret_cast<FMOD_DSP_METERING_INFO*>(input_handle);
	FMOD_DSP_METERING_INFO* native_outputinfo = reinterpret_cast<FMOD_DSP_METERING_INFO*>(output_handle);
	FMOD_RESULT result = dsp->getMeteringInfo(native_inputinfo, native_outputinfo);

	return fmodresult_get_enum_value(env, result);
}
