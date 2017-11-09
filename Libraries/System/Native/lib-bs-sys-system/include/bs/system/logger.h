#ifndef _BS_SYSTEM_SYSTEM_H_
#define _BS_SYSTEM_SYSTEM_H_

#include <stdint.h>
#include <string>

#include <bs/bs_def.h>

namespace bs {
    namespace system {
        enum class log_message_type {
            TYPE_OUT = 1,
            TYPE_ERROR = 2
        };

        BSEXPORT void log(const std::string& logger, const std::string& msg, log_message_type message_type);
        BSEXPORT void log(const std::string& msg, log_message_type message_type = log_message_type::TYPE_OUT);
    }
}

#endif