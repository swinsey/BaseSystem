#include "Boolean.h"

namespace {
	bool _initialized = false;
	jclass _class;
	jmethodID _method_ctr;
	jmethodID _method_booleanValue;

	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		//_initialized = true;

		_class = env->FindClass("java/lang/Boolean");
		_method_ctr = env->GetMethodID(_class, "<init>", "(Z)V");
		_method_booleanValue = env->GetMethodID(_class, "booleanValue", "()Z");
	}
}

jobject boolean_create(JNIEnv* env, bool value) {
	_init(env);

	return env->NewObject(_class, _method_ctr, value);
}
bool boolean_getvalue(JNIEnv* env, jobject obj) {
	_init(env);

	return env->CallBooleanMethod(obj, _method_booleanValue);
}