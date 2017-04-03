#include "FmodSoundFormat.h"

namespace {
	bool _initialized = false;
	jclass _class;
	jmethodID _method_getenumvalue;
	jmethodID _method_getvalue;

	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/awesome/smarthome/td/sound/FmodSoundFormat");
		_method_getenumvalue = env->GetStaticMethodID(_class, "getEnumValue", "(I)Lde/awesome/smarthome/td/sound/FmodSoundFormat;");
		_method_getvalue = env->GetMethodID(_class, "getValue", "()I");
	}
}

jobject fmodsoundformat_get_enum_value(JNIEnv* env, FMOD_SOUND_FORMAT result) {
	return fmodsoundformat_get_enum_value(env, static_cast<int>(result));
}
jobject fmodsoundformat_get_enum_value(JNIEnv* env, int value) {
	_init(env);
	return env->CallStaticObjectMethod(_class, _method_getenumvalue, value);
}
FMOD_SOUND_FORMAT fmodsoundformat_get_value(JNIEnv* env, jobject obj) {
	_init(env);
	return static_cast<FMOD_SOUND_FORMAT>(env->CallIntMethod(obj, _method_getvalue));
}