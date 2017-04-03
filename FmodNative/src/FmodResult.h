#ifndef _FMODNATIVE_FMODRESULT_H_
#define _FMODNATIVE_FMODRESULT_H_

#include "jni.h"
#include "fmod.hpp"

jobject fmodresult_get_enum_value(JNIEnv* env, FMOD_RESULT result);
jobject fmodresult_get_enum_value(JNIEnv* env, int value);
int fmodresult_get_value(JNIEnv* env, jobject obj);

#endif
