#if defined(WIN32)

#include <stdint.h>
#include <Windows.h>

#include <bs/dspmngr/manager.h>

namespace bs {
    namespace dspmngr {

        namespace {
            inline result_t cast_result(const dsp_mngr_result& result) {
                return static_cast<result_t>(result);
            }

            //////
            ///Getting handles from process
            //////
            struct win_handle_data {
                uint64_t process_id;
                HWND window_handle;
            };
            bool is_main_window(HWND handle) {
                return GetWindow(handle, GW_OWNER) == (HWND)0 && IsWindowVisible(handle);
            }
            BOOL CALLBACK enum_windows_callback(HWND handle, LPARAM param) {
                win_handle_data& data = *(win_handle_data*)param;
                unsigned long process_id = 0;
                GetWindowThreadProcessId(handle, &process_id);
                if (data.process_id != process_id || !is_main_window(handle)) {
                    return true; 
                }
                data.window_handle = handle;
                return false;
            }
        }

        BSEXPORT result_t get_process_handle(process_id_t process_id, display_handle& handle) {
            win_handle_data win_data;
            win_data.process_id = static_cast<uint64_t>(process_id);
            win_data.window_handle = nullptr;
            EnumWindows(enum_windows_callback, (LPARAM)&win_data);

            if(win_data.window_handle == 0){
                return cast_result(dsp_mngr_result::ERROR_INVALID_PROCESS_ID);
            }

            handle.value1 = reinterpret_cast<int64_t>(win_data.window_handle);
            return cast_result(dsp_mngr_result::SUCCESS);
        }

        BSEXPORT result_t register_display_handle(const display_handle& handle) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }
        BSEXPORT result_t unregister_display_handle(const display_handle& handle) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }

        BSEXPORT result_t set_window_position(const display_handle& handle, pos_t x, pos_t y) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }
        BSEXPORT result_t get_window_position(const display_handle& handle, pos_t* out_x, pos_t* out_y) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }

        BSEXPORT result_t set_window_alpha(const display_handle& handle, alpha_t alpha) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }
        BSEXPORT result_t get_window_alpha(const display_handle& handle, alpha_t* alpha) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }

        BSEXPORT result_t set_window_active(const display_handle& handle, bool active) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }
        BSEXPORT result_t get_window_active(const display_handle& handle, bool* active) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }

        BSEXPORT result_t set_window_layer(const display_handle& handle, layer_t layer) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }
        BSEXPORT result_t get_window_layer(const display_handle& handle, layer_t* layer) {
            return cast_result(dsp_mngr_result::ERROR_NOT_IMPLEMENTED);
        }


    }
}

#endif
