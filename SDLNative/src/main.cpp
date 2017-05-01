#include <iostream>
#include "window.h"

const int SCREEN_WIDTH = 800;
const int SCREEN_HEIGHT = 480;

int main(int argc, char* args[]){
    if(window_init("Hey, Sailor!", SCREEN_WIDTH, SCREEN_HEIGHT, true) < 0){
        printf("Failed to init window!");
        return -1;
    }

    bool quit = false;
    SDL_Event e;
    while(!quit){
        while(SDL_PollEvent(&e)){
            printf("Got Event: %i\n", e.type);
            if(e.type == SDL_QUIT){
                quit = true;
            }
        }
    }

    return 0;
}
