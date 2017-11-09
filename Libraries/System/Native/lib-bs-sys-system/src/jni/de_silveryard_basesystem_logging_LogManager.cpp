#include "de_silveryard_basesystem_logging_LogManager.h"
#include <iostream>

using namespace std;

namespace {
    JNIEnv* _env;

    jclass _cls_logmessagetype;
    jmethodID _method_logmessagetype_getenumvalue;

    jclass _cls_logmanager;
    jmethodID _method_logmanager_getInstance;
    jmethodID _method_logmanager_log;
}

JNIEXPORT void JNICALL Java_de_silveryard_basesystem_logging_LogManager_initNative
(JNIEnv* env, jclass cls) {
    _env = env;

    _cls_logmessagetype = env->FindClass("de/silveryard/basesystem/logging/LogMessageType");
    bs::assert(_cls_logmessagetype);
    _method_logmessagetype_getenumvalue = env->GetStaticMethodID(_cls_logmessagetype, "getEnumValue", "(I)Lde/silveryard/basesystem/logging/LogMessageType;");
    bs::assert(_method_logmessagetype_getenumvalue);
	
    _cls_logmanager = env->FindClass("de/silveryard/basesystem/logging/LogManager");
    bs::assert(_cls_logmanager);
    _method_logmanager_getInstance = env->GetStaticMethodID(_cls_logmanager, "getInstance", "()Lde/silveryard/basesystem/logging/LogManager;");
    bs::assert(_method_logmanager_getInstance);
	_method_logmanager_log = env->GetMethodID(_cls_logmanager, "log", "(Ljava/lang/String;Ljava/lang/String;Lde/silveryard/basesystem/logging/LogMessageType;)V");
    bs::assert(_method_logmanager_log);
}

namespace bs {
    namespace system {
        namespace internal{
            BSEXPORT void log_str(const string& logger, const string& msg, log_message_type type) {
                jobject jmngr = _env->CallStaticObjectMethod(_cls_logmanager, _method_logmanager_getInstance);
                bs::assert(jmngr);

                jobject jtype = _env->CallStaticObjectMethod(_cls_logmessagetype, _method_logmessagetype_getenumvalue, (jint)type);
                bs::assert(jtype);
                jstring jlogger = _env->NewStringUTF(logger.c_str());
                jstring jmsg = _env->NewStringUTF(msg.c_str());

                _env->CallObjectMethod(jmngr, _method_logmanager_log, jlogger, jmsg, jtype);
            }
        }
    }
}