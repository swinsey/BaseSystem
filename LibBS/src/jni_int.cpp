#include "jni/bs_jni.h"
#include <iostream>

namespace {
	bool _initialized = false;

	jclass _class;
	jmethodID _method_ctr;
	jmethodID _method_intValue;

	bs::result_t _init(JNIEnv* env) {
		if (!_initialized) {
			_class = env->FindClass("java/lang/Integer");
			if (_class == NULL) {
				return bs::jni::jni_result::ERROR_INIT_FAILED;
			}

			_method_ctr = env->GetMethodID(_class, "<init>", "(I)V");
			if (_method_ctr == NULL) {
				return bs::jni::jni_result::ERROR_INIT_FAILED;
			}

			_method_intValue = env->GetMethodID(_class, "intValue", "()I");
			if (_method_ctr == NULL) {
				return bs::jni::jni_result::ERROR_INIT_FAILED;
			}

			//TODO for now we need to disable this because otherwise create_int returns some other types except int sometimes
			_initialized = false;
		}

		return bs::jni::jni_result::SUCCESS;
	}
}

BSEXPORT bs::result_t bs::jni::int_create(JNIEnv* env, int value, jobject* out_instance) {
	bs::result_t result = _init(env);
	if (result != bs::jni::jni_result::SUCCESS) {
		return result;
	}

	jobject obj = env->NewObject(_class, _method_ctr, value);
	if (obj == NULL) {
		return bs::jni::jni_result::ERROR_UNKNOWN;
	}

	*out_instance = obj;
	return bs::jni::jni_result::SUCCESS;
}
BSEXPORT bs::result_t bs::jni::int_get(JNIEnv* env, jobject boxed, int* out_value) {
	bs::result_t result = _init(env);
	if (result != bs::jni::jni_result::SUCCESS) {
		return result;
	}

	int value = env->CallIntMethod(boxed, _method_intValue);
	*out_value = value;
	return bs::jni::jni_result::SUCCESS;
}