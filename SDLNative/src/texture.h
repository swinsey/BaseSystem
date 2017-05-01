//
// Created by Beppo on 01/02/17.
//

#ifndef SDLNATIVE_TEXTURE_H
#define SDLNATIVE_TEXTURE_H

#include <SDL.h>

const int TEXTURE_OK = 0;
const int TEXTURE_ERROR = -1;
const int TEXTURE_INVALID_ID = -2;

int texture_load(const char* path);
int texture_unload(int texture);

int texture_get_width(int texture);
int texture_get_height(int texture);

int texture_get_alpha(int texture, char* out_alpha);
int texture_set_alpha(int texture, char alpha);

int texture_get(int texture, SDL_Texture** out_tex);

#endif //SDLNATIVE_TEXTURE_H
