#ifndef _FMODNATIVE_FMODSOUNDTYPE_H_
#define _FMODNATIVE_FMODSOUNDTYPE_H_

#include "jni.h"
#include "fmod.hpp"

jobject fmodsoundtype_get_enum_value(JNIEnv* env, FMOD_SOUND_TYPE result);
jobject fmodsoundtype_get_enum_value(JNIEnv* env, int value);
FMOD_SOUND_TYPE fmodsoundtype_get_value(JNIEnv* env, jobject obj);

#endif