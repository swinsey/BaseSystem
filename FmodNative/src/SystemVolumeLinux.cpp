#include "SystemVolume.h"

#if defined(__linux__)

#include <alsa/asoundlib.h>
#include <alsa/mixer.h>
#include <vector>
#include <map>
#include <string>
#include <string.h>

using namespace std;

namespace {
    map<snd_mixer_selem_channel_id_t, int> _channel_mapping;
    
    vector<snd_mixer_selem_channel_id_t>    _output_channels;
    long                                    _output_min;
    long                                    _output_max;
    snd_mixer_t*                            _output_handle;
    snd_mixer_selem_id_t*                   _output_sid;
    snd_mixer_elem_t*                       _output_elem;
    
};

int sysvol_init() {
    _channel_mapping = map<snd_mixer_selem_channel_id_t, int>();
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_UNKNOWN, CHANNEL_TYPE_INVALID));
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_FRONT_LEFT, CHANNEL_TYPE_FRONT_LEFT));
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_FRONT_RIGHT, CHANNEL_TYPE_FRONT_RIGHT));
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_REAR_LEFT, CHANNEL_TYPE_REAR_LEFT));
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_REAR_RIGHT, CHANNEL_TYPE_REAR_RIGHT));
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_FRONT_CENTER, CHANNEL_TYPE_FRONT_CENTER));
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_WOOFER, CHANNEL_TYPE_SUB));
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_SIDE_LEFT, CHANNEL_TYPE_SIDE_LEFT));
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_SIDE_RIGHT, CHANNEL_TYPE_SIDE_RIGHT));
    _channel_mapping.insert(pair<snd_mixer_selem_channel_id_t, int>(SND_MIXER_SCHN_REAR_CENTER, CHANNEL_TYPE_REAR_CENTER));
    
    
    snd_mixer_open(&_output_handle, 0);
    snd_mixer_attach(_output_handle, "default");
    snd_mixer_selem_register(_output_handle, NULL, NULL);
    snd_mixer_load(_output_handle);
    
    snd_mixer_selem_id_alloca(&_output_sid);
    snd_mixer_selem_id_set_index(_output_sid, 0);
    snd_mixer_selem_id_set_name(_output_sid, "Master");
    _output_elem = snd_mixer_find_selem(_output_handle, _output_sid);
    
    snd_mixer_selem_get_playback_volume_range(_output_elem, &_output_min, &_output_max);
    
    _output_channels = vector<snd_mixer_selem_channel_id_t>();
    
    if(snd_mixer_selem_has_playback_channel(_output_elem, SND_MIXER_SCHN_FRONT_LEFT)){
        _output_channels.push_back(SND_MIXER_SCHN_FRONT_LEFT);
    }
    if(snd_mixer_selem_has_playback_channel(_output_elem, SND_MIXER_SCHN_FRONT_RIGHT)){
        _output_channels.push_back(SND_MIXER_SCHN_FRONT_RIGHT);
    }
    if(snd_mixer_selem_has_playback_channel(_output_elem, SND_MIXER_SCHN_REAR_LEFT)){
        _output_channels.push_back(SND_MIXER_SCHN_REAR_LEFT);
    }
    if(snd_mixer_selem_has_playback_channel(_output_elem, SND_MIXER_SCHN_REAR_RIGHT)){
        _output_channels.push_back(SND_MIXER_SCHN_REAR_RIGHT);
    }
    if(snd_mixer_selem_has_playback_channel(_output_elem, SND_MIXER_SCHN_FRONT_CENTER)){
        _output_channels.push_back(SND_MIXER_SCHN_FRONT_CENTER);
    }
    if(snd_mixer_selem_has_playback_channel(_output_elem, SND_MIXER_SCHN_WOOFER)){
        _output_channels.push_back(SND_MIXER_SCHN_WOOFER);
    }
    if(snd_mixer_selem_has_playback_channel(_output_elem, SND_MIXER_SCHN_SIDE_LEFT)){
        _output_channels.push_back(SND_MIXER_SCHN_SIDE_LEFT);
    }
    if(snd_mixer_selem_has_playback_channel(_output_elem, SND_MIXER_SCHN_SIDE_RIGHT)){
        _output_channels.push_back(SND_MIXER_SCHN_SIDE_RIGHT);
    }
    if(snd_mixer_selem_has_playback_channel(_output_elem, SND_MIXER_SCHN_REAR_CENTER)){
        _output_channels.push_back(SND_MIXER_SCHN_REAR_CENTER);
    }
    
	return SYSVOL_INIT_OK;
}
void sysvol_dispose() {
    snd_mixer_close(_output_handle);
}

//////
//Output
//////
int sysvol_get_num_output_devices() {
	return 0;
}
const char* sysvol_get_output_device_name(int index) {
	return "INVALID_INDEX";
}

bool sysvol_get_mute() {
	if(snd_mixer_selem_has_playback_switch(_output_elem)){
        int mute;
        snd_mixer_selem_get_playback_switch(_output_elem, SND_MIXER_SCHN_MONO, &mute);
        return mute == 0;
    }
    return false;
}
void sysvol_set_mute(bool mute) {
    if(snd_mixer_selem_has_playback_switch(_output_elem)){
        snd_mixer_selem_set_playback_switch_all(_output_elem, mute ? 0 : 1);
    }
}

float sysvol_get_master_volume() {
    float vol = 0;
    for(int i = 0; i < _output_channels.size(); i++){
        vol += sysvol_get_output_volume(i);
    }
    vol /= _output_channels.size();
    
    return vol;
}
void sysvol_set_master_volume(float volume) {
    float master = sysvol_get_master_volume();
    for(int i = 0; i < _output_channels.size(); i++){
        float vol_old = sysvol_get_output_volume(i);
        float div     = vol_old - master;
        float vol_new = volume + div;
        if(vol_new > 1){
            vol_new = 1;
        }
        if(vol_new < 0){
            vol_new = 0;
        }
        sysvol_set_output_volume(i, vol_new);
    }
}

int sysvol_get_num_output_channels() {
    return _output_channels.size();
}
int sys_vol_get_output_channel_type(int index) {
	if(index < 0 || index >= _output_channels.size()){
        return CHANNEL_TYPE_INVALID;
    }
    
    return _channel_mapping[_output_channels[index]];
}
float sysvol_get_output_volume(int index) {
	if(index < 0 || index >= _output_channels.size()){
        return -1;
    }
    
    float vol = -1;
    long val  = -1;
    
    snd_mixer_selem_get_playback_volume(_output_elem, _output_channels[index], &val);
    
    if(val == -1){
        vol = -1;
    }else{
        long adjusted_val = val - _output_min;
        long adjusted_max = _output_max - _output_min;
        
        vol = adjusted_val / (float)adjusted_max;
    }
    
    return vol;
}
void sysvol_set_output_volume(int index, float volume) {
    if(index < 0 || index >= _output_channels.size()){
        return;
    }
    
    long adjusted_max = _output_max - _output_min;
    long adjusted_val = adjusted_max * volume;
    long val = adjusted_val + _output_min;
    
    snd_mixer_selem_set_playback_volume(_output_elem, _output_channels[index], val);
}

//////
//Input
//////
int sysvol_get_num_input_devices() {
	return 0;
}
const char* sysvol_get_input_device_name(int index) {
	return "fallback";
}

bool sysvol_get_input_mute() {
	return false;
}
void sysvol_set_input_mute(bool mute){
    
}

float sysvol_get_input_master_volume() {
	return 0;
}
void sysvol_set_input_master_volume(float volume) {

}

int sysvol_get_num_input_channels() {
	return 0;
}
int sysvol_get_input_channel_type(int index) {
	return CHANNEL_TYPE_INVALID;
}
float sysvol_get_input_volume(int index) {
	return 0;
}
void sysvol_set_input_volume(int index, float volume) {

}

#endif
