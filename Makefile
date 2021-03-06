# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.7

# Default target executed when no arguments are given to make.
default_target: all

.PHONY : default_target

# Allow only one "make -f Makefile2" at a time, but pass parallelism.
.NOTPARALLEL:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /media/silveryard/HDD/Projects/BaseSystem/SDLNative

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /media/silveryard/HDD/Projects/BaseSystem

#=============================================================================
# Targets provided globally by CMake.

# Special rule for the target edit_cache
edit_cache:
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --cyan "No interactive CMake dialog available..."
	/usr/bin/cmake -E echo No\ interactive\ CMake\ dialog\ available.
.PHONY : edit_cache

# Special rule for the target edit_cache
edit_cache/fast: edit_cache

.PHONY : edit_cache/fast

# Special rule for the target rebuild_cache
rebuild_cache:
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --cyan "Running CMake to regenerate build system..."
	/usr/bin/cmake -H$(CMAKE_SOURCE_DIR) -B$(CMAKE_BINARY_DIR)
.PHONY : rebuild_cache

# Special rule for the target rebuild_cache
rebuild_cache/fast: rebuild_cache

.PHONY : rebuild_cache/fast

# The main all target
all: cmake_check_build_system
	$(CMAKE_COMMAND) -E cmake_progress_start /media/silveryard/HDD/Projects/BaseSystem/CMakeFiles /media/silveryard/HDD/Projects/BaseSystem/CMakeFiles/progress.marks
	$(MAKE) -f CMakeFiles/Makefile2 all
	$(CMAKE_COMMAND) -E cmake_progress_start /media/silveryard/HDD/Projects/BaseSystem/CMakeFiles 0
.PHONY : all

# The main clean target
clean:
	$(MAKE) -f CMakeFiles/Makefile2 clean
.PHONY : clean

# The main clean target
clean/fast: clean

.PHONY : clean/fast

# Prepare targets for installation.
preinstall: all
	$(MAKE) -f CMakeFiles/Makefile2 preinstall
.PHONY : preinstall

# Prepare targets for installation.
preinstall/fast:
	$(MAKE) -f CMakeFiles/Makefile2 preinstall
.PHONY : preinstall/fast

# clear depends
depend:
	$(CMAKE_COMMAND) -H$(CMAKE_SOURCE_DIR) -B$(CMAKE_BINARY_DIR) --check-build-system CMakeFiles/Makefile.cmake 1
.PHONY : depend

#=============================================================================
# Target rules for targets named SDLNative

# Build rule for target.
SDLNative: cmake_check_build_system
	$(MAKE) -f CMakeFiles/Makefile2 SDLNative
.PHONY : SDLNative

# fast build rule for target.
SDLNative/fast:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/build
.PHONY : SDLNative/fast

src/font.o: src/font.cpp.o

.PHONY : src/font.o

# target to build an object file
src/font.cpp.o:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/font.cpp.o
.PHONY : src/font.cpp.o

src/font.i: src/font.cpp.i

.PHONY : src/font.i

# target to preprocess a source file
src/font.cpp.i:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/font.cpp.i
.PHONY : src/font.cpp.i

src/font.s: src/font.cpp.s

.PHONY : src/font.s

# target to generate assembly for a file
src/font.cpp.s:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/font.cpp.s
.PHONY : src/font.cpp.s

src/jni/de_silveryard_basesystem_gui_SDLFont.o: src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.o

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLFont.o

# target to build an object file
src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.o:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.o
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.o

src/jni/de_silveryard_basesystem_gui_SDLFont.i: src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.i

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLFont.i

# target to preprocess a source file
src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.i:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.i
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.i

src/jni/de_silveryard_basesystem_gui_SDLFont.s: src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.s

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLFont.s

# target to generate assembly for a file
src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.s:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.s
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLFont.cpp.s

src/jni/de_silveryard_basesystem_gui_SDLLabel.o: src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.o

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLLabel.o

# target to build an object file
src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.o:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.o
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.o

src/jni/de_silveryard_basesystem_gui_SDLLabel.i: src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.i

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLLabel.i

# target to preprocess a source file
src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.i:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.i
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.i

src/jni/de_silveryard_basesystem_gui_SDLLabel.s: src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.s

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLLabel.s

# target to generate assembly for a file
src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.s:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.s
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLLabel.cpp.s

src/jni/de_silveryard_basesystem_gui_SDLTexture.o: src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.o

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLTexture.o

# target to build an object file
src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.o:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.o
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.o

src/jni/de_silveryard_basesystem_gui_SDLTexture.i: src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.i

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLTexture.i

# target to preprocess a source file
src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.i:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.i
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.i

src/jni/de_silveryard_basesystem_gui_SDLTexture.s: src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.s

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLTexture.s

# target to generate assembly for a file
src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.s:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.s
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLTexture.cpp.s

src/jni/de_silveryard_basesystem_gui_SDLWindow.o: src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.o

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLWindow.o

# target to build an object file
src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.o:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.o
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.o

src/jni/de_silveryard_basesystem_gui_SDLWindow.i: src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.i

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLWindow.i

# target to preprocess a source file
src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.i:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.i
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.i

src/jni/de_silveryard_basesystem_gui_SDLWindow.s: src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.s

.PHONY : src/jni/de_silveryard_basesystem_gui_SDLWindow.s

# target to generate assembly for a file
src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.s:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.s
.PHONY : src/jni/de_silveryard_basesystem_gui_SDLWindow.cpp.s

src/label.o: src/label.cpp.o

.PHONY : src/label.o

# target to build an object file
src/label.cpp.o:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/label.cpp.o
.PHONY : src/label.cpp.o

src/label.i: src/label.cpp.i

.PHONY : src/label.i

# target to preprocess a source file
src/label.cpp.i:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/label.cpp.i
.PHONY : src/label.cpp.i

src/label.s: src/label.cpp.s

.PHONY : src/label.s

# target to generate assembly for a file
src/label.cpp.s:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/label.cpp.s
.PHONY : src/label.cpp.s

src/texture.o: src/texture.cpp.o

.PHONY : src/texture.o

# target to build an object file
src/texture.cpp.o:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/texture.cpp.o
.PHONY : src/texture.cpp.o

src/texture.i: src/texture.cpp.i

.PHONY : src/texture.i

# target to preprocess a source file
src/texture.cpp.i:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/texture.cpp.i
.PHONY : src/texture.cpp.i

src/texture.s: src/texture.cpp.s

.PHONY : src/texture.s

# target to generate assembly for a file
src/texture.cpp.s:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/texture.cpp.s
.PHONY : src/texture.cpp.s

src/window.o: src/window.cpp.o

.PHONY : src/window.o

# target to build an object file
src/window.cpp.o:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/window.cpp.o
.PHONY : src/window.cpp.o

src/window.i: src/window.cpp.i

.PHONY : src/window.i

# target to preprocess a source file
src/window.cpp.i:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/window.cpp.i
.PHONY : src/window.cpp.i

src/window.s: src/window.cpp.s

.PHONY : src/window.s

# target to generate assembly for a file
src/window.cpp.s:
	$(MAKE) -f CMakeFiles/SDLNative.dir/build.make CMakeFiles/SDLNative.dir/src/window.cpp.s
.PHONY : src/window.cpp.s

# Help Target
help:
	@echo "The following are some of the valid targets for this Makefile:"
	@echo "... all (the default if no target is provided)"
	@echo "... clean"
	@echo "... depend"
	@echo "... edit_cache"
	@echo "... rebuild_cache"
	@echo "... SDLNative"
	@echo "... src/font.o"
	@echo "... src/font.i"
	@echo "... src/font.s"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLFont.o"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLFont.i"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLFont.s"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLLabel.o"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLLabel.i"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLLabel.s"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLTexture.o"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLTexture.i"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLTexture.s"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLWindow.o"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLWindow.i"
	@echo "... src/jni/de_silveryard_basesystem_gui_SDLWindow.s"
	@echo "... src/label.o"
	@echo "... src/label.i"
	@echo "... src/label.s"
	@echo "... src/texture.o"
	@echo "... src/texture.i"
	@echo "... src/texture.s"
	@echo "... src/window.o"
	@echo "... src/window.i"
	@echo "... src/window.s"
.PHONY : help



#=============================================================================
# Special targets to cleanup operation of make.

# Special rule to run CMake to check the build system integrity.
# No rule that depends on this can have commands that come from listfiles
# because they might be regenerated.
cmake_check_build_system:
	$(CMAKE_COMMAND) -H$(CMAKE_SOURCE_DIR) -B$(CMAKE_BINARY_DIR) --check-build-system CMakeFiles/Makefile.cmake 0
.PHONY : cmake_check_build_system

