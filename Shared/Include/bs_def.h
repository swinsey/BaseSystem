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

	inline bool is_success(result_t r) {
		return r >= 0;
	}
	inline bool is_error(result_t r) {
		return r < 0;
	}
}

#endif