#include "display_os.h"

#if defined(BS_DISPLAY_USE_DISPMANX)
#include <interface/vmcs_host/vc_dispmanx.h>
#include "display.h"

BSEXPORT bs::result_t bs::display::get_handle(bs::display::handle_t* handle) {
	*handle = bs::display::INVALID_HANDLE;

	DISPMANX_UPDATE_HANDLE_T update_handle = vc_dispmanx_get_update_handle();
	if (update_handle == DISPMANX_NO_HANDLE) {
		return bs::display::display_result::ERROR_DISPMANX_NO_UPDATE_HANDLE;
	}
	handle->data1 = static_cast<int64_t>(update_handle);

	DISPMANX_ELEMENT_HANDLE_T element_handle = vc_dispmanx_get_element_handle();
	if (element_handle == DISPMANX_NO_HANDLE) {
		return bs::display::display_result::ERROR_DISPMANX_NO_ELEMENT_HANDLE;
	}
	handle->data2 = static_cast<int64_t>(element_handle);

	return bs::display::display_result::SUCCESS;
}
BSEXPORT bs::result_t bs::display::get_window_size(const bs::display::handle_t& handle, uint16_t* width, uint16_t* height) {
	DISPMANX_MODEINFO_T info;

	DISPMANX_DISPLAY_HANDLE_T display = vc_dispmanx_display_open(0);
	int ret = vc_dispmanx_display_get_info(display, &info);
	if (ret != 0) {
		return bs::display::display_result::ERROR_UNKNOWN;
	}

	*width = info.width;
	*height = info.height;

	return bs::display::display_result::SUCCESS;
}

#if defined(BS_DISPLAY_SYS)
BSEXPORT bs::result_t show_window(const bs::display::handle_t& handle) {
	return bs::display::display_result::SUCCESS;
}
BSEXPORT bs::result_t hide_window(const bs::display::handle_t& handle) {
	return bs::display::display_result::SUCCESS;
}

BSEXPORT bs::result_t get_window_layer(const bs::display::handle_t& handle, bs::display::sys::layer_t* layer) {

	return bs::display::display_result::SUCCESS;
}
BSEXPORT bs::result_t set_window_layer(const bs::display::handle_t& handle, bs::display::sys::layer_t layer) {
	return bs::display::display_result::SUCCESS;
}

BSEXPORT bs::result_t get_window_position(const bs::display::handle_t& handle, int16_t* pos_x, int16_t* pos_y) {

	return bs::display::display_result::SUCCESS;
}
BSEXPORT bs::result_t set_window_position(const bs::display::handle_t& handle, int16_t pos_x, int16_t pos_y) {

	return bs::display::display_result::SUCCESS;
}

BSEXPORT bs::result_t get_window_opacity(const bs::display::handle_t& handle, bs::display::sys::opacity_t* opacity) {

	return bs::display::display_result::SUCCESS;
}
BSEXPORT bs::result_t set_window_opacity(const bs::display::handle_t& handle, bs::display::sys::opacity_t opacity) {

	return bs::display::display_result::SUCCESS;
}
#endif
#endif