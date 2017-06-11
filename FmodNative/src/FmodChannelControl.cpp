#include "helper.h"
#include "FmodResult.h"
#include "Integer.h"
#include "Wrapper.h"
#include "de_silveryard_basesystem_sound_FmodDSP.h"
#include "FmodChannelControl.h"

jobject fmodchannelcontrol_getDSP(JNIEnv* env, jobject obj, jfieldID handle, jint index, jobject dsp) {
	FMOD::ChannelControl* control = get_handle<FMOD::ChannelControl>(env, handle, obj);
	FMOD::DSP* native_dsp;
	FMOD_RESULT result = control->getDSP(index, &native_dsp);

	Java_de_silveryard_basesystem_sound_FmodDSP_setHandle(env, dsp, reinterpret_cast<long long>(native_dsp));

	return fmodresult_get_enum_value(env, result);
}
jobject fmodchannelcontrol_addDSP(JNIEnv* env, jobject obj, jfieldID handle, jint index, jobject dsp) {
	FMOD::ChannelControl* control = get_handle<FMOD::ChannelControl>(env, handle, obj);
	long long dsp_handle = Java_de_silveryard_basesystem_sound_FmodDSP_getHandle(env, dsp);
	FMOD::DSP* native_dsp = reinterpret_cast<FMOD::DSP*>(dsp_handle);
	FMOD_RESULT result = control->addDSP(index, native_dsp);

	return fmodresult_get_enum_value(env, result);
}
jobject fmodchannelcontrol_removeDSP(JNIEnv* env, jobject obj, jfieldID handle, jobject dsp) {
	FMOD::ChannelControl* control = get_handle<FMOD::ChannelControl>(env, handle, obj);
	long long dsp_handle = Java_de_silveryard_basesystem_sound_FmodDSP_getHandle(env, dsp);
	FMOD::DSP* native_dsp = reinterpret_cast<FMOD::DSP*>(dsp_handle);
	FMOD_RESULT result = control->removeDSP(native_dsp);

	return fmodresult_get_enum_value(env, result);
}
jobject fmodchannelcontrol_getNumDSPs(JNIEnv* env, jobject obj, jfieldID handle, jobject numDSPs) {
	FMOD::ChannelControl* control = get_handle<FMOD::ChannelControl>(env, handle, obj);
	int native_numdsps;
	FMOD_RESULT result = control->getNumDSPs(&native_numdsps);

	jobject numdsps_boxed = integer_create(env, native_numdsps);
	wrapper_set_value(env, numDSPs, numdsps_boxed);

	return fmodresult_get_enum_value(env, result);
}
jobject fmodchannelcontrol_setDSPIndex(JNIEnv* env, jobject obj, jfieldID handle, jobject dsp, jint index) {
	FMOD::ChannelControl* control = get_handle<FMOD::ChannelControl>(env, handle, obj);
	long long dsp_handle = Java_de_silveryard_basesystem_sound_FmodDSP_getHandle(env, dsp);
	FMOD::DSP* native_dsp = reinterpret_cast<FMOD::DSP*>(dsp_handle);
	FMOD_RESULT result = control->setDSPIndex(native_dsp, index);

	return fmodresult_get_enum_value(env, result);
}
jobject fmodchannelcontrol_getDSPIndex(JNIEnv* env, jobject obj, jfieldID handle, jobject dsp, jobject index) {
	FMOD::ChannelControl* control = get_handle<FMOD::ChannelControl>(env, handle, obj);
	long long dsp_handle = Java_de_silveryard_basesystem_sound_FmodDSP_getHandle(env, dsp);
	FMOD::DSP* native_dsp = reinterpret_cast<FMOD::DSP*>(dsp_handle);
	int native_index;
	FMOD_RESULT result = control->getDSPIndex(native_dsp, &native_index);

	jobject index_boxed = integer_create(env, native_index);
	wrapper_set_value(env, index, index_boxed);

	return fmodresult_get_enum_value(env, result);
}