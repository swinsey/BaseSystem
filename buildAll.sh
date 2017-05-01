#!/bin/sh

APF_VERSION=1.0-SNAPSHOT
APFCREATOR_VERSION=1.0-SNAPSHOT
APPSDK_VERSION=1.0-SNAPSHOT
BASESYSTEM_VERSION=1.0-SNAPSHOT
TRANSPORT_VERSION=1.0-SNAPSHOT

ARCHITECTURE=$(uname -p)

# Defining Directories
REPO_PATH="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
OUT_PATH=$REPO_PATH/build
BUILD_PATH=$OUT_PATH/bin
TOOLS_PATH=$BUILD_PATH/tools
LIBS_PATH=$BUILD_PATH/libs
STAGING_AREA_PATH=$OUT_PATH/staging_area

# Cleaning Up
echo "Cleaning Up"
if [ -d "$BUILD_PATH" ]; then
  rm -rf "$BUILD_PATH"
fi

# Creating Directories
echo "Creating Directories"
if [ ! -d "$OUT_PATH" ]; then
  mkdir "$OUT_PATH"
fi
if [ ! -d "$BUILD_PATH" ]; then
  mkdir "$BUILD_PATH"
fi
if [ ! -d "$STAGING_AREA_PATH" ]; then
  mkdir "$STAGING_AREA_PATH"
fi
if [ ! -d "$TOOLS_PATH" ]; then
  mkdir "$TOOLS_PATH"
fi
if [ ! -d "$LIBS_PATH" ]; then
  mkdir "$LIBS_PATH"
fi

# Building SDLNative
echo "Building SDLNative"

SDLNATIVE_PATH=$REPO_PATH/SDLNative
SDLNATIVE_DST_DIR_PATH=$LIBS_PATH/SDLNative
SDLNATIVE_DST_PATH=$SDLNATIVE_DST_DIR_PATH/libSDLNative.so
SDLNATIVE_STAGING_PATH=$STAGING_AREA_PATH/SDLNative
SDLNATIVE_BIN_PATH=$SDLNATIVE_STAGING_PATH/libSDLNative.so


if [ ! -d "$SDLNATIVE_DST_DIR_PATH" ]; then
  mkdir "$SDLNATIVE_DST_DIR_PATH"
fi
if [ ! -d "$SDLNATIVE_STAGING_PATH" ]; then
  mkdir "$SDLNATIVE_STAGING_PATH"
fi

cd "$SDLNATIVE_STAGING_PATH"
cmake "$SDLNATIVE_PATH"
make -j 4
cp "$SDLNATIVE_BIN_PATH" "$SDLNATIVE_DST_PATH"

# Building FmodNative
echo "Building FmodNative"

FMODNATIVE_PATH=$REPO_PATH/FmodNative
FMODNATIVE_DST_DIR_PATH=$LIBS_PATH/FmodNative
FMODNATIVE_DST_PATH=$FMODNATIVE_DST_DIR_PATH/libFmodNative.so
FMODNATIVE_STAGING_PATH=$STAGING_AREA_PATH/FmodNative
FMODNATIVE_BIN_PATH=$FMODNATIVE_STAGING_PATH/libFmodNative.so
FMOD_SRC_PATH=$FMODNATIVE_PATH/fmod/linux/lib/$ARCHITECTURE/libfmodL.so.9.3
FMOD_DST_PATH=$FMODNATIVE_DST_DIR_PATH/libfmodL.so.9.3

if [ ! -d "$FMODNATIVE_DST_DIR_PATH" ]; then
  mkdir "$FMODNATIVE_DST_DIR_PATH"
fi
if [ ! -d "$FMODNATIVE_STAGING_PATH" ]; then
  mkdir "$FMODNATIVE_STAGING_PATH"
fi

cd "$FMODNATIVE_STAGING_PATH"
cmake "$FMODNATIVE_PATH"
make -j 4
cp "$FMODNATIVE_BIN_PATH" "$FMODNATIVE_DST_PATH"
cp "$FMOD_SRC_PATH" "$FMOD_DST_PATH"

# Building Java Projects
echo "Building Java Projects"

mvn -f "$REPO_PATH/pom.xml" install

# Copying AppManager
echo "Copying AppManager"

APPMANAGER_SRC_PATH=$REPO_PATH/AppManager/target/jfx/native/AppManager
APPMANAGER_DST_PATH=$TOOLS_PATH/AppManager

cp -r "$APPMANAGER_SRC_PATH" "$APPMANAGER_DST_PATH"

# Copying LogViewer
echo "Copying LogViewer"

LOGVIEWER_SRC_PATH=$REPO_PATH/LogViewer/target/jfx/native/logviewer
LOGVIEWER_DST_PATH=$TOOLS_PATH/LogViewer

cp -r "$LOGVIEWER_SRC_PATH" "$LOGVIEWER_DST_PATH"

# Copying APFCreator
echo "Copying APFCreator"

APFCREATOR_SRC_PATH=$REPO_PATH/APFCreator/target/APFCreator-$APFCREATOR_VERSION-jar-with-dependencies.jar
APFCREATOR_DST_PATH=$TOOLS_PATH/APFCreator.jar

cp "$APFCREATOR_SRC_PATH" "$APFCREATOR_DST_PATH"

# Copying APF
echo "Copying APF"

APF_SRC_PATH=$REPO_PATH/APF/target/APF-$APF_VERSION.jar
APF_DST_PATH=$LIBS_PATH/APF.jar

# Copying AppSDK
echo "Copying AppSDK"

APPSDK_SRC_NODEP_PATH=$REPO_PATH/AppSDK/target/AppSDK-$APPSDK_VERSION.jar
APPSDK_DST_NODEP_PATH=$LIBS_PATH/AppSDK-NoDependencies.jar
APPSDK_SRC_DEP_PATH=$REPO_PATH/AppSDK/target/AppSDK-$APPSDK_VERSION-jar-with-dependencies.jar
APPSDK_DST_DEP_PATH=$LIBS_PATH/AppSDK.jar

cp "$APPSDK_SRC_NODEP_PATH" "$APPSDK_DST_NODEP_PATH"
cp "$APPSDK_SRC_DEP_PATH" "$APPSDK_DST_DEP_PATH"

# Copying BaseSystem
echo "Copying BaseSystem"

BASESYSTEM_SRC_NODEP_PATH=$REPO_PATH/BaseSystem/target/BaseSystem-$BASESYSTEM_VERSION.jar
BASESYSTEM_DST_NODEP_PATH=$LIBS_PATH/BaseSystem-NoDependencies.jar
BASESYSTEM_SRC_DEP_PATH=$REPO_PATH/BaseSystem/target/BaseSystem-$BASESYSTEM_VERSION-jar-with-dependencies.jar
BASESYSTEM_DST_DEP_PATH=$LIBS_PATH/BaseSystem.jar

cp "$BASESYSTEM_SRC_NODEP_PATH" "$BASESYSTEM_DST_NODEP_PATH"
cp "$BASESYSTEM_SRC_DEP_PATH" "$BASESYSTEM_DST_DEP_PATH"

# Copying Transport
echo "Copying Transport"

TRANSPORT_SRC_PATH=$REPO_PATH/Transport/target/Transport-$TRANSPORT_VERSION.jar
TRANSPORT_DST_PATH=$LIBS_PATH/Transport.jar

cp "$TRANSPORT_SRC_PATH" "$TRANSPORT_DST_PATH"
