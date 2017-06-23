#ifndef _LIBDISPLAY_DISPLAY_H_
#define _LIBDISPLAY_DISPLAY_H_

#if defined(_arm_)
#define BS_DISPLAY_USE_DISPMANX
#else
#define BS_DISPLAY_USE_FALLBACK
#endif

#include <bs_def.h>
#include "display_def.h"

namespace bs {
	namespace display {
		BSEXPORT bool cmp_handles(const bs::display::handle_t& h1, const bs::display::handle_t& h2);

		BSEXPORT bs::result_t get_handle(bs::display::handle_t* handle);
	}
}


#endif