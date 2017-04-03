#include "Wrapper.h"

namespace {
	bool _initialized = false;
	jclass _class;
	jfieldID _field_value;
	jmethodID _method_ctr_default;
	jmethodID _method_ctr_value;

	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/awesome/smarthome/td/util/Wrapper");
		_field_value = env->GetFieldID(_class, "value", "Ljava/lang/Object;");
		_method_ctr_default = env->GetMethodID(_class, "<init>", "()V");
		_method_ctr_value = env->GetMethodID(_class, "<init>", "(Ljava/lang/Object;)V");
	}
}

void wrapper_set_value(JNIEnv* env, jobject obj, jobject value) {
	_init(env);
	env->SetObjectField(obj, _field_value, value);
}
jobject wrapper_get_value(JNIEnv* env, jobject obj) {
	_init(env);
	return env->GetObjectField(obj, _field_value);
}

jobject wrapper_create(JNIEnv* env) {
	_init(env);
	return env->NewObject(_class, _method_ctr_default);
}
jobject wrapper_create(JNIEnv* env, jobject value) {
	_init(env);
	return env->NewObject(_class, _method_ctr_value, value);
}