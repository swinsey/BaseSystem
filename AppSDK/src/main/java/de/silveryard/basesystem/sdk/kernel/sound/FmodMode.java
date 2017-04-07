package de.silveryard.basesystem.sdk.kernel.sound;

/**
 * Created by Sebif on 07.04.2017.
 */
public abstract class FmodMode {
    public static final int FMOD_DEFAULT = 0x00000000;
    public static final int FMOD_LOOP_OFF = 0x00000001;
    public static final int FMOD_LOOP_NORMAL = 0x00000002;
    public static final int FMOD_LOOP_BIDI = 0x00000004;
    public static final int FMOD_2D = 0x00000008;
    public static final int FMOD_3D = 0x00000010;
    public static final int FMOD_CREATESTREAM = 0x00000080;
    public static final int FMOD_CREATESAMPLE = 0x00000100;
    public static final int FMOD_CREATECOMPRESSEDSAMPLE = 0x00000200;
    public static final int FMOD_OPENUSER = 0x00000400;
    public static final int FMOD_OPENMEMORY = 0x00000800;
    public static final int FMOD_OPENMEMORY_POINT = 0x10000000;
    public static final int FMOD_OPENRAW = 0x00001000;
    public static final int FMOD_OPENONLY = 0x00002000;
    public static final int FMOD_ACCURATETIME = 0x00004000;
    public static final int FMOD_MPEGSEARCH = 0x00008000;
    public static final int FMOD_NONBLOCKING = 0x00010000;
    public static final int FMOD_UNIQUE = 0x00020000;
    public static final int FMOD_3D_HEADRELATIVE = 0x00040000;
    public static final int FMOD_3D_WORLDRELATIVE = 0x00080000;
    public static final int FMOD_3D_INVERSEROLLOFF = 0x00100000;
    public static final int FMOD_3D_LINEARROLLOFF = 0x00200000;
    public static final int FMOD_3D_LINEARSQUAREROLLOFF = 0x00400000;
    public static final int FMOD_3D_INVERSETAPEREDROLLOFF = 0x00800000;
    public static final int FMOD_3D_CUSTOMROLLOFF = 0x04000000;
    public static final int FMOD_3D_IGNOREGEOMETRY = 0x40000000;
    public static final int FMOD_IGNORETAGS = 0x02000000;
    public static final int FMOD_LOWMEM = 0x08000000;
    public static final int FMOD_LOADSECONDARYRAM = 0x20000000;
    public static final int FMOD_VIRTUAL_PLAYFROMSTART = 0x80000000;
}
