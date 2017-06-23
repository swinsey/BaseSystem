cmake_minimum_required(VERSION 3.3)
project(Display)

# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/build")
# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/bin/")
message(STATUS "Runtime: ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}")

set(PLATFORM_LIBS )
if(WIN32)
else()
endif(WIN32)

set (CMAKE_CXX_STANDARD 11)
find_package(JNI)
include_directories(${JNI_INCLUDE_DIRS})

include_directories(
${PROJECT_SOURCE_DIR}/../Shared/Include
${PROJECT_SOURCE_DIR}/../3rdParty/boost/
${PROJECT_SOURCE_DIR}/include
)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
	src/display_base.cpp
	src/display_dispmanx.cpp
	src/display_fallback.cpp
	src/display_windows.cpp
	src/de_silveryard_basesystem_libdisplayjava_Display
)

add_library(Display SHARED ${SOURCE_FILES})
target_link_libraries(Display
        ${JNI_LIBRARIES}
		${PLATFORM_LIBS})
