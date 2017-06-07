#include "de_silveryard_basesystem_sound_FmodChannelGroup.h"

namespace {
	bool _initialized = false;

	jclass _class;
	jfieldID _field_handle;


	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/silveryard/basesystem/sound/FmodChannelGroup");
		_field_handle = env->GetFieldID(_class, "handle", "J");
	}
}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_setHandle
(JNIEnv* env, jobject obj, jlong handle) {
	_init(env);

	env->SetLongField(obj, _field_handle, handle);
}

JNIEXPORT jlong JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getHandle
(JNIEnv* env, jobject obj) {
	_init(env);

	return env->GetLongField(obj, _field_handle);
}
