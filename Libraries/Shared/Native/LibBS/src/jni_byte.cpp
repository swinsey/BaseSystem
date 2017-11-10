#include "bs/jni/bs_jni.h"
#include <iostream>

namespace {
	bool _initialized = false;

	jclass _class;
	jmethodID _method_ctr;
	jmethodID _method_byteValue;

	bs::result_t _init(JNIEnv* env) {
		if (!_initialized) {
			_class = env->FindClass("java/lang/Byte");
			if (_class == NULL) {
				return bs::jni::jni_result::ERROR_INIT_FAILED;
			}

			_method_ctr = env->GetMethodID(_class, "<init>", "(B)V");
			if (_method_ctr == NULL) {
				return bs::jni::jni_result::ERROR_INIT_FAILED;
			}

			_method_byteValue = env->GetMethodID(_class, "byteValue", "()B");
			if (_method_byteValue == NULL) {
				return bs::jni::jni_result::ERROR_INIT_FAILED;
			}

			//TODO for now we need to disable this because otherwise create_int returns some other types except int sometimes
			_initialized = false;
		}

		return bs::jni::jni_result::SUCCESS;
	}
}

BSEXPORT bs::result_t bs::jni::byte_create(JNIEnv* env, char value, jobject* out_instance) {
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
BSEXPORT bs::result_t bs::jni::byte_get(JNIEnv* env, jobject boxed, char* out_value) {
	bs::result_t result = _init(env);
	if (result != bs::jni::jni_result::SUCCESS) {
		return result;
	}

	char value = env->CallByteMethod(boxed, _method_byteValue);
	*out_value = value;
	return bs::jni::jni_result::SUCCESS;
}