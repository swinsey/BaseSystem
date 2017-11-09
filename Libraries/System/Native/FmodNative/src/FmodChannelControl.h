#ifndef _FMODNATIVE_FMODCHANNELCONTROL_H_
#define _FMODNATIVE_FMODCHANNELCONTROL_H_

#include <jni.h>

jobject fmodchannelcontrol_getDSP(JNIEnv* env, jobject obj, jfieldID handle, jint index, jobject dsp);
jobject fmodchannelcontrol_addDSP(JNIEnv* env, jobject obj, jfieldID handle, jint index, jobject dsp);
jobject fmodchannelcontrol_removeDSP(JNIEnv* env, jobject obj, jfieldID handle, jobject dsp);
jobject fmodchannelcontrol_getNumDSPs(JNIEnv* env, jobject obj, jfieldID handle, jobject numDSPs);
jobject fmodchannelcontrol_setDSPIndex(JNIEnv* env, jobject obj, jfieldID handle, jobject dsp, jint index);
jobject fmodchannelcontrol_getDSPIndex(JNIEnv* env, jobject obj, jfieldID handle, jobject dsp, jobject index);

#endif