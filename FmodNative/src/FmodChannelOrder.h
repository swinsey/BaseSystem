#ifndef _FMODNATIVE_FMODCHANNELORDER_H_
#define _FMODNATIVE_FMODCHANNELORDER_H_

#include "jni.h"
#include "fmod.hpp"

jobject fmodchannelorder_get_enum_value(JNIEnv* env, FMOD_CHANNELORDER result);
jobject fmodchannelorder_get_enum_value(JNIEnv* env, int value);
FMOD_CHANNELORDER fmodchannelorder_get_value(JNIEnv* env, jobject obj);

#endif