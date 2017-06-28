#include <jni.h>
#include "../bs_def.h"

namespace bs {
	namespace jni {
		const bs::scope_t scope = bs::SCOPE_MIXED;
		const bs::library_t library = bs::LIBRARY_BS;
		const bs::subset_t subset = bs::SUBSET_BS_JNI;

		typedef uint16_t wrapper_handle_t;

		enum jni_result {
			SUCCESS = create_result(true, scope, library, subset, 0),

			ERROR_UNKNOWN = create_result(false, scope, library, subset, 1),
			ERROR_INIT_FAILED = create_result(false, scope, library, subset, 2),

			//Wrapper
			ERROR_WRAPPER_INVALID_CLASSNAME = create_result(false, scope, library, subset, 10),
			ERROR_WRAPPER_NO_WRAPPER_CLASS = create_result(false, scope, library, subset, 11),
			ERROR_WRAPPER_INVALID_HANDLE = create_result(false, scope, library, subset, 12),
		};

		BSEXPORT result_t bool_create(JNIEnv* env, bool value, jobject* out_instance);
		BSEXPORT result_t bool_get(JNIEnv* env, jobject boxed, bool* out_value);

		BSEXPORT result_t float_create(JNIEnv* env, float value, jobject* out_instance);
		BSEXPORT result_t float_get(JNIEnv* env, jobject boxed, float* out_value);

		BSEXPORT result_t int_create(JNIEnv* env, int value, jobject* out_instance);
		BSEXPORT result_t int_get(JNIEnv* env, jobject boxed, int* out_value);

		BSEXPORT result_t wrapper_create_handle(JNIEnv* env, const char* class_name, wrapper_handle_t* out_handle);
		BSEXPORT result_t wrapper_create(JNIEnv* env, wrapper_handle_t handle, jobject* out_instance);
		BSEXPORT result_t wrapper_create(JNIEnv* env, wrapper_handle_t handle, jobject value, jobject* out_instance);
		BSEXPORT result_t wrapper_set_value(JNIEnv* env, wrapper_handle_t handle, jobject wrapper, jobject value);
		BSEXPORT result_t wrapper_get_value(JNIEnv* env, wrapper_handle_t handle, jobject wrapper, jobject* out_value);
	}
}