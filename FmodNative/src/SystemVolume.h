#ifndef _FMODNATIVE_SYSTEMVOLUME_H_
#define _FMODNATIVE_SYSTEMVOLUME_H_

#if defined(WIN32)
#define SYSVOL_KNOWN_PLATFORM
#endif

#if defined(__linux__)
#define SYSVOL_KNOWN_PLATFORM
#endif

const int SYSVOL_INIT_OK = 0;
const int SYSVOL_INIT_ERROR = 1;

const int CHANNEL_TYPE_INVALID      = -1;
const int CHANNEL_TYPE_FRONT_LEFT   = 1;
const int CHANNEL_TYPE_FRONT_CENTER = 2;
const int CHANNEL_TYPE_FRONT_RIGHT  = 3;
const int CHANNEL_TYPE_SIDE_LEFT    = 4;
const int CHANNEL_TYPE_SIDE_RIGHT   = 5;
const int CHANNEL_TYPE_REAR_LEFT    = 6;
const int CHANNEL_TYPE_REAR_CENTER  = 7;
const int CHANNEL_TYPE_REAR_RIGHT   = 8;
const int CHANNEL_TYPE_SUB          = 9;

int sysvol_init();
void sysvol_dispose();

//////
//Output
//////

int sysvol_get_num_output_devices();
const char* sysvol_get_output_device_name(int index);

bool sysvol_get_mute();
void sysvol_set_mute(bool mute);

float sysvol_get_master_volume();
void sysvol_set_master_volume(float volume);

int sysvol_get_num_output_channels();
int sys_vol_get_output_channel_type(int index);
float sysvol_get_output_volume(int index);
void sysvol_set_output_volume(int index, float volume);

//////
//Input
//////

int sysvol_get_num_input_devices();
const char* sysvol_get_input_device_name(int index);

bool sysvol_get_input_mute();
void sysvol_set_input_mute(bool mute);

float sysvol_get_input_master_volume();
void sysvol_set_input_master_volume(float volume);

int sysvol_get_num_input_channels();
int sysvol_get_input_channel_type(int index);
float sysvol_get_input_volume(int index);
void sysvol_set_input_volume(int index, float volume);

#endif
