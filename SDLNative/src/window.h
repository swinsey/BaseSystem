//
// Created by Beppo on 01/02/17.
//

#ifndef SDLNATIVE_WINDOW_H
#define SDLNATIVE_WINDOW_H

#include <SDL.h>

const int WINDOW_OK = 0;
const int WINDOW_ALREADY_INITIALIZED = -1;
const int WINDOW_FAILED_INIT_SDL = -2;
const int WINDOW_FAILED_INIT_WINDOW = -3;
const int WINDOW_FAILED_INIT_RENDERER = -4;
const int WINDOW_FAILED_INIT_IMG = -5;
const int WINDOW_FAILED_INIT_TTF = -6;

struct Rect{
    int x;
    int y;
    int width;
    int height;
};

int window_init(const char* title, int width, int height, bool show_cursor);
int window_dispose();

bool window_is_initialized();

int window_get_width();
int window_get_height();

SDL_Renderer* window_get_renderer();

void window_update_screen();
void window_set_draw_color(char r, char g, char b, char a);
void window_clear();
void window_draw_rect(Rect* rect, bool fill);
void window_draw_line(int x, int y, int x2, int y2);
void window_draw_texture(SDL_Texture* tex, Rect* src, Rect* dst);
void window_draw_texture(SDL_Texture* tex, Rect* src, Rect* dst, double angle);

int window_poll_event(SDL_Event* out_event);
void window_get_mouse(int* out_x, int* out_y);

#endif //SDLNATIVE_WINDOW_H
