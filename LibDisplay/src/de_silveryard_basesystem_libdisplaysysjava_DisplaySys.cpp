#if defined(BS_DISPLAY_SYS)

#include <iostream>
#include <jni\bs_jni.h>
#include "display.h"
#include "de_silveryard_basesystem_libdisplaysysjava_DisplaySys.h"

namespace {
	const char* WRAPPER_NAME = "de/silveryard/basesystem/libdisplayjava/Wrapper";

	bool _initialized;

	bs::jni::wrapper_handle_t _wrapper_handle;

	jclass _class_handle;
	jfieldID _field_handle_data1;
	jfieldID _field_handle_data2;
	jfieldID _field_handle_data3;
	jfieldID _field_handle_data4;

	bs::result_t _initialize(JNIEnv* env) {
		if (_initialized) {
			return bs::display::display_result::SUCCESS;
		}

		_class_handle = env->FindClass("de/silveryard/basesystem/libdisplayjava/Handle");
		if (_class_handle == NULL) {
			return bs::display::display_result::ERROR_UNKNOWN;
		}
		_field_handle_data1 = env->GetFieldID(_class_handle, "data1", "J");
		if (_field_handle_data1 == NULL) {
			return bs::display::display_result::ERROR_UNKNOWN;
		}
		_field_handle_data2 = env->GetFieldID(_class_handle, "data2", "J");
		if (_field_handle_data2 == NULL) {
			return bs::display::display_result::ERROR_UNKNOWN;
		}
		_field_handle_data3 = env->GetFieldID(_class_handle, "data3", "J");
		if (_field_handle_data3 == NULL) {
			return bs::display::display_result::ERROR_UNKNOWN;
		}
		_field_handle_data4 = env->GetFieldID(_class_handle, "data4", "J");
		if (_field_handle_data4 == NULL) {
			return bs::display::display_result::ERROR_UNKNOWN;
		}

		bs::result_t result = bs::jni::wrapper_create_handle(env, WRAPPER_NAME, &_wrapper_handle);
		if (bs::is_error(result)) {
			return result;
		}

		return bs::display::display_result::SUCCESS;
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

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplaysysjava_DisplaySys_initNative
(JNIEnv* env, jclass clazz) {
	return _initialize(env);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplaysysjava_DisplaySys_showWindowNative
(JNIEnv* env, jclass clazz, jobject handle) {
	bs::result_t result = _initialize(env);
	if (bs::is_error(result)) {
		return result;
	}

	bs::display::handle_t n_handle;
	_read_handle(env, handle, n_handle);

	return bs::display::sys::show_window(n_handle);
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplaysysjava_DisplaySys_hideWindowNative
(JNIEnv* env, jclass clazz, jobject handle) {
	bs::result_t result = _initialize(env);
	if (bs::is_error(result)) {
		return result;
	}

	bs::display::handle_t n_handle;
	_read_handle(env, handle, n_handle);

	return bs::display::sys::hide_window(n_handle);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplaysysjava_DisplaySys_getWindowLayerNative
(JNIEnv* env, jclass clazz, jobject handle, jobject layer) {
	bs::result_t result = _initialize(env);
	if (bs::is_error(result)) {
		return result;
	}

	bs::display::handle_t n_handle;
	_read_handle(env, handle, n_handle);

	bs::display::sys::layer_t n_layer;
	result = bs::display::sys::get_window_layer(n_handle, &n_layer);
	if (bs::is_error(result)) {
		return result;
	}

	jobject b_layer;
	result = bs::jni::int_create(env, n_layer, &b_layer);
	if (bs::is_error(result)) {
		return result;
	}

	result = bs::jni::wrapper_set_value(env, _wrapper_handle, layer, b_layer);
	if (bs::is_error(result)) {
		return result;
	}

	return bs::display::display_result::SUCCESS;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplaysysjava_DisplaySys_setWindowLayerNative
(JNIEnv* env, jclass clazz, jobject handle, jint layer) {
	bs::result_t result = _initialize(env);
	if (bs::is_error(result)) {
		return result;
	}

	bs::display::handle_t n_handle;
	_read_handle(env, handle, n_handle);

	return bs::display::sys::set_window_layer(n_handle, static_cast<bs::display::sys::layer_t>(layer));
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplaysysjava_DisplaySys_getWindowPositionNative
(JNIEnv* env, jclass clazz, jobject handle, jobject posX, jobject posY) {
	bs::result_t result = _initialize(env);
	if (bs::is_error(result)) {
		return result;
	}

	bs::display::handle_t n_handle;
	_read_handle(env, handle, n_handle);

	int16_t n_pos_x, n_pos_y;
	result = bs::display::sys::get_window_position(n_handle, &n_pos_x, &n_pos_y);
	if (bs::is_error(result)) {
		return result;
	}

	jobject b_pos_x;
	result = bs::jni::int_create(env, n_pos_x, &b_pos_x);
	if (bs::is_error(result)) {
		return result;
	}

	jobject b_pos_y;
	result = bs::jni::int_create(env, n_pos_y, &b_pos_y);
	if (bs::is_error(result)) {
		return result;
	}

	result = bs::jni::wrapper_set_value(env, _wrapper_handle, posX, b_pos_x);
	if (bs::is_error(result)) {
		return result;
	}

	result = bs::jni::wrapper_set_value(env, _wrapper_handle, posY, b_pos_y);
	if (bs::is_error(result)) {
		return result;
	}

	return bs::display::display_result::SUCCESS;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplaysysjava_DisplaySys_setWindowPositionNative
(JNIEnv* env, jclass clazz, jobject handle, jint posX, jint posY) {
	bs::result_t result = _initialize(env);
	if (bs::is_error(result)) {
		return result;
	}

	bs::display::handle_t n_handle;
	_read_handle(env, handle, n_handle);

	return bs::display::sys::set_window_position(n_handle, static_cast<int16_t>(posX), static_cast<int16_t>(posY));
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplaysysjava_DisplaySys_getWindowOpacityNative
(JNIEnv* env, jclass clazz, jobject handle, jobject opacity) {
	bs::result_t result = _initialize(env);
	if (bs::is_error(result)) {
		return result;
	}

	bs::display::handle_t n_handle;
	_read_handle(env, handle, n_handle);

	bs::display::sys::opacity_t n_opacity;
	result = bs::display::sys::get_window_opacity(n_handle, &n_opacity);
	if (bs::is_error(result)) {
		return result;
	}

	jobject b_opacity;
	result = bs::jni::int_create(env, n_opacity, &b_opacity);
	if (bs::is_error(result)) {
		return result;
	}

	result = bs::jni::wrapper_set_value(env, _wrapper_handle, opacity, b_opacity);
	if (bs::is_error(result)) {
		return result;
	}

	return bs::display::display_result::SUCCESS;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_libdisplaysysjava_DisplaySys_setWindowOpacityNative
(JNIEnv* env, jclass clazz, jobject handle, jint opacity) {
	bs::result_t result = _initialize(env);
	if (bs::is_error(result)) {
		return result;
	}

	bs::display::handle_t n_handle;
	_read_handle(env, handle, n_handle);

	return bs::display::sys::set_window_opacity(n_handle, static_cast<bs::display::sys::opacity_t>(opacity));
}

#endif