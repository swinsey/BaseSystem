#ifndef _LIBDISPLAY_DISPLAY_OS_H_
#define _LIBDISPLAY_DISPLAY_OS_H_

#if defined(_arm_)
#define BS_DISPLAY_USE_DISPMANX
#elif defined(__WIN32) || defined (WIN32) || defined(__WIN32__)
#define BS_DISPLAY_USE_WINDOWS
#else
#define BS_DISPLAY_USE_FALLBACK
#endif

#endif