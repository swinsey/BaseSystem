//
// Created by Beppo on 01/02/17.
//

#include "de_silveryard_basesystem_gui_SDLWindow.h"
#include "../window.h"
#include "../texture.h"
#include "../label.h"

namespace{
    const char* SIG_INT = "I";
    const char* SIG_FLOAT = "F";
    const char* SIG_LONG = "J";

    jclass class_eventtypes;
    jclass class_event;

    jfieldID field_eventtypes_sdl_quit;
    jfieldID field_eventtypes_sdl_mousemotion;
    jfieldID field_eventtypes_sdl_mousebuttondown;
    jfieldID field_eventtypes_sdl_mousebuttonup;
    jfieldID field_eventtypes_sdl_fingermotion;
    jfieldID field_eventtypes_sdl_fingerdown;
    jfieldID field_eventtypes_sdl_fingerup;

    jfieldID field_event_type;
    jfieldID field_event_tfinger_fingerid;
    jfieldID field_event_tfinger_x;
    jfieldID field_event_tfinger_y;
    jfieldID field_event_tfinger_dx;
    jfieldID field_event_tfinger_dy;
    jfieldID field_event_tfinger_pressure;

    bool try_get_class(JNIEnv* env, const char* cls, jclass* field){
        (*field) = env->FindClass(cls);
        return (*field) != NULL;
    }
    bool try_get_field(JNIEnv* env, jclass clazz, const char* name, const char* sig, jfieldID* field){
        (*field) = env->GetStaticFieldID(clazz, name, sig);
        return (*field) != NULL;
    }

    bool init_classes(JNIEnv* env){
        if(!try_get_class(env, "de/silveryard/basesystem/gui/SDLEventTypes", &class_eventtypes)) return false;
        if(!try_get_class(env, "de/silveryard/basesystem/gui/SDLEvent", &class_event)) return false;
        return true;
    }
    bool init_fields(JNIEnv* env){
        if(!try_get_field(env, class_eventtypes, "SDL_QUIT", SIG_INT, &field_eventtypes_sdl_quit)) return false;
        if(!try_get_field(env, class_eventtypes, "SDL_MOUSEMOTION", SIG_INT, &field_eventtypes_sdl_mousemotion)) return false;
        if(!try_get_field(env, class_eventtypes, "SDL_MOUSEBUTTONDOWN", SIG_INT, &field_eventtypes_sdl_mousebuttondown)) return false;
        if(!try_get_field(env, class_eventtypes, "SDL_MOUSEBUTTONUP", SIG_INT, &field_eventtypes_sdl_mousebuttonup)) return false;
        if(!try_get_field(env, class_eventtypes, "SDL_FINGERMOTION", SIG_INT, &field_eventtypes_sdl_fingermotion)) return false;
        if(!try_get_field(env, class_eventtypes, "SDL_FINGERDOWN", SIG_INT, &field_eventtypes_sdl_fingerdown)) return false;
        if(!try_get_field(env, class_eventtypes, "SDL_FINGERUP", SIG_INT, &field_eventtypes_sdl_fingerup)) return false;

        if(!try_get_field(env, class_event, "TYPE", SIG_INT, &field_event_type)) return false;
        if(!try_get_field(env, class_event, "TFINGER_FINGERID", SIG_LONG, &field_event_tfinger_fingerid)) return false;
        if(!try_get_field(env, class_event, "TFINGER_X", SIG_FLOAT, &field_event_tfinger_x)) return false;
        if(!try_get_field(env, class_event, "TFINGER_Y", SIG_FLOAT, &field_event_tfinger_y)) return false;
        if(!try_get_field(env, class_event, "TFINGER_DX", SIG_FLOAT, &field_event_tfinger_dx)) return false;
        if(!try_get_field(env, class_event, "TFINGER_DY", SIG_FLOAT, &field_event_tfinger_dy)) return false;
        if(!try_get_field(env, class_event, "TFINGER_PRESSURE", SIG_FLOAT, &field_event_tfinger_pressure)) return false;
        return true;
    }
    bool init_settypes(JNIEnv* env){
        env->SetStaticIntField(class_eventtypes, field_eventtypes_sdl_quit, SDL_QUIT);

        env->SetStaticIntField(class_eventtypes, field_eventtypes_sdl_mousemotion, SDL_MOUSEMOTION);
        env->SetStaticIntField(class_eventtypes, field_eventtypes_sdl_mousebuttondown, SDL_MOUSEBUTTONDOWN);
        env->SetStaticIntField(class_eventtypes, field_eventtypes_sdl_mousebuttonup, SDL_MOUSEBUTTONUP);

        env->SetStaticIntField(class_eventtypes, field_eventtypes_sdl_fingermotion, SDL_FINGERMOTION);
        env->SetStaticIntField(class_eventtypes, field_eventtypes_sdl_fingerdown, SDL_FINGERDOWN);
        env->SetStaticIntField(class_eventtypes, field_eventtypes_sdl_fingerup, SDL_FINGERUP);
        return true;
    }
    bool init_jni(JNIEnv* env){
        if(!init_classes(env)) return false;
        if(!init_fields(env)) return false;
        if(!init_settypes(env)) return false;
        return true;
    }

    void set_event(JNIEnv* env, SDL_Event* e){
        env->SetStaticIntField(class_event, field_event_type, e->type);

        env->SetStaticLongField(class_event, field_event_tfinger_fingerid, e->tfinger.fingerId);
        env->SetStaticFloatField(class_event, field_event_tfinger_x, e->tfinger.x);
        env->SetStaticFloatField(class_event, field_event_tfinger_y, e->tfinger.y);
        env->SetStaticFloatField(class_event, field_event_tfinger_dx, e->tfinger.dx);
        env->SetStaticFloatField(class_event, field_event_tfinger_dy, e->tfinger.dy);
        env->SetStaticFloatField(class_event, field_event_tfinger_pressure, e->tfinger.pressure);
    }
};

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowInit
        (JNIEnv* env, jclass obj, jstring title, jint width, jint height, jboolean show_cursor){
    if(!init_jni(env)){
        return -1;
    }

    const char* native_title = env->GetStringUTFChars(title, 0);
    int result = window_init(native_title, width, height, show_cursor);
    env->ReleaseStringUTFChars(title, native_title);
    return result;
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDispose
        (JNIEnv* env, jclass obj){
    return window_dispose();
}
JNIEXPORT jboolean JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowIsInitialized
        (JNIEnv* env, jclass obj){
    return window_is_initialized() ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowGetWidth
        (JNIEnv* env, jclass obj){
    return window_get_width();
}
JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowGetHeight
        (JNIEnv* env, jclass obj){
    return window_get_height();
}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowUpdateScreen
        (JNIEnv* env, jclass obj){
    window_update_screen();
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowSetDrawColor
        (JNIEnv* env, jclass obj, jbyte r, jbyte g, jbyte b, jbyte a){
    window_set_draw_color(r, g, b, a);
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowClear
        (JNIEnv* env, jclass obj){
    window_clear();
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDrawRect
        (JNIEnv* env, jclass obj,
         jint x, jint y, jint width, jint height,
         jboolean fill){
    Rect r;
    r.x = x;
    r.y = y;
    r.width = width;
    r.height = height;
    window_draw_rect(&r, fill == JNI_TRUE);
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDrawLine
        (JNIEnv* env, jclass obj, jint x, jint y, jint x2, jint y2){
    window_draw_line(x, y, x2, y2);
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDrawTexture
(JNIEnv* env, jclass obj, jint tex,
	jint srcX, jint srcY, jint srcWidth, jint srcHeight,
	jint dstX, jint dstY, jint dstWidth, jint dstHeight,
	jdouble angle) {
	SDL_Texture* texture;
	int res = texture_get(tex, &texture);
	if (res < 0) {
		//TODO return error code
		return;
	}

	Rect src_r;
	src_r.x = srcX;
	src_r.y = srcY;
	src_r.width = srcWidth;
	src_r.height = srcHeight;

	Rect dst_r;
	dst_r.x = dstX;
	dst_r.y = dstY;
	dst_r.width = dstWidth;
	dst_r.height = dstHeight;

	window_draw_texture(texture, &src_r, &dst_r, angle);
}
JNIEXPORT void JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowDrawLabel
        (JNIEnv* env, jclass obj, jint label, jint x, jint y){
    SDL_Texture* texture;
    int res = label_get_texture(label, &texture);
    if(res != LABEL_OK){
        //TODO return error code
        return;
    }

    int w = label_get_width(label);
    int h = label_get_height(label);

    if(w < 0 || h < 0){
        //TODO return error code
        return;
    }

    Rect src_r;
    src_r.x = 0;
    src_r.y = 0;
    src_r.width = w;
    src_r.height = h;

    Rect dst_r;
    dst_r.x = x;
    dst_r.y = y;
    dst_r.width = w;
    dst_r.height = h;

    window_draw_texture(texture, &src_r, &dst_r);
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowPollEvent
        (JNIEnv* env, jclass clazz){
    SDL_Event e;
    if(window_poll_event(&e)){
        set_event(env, &e);
        return 1;
    }else{
        return 0;
    }
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowGetMouseX
(JNIEnv* env, jclass clazz) {
	int x, y;
	window_get_mouse(&x, &y);
	return x;
}

JNIEXPORT jint JNICALL Java_de_silveryard_basesystem_gui_SDLWindow_windowGetMouseY
(JNIEnv* env, jclass clazz) {
	int x, y;
	window_get_mouse(&x, &y);
	return y;
}