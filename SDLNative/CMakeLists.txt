cmake_minimum_required(VERSION 3.3)
project(SDLNative)

# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/build")
# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/bin/")
message(STATUS "Runtime: ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}")

if(WIN32)
	set(SDL2_INCLUDE_DIR ${PROJECT_SOURCE_DIR}/sdl/win/debug/sdl2/include)
	set(SDL2_LIBRARY ${PROJECT_SOURCE_DIR}/sdl/win/debug/sdl2/lib/x64/SDL2.lib)
	
	set(SDL2_IMAGE_INCLUDE_DIR ${PROJECT_SOURCE_DIR}/sdl/win/debug/sdl2_image/include)
	set(SDL2_IMAGE_LIBRARIES ${PROJECT_SOURCE_DIR}/sdl/win/debug/sdl2_image/lib/x64/SDL2_image.lib)
	
	set(SDL2_TTF_INCLUDE_DIR ${PROJECT_SOURCE_DIR}/sdl/win/debug/sdl2_ttf/include)
	set(SDL2_TTF_LIBRARIES ${PROJECT_SOURCE_DIR}/sdl/win/debug/sdl2_ttf/lib/x64/SDL2_ttf.lib)
else()
	# includes cmake/FindSDL2.cmake
	set(CMAKE_MODULE_PATH ${PROJECT_SOURCE_DIR}/cmake)
	find_package(SDL2 REQUIRED)
	find_package(SDL2_image REQUIRED)
	find_package(SDL2_ttf REQUIRED)
endif(WIN32)

find_package(JNI)

include_directories(
        ${SDL2_INCLUDE_DIR}
        ${SDL2_IMAGE_INCLUDE_DIR}
        ${SDL2_TTF_INCLUDE_DIR}
        ${JNI_INCLUDE_DIRS})

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
	src/window.h 
	src/window.cpp 
	src/texture.h 
	src/texture.cpp 
	src/font.h 
	src/font.cpp 
	src/label.h 
	src/label.cpp 
	src/jni/de_silveryard_basesystem_gui_SDLWindow.h 
	src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp 
	src/jni/de_silveryard_basesystem_gui_SDLTexture.h 
	src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp 
	src/jni/de_silveryard_basesystem_gui_SDLFont.h 
	src/jni/de_silveryard_basesystem_gui_SDLFont.cpp 
	src/jni/de_silveryard_basesystem_gui_SDLLabel.h 
	src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp)

add_library(SDLNative SHARED ${SOURCE_FILES})
target_link_libraries(SDLNative
        ${SDL2_LIBRARY}
        ${SDL2_IMAGE_LIBRARIES}
        ${SDL2_TTF_LIBRARIES}
        ${JNI_LIBRARIES})