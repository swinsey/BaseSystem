cmake_minimum_required(VERSION 3.3)
project(DisplayManager)

# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/build")
# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/bin/")
message(STATUS "Runtime: ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}")

set(PLATFORM_LIBS )
if(WIN32)
else()
endif()

set (CMAKE_CXX_STANDARD 11)
find_package(JNI)
include_directories(${JNI_INCLUDE_DIRS})

include_directories(
${PROJECT_SOURCE_DIR}/../../../../3rdParty/boost/
${PROJECT_SOURCE_DIR}/../../../Shared/Native/LibBS/include
${PROJECT_SOURCE_DIR}/../../../System/Native/lib-bs-sys-system/include
${PROJECT_SOURCE_DIR}/include
${PROJECT_SOURCE_DIR}/include_private
)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
	src/manager_windows.cpp
	src/jni/de_silveryard_basesystem_gui_DisplayManager.cpp
)

add_library(DisplayManager SHARED ${SOURCE_FILES})
target_link_libraries(DisplayManager
        ${JNI_LIBRARIES}
		${PLATFORM_LIBS}
		${PROJECT_SOURCE_DIR}/../../../../build/lib/shared/native/lib-bs-sha-1-0-0.lib
		${PROJECT_SOURCE_DIR}/../../../../build/lib/system/native/lib-bs-sys-system-1-0-0.lib)
set_target_properties(DisplayManager PROPERTIES OUTPUT_NAME "lib-bs-sys-dspmngr-1-0-0")

