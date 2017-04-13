package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 25.03.2017.
 */
public abstract class FmodInitFlags {
    /**
     * FMOD_INIT_NORMAL
     */
    public static final int FMOD_INIT_NORMAL = 0x00000000;
    /**
     * FMOD_INIT_STREAM_FROM_UPDATE
     */
    public static final int FMOD_INIT_STREAM_FROM_UPDATE = 0x00000001;
    /**
     * FMOD_INIT_MIX_FROM_UPDATE
     */
    public static final int FMOD_INIT_MIX_FROM_UPDATE = 0x00000002;
    /**
     * FMOD_INIT_3D_RIGHTHANDED
     */
    public static final int FMOD_INIT_3D_RIGHTHANDED = 0x00000004;
    /**
     * FMOD_CHANNEL_LOWPASS
     */
    public static final int FMOD_CHANNEL_LOWPASS = 0x00000100;
    /**
     * FMOD_CHANNEL_DISTANCEFILTER
     */
    public static final int FMOD_CHANNEL_DISTANCEFILTER = 0x00000200;
    /**
     * FMOD_PROFILE_ENABLE
     */
    public static final int FMOD_PROFILE_ENABLE = 0x00010000;
    /**
     * FMOD_INIT_VOL0_BECOMES_VIRTUAL
     */
    public static final int FMOD_INIT_VOL0_BECOMES_VIRTUAL = 0x00020000;
    /**
     * FMOD_INIT_GEOMETRY_USECLOSEST
     */
    public static final int FMOD_INIT_GEOMETRY_USECLOSEST = 0x00040000;
    /**
     * FMOD_INIT_PREFER_DOLBY_DOWNMIX
     */
    public static final int FMOD_INIT_PREFER_DOLBY_DOWNMIX = 0x00080000;
    /**
     * FMOD_INIT_THREAD_UNSAFE
     */
    public static final int FMOD_INIT_THREAD_UNSAFE = 0x00100000;
    /**
     * FMOD_INIT_PROFILE_METER_ALL
     */
    public static final int FMOD_INIT_PROFILE_METER_ALL = 0x00200000;
}
