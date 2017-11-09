#include <map>
#include "jni/bs_jni.h"

using namespace std;

namespace {
	struct _meta {
		jclass cls;
		jfieldID field_value;
		jmethodID method_ctr_default;
		jmethodID method_ctr_value;
	};

	bool _initialized = false;

	bs::jni::wrapper_handle_t _next_handle = 1;
	map<bs::jni::wrapper_handle_t, _meta> _handles;

	bs::result_t _init(JNIEnv* env) {
		if (!_initialized) {


			_initialized = true;
		}

		return bs::jni::jni_result::SUCCESS;
	}
}

BSEXPORT bs::result_t bs::jni::wrapper_create_handle(JNIEnv* env, const char* class_name, wrapper_handle_t* out_handle) {
	_meta meta;

	meta.cls = env->FindClass(class_name);
	if (meta.cls == NULL) {
		return bs::jni::jni_result::ERROR_WRAPPER_INVALID_CLASSNAME;
	}

	meta.field_value = env->GetFieldID(meta.cls, "value", "Ljava/lang/Object;");
	if (meta.field_value == NULL) {
		return bs::jni::jni_result::ERROR_WRAPPER_NO_WRAPPER_CLASS;
	}

	meta.method_ctr_default = env->GetMethodID(meta.cls, "<init>", "()V");
	if (meta.method_ctr_default == NULL) {
		return bs::jni::jni_result::ERROR_WRAPPER_NO_WRAPPER_CLASS;
	}

	meta.method_ctr_value = env->GetMethodID(meta.cls, "<init>", "(Ljava/lang/Object;)V");
	if (meta.method_ctr_value == NULL) {
		return bs::jni::jni_result::ERROR_WRAPPER_NO_WRAPPER_CLASS;
	}

	*out_handle = _next_handle++;
	_handles.insert(pair<wrapper_handle_t, _meta>(*out_handle, meta));
	return bs::jni::jni_result::SUCCESS;
}
BSEXPORT bs::result_t bs::jni::wrapper_create(JNIEnv* env, wrapper_handle_t handle, jobject* out_instance) {
	if (_handles.find(handle) == _handles.end()) {
		return bs::jni::jni_result::ERROR_WRAPPER_INVALID_HANDLE;
	}

	_meta meta = _handles[handle];
	jobject inst = env->NewObject(meta.cls, meta.method_ctr_default);
	if (inst == NULL) {
		return bs::jni::jni_result::ERROR_UNKNOWN;
	}

	*out_instance = inst;
	return bs::jni::jni_result::SUCCESS;
}
BSEXPORT bs::result_t bs::jni::wrapper_create(JNIEnv* env, wrapper_handle_t handle, jobject value, jobject* out_instance) {
	if (_handles.find(handle) == _handles.end()) {
		return bs::jni::jni_result::ERROR_WRAPPER_INVALID_HANDLE;
	}

	_meta meta = _handles[handle];
	jobject inst = env->NewObject(meta.cls, meta.method_ctr_value, value);
	if (inst == NULL) {
		return bs::jni::jni_result::ERROR_UNKNOWN;
	}

	*out_instance = inst;
	return bs::jni::jni_result::SUCCESS;
}
BSEXPORT bs::result_t bs::jni::wrapper_set_value(JNIEnv* env, wrapper_handle_t handle, jobject wrapper, jobject value) {
	if (_handles.find(handle) == _handles.end()) {
		return bs::jni::jni_result::ERROR_WRAPPER_INVALID_HANDLE;
	}

	_meta meta = _handles[handle];
	env->SetObjectField(wrapper, meta.field_value, value);

	return bs::jni::jni_result::SUCCESS;
}
BSEXPORT bs::result_t bs::jni::wrapper_get_value(JNIEnv* env, wrapper_handle_t handle, jobject wrapper, jobject* out_value) {
	if (_handles.find(handle) == _handles.end()) {
		return bs::jni::jni_result::ERROR_WRAPPER_INVALID_HANDLE;
	}

	_meta meta = _handles[handle];
	jobject value = env->GetObjectField(wrapper, meta.field_value);
	
	*out_value = value;
	return bs::jni::jni_result::SUCCESS;
}