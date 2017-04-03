package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 28.03.2017.
 */
public abstract class FmodTimeUnit {
    public static final int TIMEUNIT_MS               = 0x00000001;
    public static final int TIMEUNIT_PCM              = 0x00000002;
    public static final int TIMEUNIT_PCMBYTES         = 0x00000004;
    public static final int TIMEUNIT_RAWBYTES         = 0x00000008;
    public static final int TIMEUNIT_PCMFRACTION      = 0x00000010;
    public static final int TIMEUNIT_MODORDER         = 0x00000100;
    public static final int TIMEUNIT_MODROW           = 0x00000200;
    public static final int TIMEUNIT_MODPATTERN       = 0x00000400;
    public static final int TIMEUNIT_BUFFERED         = 0x10000000;
}
