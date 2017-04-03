package de.awesome.smarthome.td.gui;

/**
 * Created by beppo on 05/02/17.
 */
abstract class SDLEventTypes {
    public static int SDL_QUIT;

    //Mouse
    public static int SDL_MOUSEMOTION;
    public static int SDL_MOUSEBUTTONDOWN;
    public static int SDL_MOUSEBUTTONUP;

    //Touch
    public static int SDL_FINGERMOTION;
    public static int SDL_FINGERDOWN;
    public static int SDL_FINGERUP;
}
