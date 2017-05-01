package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 27.03.2017.
 */
public abstract class FmodMode {
    /**
     * FMOD_DEFAULT
     */
    public static final int FMOD_DEFAULT = 0x00000000;
    /**
     * FMOD_LOOP_OFF
     */
    public static final int FMOD_LOOP_OFF = 0x00000001;
    /**
     * FMOD_LOOP_NORMAL
     */
    public static final int FMOD_LOOP_NORMAL = 0x00000002;
    /**
     * FMOD_LOOP_BIDI
     */
    public static final int FMOD_LOOP_BIDI = 0x00000004;
    /**
     * FMOD_2D
     */
    public static final int FMOD_2D = 0x00000008;
    /**
     * FMOD_3D
     */
    public static final int FMOD_3D = 0x00000010;
    /**
     * FMOD_CREATESTREAM
     */
    public static final int FMOD_CREATESTREAM = 0x00000080;
    /**
     * FMOD_CREATESAMPLE
     */
    public static final int FMOD_CREATESAMPLE = 0x00000100;
    /**
     * FMOD_CREATECOMPRESSEDSAMPLE
     */
    public static final int FMOD_CREATECOMPRESSEDSAMPLE = 0x00000200;
    /**
     * FMOD_OPENUSER
     */
    public static final int FMOD_OPENUSER = 0x00000400;
    /**
     * FMOD_OPENMEMORY
     */
    public static final int FMOD_OPENMEMORY = 0x00000800;
    /**
     * FMOD_OPENMEMORY_POINT
     */
    public static final int FMOD_OPENMEMORY_POINT = 0x10000000;
    /**
     * FMOD_OPENRAW
     */
    public static final int FMOD_OPENRAW = 0x00001000;
    /**
     * FMOD_OPENONLY
     */
    public static final int FMOD_OPENONLY = 0x00002000;
    /**
     * FMOD_ACCURATETIME
     */
    public static final int FMOD_ACCURATETIME = 0x00004000;
    /**
     * FMOD_MPEGSEARCH
     */
    public static final int FMOD_MPEGSEARCH = 0x00008000;
    /**
     * FMOD_NONBLOCKING
     */
    public static final int FMOD_NONBLOCKING = 0x00010000;
    /**
     * FMOD_UNIQUE
     */
    public static final int FMOD_UNIQUE = 0x00020000;
    /**
     * FMOD_3D_HEADRELATIVE
     */
    public static final int FMOD_3D_HEADRELATIVE = 0x00040000;
    /**
     * FMOD_3D_WORLDRELATIVE
     */
    public static final int FMOD_3D_WORLDRELATIVE = 0x00080000;
    /**
     * FMOD_3D_INVERSEROLLOFF
     */
    public static final int FMOD_3D_INVERSEROLLOFF = 0x00100000;
    /**
     * FMOD_3D_LINEARROLLOFF
     */
    public static final int FMOD_3D_LINEARROLLOFF = 0x00200000;
    /**
     * FMOD_3D_LINEARSQUAREROLLOFF
     */
    public static final int FMOD_3D_LINEARSQUAREROLLOFF = 0x00400000;
    /**
     * FMOD_3D_INVERSETAPEREDROLLOFF
     */
    public static final int FMOD_3D_INVERSETAPEREDROLLOFF = 0x00800000;
    /**
     * FMOD_3D_CUSTOMROLLOFF
     */
    public static final int FMOD_3D_CUSTOMROLLOFF = 0x04000000;
    /**
     * FMOD_3D_IGNOREGEOMETRY
     */
    public static final int FMOD_3D_IGNOREGEOMETRY = 0x40000000;
    /**
     * FMOD_IGNORETAGS
     */
    public static final int FMOD_IGNORETAGS = 0x02000000;
    /**
     * FMOD_LOWMEM
     */
    public static final int FMOD_LOWMEM = 0x08000000;
    /**
     * FMOD_LOADSECONDARYRAM
     */
    public static final int FMOD_LOADSECONDARYRAM = 0x20000000;
    /**
     * FMOD_VIRTUAL_PLAYFROMSTART
     */
    public static final int FMOD_VIRTUAL_PLAYFROMSTART = 0x80000000;
}
