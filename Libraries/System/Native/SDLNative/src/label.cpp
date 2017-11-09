//
// Created by Beppo on 02/02/17.
//

#include <SDL.h>
#include <SDL_ttf.h>
#include <map>
#include "window.h"
#include "label.h"
#include "font.h"

using namespace std;

namespace{
    struct Label{
        int font;
        SDL_Texture* texture;
        const char* text;
        char r, g, b, a;
		int width;
    };

    map<int, Label*> labels;
    int next_id = 1;

    Label* try_get_label(int label){
        if(labels.find(label) == labels.end()){
            return NULL;
        }

        return labels[label];
    }

    int render_label(Label* label){
        SDL_Color col;
        col.r = label->r;
        col.g = label->g;
        col.b = label->b;
        col.a = label->a;

        TTF_Font* fnt;
        int fnt_res = font_get(label->font, &fnt);
        if(fnt_res != FONT_OK || fnt == NULL){
            return LABEL_INVALID_FONT;
        }
        if(strlen(label->text) == 0){
            return LABEL_EMPTY_TEXT;
        }
        
        SDL_Surface* surf = TTF_RenderText_Blended_Wrapped(fnt, label->text, col, label->width);
        if(surf == NULL){
            return LABEL_ERROR;
        }

        SDL_Texture* tex = SDL_CreateTextureFromSurface(window_get_renderer(), surf);
        SDL_FreeSurface(surf);
        if(tex == NULL){
            return LABEL_ERROR;
        }
		SDL_SetTextureBlendMode(tex, SDL_BLENDMODE_BLEND);
		Uint8 a = static_cast<char>(col.a);
		SDL_SetTextureAlphaMod(tex, a);

        if(label->texture != NULL){
            SDL_DestroyTexture(label->texture);
        }
        label->texture = tex;
        return LABEL_OK;
    }
}

int label_create(int font, const char* text, char r, char g, char b, char a, int width){
    if(strlen(text) == 0){
        return LABEL_EMPTY_TEXT;
    }
    TTF_Font* fnt;
    int fnt_res = font_get(font, &fnt);
    if(fnt_res != FONT_OK || fnt == NULL){
        return LABEL_INVALID_FONT;
    }

    Label* label = new Label();
    label->font = font;
    label->texture = NULL;
    label->text = text;
    label->r = r;
    label->g = g;
    label->b = b;
    label->a = a;
	label->width = width;
    int render_res = render_label(label);
    if(render_res != LABEL_OK){
        delete label;
        return render_res;
    }

    int id = next_id;
    next_id++;
    labels.insert(pair<int, Label*>(id, label));
    return id;
}
int label_destroy(int label){
    Label* lbl = try_get_label(label);
    if(lbl == NULL){
        return LABEL_INVALID_ID;
    }

    labels.erase(label);
    SDL_DestroyTexture(lbl->texture);
    delete lbl;
    return LABEL_OK;
}

int label_set_text(int label, const char* text){
    Label* lbl = try_get_label(label);
    if(lbl == NULL){
        return LABEL_INVALID_ID;
    }

    if(strlen(text) == 0){
        return LABEL_EMPTY_TEXT;
    }

    lbl->text = text;
    return render_label(lbl);
}
int label_set_font(int label, int font){
    Label* lbl = try_get_label(label);
    if(lbl == NULL){
        return LABEL_INVALID_ID;
    }

    TTF_Font* fnt;
    if(font_get(font, &fnt) != FONT_OK){
        return LABEL_INVALID_FONT;
    }

    lbl->font = font;
    return render_label(lbl);
}
int label_set_color(int label, char r, char g, char b, char a){
    Label* lbl = try_get_label(label);
    if(lbl == NULL){
        return LABEL_INVALID_ID;
    }

    lbl->r = r;
    lbl->g = g;
    lbl->b = b;
    lbl->a = a;
    return render_label(lbl);
}
int label_set_width(int label, int width) {
	Label* lbl = try_get_label(label);
	if (lbl == NULL) {
		return LABEL_INVALID_ID;
	}

	lbl->width = width;
	return render_label(lbl);
}

int label_get_width(int label){
    Label* lbl = try_get_label(label);
    if(lbl == NULL){
        return LABEL_INVALID_ID;
    }

    Uint32 format;
    int access;
    int width;
    int height;
    SDL_QueryTexture(lbl->texture, &format, &access, &width, &height);
    return width;
}
int label_get_height(int label){
    Label* lbl = try_get_label(label);
    if(lbl == NULL){
        return LABEL_INVALID_ID;
    }

    Uint32 format;
    int access;
    int width;
    int height;
    SDL_QueryTexture(lbl->texture, &format, &access, &width, &height);
    return height;
}

int label_get_alpha(int label, char* out_alpha) {
	Label* lbl = try_get_label(label);
	if (lbl == NULL) {
		return LABEL_INVALID_ID;
	}

	(*out_alpha) = lbl->a;
	return LABEL_OK;
}
int label_set_alpha(int label, char alpha) {
	Label* lbl = try_get_label(label);
	if (lbl == NULL) {
		return LABEL_INVALID_ID;
	}

	lbl->a = alpha;
	return render_label(lbl);
}

int label_get_texture(int label, SDL_Texture** out_tex){
    Label* lbl = try_get_label(label);
    if(lbl == NULL){
        return LABEL_INVALID_ID;
    }

    (*out_tex) = lbl->texture;
    return LABEL_OK;
}