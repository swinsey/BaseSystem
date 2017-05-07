apt-get install libfreetype6-dev autoconf automake openjdk-8-jdk maven xorg-dev openjfx libgl1-mesa-dev libasound2-dev doxygen cloc

# Defining Directories
REPO_PATH=$(dirname "$0")
STAGING_AREA_PATH=$REPO_PATH/build/staging_area

# Creating Directories
echo "Creating Directories"
if [ ! -d "$STAGING_AREA_PATH" ]; then
  mkdir "$STAGING_AREA_PATH"
fi

SDLNATIVE_PATH=$REPO_PATH/SDLNative
SDL2_SRC_PATH=$SDLNATIVE_PATH/sdl/linux/SDL2
SDL2_STAGING_PATH=$STAGING_AREA_PATH/SDL2
SDLIMG_SRC_PATH=$SDLNATIVE_PATH/sdl/linux/SDL2_image
SDLIMG_STAGING_PATH=$STAGING_AREA_PATH/SDL2_image
SDLTTF_SRC_PATH=$SDLNATIVE_PATH/sdl/linux/SDL2_ttf
SDLTTF_STAGING_PATH=$STAGING_AREA_PATH/SDL2_ttf

if [ ! -d "$SDL2_STAGING_PATH" ]; then
  mkdir "$SDL2_STAGING_PATH"
fi
if [ ! -d "$SDLIMG_STAGING_PATH" ]; then
  mkdir "$SDLIMG_STAGING_PATH"
fi
if [ ! -d "$SDLTTF_STAGING_PATH" ]; then
  mkdir "$SDLTTF_STAGING_PATH"
fi

# Build SDL2
cd "$SDL2_SRC_PATH"
cmake -H. -B$SDL2_STAGING_PATH
cd "$SDL2_STAGING_PATH"
make -j 4
make install

# Build SDL2_image
cd "$SDLIMG_SRC_PATH"
./autogen.sh
cd "$SDLIMG_STAGING_PATH"
"$SDLIMG_SRC_PATH/configure"
make -j 4
make install

# Build SDL2_ttf
cd "$SDLTTF_SRC_PATH"
./autogen.sh
cd "$SDLTTF_STAGING_PATH"
"$SDLTTF_SRC_PATH/configure"
make -j 4
make install

cd "$SDL2_SRC_PATH"
git checkout .
git clean -f
git clean -fd
cd "$SDLIMG_SRC_PATH"
git checkout .
git clean -f
git clean -fd
cd "$SDLTTF_SRC_PATH"
git checkout .
git clean -f
git clean -fd
