package de.awesome.smarthome.td.sound;

/**
 * Created by Sebif on 25.03.2017.
 */
public abstract class FmodInitFlags {
    public static final int FMOD_INIT_NORMAL = 0x00000000;
    public static final int FMOD_INIT_STREAM_FROM_UPDATE = 0x00000001;
    public static final int FMOD_INIT_MIX_FROM_UPDATE = 0x00000002;
    public static final int FMOD_INIT_3D_RIGHTHANDED = 0x00000004;
    public static final int FMOD_CHANNEL_LOWPASS = 0x00000100;
    public static final int FMOD_CHANNEL_DISTANCEFILTER = 0x00000200;
    public static final int FMOD_PROFILE_ENABLE = 0x00010000;
    public static final int FMOD_INIT_VOL0_BECOMES_VIRTUAL = 0x00020000;
    public static final int FMOD_INIT_GEOMETRY_USECLOSEST = 0x00040000;
    public static final int FMOD_INIT_PREFER_DOLBY_DOWNMIX = 0x00080000;
    public static final int FMOD_INIT_THREAD_UNSAFE = 0x00100000;
    public static final int FMOD_INIT_PROFILE_METER_ALL = 0x00200000;
}
