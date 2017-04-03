#ifndef _FMODNATIVE_FMODSOUNDFORMAT_H_
#define _FMODNATIVE_FMODSOUNDFORMAT_H_

#include "jni.h"
#include "fmod.hpp"

jobject fmodsoundformat_get_enum_value(JNIEnv* env, FMOD_SOUND_FORMAT result);
jobject fmodsoundformat_get_enum_value(JNIEnv* env, int value);
FMOD_SOUND_FORMAT fmodsoundformat_get_value(JNIEnv* env, jobject obj);

#endif
