#ifndef _FMODNATIVE_FLOAT_H_
#define _FMODNATIVE_FLOAT_H_

#include "jni.h"

jobject float_create(JNIEnv* env, float value);
float float_getvalue(JNIEnv* env, jobject obj);

#endif
