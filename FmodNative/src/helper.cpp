#include <string.h>
#include "helper.h"

namespace {
	const char _hex_values[16] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	void _write_hex_byte(char* str, char byte, size_t offset) {
		unsigned char val1 = (byte >> 4) & 0xF;
		unsigned char val2 = byte & 0xF;

		str[offset + 0] = _hex_values[val1];
		str[offset + 1] = _hex_values[val2];
	}
	void _write_hex_short(char* str, short s, size_t offset) {
		char char1 = (s >> 8) & 0xFF;
		char char2 = s & 0xFF;

		_write_hex_byte(str, char1, offset + 0);
		_write_hex_byte(str, char2, offset + 2);
	}
	void _write_hex_int(char* str, int i, size_t offset) {
		short s1 = (i >> 16) & 0xFFFF;
		short s2 = i & 0xFFFF;

		_write_hex_short(str, s1, offset + 0);
		_write_hex_short(str, s2, offset + 4);
	}
}

const char* guid_to_string(FMOD_GUID guid) {
	char str[37];
	str[8] = '-';
	str[13] = '-';
	str[18] = '-';
	str[23] = '-';
	str[36] = 0;

	_write_hex_int(str, guid.Data1, 0);
	_write_hex_short(str, guid.Data2, 9);
	_write_hex_short(str, guid.Data3, 14);
	_write_hex_byte(str, guid.Data4[0], 19);
	_write_hex_byte(str, guid.Data4[1], 21);
	_write_hex_byte(str, guid.Data4[2], 24);
	_write_hex_byte(str, guid.Data4[3], 26);
	_write_hex_byte(str, guid.Data4[4], 28);
	_write_hex_byte(str, guid.Data4[5], 30);
	_write_hex_byte(str, guid.Data4[6], 32);
	_write_hex_byte(str, guid.Data4[7], 34);

	return strdup(str);
}