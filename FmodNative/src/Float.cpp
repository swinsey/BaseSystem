#include "Float.h"

namespace {
	bool _initialized = false;
	jclass _class;
	jmethodID _method_ctr;
	jmethodID _method_floatValue;

	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		//_initialized = true;

		_class = env->FindClass("java/lang/Float");
		_method_ctr = env->GetMethodID(_class, "<init>", "(F)V");
		_method_floatValue = env->GetMethodID(_class, "floatValue", "()F");
	}
}

jobject float_create(JNIEnv* env, float value) {
	_init(env);

	return env->NewObject(_class, _method_ctr, value);
}
float float_getvalue(JNIEnv* env, jobject obj) {
	return env->CallFloatMethod(obj, _method_floatValue);
}