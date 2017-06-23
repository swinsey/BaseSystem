#include "display.h"

#if defined(BS_DISPLAY_USE_DISPMANX)

#include <interface\vmcs_host\vc_dispmanx.h>

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

	return bs::display::display_result::SUCCESS;
}

#endif