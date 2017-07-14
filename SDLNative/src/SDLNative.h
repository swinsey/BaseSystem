#ifndef _SDLNATIVE_SDLNATIVE_H_
#define _SDLNATIVE_SDLNATIVE_H_

#if defined(WIN32)
#if defined(SDLNative_EXPORTS)
#define SDLNEXPORT __declspec(dllexport)
#else
#define SDLNEXPORT __declspec(dllimport)
#endif
#else
#define SDLNEXPORT
#endif

#endif
