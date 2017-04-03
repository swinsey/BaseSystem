#ifndef _FMODNATIVE_WRAPPER_H_
#define _FMODNATIVE_WRAPPER_H_

#include "jni.h"

void wrapper_set_value(JNIEnv* env, jobject obj, jobject value);
jobject wrapper_get_value(JNIEnv* env, jobject obj);

jobject wrapper_create(JNIEnv* env);
jobject wrapper_create(JNIEnv* env, jobject value);

#endif
