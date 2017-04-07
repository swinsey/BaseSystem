package de.silveryard.basesystem.sdk.kernel.sound;

/**
 * Created by Sebif on 07.04.2017.
 */
public abstract class FmodTimeUnit {
    /**
     * FMOD_TIMEUNIT_MS
     */
    public static final int TIMEUNIT_MS               = 0x00000001;
    /**
     * FMOD_TIMEUNIT_PCM
     */
    public static final int TIMEUNIT_PCM              = 0x00000002;
    /**
     * FMOD_TIMEUNIT_PCMBYTES
     */
    public static final int TIMEUNIT_PCMBYTES         = 0x00000004;
    /**
     * FMOD_TIMEUNIT_RAWBYTES
     */
    public static final int TIMEUNIT_RAWBYTES         = 0x00000008;
    /**
     * FMOD_TIMEUNIT_PCMFRACTION
     */
    public static final int TIMEUNIT_PCMFRACTION      = 0x00000010;
    /**
     * FMOD_TIMEUNIT_MODORDER
     */
    public static final int TIMEUNIT_MODORDER         = 0x00000100;
    /**
     * FMOD_TIMEUNIT_MODROW
     */
    public static final int TIMEUNIT_MODROW           = 0x00000200;
    /**
     * FMOD_TIMEUNIT_MODPATTERN
     */
    public static final int TIMEUNIT_MODPATTERN       = 0x00000400;
    /**
     * FMOD_TIMEUNIT_BUFFERED
     */
    public static final int TIMEUNIT_BUFFERED         = 0x10000000;
}

