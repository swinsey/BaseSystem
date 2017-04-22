#include "SystemVolume.h"

#ifndef SYSVOL_KNOWN_PLATFORM

int sysvol_init() {
	return SYSVOL_INIT_OK;
}
void sysvol_dispose() {

}

//////
//Output
//////
int sysvol_get_num_output_devices() {
	return 0;
}
const char* sysvol_get_output_device_name(int index) {
	return "fallback";
}

bool sysvol_get_mute() {
	return false;
}
void sysvol_set_mute(bool mute) {

}

float sysvol_get_master_volume() {
	return 0;
}
void sysvol_set_master_volume(float volume) {

}

int sysvol_get_num_output_channels() {
	return 0;
}
int sys_vol_get_output_channel_type(int index) {
	return CHANNEL_TYPE_INVALID;
}
float sysvol_get_output_volume(int index) {
	return 0;
}
void sysvol_set_output_volume(int index, float volume) {

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
