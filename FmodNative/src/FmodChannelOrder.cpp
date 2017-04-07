#include "FmodChannelOrder.h"

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

		_class = env->FindClass("de/silveryard/basesystem/sound/FmodChannelOrder");
		_method_getenumvalue = env->GetStaticMethodID(_class, "getEnumValue", "(I)Lde/silveryard/basesystem/sound/FmodChannelOrder;");
		_method_getvalue = env->GetMethodID(_class, "getValue", "()I");
	}
}

jobject fmodchannelorder_get_enum_value(JNIEnv* env, FMOD_CHANNELORDER result) {
	return fmodchannelorder_get_enum_value(env, static_cast<int>(result));
}
jobject fmodchannelorder_get_enum_value(JNIEnv* env, int value) {
	_init(env);
	return env->CallStaticObjectMethod(_class, _method_getenumvalue, value);
}
FMOD_CHANNELORDER fmodchannelorder_get_value(JNIEnv* env, jobject obj) {
	_init(env);
	return static_cast<FMOD_CHANNELORDER>(env->CallIntMethod(obj, _method_getvalue));
}