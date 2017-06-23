#include "display_os.h"

#if defined(BS_DISPLAY_USE_WINDOWS)
#include "display.h"
#include <Windows.h>
#include "bs_util.h"

//Fix for ERROR_INVALID_HANDLE defined in winerror.h
#define WIN_ERROR_INVALID_HANDLE ERROR_INVALID_HANDLE
#define ERROR_INVALID_HANDLE ERROR_INVALID_HANDLE

namespace {
	bool _get_hwnd(const bs::display::handle_t& handle, HWND* hwnd) {
		*hwnd = reinterpret_cast<HWND>(handle.data1);
		return IsWindow(*hwnd);
	}
}

BSEXPORT bs::result_t bs::display::get_handle(bs::display::handle_t* handle) {
	*handle = bs::display::INVALID_HANDLE;
	
	HWND hwnd = GetActiveWindow();
	if (hwnd == NULL) {
		return bs::display::display_result::ERROR_WINDOWS_NO_HWND;
	}

	handle->data1 = reinterpret_cast<uint64_t>(hwnd);
	return bs::display::display_result::SUCCESS;
}
BSEXPORT bs::result_t bs::display::get_window_size(const bs::display::handle_t& handle, uint16_t* width, uint16_t* height) {
	HWND hwnd;
	if (!_get_hwnd(handle, &hwnd)) {
		return bs::display::display_result::ERROR_INVALID_HANDLE;
	}

	RECT rect;
	if (!GetClientRect(hwnd, &rect)) {
		return bs::display::display_result::ERROR_UNKNOWN;
	}

	*width = rect.right - rect.left;
	*height = rect.bottom - rect.top;

	return bs::display::display_result::SUCCESS;
}

#if defined(BS_DISPLAY_SYS)
BSEXPORT bs::result_t show_window(const bs::display::handle_t& handle) {
	HWND hwnd;
	if (!_get_hwnd(handle, &hwnd)) {
		return bs::display::display_result::ERROR_INVALID_HANDLE;
	}

	if (!ShowWindow(hwnd, SW_HIDE)) {
		return bs::display::display_result::ERROR_UNKNOWN;
	}

	return bs::display::display_result::SUCCESS;
}
BSEXPORT bs::result_t hide_window(const bs::display::handle_t& handle) {
	HWND hwnd;
	if (!_get_hwnd(handle, &hwnd)) {
		return bs::display::display_result::ERROR_INVALID_HANDLE;
	}

	if (!ShowWindow(hwnd, SW_SHOW)) {
		return bs::display::display_result::ERROR_UNKNOWN;
	}

	return bs::display::display_result::SUCCESS;
}

BSEXPORT bs::result_t get_window_layer(const bs::display::handle_t& handle, bs::display::sys::layer_t* layer) {
	*layer = 0;

	return bs::display::display_result::SUCCESS_NOOP;
}
BSEXPORT bs::result_t set_window_layer(const bs::display::handle_t& handle, bs::display::sys::layer_t layer) {
	return bs::display::display_result::SUCCESS_NOOP;
}

BSEXPORT bs::result_t get_window_position(const bs::display::handle_t& handle, int16_t* pos_x, int16_t* pos_y) {
	HWND hwnd;
	if (!_get_hwnd(handle, &hwnd)) {
		return bs::display::display_result::ERROR_INVALID_HANDLE;
	}

	RECT rect;
	if (!GetWindowRect(hwnd, &rect)) {
		return bs::display::display_result::ERROR_UNKNOWN;
	}

	*pos_x = rect.left;
	*pos_y = rect.top;

	return bs::display::display_result::SUCCESS;
}
BSEXPORT bs::result_t set_window_position(const bs::display::handle_t& handle, int16_t pos_x, int16_t pos_y) {
	HWND hwnd;
	if (!_get_hwnd(handle, &hwnd)) {
		return bs::display::display_result::ERROR_INVALID_HANDLE;
	}

	uint16_t width, height;
	bs::result_t result = bs::display::get_window_size(handle, &width, &height);
	if (bs::is_error(result)) {
		return result;
	}

	RECT rect;
	if (!GetWindowRect(hwnd, &rect)) {
		return bs::display::display_result::ERROR_UNKNOWN;
	}

	rect.left = pos_x;
	rect.right = pos_x + width;
	rect.top = pos_y;
	rect.bottom = pos_y + height;

	return bs::display::display_result::SUCCESS;
}

BSEXPORT bs::result_t get_window_opacity(const bs::display::handle_t& handle, bs::display::sys::opacity_t* opacity) {
	HWND hwnd;
	if (!_get_hwnd(handle, &hwnd)) {
		return bs::display::display_result::ERROR_INVALID_HANDLE;
	}

	//Ensure that window is layered
	long l = GetWindowLong(hwnd, GWL_EXSTYLE);
	SetWindowLong(hwnd, GWL_EXSTYLE, l ^ WS_EX_LAYERED);

	COLORREF cref;
	BYTE alpha;
	DWORD flags;
	if (!GetLayeredWindowAttributes(hwnd, &cref, &alpha, &flags)) {
		return bs::display::display_result::ERROR_UNKNOWN;
	}

	*opacity = bs::util::translate(bs::display::sys::OPACITY_MIN, bs::display::sys::OPACITY_MAX, (BYTE)0, (BYTE)255, alpha);

	return bs::display::display_result::SUCCESS;
}
BSEXPORT bs::result_t set_window_opacity(const bs::display::handle_t& handle, bs::display::sys::opacity_t opacity) {
	HWND hwnd;
	if (!_get_hwnd(handle, &hwnd)) {
		return bs::display::display_result::ERROR_INVALID_HANDLE;
	}

	//Ensure that window is layered
	long l = GetWindowLong(hwnd, GWL_EXSTYLE);
	SetWindowLong(hwnd, GWL_EXSTYLE, l ^ WS_EX_LAYERED);

	BYTE val = bs::util::translate((BYTE)0, (BYTE)255, bs::display::sys::OPACITY_MIN, bs::display::sys::OPACITY_MAX, opacity);
	if (!SetLayeredWindowAttributes(hwnd, RGB(0, 0, 0), val, LWA_ALPHA)) {
		return bs::display::display_result::ERROR_UNKNOWN;
	}

	return bs::display::display_result::SUCCESS;
}
#endif
#endif