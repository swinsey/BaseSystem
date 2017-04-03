#ifndef _FMODNATIVE_FMODOUTPUTTYPE_H_
#define _FMODNATIVE_FMODOUTPUTTYPE_H_

#include "jni.h"

jobject fmodoutputtype_get_enum_value(JNIEnv* env, int value);
int fmodoutputtype_get_value(JNIEnv* env, jobject obj);

#endif
