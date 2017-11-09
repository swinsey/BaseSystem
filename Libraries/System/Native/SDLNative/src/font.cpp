//
// Created by Beppo on 02/02/17.
//

#include <map>
#include "font.h"

using namespace std;

namespace{
    map<int, TTF_Font*> fonts;
    int next_id = 1;

    TTF_Font* try_get_font(int font){
        if(fonts.find(font) == fonts.end()){
            return NULL;
        }

        return fonts[font];
    }
};

int font_load(const char* path, int size){
    TTF_Font* font = TTF_OpenFont(path, size);
    if(font == NULL){
        return FONT_ERROR;
    }

    int id = next_id;
    next_id++;
    fonts.insert(pair<int, TTF_Font*>(id, font));
    return id;
}
int font_unload(int font){
    TTF_Font* fnt = try_get_font(font);
    if(fnt == NULL){
        return FONT_INVALID_ID;
    }

    fonts.erase(font);
    TTF_CloseFont(fnt);
    return FONT_OK;
}

int font_get(int font, TTF_Font** out_font){
    TTF_Font* fnt = try_get_font(font);
    if(fnt == NULL){
        return FONT_INVALID_ID;
    }

    (*out_font) = fnt;
    return FONT_OK;
}