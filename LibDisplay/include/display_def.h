#ifndef _LIBDISPLAY_DISPLAY_DEF_H_
#define _LIBDISPLAY_DISPLAY_DEF_H_

#include <stdint.h>

#define BS_DISPLAY_SYS

namespace bs {
	namespace display {
		enum display_result {
			SUCCESS_NOOP = 1,
			SUCCESS = 0,
			ERROR_UNKNOWN = -1,
			ERROR_INVALID_HANDLE = -2,

			//Fallback
			ERROR_UNKNOWN_PLATFORM = -1000,

			//DispmanX
			ERROR_DISPMANX_NO_UPDATE_HANDLE = -2000,
			ERROR_DISPMANX_NO_ELEMENT_HANDLE = -2001,

			//Windows
			ERROR_WINDOWS_NO_HWND = -3000,
		};

		struct handle_t {
			int64_t data1;
			int64_t data2;
			int64_t data3;
			int64_t data4;
		};
		const handle_t INVALID_HANDLE = handle_t{ 0, 0, 0, 0 };

#if defined(BS_DISPLAY_SYS)
		namespace sys {
			typedef int32_t layer_t;
			typedef uint8_t opacity_t;

			const opacity_t OPACITY_MIN = 0;
			const opacity_t OPACITY_MAX = 255;
		}
#endif
	}
}


#endif