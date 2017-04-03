//
// Created by Beppo on 02/02/17.
//

#ifndef SDLNATIVE_LABEL_H
#define SDLNATIVE_LABEL_H

#include <SDL.h>

const int LABEL_OK = 0;
const int LABEL_ERROR = -1;
const int LABEL_INVALID_ID = -2;
const int LABEL_INVALID_FONT = -3;
const int LABEL_EMPTY_TEXT = -3;

int label_create(int font, const char* text, char r, char g, char b, char a, int width);
int label_destroy(int label);

int label_set_text(int label, const char* text);
int label_set_font(int label, int font);
int label_set_color(int label, char r, char g, char b, char a);
int label_set_width(int label, int width);

int label_get_width(int label);
int label_get_height(int label);

int label_get_alpha(int label, char* out_alpha);
int label_set_alpha(int label, char alpha);

int label_get_texture(int label, SDL_Texture** out_tex);

#endif //SDLNATIVE_LABEL_H
