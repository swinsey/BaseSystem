#include <bs/system/logger.h>
#include "de_silveryard_basesystem_logging_LogManager.h"

using namespace std;

namespace bs {
    namespace system {
        BSEXPORT void log(const std::string& logger, const std::string& msg, log_message_type message_type) {
            internal::log_str(logger, msg, message_type);
        }

        BSEXPORT void log(const string& msg, log_message_type message_type) {
            log("System", msg, message_type);
        }
    }
}