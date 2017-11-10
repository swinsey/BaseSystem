#include <string>

#include <bs/bs_def.h>
#include <bs/jni/bs_jni.h>
#include <bs/system/logger.h>
#include <bs/dspmngr/manager.h>

#include "de_silveryard_basesystem_gui_DisplayManager.h"

using namespace std;

namespace {
    bs::jni::wrapper_handle_t _wrapper_handle;

    jclass _class_displayhandle;
    jfieldID _field_displayhandle_value1;
}

namespace {
    void managed_to_native_handle(JNIEnv* env, jobject managed, bs::dspmngr::display_handle& native) {
        bs::assert(managed);
        
        native.value1 = env->GetLongField(managed, _field_displayhandle_value1);
    }
    void native_to_managed_handle(JNIEnv* env, jobject managed, const bs::dspmngr::display_handle& native) {
        bs::assert(managed);

        env->SetLongField(managed, _field_displayhandle_value1, native.value1);
    }
}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_initNative
  (JNIEnv* env, jclass clazz) {
    bs::result_t result = bs::jni::wrapper_create_handle(env, "de/silveryard/basesystem/util/Wrapper", &_wrapper_handle);
    bs::assert(bs::is_success(result));

    _class_displayhandle = env->FindClass("de/silveryard/basesystem/gui/DisplayHandle");
    bs::assert(_class_displayhandle);
    _field_displayhandle_value1 = env->GetFieldID(_class_displayhandle, "value1", "J");
    bs::assert(_field_displayhandle_value1);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeGetProcessHandle
(JNIEnv* env, jobject obj, jlong processId, jobject processHandle) {

    bs::assert(processHandle);

    bs::dspmngr::process_id_t native_process_id = static_cast<bs::dspmngr::process_id_t>(processId);
    bs::dspmngr::display_handle native_handle;
    bs::result_t result = bs::dspmngr::get_process_handle(native_process_id, native_handle);

    native_to_managed_handle(env, processHandle, native_handle);

    return result;
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeRegisterHandle
  (JNIEnv* env, jobject obj, jobject handle) {

    bs::assert(handle);

    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bs::result_t result = bs::dspmngr::register_display_handle(native_handle);
    return static_cast<jint>(result);
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeUnregisterHandle
  (JNIEnv* env, jobject obj, jobject handle) {

    bs::assert(handle);

    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bs::result_t result = bs::dspmngr::unregister_display_handle(native_handle);
    return static_cast<jint>(result);
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeSetWindowPosition
  (JNIEnv* env, jobject obj, jobject handle, jint x, jint y) {

    bs::assert(handle);
   
    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bs::dspmngr::pos_t native_x = static_cast<bs::dspmngr::pos_t>(x);
    bs::dspmngr::pos_t native_y = static_cast<bs::dspmngr::pos_t>(y);

    bs::result_t result = bs::dspmngr::set_window_position(native_handle, native_x, native_y);
    return static_cast<jint>(result);
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeGetWindowPosition
  (JNIEnv* env, jobject obj, jobject handle, jobject outX, jobject outY) {

    bs::assert(handle);
    bs::assert(outX);
    bs::assert(outY);

    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bs::dspmngr::pos_t native_x;
    bs::dspmngr::pos_t native_y;
    bs::result_t result = bs::dspmngr::get_window_position(native_handle, &native_x, &native_y);

    jobject boxed_x;
    bs::result_t result_boxed = bs::jni::int_create(env, static_cast<int>(native_x), &boxed_x);
    bs::assert(bs::is_success(result_boxed));
    result_boxed = bs::jni::wrapper_set_value(env, _wrapper_handle, outX, boxed_x);
    bs::assert(bs::is_success(result_boxed));

    jobject boxed_y;
    result_boxed = bs::jni::int_create(env, static_cast<int>(native_y), &boxed_y);
    bs::assert(bs::is_success(result_boxed));
    result_boxed = bs::jni::wrapper_set_value(env, _wrapper_handle, outY, boxed_y);
    bs::assert(bs::is_success(result_boxed));

    return static_cast<bs::result_t>(result);
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeSetWindowAlpha
  (JNIEnv* env, jobject obj, jobject handle, jbyte alpha) {
    
    bs::assert(handle);

    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bs::dspmngr::alpha_t native_alpha = static_cast<bs::dspmngr::alpha_t>(alpha);

    bs::result_t result = bs::dspmngr::set_window_alpha(native_handle, native_alpha);
    return static_cast<jint>(result);
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeGetWindowAlpha
  (JNIEnv* env, jobject obj, jobject handle, jobject outAlpha) {
   
    bs::assert(handle);
    bs::assert(outAlpha);

    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bs::dspmngr::alpha_t native_alpha;
    bs::result_t result = bs::dspmngr::get_window_alpha(native_handle, &native_alpha);

    jobject boxed_alpha;
    bs::result_t result_boxed = bs::jni::byte_create(env, static_cast<char>(native_alpha), &boxed_alpha);
    bs::assert(bs::is_success(result_boxed));
    result_boxed = bs::jni::wrapper_set_value(env, _wrapper_handle, outAlpha, boxed_alpha);
    bs::assert(bs::is_success(result_boxed));

    return result;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeSetWindowActive
  (JNIEnv* env, jobject obj, jobject handle, jboolean active) {

    bs::assert(handle);

    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bool native_active = static_cast<bool>(active);

    bs::result_t result = bs::dspmngr::set_window_active(native_handle, native_active);
    return result;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeGetWindowActive
  (JNIEnv* env, jobject obj, jobject handle, jobject outActive) {

    bs::assert(handle);
    bs::assert(outActive);

    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bool native_active;
    bs::result_t result = bs::dspmngr::get_window_active(native_handle, &native_active);

    jobject boxed_active;
    bs::result_t result_boxed = bs::jni::bool_create(env, native_active, &boxed_active);
    bs::assert(bs::is_success(result_boxed));
    result_boxed = bs::jni::wrapper_set_value(env, _wrapper_handle, outActive, boxed_active);
    bs::assert(bs::is_success(result_boxed));

    return result;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeSetWindowLayer
  (JNIEnv* env, jobject obj, jobject handle, jbyte layer) {

    bs::assert(handle);

    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bs::dspmngr::layer_t native_layer = static_cast<bs::dspmngr::layer_t>(layer);

    bs::result_t result = bs::dspmngr::set_window_layer(native_handle, native_layer);
    return result;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_DisplayManager_nativeGetWindowLayer
  (JNIEnv* env, jobject obj, jobject handle, jobject outLayer){
    
    bs::assert(handle);
    bs::assert(outLayer);

    bs::dspmngr::display_handle native_handle;
    managed_to_native_handle(env, handle, native_handle);

    bs::dspmngr::layer_t native_layer;
    bs::result_t result = bs::dspmngr::get_window_layer(native_handle, &native_layer);

    jobject boxed_layer;
    bs::result_t result_boxed = bs::jni::byte_create(env, static_cast<char>(native_layer), &boxed_layer);
    bs::assert(bs::is_success(result_boxed));
    result_boxed = bs::jni::wrapper_set_value(env, _wrapper_handle, outLayer, boxed_layer);
    bs::assert(bs::is_success(result_boxed));

    return result;
}
