#ifndef _FMODNATIVE_FMODSPEAKERMODEE_H_
#define _FMODNATIVE_FMODSPEAKERMODE_H_

#include "jni.h"

jobject fmodspeakermode_get_enum_value(JNIEnv* env, int value);
int fmodspeakermode_get_value(JNIEnv* env, jobject obj);

#endif
