cmake_minimum_required(VERSION 3.3)
project(FmodNative)

# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/build")
# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/bin/")
message(STATUS "Runtime: ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}")

if(WIN32)
	set(FMOD_INCLUDE_DIR ${PROJECT_SOURCE_DIR}/fmod/win/inc)
	set(FMOD_LIBRARY ${PROJECT_SOURCE_DIR}/fmod/win/lib/fmodL64_vc.lib)
	set(ADDITIONAL_LIBRARIES comctl32)
else()
    if(${CMAKE_SYSTEM_PROCESSOR} MATCHES "arm") 
        set(ARCH "armhf")
    else()
        set(ARCH "x86_64")
    endif()
    
	set(FMOD_INCLUDE_DIR ${PROJECT_SOURCE_DIR}/fmod/linux/inc)
	set(FMOD_LIBRARY ${PROJECT_SOURCE_DIR}/fmod/linux/lib/${ARCH}/libfmodL.so.9.3)
	set(ADDITIONAL_LIBRARIES asound)
endif(WIN32)

find_package(JNI)

include_directories(
        ${JNI_INCLUDE_DIRS}
		${FMOD_INCLUDE_DIR})

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    src/helper.cpp 
	src/Wrapper.cpp 
	src/Integer.cpp 
	src/Boolean.cpp 
	src/Float.cpp 
	src/FmodResult.cpp 
	src/FmodOutputType.cpp 
	src/FmodSpeakerMode.cpp 
	src/FmodSoundFormat.cpp 
	src/FmodSoundType.cpp
	src/FmodChannelOrder.cpp 
	src/de_silveryard_basesystem_sound_FmodSystem.cpp 
	src/de_silveryard_basesystem_sound_FmodSound.cpp
	src/de_silveryard_basesystem_sound_FmodChannel.cpp
	src/de_silveryard_basesystem_sound_FmodCreateSoundExInfo.cpp
	src/de_silveryard_basesystem_sound_SystemVolume.cpp
	src/SystemVolumeWindows.cpp
	src/SystemVolumeLinux.cpp
	src/SystemVolumeFallback.cpp
	)

add_library(FmodNative SHARED ${SOURCE_FILES})
target_link_libraries(FmodNative
        ${JNI_LIBRARIES}
		${FMOD_LIBRARY}
		${ADDITIONAL_LIBRARIES})
