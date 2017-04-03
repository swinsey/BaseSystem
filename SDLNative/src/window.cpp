//
// Created by Beppo on 01/02/17.
//

#include <SDL.h>
#include <SDL_image.h>
#include <SDL_ttf.h>
#include <iostream>
#include "window.h"

namespace{
    bool initialized = false;

    int window_width = 0;
    int window_height = 0;

    SDL_Window* window = NULL;
    SDL_Renderer* renderer = NULL;

    void create_rect(Rect* src, SDL_Rect* dst){
        dst->x = src->x;
        dst->y = src->y;
        dst->w = src->width;
        dst->h = src->height;
    }
};

int window_init(const char* title, int width, int height, bool show_cursor){
    if(initialized){
        return WINDOW_ALREADY_INITIALIZED;
    }

    window_width = 0;
    window_height = 0;

    if(SDL_Init(SDL_INIT_VIDEO | SDL_INIT_EVENTS) < 0){
        printf( "SDL could not initialize! SDL_Error: %s\n", SDL_GetError() );
        return WINDOW_FAILED_INIT_SDL;
    }else {
        window = SDL_CreateWindow(title,
                                  SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED,
                                  width, height,
                                  SDL_WINDOW_SHOWN);
        if (window == NULL){
            printf( "Window could not be created! SDL_Error: %s\n", SDL_GetError() );
            return WINDOW_FAILED_INIT_WINDOW;
        }

		SDL_ShowCursor(show_cursor ? SDL_ENABLE : SDL_DISABLE);

        renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);
        if(renderer == NULL) {
			printf( "Could not create renderer! SDL_Error: %s\n", SDL_GetError() );
            return WINDOW_FAILED_INIT_RENDERER;
        }

        int imgFlags = IMG_INIT_PNG;
        if(!(IMG_Init(imgFlags) & imgFlags)) {
            return WINDOW_FAILED_INIT_IMG;
        }

        if(TTF_Init() == -1){
            return WINDOW_FAILED_INIT_TTF;
        }
    }

    initialized = true;
    return WINDOW_OK;
}
int window_dispose(){
    SDL_DestroyWindow(window);
    IMG_Quit();
    SDL_Quit();
    return WINDOW_OK;
}

bool window_is_initialized(){
    return initialized;
}

int window_get_width(){
    return window_width;
}
int window_get_height(){
    return window_height;
}

SDL_Renderer* window_get_renderer(){
    return renderer;
}

void window_update_screen(){
    SDL_RenderPresent(renderer);
}
void window_set_draw_color(char r, char g, char b, char a){
    SDL_SetRenderDrawColor(renderer, r, g, b, a);
}
void window_clear(){
    SDL_RenderClear(renderer);
}
void window_draw_rect(Rect* rect, bool fill){
    SDL_Rect r;
    create_rect(rect, &r);

    if(fill){
        SDL_RenderFillRect(renderer, &r);
    }else{
        SDL_RenderDrawRect(renderer, &r);
    }
}
void window_draw_line(int x, int y, int x2, int y2){
    SDL_RenderDrawLine(renderer, x, y, x2, y2);
}
void window_draw_texture(SDL_Texture* tex, Rect* src, Rect* dst){
    SDL_Rect src_r;
    SDL_Rect dst_r;
    create_rect(src, &src_r);
    create_rect(dst, &dst_r);
    SDL_RenderCopy(renderer, tex, &src_r, &dst_r);
}
void window_draw_texture(SDL_Texture* tex, Rect* src, Rect* dst, double angle){
	SDL_Rect src_r;
	SDL_Rect dst_r;
	create_rect(src, &src_r);
	create_rect(dst, &dst_r);
	SDL_RenderCopyEx(renderer, tex, &src_r, &dst_r, angle, NULL, SDL_FLIP_NONE);
}

int window_poll_event(SDL_Event* out_event){
    SDL_PumpEvents();
    return SDL_PollEvent(out_event);
}
void window_get_mouse(int* out_x, int* out_y) {
	SDL_GetMouseState(out_x, out_y);
}