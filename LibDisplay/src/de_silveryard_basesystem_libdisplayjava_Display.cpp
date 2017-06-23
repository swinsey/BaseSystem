#include "de_silveryard_basesystem_libdisplayjava_Display.h"
#include "display.h"
#include <iostream>

namespace {
	bool _is_initialized = false;

	jclass _class_handle;
	jfieldID _field_handle_data1;
	jfieldID _field_handle_data2;
	jfieldID _field_handle_data3;
	jfieldID _field_handle_data4;

	void _initialize(JNIEnv* env) {
		if (_is_initialized) {
			return;
		}
		_is_initialized = true;

		_class_handle = env->FindClass("de/silveryard/basesystem/libdisplayjava/Handle");
		_field_handle_data1 = env->GetFieldID(_class_handle, "data1", "J");
		_field_handle_data2 = env->GetFieldID(_class_handle, "data2", "J");
		_field_handle_data3 = env->GetFieldID(_class_handle, "data3", "J");
		_field_handle_data4 = env->GetFieldID(_class_handle, "data4", "J");
	}
	void _read_handle(JNIEnv* env, jobject obj, bs::display::handle_t& native_handle) {
		native_handle.data1 = env->GetLongField(obj, _field_handle_data1);
		native_handle.data2 = env->GetLongField(obj, _field_handle_data2);
		native_handle.data3 = env->GetLongField(obj, _field_handle_data3);
		native_handle.data4 = env->GetLongField(obj, _field_handle_data4);
	}
	void _write_handle(JNIEnv* env, jobject obj, const bs::display::handle_t& native_handle) {
		env->SetLongField(obj, _field_handle_data1, native_handle.data1);
		env->SetLongField(obj, _field_handle_data2, native_handle.data2);
		env->SetLongField(obj, _field_handle_data3, native_handle.data3);
		env->SetLongField(obj, _field_handle_data4, native_handle.data4);
	}
}

JNIEXPORT jboolean JNICALL Java_de_silveryard_basesystem_libdisplayjava_Display_compareHandlesNative
(JNIEnv* env, jclass clazz, jobject h1, jobject h2) {
	_initialize(env);

	bs::display::handle_t n_h1;
	bs::display::handle_t n_h2;

	_read_handle(env, h1, n_h1);
	_read_handle(env, h2, n_h2);

	return bs::display::cmp_handles(n_h1, n_h2);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplayjava_Display_getHandleNative
(JNIEnv* env, jclass clazz, jobject handle) {
	_initialize(env);

	bs::display::handle_t n_handle;
	bs::result_t result = bs::display::get_handle(&n_handle);

	_write_handle(env, handle, n_handle);
	return result;
}

JNIEXPORT jboolean JNICALL Java_de_silveryard_basesystem_libdisplayjava_Display_isSuccessNative
(JNIEnv* env, jclass clazz, jint result) {
	_initialize(env);

	return bs::is_success(result);
}

JNIEXPORT jboolean JNICALL Java_de_silveryard_basesystem_libdisplayjava_Display_isErrorNative
(JNIEnv* env, jclass clazz, jint result) {
	_initialize(env);

	return bs::is_error(result);
}

