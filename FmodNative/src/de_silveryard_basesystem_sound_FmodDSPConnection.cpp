#include "helper.h"
#include "Float.h"
#include "FmodResult.h"
#include "FmodDSPConnectionType.h"
#include "Wrapper.h"
#include "de_silveryard_basesystem_sound_FmodDSP.h"
#include "de_silveryard_basesystem_sound_FmodDSPConnection.h"

namespace {
	bool _initialized = false;

	jclass _class;
	jfieldID _field_handle;


	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/silveryard/basesystem/sound/FmodDSPConnection");
		_field_handle = env->GetFieldID(_class, "handle", "J");
	}
}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_FmodDSPConnection_setHandle
(JNIEnv* env, jobject obj, jlong handle) {
	_init(env);

	env->SetLongField(obj, _field_handle, handle);
}
JNIEXPORT jlong JNICALL Java_de_silveryard_basesystem_sound_FmodDSPConnection_getHandle
(JNIEnv* env, jobject obj) {
	_init(env);

	return env->GetLongField(obj, _field_handle);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSPConnection_getInput
(JNIEnv* env, jobject obj, jobject dsp) {
	_init(env);

	FMOD::DSPConnection* connection = get_handle<FMOD::DSPConnection>(env, _field_handle, obj);
	FMOD::DSP* dsp_native;
	FMOD_RESULT result = connection->getInput(&dsp_native);

	Java_de_silveryard_basesystem_sound_FmodDSP_setHandle(env, dsp, reinterpret_cast<long long>(dsp_native));

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSPConnection_getOuput
(JNIEnv* env, jobject obj, jobject dsp) {
	_init(env);

	FMOD::DSPConnection* connection = get_handle<FMOD::DSPConnection>(env, _field_handle, obj);
	FMOD::DSP* dsp_native;
	FMOD_RESULT result = connection->getOutput(&dsp_native);

	Java_de_silveryard_basesystem_sound_FmodDSP_setHandle(env, dsp, reinterpret_cast<long long>(dsp_native));

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSPConnection_setMix
(JNIEnv* env, jobject obj, jfloat volume) {
	_init(env);

	FMOD::DSPConnection* connection = get_handle<FMOD::DSPConnection>(env, _field_handle, obj);
	FMOD_RESULT result = connection->setMix(volume);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSPConnection_getMix
(JNIEnv* env, jobject obj, jobject volume) {
	_init(env);

	FMOD::DSPConnection* connection = get_handle<FMOD::DSPConnection>(env, _field_handle, obj);
	float volume_native;
	FMOD_RESULT result = connection->getMix(&volume_native);

	jobject volume_boxed = float_create(env, volume_native);
	wrapper_set_value(env, volume, volume_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodDSPConnection_getType
(JNIEnv* env, jobject obj, jobject type) {
	_init(env);

	FMOD::DSPConnection* connection = get_handle<FMOD::DSPConnection>(env, _field_handle, obj);
	FMOD_DSPCONNECTION_TYPE native_type;
	FMOD_RESULT result = connection->getType(&native_type);

	jobject type_boxed = fmoddspconnectiontype_get_enum_value(env, native_type);
	wrapper_set_value(env, type, type_boxed);

	return fmodresult_get_enum_value(env, result);
}
