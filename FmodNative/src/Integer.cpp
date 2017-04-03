#include "Integer.h"

namespace {
	bool _initialized = false;
	jclass _class;
	jmethodID _method_ctr;
	jmethodID _method_intValue;

	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		//_initialized = true;

		_class = env->FindClass("java/lang/Integer");
		_method_ctr = env->GetMethodID(_class, "<init>", "(I)V");
		_method_intValue = env->GetMethodID(_class, "intValue", "()I");
	}
}

jobject integer_create(JNIEnv* env, int value) {
	_init(env);

	return env->NewObject(_class, _method_ctr, value);
}
int integer_getvalue(JNIEnv* env, jobject obj) {
	return env->CallIntMethod(obj, _method_intValue);
}