#include "display_os.h"

#if defined(BS_DISPLAY_USE_FALLBACK)
#include "display.h"

BSEXPORT bs::result_t bs::display::get_handle(bs::display::handle_t* handle) {
	*handle = bs::display::INVALID_HANDLE;
	
	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}
BSEXPORT bs::result_t bs::display::get_window_size(const bs::display::handle_t& handle, uint16_t* width, uint16_t* height) {
	*width = 0;
	*height = 0;

	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}

#if defined(BS_DISPLAY_SYS)
BSEXPORT bs::result_t bs::display::sys::show_window(const bs::display::handle_t& handle) {
	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}
BSEXPORT bs::result_t bs::display::sys::hide_window(const bs::display::handle_t& handle) {
	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}

BSEXPORT bs::result_t bs::display::sys::get_window_layer(const bs::display::handle_t& handle, bs::display::sys::layer_t* layer) {
	*layer = 0;

	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}
BSEXPORT bs::result_t bs::display::sys::set_window_layer(const bs::display::handle_t& handle, bs::display::sys::layer_t layer) {
	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}

BSEXPORT bs::result_t bs::display::sys::get_window_position(const bs::display::handle_t& handle, int16_t* pos_x, int16_t* pos_y) {
	*pos_x = 0;
	*pos_y = 0;

	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}
BSEXPORT bs::result_t bs::display::sys::set_window_position(const bs::display::handle_t& handle, int16_t pos_x, int16_t pos_y) {
	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}

BSEXPORT bs::result_t bs::display::sys::get_window_opacity(const bs::display::handle_t& handle, bs::display::sys::opacity_t* opacity) {
	*opacity = bs::display::sys::OPACITY_MIN;

	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}
BSEXPORT bs::result_t bs::display::sys::set_window_opacity(const bs::display::handle_t& handle, bs::display::sys::opacity_t opacity) {
	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}
#endif

#endif