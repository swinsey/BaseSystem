#include "helper.h"
#include "Wrapper.h"
#include "Integer.h"
#include "FmodResult.h"
#include "FmodChannelControl.h"
#include "de_silveryard_basesystem_sound_FmodChannel.h"
#include "de_silveryard_basesystem_sound_FmodDSPConnection.h"
#include "de_silveryard_basesystem_sound_FmodChannelGroup.h"

namespace {
	bool _initialized = false;

	jclass _class;
	jfieldID _field_handle;


	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/silveryard/basesystem/sound/FmodChannelGroup");
		_field_handle = env->GetFieldID(_class, "handle", "J");
	}
}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_setHandle
(JNIEnv* env, jobject obj, jlong handle) {
	_init(env);

	env->SetLongField(obj, _field_handle, handle);
}

JNIEXPORT jlong JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getHandle
(JNIEnv* env, jobject obj) {
	_init(env);

	return env->GetLongField(obj, _field_handle);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_addGroup
(JNIEnv* env, jobject obj, jobject group, jboolean propagatedSpClock, jobject connection) {
	_init(env);

	FMOD::ChannelGroup* chgroup = get_handle<FMOD::ChannelGroup>(env, _field_handle, obj);
	long long group_handle = Java_de_silveryard_basesystem_sound_FmodChannelGroup_getHandle(env, group);
	FMOD::ChannelGroup* native_group = reinterpret_cast<FMOD::ChannelGroup*>(group_handle);
	FMOD::DSPConnection* native_connection;
	FMOD_RESULT result = chgroup->addGroup(native_group, propagatedSpClock, &native_connection);

	if (connection != NULL) {
		Java_de_silveryard_basesystem_sound_FmodDSPConnection_setHandle(env, connection, reinterpret_cast<long long>(native_connection));
	}

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getNumGroups
(JNIEnv* env, jobject obj, jobject numGroups) {
	_init(env);

	FMOD::ChannelGroup* chgroup = get_handle<FMOD::ChannelGroup>(env, _field_handle, obj);
	int native_numgroups;
	FMOD_RESULT result = chgroup->getNumGroups(&native_numgroups);

	jobject numgroups_wrapped = integer_create(env, native_numgroups);
	wrapper_set_value(env, numGroups, numgroups_wrapped);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getGroup
(JNIEnv* env, jobject obj, jint index, jobject group) {
	_init(env);

	FMOD::ChannelGroup* chgroup = get_handle<FMOD::ChannelGroup>(env, _field_handle, obj);
	FMOD::ChannelGroup* native_group;
	FMOD_RESULT result = chgroup->getGroup(index, &native_group);

	Java_de_silveryard_basesystem_sound_FmodChannelGroup_setHandle(env, group, reinterpret_cast<long long>(native_group));

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getParentGroup
(JNIEnv* env, jobject obj, jobject group) {
	_init(env);

	FMOD::ChannelGroup* chgroup = get_handle<FMOD::ChannelGroup>(env, _field_handle, obj);
	FMOD::ChannelGroup* native_group;
	FMOD_RESULT result = chgroup->getParentGroup(&native_group);

	Java_de_silveryard_basesystem_sound_FmodChannelGroup_setHandle(env, group, reinterpret_cast<long long>(native_group));

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getName
(JNIEnv* env, jobject obj, jobject name) {
	_init(env);

	FMOD::ChannelGroup* chgroup = get_handle<FMOD::ChannelGroup>(env, _field_handle, obj);
	char native_name[255];
	FMOD_RESULT result = chgroup->getName(native_name, sizeof(native_name));

	jstring name_boxed = env->NewStringUTF(native_name);
	wrapper_set_value(env, name, name_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getNumChannels
(JNIEnv* env, jobject obj, jobject numChannels) {
	_init(env);

	FMOD::ChannelGroup* chgroup = get_handle<FMOD::ChannelGroup>(env, _field_handle, obj);
	int native_numchannels;
	FMOD_RESULT result = chgroup->getNumChannels(&native_numchannels);

	jobject numchannels_boxed = integer_create(env, native_numchannels);
	wrapper_set_value(env, numChannels, numchannels_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getChannel
(JNIEnv* env, jobject obj, jint index, jobject channel) {
	_init(env);

	FMOD::ChannelGroup* chgroup = get_handle<FMOD::ChannelGroup>(env, _field_handle, obj);
	FMOD::Channel* native_channel;
	FMOD_RESULT result = chgroup->getChannel(index, &native_channel);

	Java_de_silveryard_basesystem_sound_FmodChannel_setHandle(env, channel, reinterpret_cast<long long>(native_channel));

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getDSP
(JNIEnv* env, jobject obj, jint index, jobject dsp) {
	_init(env);
	return fmodchannelcontrol_getDSP(env, obj, _field_handle, index, dsp);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_addDSP
(JNIEnv* env, jobject obj, jint index, jobject dsp) {
	_init(env);
	return fmodchannelcontrol_addDSP(env, obj, _field_handle, index, dsp);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_removeDSP
(JNIEnv* env, jobject obj, jobject dsp){
	_init(env);
	return fmodchannelcontrol_removeDSP(env, obj, _field_handle, dsp);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getNumDSPs
(JNIEnv* env, jobject obj, jobject numDSPs) {
	_init(env);
	return fmodchannelcontrol_getNumDSPs(env, obj, _field_handle, numDSPs);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_setDSPIndex
(JNIEnv* env, jobject obj, jobject dsp, jint index) {
	_init(env);
	return fmodchannelcontrol_setDSPIndex(env, obj, _field_handle, dsp, index);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodChannelGroup_getDSPIndex
(JNIEnv* env, jobject obj, jobject dsp, jobject index) {
	_init(env);
	return fmodchannelcontrol_getDSPIndex(env, obj, _field_handle, dsp, index);
}