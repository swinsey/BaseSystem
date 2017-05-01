//
// Created by Beppo on 02/02/17.
//

#ifndef SDLNATIVE_FONT_H
#define SDLNATIVE_FONT_H

#include <SDL_ttf.h>

const int FONT_OK = 0;
const int FONT_ERROR = -1;
const int FONT_INVALID_ID = -2;

int font_load(const char* path, int size);
int font_unload(int font);

int font_get(int font, TTF_Font** out_font);

#endif //SDLNATIVE_FONT_H
