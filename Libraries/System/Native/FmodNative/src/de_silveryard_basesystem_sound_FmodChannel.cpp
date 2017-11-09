#include "fmod.hpp"
#include "fmod_errors.h"
#include "helper.h"
#include "FmodResult.h"
#include "Boolean.h"
#include "Float.h"
#include "Integer.h"
#include "Wrapper.h"
#include "FmodChannelControl.h"
#include "de_silveryard_basesystem_sound_FmodChannelGroup.h"
#include "de_silveryard_basesystem_sound_FmodChannel.h"

namespace {
	bool _initialized = false;

	jclass _class;
	jfieldID _field_handle;


	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/silveryard/basesystem/sound/FmodChannel");
		_field_handle = env->GetFieldID(_class, "handle", "J");
	}
}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setHandle
(JNIEnv* env, jobject obj, jlong handle){
	_init(env);

	env->SetLongField(obj, _field_handle, handle);
}
JNIEXPORT jlong JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getHandle
(JNIEnv* env, jobject obj) {
	_init(env);

	return env->GetLongField(obj, _field_handle);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_stop
(JNIEnv* env, jobject obj) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD_RESULT result = channel->stop();

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setPaused
(JNIEnv* env, jobject obj, jboolean paused) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD_RESULT result = channel->setPaused(paused);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getPaused
(JNIEnv* env, jobject obj, jobject paused) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	bool native_paused;
	FMOD_RESULT result = channel->getPaused(&native_paused);

	jobject paused_boxed = boolean_create(env, native_paused);
	wrapper_set_value(env, paused, paused_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setVolume
(JNIEnv* env, jobject obj, jfloat volume) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD_RESULT result = channel->setVolume(volume);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getVolume
(JNIEnv* env, jobject obj, jobject volume) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	float native_volume;
	FMOD_RESULT result = channel->getVolume(&native_volume);

	jobject volume_boxed = float_create(env, native_volume);
	wrapper_set_value(env, volume, volume_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setVolumeRamp
(JNIEnv* env, jobject obj, jboolean ramp) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD_RESULT result = channel->setVolumeRamp(ramp);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getVolumeRamp
(JNIEnv* env, jobject obj, jobject ramp) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	bool native_ramp;
	FMOD_RESULT result = channel->getVolumeRamp(&native_ramp);

	jobject ramp_boxed = boolean_create(env, native_ramp);
	wrapper_set_value(env, ramp, ramp_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getAudibility
(JNIEnv* env, jobject obj, jobject audibility) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	float native_audibility;
	FMOD_RESULT result = channel->getAudibility(&native_audibility);

	jobject audibility_boxed = float_create(env, native_audibility);
	wrapper_set_value(env, audibility, audibility_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setPitch
(JNIEnv* env, jobject obj, jfloat pitch) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD_RESULT result = channel->setPitch(pitch);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getPitch
(JNIEnv* env, jobject obj, jobject pitch) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	float native_pitch;
	FMOD_RESULT result = channel->getPitch(&native_pitch);

	jobject pitch_boxed = float_create(env, native_pitch);
	wrapper_set_value(env, pitch, pitch_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setMute
(JNIEnv* env, jobject obj, jboolean mute) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD_RESULT result = channel->setMute(mute);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getMute
(JNIEnv* env, jobject obj, jobject mute) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	bool native_mute;
	FMOD_RESULT result = channel->getMute(&native_mute);

	jobject mute_boxed = boolean_create(env, native_mute);
	wrapper_set_value(env, mute, mute_boxed);
	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_isPlaying
(JNIEnv* env, jobject obj, jobject isPlaying) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	bool is_playing;
	FMOD_RESULT result = channel->isPlaying(&is_playing);

	jobject is_playing_boxed = boolean_create(env, is_playing);
	wrapper_set_value(env, obj, is_playing_boxed);

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setFrequency
(JNIEnv* env, jobject obj, jfloat frequency) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD_RESULT result = channel->setFrequency(frequency);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getFrequency
(JNIEnv* env, jobject obj, jobject frequency) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	float native_frequency;
	FMOD_RESULT result = channel->getFrequency(&native_frequency);

	jobject frequency_boxed = float_create(env, native_frequency);
	wrapper_set_value(env, frequency, frequency_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setPriority
(JNIEnv* env, jobject obj, jint priority) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD_RESULT result = channel->setPriority(priority);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getPriority
(JNIEnv* env, jobject obj, jobject priority) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	int native_priority;
	FMOD_RESULT result = channel->getPriority(&native_priority);

	jobject priority_boxed = integer_create(env, native_priority);
	wrapper_set_value(env, priority, priority_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setPosition
(JNIEnv* env, jobject obj, jint position, jint posType) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD_RESULT result = channel->setPosition(position, posType);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getPosition
(JNIEnv* env, jobject obj, jobject position, jint posType) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	unsigned int native_position;
	FMOD_RESULT result = channel->getPosition(&native_position, posType);

	jobject position_boxed = integer_create(env, native_position);
	wrapper_set_value(env, position, position_boxed);

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setChannelGroup
(JNIEnv* env, jobject obj, jobject channelGroup) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	jlong group_handle = Java_de_silveryard_basesystem_sound_FmodChannelGroup_getHandle(env, channelGroup);
	FMOD::ChannelGroup* native_group = reinterpret_cast<FMOD::ChannelGroup*>(group_handle);
	FMOD_RESULT result = channel->setChannelGroup(native_group);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getChannelGroup
(JNIEnv* env, jobject obj, jobject channelGroup) {
	_init(env);

	FMOD::Channel* channel = get_handle<FMOD::Channel>(env, _field_handle, obj);
	FMOD::ChannelGroup* native_group;
	FMOD_RESULT result = channel->setChannelGroup(native_group);

	Java_de_silveryard_basesystem_sound_FmodChannelGroup_setHandle(env, channelGroup, reinterpret_cast<long long>(native_group));

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getDSP
(JNIEnv* env, jobject obj, jint index, jobject dsp) {
	_init(env);
	return fmodchannelcontrol_getDSP(env, obj, _field_handle, index, dsp);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_addDSP
(JNIEnv* env, jobject obj, jint index, jobject dsp) {
	_init(env);
	return fmodchannelcontrol_addDSP(env, obj, _field_handle, index, dsp);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_removeDSP
(JNIEnv* env, jobject obj, jobject dsp) {
	_init(env);
	return fmodchannelcontrol_removeDSP(env, obj, _field_handle, dsp);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getNumDSPs
(JNIEnv* env, jobject obj, jobject numDSPs) {
	_init(env);
	return fmodchannelcontrol_getNumDSPs(env, obj, _field_handle, numDSPs);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_setDSPIndex
(JNIEnv* env, jobject obj, jobject dsp, jint index) {
	_init(env);
	return fmodchannelcontrol_setDSPIndex(env, obj, _field_handle, dsp, index);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannel_getDSPIndex
(JNIEnv* env, jobject obj, jobject dsp, jobject index) {
	_init(env);
	return fmodchannelcontrol_getDSPIndex(env, obj, _field_handle, dsp, index);
}