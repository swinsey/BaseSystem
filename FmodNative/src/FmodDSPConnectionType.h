#ifndef _FMODNATIVE_FMODDSPCONNECTIONTYPE_H_
#define _FMODNATIVE_FMODDSPCONNECTIONTYPE_H_

#include "jni.h"

jobject fmoddspconnectiontype_get_enum_value(JNIEnv* env, int value);
int fmoddspconnectiontype_get_value(JNIEnv* env, jobject obj);

#endif