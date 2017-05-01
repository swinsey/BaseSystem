#ifndef _FMODNATIVE_BOOLEAN_H_
#define _FMODNATIVE_BOOLEAN_H_

#include "jni.h"

jobject boolean_create(JNIEnv* env, bool value);
bool boolean_getvalue(JNIEnv* env, jobject obj);

#endif
