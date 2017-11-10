#ifndef _BS_SYSTEM_DISPLAYMANAGER_H_
#define _BS_SYSTEM_DISPLAYMANAGER_H_

#include <stdint.h>
#include <bs/bs_def.h>

namespace bs {
    namespace dspmngr {

        const bs::scope_t scope = bs::SCOPE_SYSTEM;
        const bs::library_t library = bs::LIBRARY_DSPMNGR;
        const bs::subset_t subset = bs::SUBSET_BS_JNI;

        enum class dsp_mngr_result : result_t {
            SUCCESS = create_result(true, scope, library, subset, 0),

            ERROR_UNKNOWN = create_result(false, scope, library, subset, 1),
            ERROR_NOT_IMPLEMENTED = create_result(false, scope, library, subset, 2),
            ERROR_INVALID_DISPLAY_HANDLE = create_result(false, scope, library, subset, 3),
            ERROR_INVALID_PROCESS_ID = create_result(false, scope, library, subset, 4),
        };

        typedef uint32_t pos_t;
        typedef uint8_t alpha_t;
        typedef uint8_t layer_t;
        typedef uint64_t process_id_t;

        struct display_handle {
            int64_t value1;
        };

        BSEXPORT result_t get_process_handle(process_id_t process_id, display_handle& handle);

        BSEXPORT result_t register_display_handle(const display_handle& handle);
        BSEXPORT result_t unregister_display_handle(const display_handle& handle);

        BSEXPORT result_t set_window_position(const display_handle& handle, pos_t x, pos_t y);
        BSEXPORT result_t get_window_position(const display_handle& handle, pos_t* out_x, pos_t* out_y);

        BSEXPORT result_t set_window_alpha(const display_handle& handle, alpha_t alpha);
        BSEXPORT result_t get_window_alpha(const display_handle& handle, alpha_t* alpha);

        BSEXPORT result_t set_window_active(const display_handle& handle, bool active);
        BSEXPORT result_t get_window_active(const display_handle& handle, bool* active);

        BSEXPORT result_t set_window_layer(const display_handle& handle, layer_t layer);
        BSEXPORT result_t get_window_layer(const display_handle& handle, layer_t* layer);
    }
}

#endif