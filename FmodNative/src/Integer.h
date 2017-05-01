#ifndef _FMODNATIVE_INTEGER_H_
#define _FMODNATIVE_INTEGER_H_

#include "jni.h"

jobject integer_create(JNIEnv* env, int value);
int integer_getvalue(JNIEnv* env, jobject obj);

#endif
