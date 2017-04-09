#ifndef _FMODNATIVE_SYSTEMVOLUME_H_
#define _FMODNATIVE_SYSTEMVOLUME_H_

#if defined(WIN32)
#define SYSVOL_KNOWN_PLATFORM
#endif

const int SYSVOL_INIT_OK = 0;
const int SYSVOL_INIT_ERROR = 1;

const int CHANNEL_TYPE_INVALID = -1;
const int CHANNEL_TYPE_MONO = 1;
const int CHANNEL_TYPE_STEREO_LEFT = 2;
const int CHANNEL_TYPE_STEREO_RIGHT = 3;
const int CHANNEL_TYPE_QUAD_FRONT_LEFT = 4;
const int CHANNEL_TYPE_QUAD_FRONT_RIGHT = 5;
const int CHANNEL_TYPE_QUAD_REAR_LEFT = 6;
const int CHANNEL_TYPE_QUAD_REAR_RIGHT = 7;
const int CHANNEL_TYPE_5_1_FRONT_LEFT = 8;
const int CHANNEL_TYPE_5_1_FRONT_CENTER = 9;
const int CHANNEL_TYPE_5_1_FRONT_RIGHT = 10;
const int CHANNEL_TYPE_5_1_REAR_LEFT = 11;
const int CHANNEL_TYPE_5_1_REAR_RIGHT = 12;
const int CHANNEL_TYPE_5_1_SUB = 13;
const int CHANNEL_TYPE_7_1_FRONT_LEFT = 14;
const int CHANNEL_TYPE_7_1_FRONT_CENTER = 15;
const int CHANNEL_TYPE_7_1_FRONT_RIGHT = 16;
const int CHANNEL_TYPE_7_1_SIDE_LEFT = 17;
const int CHANNEL_TYPE_7_1_SIDE_RIGHT = 18;
const int CHANNEL_TYPE_7_1_REAR_LEFT = 19;
const int CHANNEL_TYPE_7_1_REAR_RIGHT = 20;
const int CHANNEL_TYPE_7_1_SUB = 21;

int sysvol_init();
void sysvol_dispose();

bool sysvol_get_mute();
void sysvol_set_mute(bool mute);

float sysvol_get_master_volume();
void sysvol_set_master_volume(float volume);

int sysvol_get_num_output_channels();
int sys_vol_get_output_channel_type(int index);
float sysvol_get_output_volume(int index);
void sysvol_set_output_volume(int index, float volume);

#endif
