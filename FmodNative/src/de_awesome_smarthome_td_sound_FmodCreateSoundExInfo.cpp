#include <string.h>
#include "fmod.hpp"
#include "fmod_errors.h"
#include "helper.h"
#include "FmodSoundFormat.h"
#include "FmodSoundType.h"
#include "FmodChannelOrder.h"
#include "de_awesome_smarthome_td_sound_FmodCreateSoundExInfo.h"

namespace {
	bool _initialized = false;

	jclass _class;
	jfieldID _field_handle;


	void _init(JNIEnv* env, jobject obj) {
		if (_initialized) {
			return;
		}
		_initialized = true;

		_class = env->FindClass("de/awesome/smarthome/td/sound/FmodCreateSoundExInfo");
		_field_handle = env->GetFieldID(_class, "handle", "J");

		FMOD_CREATESOUNDEXINFO* info = new FMOD_CREATESOUNDEXINFO();
		info->cbsize = sizeof(FMOD_CREATESOUNDEXINFO);
		set_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj, info);
	}
}

JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_dispose
(JNIEnv* env, jobject obj) {
	_init(env, obj);
	
	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	delete info;
}

JNIEXPORT jlong JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getHandle
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	return env->GetLongField(obj, _field_handle);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getLength
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->length;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setLength
(JNIEnv* env, jobject obj, jint length) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->length = length;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getFileOffset
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->fileoffset;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setFileOffset
(JNIEnv* env, jobject obj, jint fileOffset) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->fileoffset = fileOffset;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getNumChannels
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->numchannels;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setNumChannels
(JNIEnv* env, jobject obj, jint numChannels) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->numchannels = numChannels;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getDefaultFrequency
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->defaultfrequency;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setDefaultFrequency
(JNIEnv* env, jobject obj, jint defaultFrequency) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->defaultfrequency = defaultFrequency;
}

JNIEXPORT jobject JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getFormat
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return fmodsoundformat_get_enum_value(env, info->format);
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setFormat
(JNIEnv* env, jobject obj, jobject format) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->format = fmodsoundformat_get_value(env, format);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getDecodeBufferSize
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->decodebuffersize;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setDecodeBufferSize
(JNIEnv* env, jobject obj, jint decodeBufferSize) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->decodebuffersize = decodeBufferSize;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getInitialSubSound
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->initialsubsound;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setInitialSubSound
(JNIEnv* env, jobject obj, jint initialSubSound) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->initialsubsound = initialSubSound;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getNumSubSounds
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->numsubsounds;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setNumSubSounds
(JNIEnv* env, jobject obj, jint numSubSounds) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->numsubsounds = numSubSounds;
}

JNIEXPORT jintArray JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getInclusionList
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	jintArray arr = env->NewIntArray(info->inclusionlistnum);
	env->SetIntArrayRegion(arr, 0, info->inclusionlistnum, reinterpret_cast<const jint*>(info->inclusionlist));
	return arr;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setInclusionList
(JNIEnv* env, jobject obj, jintArray inclusionList) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->inclusionlistnum = env->GetArrayLength(inclusionList);
	info->inclusionlist = reinterpret_cast<int*>(env->GetIntArrayElements(inclusionList, false));
}

JNIEXPORT jstring JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getDlsName
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	jstring jname = env->NewStringUTF(info->dlsname);
	return jname;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setDlsName
(JNIEnv* env, jobject obj, jstring dlsName) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->dlsname = strdup(env->GetStringUTFChars(dlsName, false));
}

JNIEXPORT jstring JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getEncryptionKey
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	jstring jEncryptionKey = env->NewStringUTF(info->encryptionkey);
	return jEncryptionKey;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setEncryptionKey
(JNIEnv* env, jobject obj, jstring encryptionKey) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->encryptionkey = strdup(env->GetStringUTFChars(encryptionKey, false));
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getMaxPolyphony
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->maxpolyphony;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setMaxPolyphony
(JNIEnv* env, jobject obj, jint maxPolyphony) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->maxpolyphony = maxPolyphony;
}

JNIEXPORT jobject JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getSuggestedSoundType
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return fmodsoundtype_get_enum_value(env, info->suggestedsoundtype);
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setSuggestedSoundType
(JNIEnv* env, jobject obj, jobject suggestedSoundType) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->suggestedsoundtype = fmodsoundtype_get_value(env, suggestedSoundType);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getFileBufferSize
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->filebuffersize;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setFileBufferSize
(JNIEnv* env, jobject obj, jint fileBufferSize) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->filebuffersize = fileBufferSize;
}

JNIEXPORT jobject JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getChannelOrder
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return fmodchannelorder_get_enum_value(env, info->channelorder);
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setChannelOrder
(JNIEnv* env, jobject obj, jobject channelOrder) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->channelorder = fmodchannelorder_get_value(env, channelOrder);
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getChannelMask
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->channelmask;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setChannelMask
(JNIEnv* env, jobject obj, jint channelMask) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->channelmask = channelMask;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getInitialSeekPosition
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->initialseekposition;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setInitialSeekPosition
(JNIEnv* env, jobject obj, jint initialSeekPosition) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->initialseekposition = initialSeekPosition;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getInitialSeekPosType
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->initialseekpostype;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setInitialSeekPosType
(JNIEnv* env, jobject obj, jint initialSeekPosType) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->initialseekpostype = initialSeekPosType;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getIgnoreSetFilesystem
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->ignoresetfilesystem;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setIgnoreSetFilesystem
(JNIEnv* env, jobject obj, jint ignoreSetFilesystem) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->ignoresetfilesystem = ignoreSetFilesystem;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getAudioQueuePolicy
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->audioqueuepolicy;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setAudioQueuePolicy
(JNIEnv* env, jobject obj, jint audioQueuePolicy) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->audioqueuepolicy = audioQueuePolicy;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getMinMidiGranularity
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->minmidigranularity;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setMinMidiGranularity
(JNIEnv* env, jobject obj, jint minMidiGranularity) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->minmidigranularity = minMidiGranularity;
}

JNIEXPORT jint JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_getNonBlockThreadId
(JNIEnv* env, jobject obj) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	return info->nonblockthreadid;
}
JNIEXPORT void JNICALL Java_de_awesome_smarthome_td_sound_FmodCreateSoundExInfo_setNonBlockThreadId
(JNIEnv* env, jobject obj, jint nonBlockThreadId) {
	_init(env, obj);

	FMOD_CREATESOUNDEXINFO* info = get_handle<FMOD_CREATESOUNDEXINFO>(env, _field_handle, obj);
	info->nonblockthreadid = nonBlockThreadId;
}
