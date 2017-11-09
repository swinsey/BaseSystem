//
// Created by Silveryard on 7/12/17.
//

#ifndef SDLNATIVE_SHADER_H
#define SDLNATIVE_SHADER_H

#include "SDLNative.h"

const int SHADER_TYPE_VERTEX = 1;
const int SHADER_TYPE_FRAGMENT = 2;

SDLNEXPORT int shader_create(int shader_type);
SDLNEXPORT void shader_delete(int shader);
SDLNEXPORT void shader_set_source(int shader, const char* source);
SDLNEXPORT int shader_compile(int shader);

SDLNEXPORT int shader_program_create();
SDLNEXPORT void shader_program_attach(int program, int shader);
SDLNEXPORT int shader_program_link(int program);

SDLNEXPORT int shader_program_get_uniform_location(int program, const char* name);
SDLNEXPORT void shader_program_set_float(int location, float value);
SDLNEXPORT void shader_program_set_int(int location, int value);
SDLNEXPORT void shader_program_set_vector2f(int location, float x, float y);
SDLNEXPORT void shader_program_set_vector3f(int location, float x, float y, float z);
SDLNEXPORT void shader_program_set_vector4f(int location, float x, float y, float z, float w);

SDLNEXPORT void shader_program_use(int program);

#endif
