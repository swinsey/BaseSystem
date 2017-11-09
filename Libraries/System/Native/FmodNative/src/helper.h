#ifndef _FMODNATIVE_HELPER_H_
#define _FMODNATIVE_HELPER_H_

#include "jni.h"
#include "fmod.hpp"

template <typename T>
T* get_handle(JNIEnv* env, jfieldID handleField, jobject obj) {
	jlong handle = env->GetLongField(obj, handleField);
	return reinterpret_cast<T *>(handle);
}

template <typename T>
void set_handle(JNIEnv* env, jfieldID handleField, jobject obj, T* t) {
	jlong handle = reinterpret_cast<jlong>(t);
	env->SetLongField(obj, handleField, handle);
}

template <typename T>
jobject create_handle_obj(JNIEnv* env, jclass clazz, jmethodID ctr, T* t) {
	jlong handle = reinterpret_cast<jlong>(t);
	jobject obj = env->NewObject(clazz, ctr, handle);
	return obj;
}

const char* guid_to_string(FMOD_GUID guid);

#endif
