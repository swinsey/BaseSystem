#define GLEW_STATIC

#include <map>
#include <GL/glew.h>
#include "shader.h"

using namespace std;

SDLNEXPORT int shader_create(int shader_type) {
	GLint type = 0;
	switch (shader_type) {
	case SHADER_TYPE_VERTEX:
		type = GL_VERTEX_SHADER;
		break;
	case SHADER_TYPE_FRAGMENT:
		type = GL_FRAGMENT_SHADER;
		break;
	default:
		return -1;
	}

	GLuint shader = glCreateShader(type);
	return static_cast<int>(shader);
}
SDLNEXPORT void shader_delete(int shader) {
	glDeleteShader(shader);
}
SDLNEXPORT void shader_set_source(int shader, const char* source) {
	GLuint glShader = static_cast<GLuint>(shader);
	const GLchar* shaderSource[] = { source };

	glShaderSource(glShader, 1, shaderSource, NULL);
}
SDLNEXPORT int shader_compile(int shader) {
	GLuint glShader = static_cast<GLuint>(shader);

	glCompileShader(glShader);

	GLint compiled = GL_FALSE;
	glGetShaderiv(glShader, GL_COMPILE_STATUS, &compiled);
	if (compiled != GL_TRUE) {
		printf("Unable to compile vertex shader %d!\n", glShader);
		return -1;
	}
	return 0;
}

SDLNEXPORT int shader_program_create() {
	return static_cast<int>(glCreateProgram());
}
SDLNEXPORT void shader_program_attach(int program, int shader) {
	GLuint glProgram = static_cast<GLuint>(program);
	GLuint glShader = static_cast<GLuint>(shader);

	glAttachShader(glProgram, glShader);
}
SDLNEXPORT int shader_program_link(int program) {
	GLuint glProgram = static_cast<GLuint>(program);

	glLinkProgram(glProgram);

	GLint success = GL_FALSE;
	glGetProgramiv(glProgram, GL_LINK_STATUS, &success);
	if (success != GL_TRUE) {
		printf("Error linking program %d!\n", glProgram);
		return -1;
	}
	return 0;
}

SDLNEXPORT int shader_program_get_uniform_location(int program, const char* name) {
	GLuint glProgram = static_cast<GLuint>(program);

	GLint location = glGetUniformLocation(glProgram, name);
	return static_cast<int>(location);
}
SDLNEXPORT void shader_program_set_float(int location, float value) {
	glUniform1f(location, value);
}
SDLNEXPORT void shader_program_set_int(int location, int value) {
	glUniform1i(location, value);
}
SDLNEXPORT void shader_program_set_vector2f(int location, float x, float y) {
	glUniform2f(location, x, y);
}
SDLNEXPORT void shader_program_set_vector3f(int location, float x, float y, float z) {
	glUniform3f(location, x, y, z);
}
SDLNEXPORT void shader_program_set_vector4f(int location, float x, float y, float z, float w) {
	glUniform4f(location, x, y, z, w);
}

SDLNEXPORT void shader_program_use(int program) {
	glUseProgram(program);
}