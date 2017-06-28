#ifndef _LIBDISPLAY_DISPLAY_DEF_H_
#define _LIBDISPLAY_DISPLAY_DEF_H_

#include <stdint.h>

namespace bs {
	namespace display {
		const bs::scope_t scope = bs::SCOPE_MIXED;
		const bs::library_t library = 2;
		const bs::subset_t subset = 1;

		enum display_result {
			SUCCESS_NOOP = bs::create_result(true, scope, library, subset, 1),
			SUCCESS = bs::create_result(true, scope, library, subset, 0),
			ERROR_UNKNOWN = bs::create_result(false, scope, library, subset, 1),
			ERROR_INVALID_HANDLE = bs::create_result(false, scope, library, subset, 2),

			//Fallback
			ERROR_UNKNOWN_PLATFORM = bs::create_result(false, scope, library, subset, 10),

			//DispmanX
			ERROR_DISPMANX_NO_UPDATE_HANDLE = bs::create_result(false, scope, library, subset, 20),
			ERROR_DISPMANX_NO_ELEMENT_HANDLE = bs::create_result(false, scope, library, subset, 21),

			//Windows
			ERROR_WINDOWS_NO_HWND = bs::create_result(false, scope, library, subset, 30),
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