#include "display.h"

#if defined(BS_DISPLAY_USE_FALLBACK)

BSEXPORT bs::result_t bs::display::get_handle(bs::display::handle_t* handle) {
	*handle = bs::display::INVALID_HANDLE;
	
	return bs::display::display_result::ERROR_UNKNOWN_PLATFORM;
}

#endif