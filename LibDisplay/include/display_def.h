#ifndef _LIBDISPLAY_DISPLAY_DEF_H_
#define _LIBDISPLAY_DISPLAY_DEF_H_

#include <stdint.h>

namespace bs {
	namespace display {
		struct handle_t {
			int64_t data1;
			int64_t data2;
			int64_t data3;
			int64_t data4;
		};
	
		const handle_t INVALID_HANDLE = handle_t{ 0, 0, 0, 0 };

		enum display_result {
			SUCCESS = 0,

			//Fallback
			ERROR_UNKNOWN_PLATFORM = -1,

			//DispmanX
			ERROR_DISPMANX_NO_UPDATE_HANDLE = -2,
			ERROR_DISPMANX_NO_ELEMENT_HANDLE = -3
		};
	}
}


#endif