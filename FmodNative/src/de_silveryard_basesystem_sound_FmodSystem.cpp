#include "fmod.hpp"
#include "helper.h"
#include "Wrapper.h"
#include "Boolean.h"
#include "Integer.h"
#include "FmodResult.h"
#include "FmodOutputType.h"
#include "FmodSpeakerMode.h"
#include "FmodDSPType.h"
#include "de_silveryard_basesystem_sound_FmodDSP.h"
#include "de_silveryard_basesystem_sound_FmodSound.h"
#include "de_silveryard_basesystem_sound_FmodChannel.h"
#include "de_silveryard_basesystem_sound_FmodChannelGroup.h"
#include "de_silveryard_basesystem_sound_FmodCreateSoundExInfo.h"
#include "de_silveryard_basesystem_sound_FmodSystem.h"
#include <iostream>

namespace {
	bool _initialized = false;

	jclass _class;
	jfieldID _field_handle;
	jmethodID _method_ctr;

	void _init(JNIEnv* env) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/silveryard/basesystem/sound/FmodSystem");
		_field_handle = env->GetFieldID(_class, "handle", "J");
		_method_ctr = env->GetMethodID(_class, "<init>", "(J)V");
	}
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_createSystem
(JNIEnv* env, jclass clazz) {
	_init(env);

	FMOD::System* system;
	FMOD::System_Create(&system);
	
	return create_handle_obj(env, _class, _method_ctr, system);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_init
(JNIEnv* env, jobject obj, jint maxChannels, jint initFlags) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD_RESULT result = system->init(maxChannels, initFlags, NULL);

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_update
(JNIEnv* env, jobject obj) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD_RESULT result = system->update();

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_dispose
(JNIEnv* env, jobject obj) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD_RESULT result = system->release();

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_setOutput
(JNIEnv* env, jobject obj, jobject output) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	int outputValue = fmodoutputtype_get_value(env, output);
	FMOD_OUTPUTTYPE type = static_cast<FMOD_OUTPUTTYPE>(outputValue);
	FMOD_RESULT result = system->setOutput(type);
	
	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_getOutput
(JNIEnv* env, jobject obj, jobject output) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD_OUTPUTTYPE type;
	FMOD_RESULT result = system->getOutput(&type);

	int type_int = static_cast<int>(result);
	jobject type_enum = fmodoutputtype_get_enum_value(env, type_int);
	wrapper_set_value(env, output, type_enum);

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_getNumDrivers
(JNIEnv* env, jobject obj, jobject numDrivers) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	int num_drivers;
	FMOD_RESULT result = system->getNumDrivers(&num_drivers);

	jobject drivers_boxed = integer_create(env, num_drivers);
	wrapper_set_value(env, numDrivers, drivers_boxed);

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_getDriverInfo
(JNIEnv* env, jobject obj, jint id, jobject name, jobject guid, jobject systemRate, jobject speakerMode, jobject speakerModeChannels) {
	_init(env);
	
	char native_name[255];
	FMOD_GUID native_guid;
	int system_rate;
	FMOD_SPEAKERMODE speaker_mode;
	int speaker_mode_channels;
	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD_RESULT result = system->getDriverInfo(id, native_name, sizeof(native_name), &native_guid, &system_rate, &speaker_mode, &speaker_mode_channels);

	jstring jname = env->NewStringUTF(native_name);
	wrapper_set_value(env, name, jname);

	const char* nguid = guid_to_string(native_guid);
	jstring jguid = env->NewStringUTF(nguid);
	wrapper_set_value(env, guid, jguid);

	jobject system_rate_boxed = integer_create(env, system_rate);
	wrapper_set_value(env, systemRate, system_rate_boxed);

	jobject speaker_mode_enum = fmodspeakermode_get_enum_value(env, speaker_mode);
	wrapper_set_value(env, speakerMode, speaker_mode_enum);

	jobject speaker_mode_channels_boxed = integer_create(env, speaker_mode_channels);
	wrapper_set_value(env, speakerModeChannels, speaker_mode_channels_boxed);

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_setDriver
(JNIEnv* env, jobject obj, jint driver) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD_RESULT result = system->setDriver(driver);

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_getDriver
(JNIEnv* env, jobject obj, jobject driver) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	int idriver;
	FMOD_RESULT result = system->getDriver(&idriver);

	jobject driver_boxed = integer_create(env, idriver);
	wrapper_set_value(env, driver, driver_boxed);

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_createSound
(JNIEnv* env, jobject obj, jstring nameOrData, jint mode, jobject exInfo, jobject sound) {
	_init(env);

	FMOD_CREATESOUNDEXINFO* info = NULL;
	if (exInfo != NULL) {
		long long infoPtr = Java_de_silveryard_basesystem_sound_FmodCreateSoundExInfo_getHandle(env, exInfo);
		info = reinterpret_cast<FMOD_CREATESOUNDEXINFO*>(infoPtr);
	}
	const char* name_or_data = NULL;
	if(nameOrData != NULL){
		name_or_data = env->GetStringUTFChars(nameOrData, NULL);
	}
	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD::Sound* native_sound;
	FMOD_RESULT result = system->createSound(name_or_data, static_cast<FMOD_MODE>(mode), info, &native_sound);

	Java_de_silveryard_basesystem_sound_FmodSound_setHandle(env, sound, reinterpret_cast<long long>(native_sound));

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_createStream
(JNIEnv* env, jobject obj, jstring nameOrData, jint mode, jobject exInfo, jobject sound) {
	_init(env);
	
	FMOD_CREATESOUNDEXINFO* info = NULL;
	if (exInfo != NULL) {
		long long infoPtr = Java_de_silveryard_basesystem_sound_FmodCreateSoundExInfo_getHandle(env, exInfo);
		info = reinterpret_cast<FMOD_CREATESOUNDEXINFO*>(infoPtr);
	}
	const char* name_or_data = NULL;
	if (nameOrData != NULL) {
		name_or_data = env->GetStringUTFChars(nameOrData, NULL);
	}
	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD::Sound* native_sound;
	FMOD_RESULT result = system->createStream(name_or_data, static_cast<FMOD_MODE>(mode), info, &native_sound);

	Java_de_silveryard_basesystem_sound_FmodSound_setHandle(env, sound, reinterpret_cast<long long>(native_sound));

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_createDSPByType
(JNIEnv* env, jobject obj, jobject type, jobject dsp) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD_DSP_TYPE native_type = (FMOD_DSP_TYPE)fmoddsptype_get_value(env, type);
	jlong dspHandle = Java_de_silveryard_basesystem_sound_FmodDSP_getHandle(env, dsp);
	FMOD::DSP* native_dsp;
	FMOD_RESULT result = system->createDSPByType(native_type, &native_dsp);

	Java_de_silveryard_basesystem_sound_FmodDSP_setHandle(env, dsp, reinterpret_cast<long long>(native_dsp));

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_createChannelGroup
(JNIEnv* env, jobject obj, jstring name, jobject channelGroup) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	const char* native_name = NULL;
	if (name != NULL) {
		native_name = env->GetStringUTFChars(name, NULL);
	}
	FMOD::ChannelGroup* native_group;
	FMOD_RESULT result = system->createChannelGroup(native_name, &native_group);

	Java_de_silveryard_basesystem_sound_FmodChannelGroup_setHandle(env, channelGroup, reinterpret_cast<long long>(native_group));

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_playSound
(JNIEnv* env, jobject obj, jobject sound, jboolean paused, jobject channel) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	jlong soundHandle = Java_de_silveryard_basesystem_sound_FmodSound_getHandle(env, sound);
	FMOD::Sound* native_sound = reinterpret_cast<FMOD::Sound*>(soundHandle);
	FMOD::Channel* native_channel;
	FMOD_RESULT result = system->playSound(native_sound, NULL, paused, &native_channel);

	Java_de_silveryard_basesystem_sound_FmodChannel_setHandle(env, channel, reinterpret_cast<long long>(native_channel));

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_getMasterChannelGroup
(JNIEnv* env, jobject obj, jobject channelGroup) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD::ChannelGroup* native_group;
	FMOD_RESULT result = system->getMasterChannelGroup(&native_group);

	Java_de_silveryard_basesystem_sound_FmodChannelGroup_setHandle(env, obj, reinterpret_cast<long long>(native_group));

	return fmodresult_get_enum_value(env, result);
}

JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_getRecordNumDrivers
(JNIEnv* env, jobject obj, jobject numDrivers, jobject numConnected) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	int num_drivers;
	int num_connected;
	FMOD_RESULT result = system->getRecordNumDrivers(&num_drivers, &num_connected);

	jobject num_drivers_boxed = integer_create(env, num_drivers);
	wrapper_set_value(env, numDrivers, num_drivers_boxed);

	jobject num_connected_boxed = integer_create(env, num_connected);
	wrapper_set_value(env, numConnected, num_connected_boxed);

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_getRectordDriverInfo
(JNIEnv* env, jobject obj, jint id, jobject name, jobject guid, jobject systemRate, jobject speakerMode, jobject speakerModeChannels, jobject state) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	char native_name[255];
	FMOD_GUID native_guid;
	int system_rate;
	FMOD_SPEAKERMODE speaker_mode;
	int speaker_mode_channels;
	FMOD_DRIVER_STATE native_state;
	FMOD_RESULT result = system->getRecordDriverInfo(id, native_name, sizeof(native_name), &native_guid, &system_rate, &speaker_mode, &speaker_mode_channels, &native_state);

	jstring jname = env->NewStringUTF(native_name);
	wrapper_set_value(env, name, jname);

	const char* nguid = guid_to_string(native_guid);
	jstring jguid = env->NewStringUTF(nguid);
	wrapper_set_value(env, guid, jguid);

	jobject system_rate_boxed = integer_create(env, system_rate);
	wrapper_set_value(env, systemRate, system_rate_boxed);

	jobject speaker_mode_enum = fmodspeakermode_get_enum_value(env, speaker_mode);
	wrapper_set_value(env, speakerMode, speaker_mode_enum);

	jobject speaker_mode_channels_boxed = integer_create(env, speaker_mode_channels);
	wrapper_set_value(env, speakerModeChannels, speaker_mode_channels_boxed);

	jobject state_boxed = integer_create(env, native_state);
	wrapper_set_value(env, state, state_boxed);

	int res_int = static_cast<int>(result);
	jobject res_enum = fmodresult_get_enum_value(env, res_int);
	return res_enum;
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_getRecordPosition
(JNIEnv* env, jobject obj, jint id, jobject position) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	unsigned int pos;
	FMOD_RESULT result = system->getRecordPosition(id, &pos);

	jobject pos_boxed = integer_create(env, static_cast<int>(pos));
	wrapper_set_value(env, position, pos_boxed);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_recordStart
(JNIEnv* env, jobject obj, jint id, jobject sound, jboolean loop) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	long long soundPtr = Java_de_silveryard_basesystem_sound_FmodSound_getHandle(env, sound);
	FMOD::Sound* native_sound = reinterpret_cast<FMOD::Sound*>(soundPtr);
	FMOD_RESULT result = system->recordStart(id, native_sound, loop);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_recordStop
(JNIEnv* env, jobject obj, jint id) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	FMOD_RESULT result = system->recordStop(id);

	return fmodresult_get_enum_value(env, result);
}
JNIEXPORT jobject JNICALL Java_de_silveryard_basesystem_sound_FmodSystem_isRecording
(JNIEnv* env, jobject obj, jint id, jobject isRecording) {
	_init(env);

	FMOD::System* system = get_handle<FMOD::System>(env, _field_handle, obj);
	bool is_recording;
	FMOD_RESULT result = system->isRecording(id, &is_recording);

	jobject recording_boxed = boolean_create(env, is_recording);
	wrapper_set_value(env, isRecording, recording_boxed);

	return fmodresult_get_enum_value(env, result);
}
