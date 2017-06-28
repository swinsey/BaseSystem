cmake_minimum_required(VERSION 3.3)
project(Display)
project(DisplaySys)

# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/build")
# set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/bin/")
message(STATUS "Runtime: ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}")

set(PLATFORM_LIBS )
if(WIN32)
else()
	#Raspberrypi Platform
	if(EXISTS "/opt/vc/include/bcm_host.h") 
		include_directories(/opt/vc/include)
		link_directories(/opt/vc/lib)
		set(PLATFORM_LIBS bcm_host)
		add_definitions(-DDISPMANX)
	endif()
endif()

set (CMAKE_CXX_STANDARD 11)
find_package(JNI)
include_directories(${JNI_INCLUDE_DIRS})

include_directories(
${PROJECT_SOURCE_DIR}/../LibBS/include
${PROJECT_SOURCE_DIR}/../3rdParty/boost/
${PROJECT_SOURCE_DIR}/include
)
link_directories(${PROJECT_SOURCE_DIR}/../LibBS/build/bin)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
	src/display_base.cpp
	src/display_dispmanx.cpp
	src/display_fallback.cpp
	src/display_windows.cpp
	src/de_silveryard_basesystem_libdisplayjava_Display.cpp
	src/de_silveryard_basesystem_libdisplaysysjava_DisplaySys.cpp
)

add_library(Display SHARED ${SOURCE_FILES})
target_link_libraries(Display
		BS
        ${JNI_LIBRARIES}
		${PLATFORM_LIBS})

		
add_library(DisplaySys SHARED ${SOURCE_FILES})
target_compile_definitions(DisplaySys PUBLIC BS_DISPLAY_SYS=1)
target_link_libraries(DisplaySys
		BS
        ${JNI_LIBRARIES}
		${PLATFORM_LIBS})