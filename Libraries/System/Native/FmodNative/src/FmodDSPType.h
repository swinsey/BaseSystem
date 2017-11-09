#ifndef _FMODNATIVE_FMODDSPTYPE_H_
#define _FMODNATIVE_FMODDSPTYPE_H_

#include "jni.h"

jobject fmoddsptype_get_enum_value(JNIEnv* env, int value);
int fmoddsptype_get_value(JNIEnv* env, jobject obj);

#endif