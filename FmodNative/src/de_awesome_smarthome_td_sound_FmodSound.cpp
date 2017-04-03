#include "fmod.hpp"
#include "fmod_errors.h"
#include "helper.h"
#include "FmodResult.h"
#include "Integer.h"
#include "Wrapper.h"
#include "de_awesome_smarthome_td_sound_FmodSound.h"

namespace {
	bool _initialized = false;

	jclass _class;
	jfieldID _field_handle;


	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/awesome/smarthome/td/sound/FmodSound");
		_field_handle = env->GetFieldID(_class, "handle", "J");
	}
}

JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodSound_setHandle
(JNIEnv* env, jobject obj, jlong handle) {
	_init(env);

	env->SetLongField(obj, _field_handle, handle);
}
JNIEXPORT jlong JNICALL Java_de_awesome_smarthome_td_sound_FmodSound_getHandle
(JNIEnv* env, jobject obj) {
	_init(env);

	return env->GetLongField(obj, _field_handle);
}

JNIEXPORT jobject JNICALL Java_de_awesome_smarthome_td_sound_FmodSound_getLength
(JNIEnv* env, jobject obj, jobject length, jint lengthtype) {
	_init(env);

	FMOD::Sound* sound = get_handle<FMOD::Sound>(env, _field_handle, obj);
	unsigned int native_length;
	FMOD_RESULT result = sound->getLength(&native_length, lengthtype);

	jobject length_boxed = integer_create(env, native_length);
	wrapper_set_value(env, length, length_boxed);

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_awesome_smarthome_td_sound_FmodSound_release
(JNIEnv* env, jobject obj) {
	_init(env);

	FMOD::Sound* sound = get_handle<FMOD::Sound>(env, _field_handle, obj);
	FMOD_RESULT result = sound->release();

	return fmodresult_get_enum_value(env, result);
}
