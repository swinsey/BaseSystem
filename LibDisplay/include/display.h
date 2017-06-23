#ifndef _LIBDISPLAY_DISPLAY_H_
#define _LIBDISPLAY_DISPLAY_H_

#include <bs_def.h>
#include "display_def.h"

namespace bs {
	namespace display {
		BSEXPORT bool cmp_handles(const bs::display::handle_t& h1, const bs::display::handle_t& h2);

		BSEXPORT bs::result_t get_handle(bs::display::handle_t* handle);
		BSEXPORT bs::result_t get_window_size(const bs::display::handle_t& handle, uint16_t* width, uint16_t* height);

#if defined(BS_DISPLAY_SYS)
		namespace sys {
			BSEXPORT bs::result_t show_window(const bs::display::handle_t& handle);
			BSEXPORT bs::result_t hide_window(const bs::display::handle_t& handle);

			BSEXPORT bs::result_t get_window_layer(const bs::display::handle_t& handle, bs::display::sys::layer_t* layer);
			BSEXPORT bs::result_t set_window_layer(const bs::display::handle_t& handle, bs::display::sys::layer_t layer);

			BSEXPORT bs::result_t get_window_position(const bs::display::handle_t& handle, int16_t* pos_x, int16_t* pos_y);
			BSEXPORT bs::result_t set_window_position(const bs::display::handle_t& handle, int16_t pos_x, int16_t pos_y);

			BSEXPORT bs::result_t get_window_opacity(const bs::display::handle_t& handle, bs::display::sys::opacity_t* opacity);
			BSEXPORT bs::result_t set_window_opacity(const bs::display::handle_t& handle, bs::display::sys::opacity_t opacity);
		}
#endif
	}
}


#endif