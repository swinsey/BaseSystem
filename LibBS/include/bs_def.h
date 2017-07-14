#ifndef _SHARED_BS_DEV_H_
#define _SHARED_BS_DEV_H_

#if defined(WIN32)
#define BSEXPORT __declspec(dllexport)
#else
#define BSEXPORT
#endif

#include <stdint.h>

namespace bs {
	typedef int32_t result_t;
	typedef int8_t scope_t;
	typedef int8_t library_t;
	typedef int8_t subset_t;
	typedef int8_t code_t;

	const bs::scope_t SCOPE_USER = 0x01;
	const bs::scope_t SCOPE_SYSTEM = 0x02;
	const bs::scope_t SCOPE_MIXED = SCOPE_USER | SCOPE_SYSTEM;

	const bs::library_t LIBRARY_BS = 1;

	const bs::subset_t SUBSET_BS_JNI = 1;

	BSEXPORT bool is_success(result_t r);
	BSEXPORT bool is_error(result_t r);

	constexpr BSEXPORT result_t create_result(bool success, scope_t scope, library_t library, subset_t subset, code_t code) {
		result_t t = 0;
		t |= (scope & 0xFF) << 24;
		t |= (library & 0xFF) << 16;
		t |= (subset & 0xFF) << 8;
		t |= (code & 0xFF);

		if (!success) {
			t *= -1;
		}

		return t;
	}
	BSEXPORT scope_t get_scope(result_t result);
	BSEXPORT library_t get_library(result_t result);
	BSEXPORT subset_t get_subset(result_t result);
	BSEXPORT code_t get_code(result_t result);
}

#endif