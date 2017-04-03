//
// Created by Beppo on 01/02/17.
//

#include <map>
#include <SDL_image.h>
#include "texture.h"
#include "window.h"

using namespace std;

namespace{
    map<int, SDL_Texture*> textures = map<int, SDL_Texture*>();
    int next_id = 1;

    SDL_Texture* try_get_texture(int texture){
        if(textures.find(texture) == textures.end()){
            return NULL;
        }

        return textures[texture];
    }
};

int texture_load(const char* path){
    SDL_Surface* surf = IMG_Load(path);
    if(surf == NULL){
        return TEXTURE_ERROR;
    }

    SDL_Texture* tex = SDL_CreateTextureFromSurface(window_get_renderer(), surf);
    if(tex == NULL){
        return TEXTURE_ERROR;
    }

    SDL_FreeSurface(surf);
	SDL_SetTextureBlendMode(tex, SDL_BLENDMODE_BLEND);

    int id = next_id;
    next_id++;
    textures.insert(pair<int, SDL_Texture*>(id, tex));

    return id;
}
int texture_unload(int texture){
    SDL_Texture* tex = try_get_texture(texture);
    if(tex == NULL){
        return TEXTURE_INVALID_ID;
    }

    textures.erase(texture);
    SDL_DestroyTexture(tex);
    return TEXTURE_OK;
}

int texture_get_width(int texture){
    SDL_Texture* tex = try_get_texture(texture);
    if(tex == NULL){
        return TEXTURE_INVALID_ID;
    }

    Uint32 format;
    int access;
    int width;
    int height;
    SDL_QueryTexture(tex, &format, &access, &width, &height);
    return width;
}
int texture_get_height(int texture){
    SDL_Texture* tex = try_get_texture(texture);
    if(tex == NULL){
        return TEXTURE_INVALID_ID;
    }

    Uint32 format;
    int access;
    int width;
    int height;
    SDL_QueryTexture(tex, &format, &access, &width, &height);
    return height;
}

int texture_get_alpha(int texture, char* out_alpha) {
	SDL_Texture* tex = try_get_texture(texture);
	if (tex == NULL) {
		return TEXTURE_INVALID_ID;
	}

	Uint8 alpha;
	int res = SDL_GetTextureAlphaMod(tex, &alpha);
	(*out_alpha) = static_cast<char>(alpha);
	return res;

}
int texture_set_alpha(int texture, char alpha) {
	SDL_Texture* tex = try_get_texture(texture);
	if (tex == NULL) {
		return TEXTURE_INVALID_ID;
	}

	Uint8 a = static_cast<char>(alpha);
	return SDL_SetTextureAlphaMod(tex, a);
}

int texture_get(int texture, SDL_Texture** out_tex){
    SDL_Texture* tex = try_get_texture(texture);
    if(tex == NULL){
        return TEXTURE_INVALID_ID;
    }

    (*out_tex) = tex;
    return TEXTURE_OK;
}