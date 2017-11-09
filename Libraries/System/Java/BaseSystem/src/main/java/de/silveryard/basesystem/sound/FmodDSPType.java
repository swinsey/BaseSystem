package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 06.06.2017.
 */
public enum FmodDSPType {
    /**
     *  This unit was created via a non FMOD plugin so has an unknown purpose.
     */
    FMOD_DSP_TYPE_UNKNOWN(0),
    /**
     *  This unit does nothing but take inputs and mix them together then feed the result to the soundcard unit.
     */
    FMOD_DSP_TYPE_MIXER(1),
    /**
     *  This unit generates sine/square/saw/triangle or noise tones.
     */
    FMOD_DSP_TYPE_OSCILLATOR(2),
    /**
    * This unit filters sound using a high quality, resonant lowpass filter algorithm but consumes more CPU time. Deprecated and will be removed in a future release (see FMOD_DSP_LOWPASS remarks for alternatives).
    */
    FMOD_DSP_TYPE_LOWPASS(3),
    /**
     * This unit filters sound using a resonant lowpass filter algorithm that is used in Impulse Tracker, but with limited cutoff range (0 to 8060hz).
     */
    FMOD_DSP_TYPE_ITLOWPASS(4),
    /**
     * This unit filters sound using a resonant highpass filter algorithm. Deprecated and will be removed in a future release (see FMOD_DSP_HIGHPASS remarks for alternatives).
     */
    FMOD_DSP_TYPE_HIGHPASS(5),
    /**
     * This unit produces an echo on the sound and fades out at the desired rate.
     */
    FMOD_DSP_TYPE_ECHO(6),
    /**
     * This unit pans and scales the volume of a unit.
     */
    FMOD_DSP_TYPE_FADER(7),
    /**
     * This unit produces a flange effect on the sound.
     */
    FMOD_DSP_TYPE_FLANGE(8),
    /**
     * This unit distorts the sound.
     */
    FMOD_DSP_TYPE_DISTORTION(9),
    /**
     * This unit normalizes or amplifies the sound to a certain level.
     */
    FMOD_DSP_TYPE_NORMALIZE(10),
    /**
     * This unit limits the sound to a certain level.
     */
    FMOD_DSP_TYPE_LIMITER(11),
    /**
     * This unit attenuates or amplifies a selected frequency range. Deprecated and will be removed in a future release (see FMOD_DSP_PARAMEQ remarks for alternatives).
     */
    FMOD_DSP_TYPE_PARAMEQ(12),
    /**
     * This unit bends the pitch of a sound without changing the speed of playback.
     */
    FMOD_DSP_TYPE_PITCHSHIFT(13),
    /**
     * This unit produces a chorus effect on the sound.
     */
    FMOD_DSP_TYPE_CHORUS(14),
    /**
     * This unit allows the use of Steinberg VST plugins
     */
    FMOD_DSP_TYPE_VSTPLUGIN(15),
    /**
     * This unit allows the use of Nullsoft Winamp plugins
     */
    FMOD_DSP_TYPE_WINAMPPLUGIN(16),
    /**
     * This unit produces an echo on the sound and fades out at the desired rate as is used in Impulse Tracker.
     */
    FMOD_DSP_TYPE_ITECHO(17),
    /**
     * This unit implements dynamic compression (linked/unlinked multichannel, wideband)
     */
    FMOD_DSP_TYPE_COMPRESSOR(18),
    /**
     * This unit implements SFX reverb
     */
    FMOD_DSP_TYPE_SFXREVERB(19),
    /**
     * This unit filters sound using a simple lowpass with no resonance, but has flexible cutoff and is fast. Deprecated and will be removed in a future release (see FMOD_DSP_LOWPASS_SIMPLE remarks for alternatives).
     */
    FMOD_DSP_TYPE_LOWPASS_SIMPLE(20),
    /**
     * This unit produces different delays on individual channels of the sound.
     */
    FMOD_DSP_TYPE_DELAY(21),
    /**
     * This unit produces a tremolo / chopper effect on the sound.
     */
    FMOD_DSP_TYPE_TREMOLO(22),
    /**
     * Unsupported / Deprecated.
     */
    FMOD_DSP_TYPE_LADSPAPLUGIN(23),
    /**
     * This unit sends a copy of the signal to a return DSP anywhere in the DSP tree.
     */
    FMOD_DSP_TYPE_SEND(24),
    /**
     * This unit receives signals from a number of send DSPs.
     */
    FMOD_DSP_TYPE_RETURN(25),
    /**
     * This unit filters sound using a simple highpass with no resonance, but has flexible cutoff and is fast. Deprecated and will be removed in a future release (see FMOD_DSP_HIGHPASS_SIMPLE remarks for alternatives).
     */
    FMOD_DSP_TYPE_HIGHPASS_SIMPLE(26),
    /**
     * This unit pans the signal, possibly upmixing or downmixing as well.
     */
    FMOD_DSP_TYPE_PAN(27),
    /**
     * This unit is a three-band equalizer.
     */
    FMOD_DSP_TYPE_THREE_EQ(28),
    /**
     * This unit simply analyzes the signal and provides spectrum information back through getParameter.
     */
    FMOD_DSP_TYPE_FFT(29),
    /**
     * This unit analyzes the loudness and true peak of the signal.
     */
    FMOD_DSP_TYPE_LOUDNESS_METER(30),
    /**
     * This unit tracks the envelope of the input/sidechain signal. Format to be publicly disclosed soon.
     */
    FMOD_DSP_TYPE_ENVELOPEFOLLOWER(31),
    /**
     * This unit implements convolution reverb.
     */
    FMOD_DSP_TYPE_CONVOLUTIONREVERB(32),
    /**
     * This unit provides per signal channel gain, and output channel mapping to allow 1 multichannel signal made up of many groups of signals to map to a single output signal.
     */
    FMOD_DSP_TYPE_CHANNELMIX(33),
    /**
     * This unit 'sends' and 'receives' from a selection of up to 32 different slots.  It is like a send/return but it uses global slots rather than returns as the destination.  It also has other features.  Multiple transceivers can receive from a single channel, or multiple transceivers can send to a single channel, or a combination of both.
     */
    FMOD_DSP_TYPE_TRANSCEIVER(34),
    /**
     * This unit sends the signal to a 3d object encoder like Dolby Atmos.   Supports a subset of the FMOD_DSP_TYPE_PAN parameters.
     */
    FMOD_DSP_TYPE_OBJECTPAN(35),
    /**
     * This unit is a flexible five band parametric equalizer.
     */
    FMOD_DSP_TYPE_MULTIBAND_EQ(36),

    /**
     * Maximum number of pre-defined DSP types.
     */
    FMOD_DSP_TYPE_MAX(37),
    /**
     * Makes sure this enum is signed 32bit.
     */
    FMOD_DSP_TYPE_FORCEINT(65536);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static FmodDSPType getEnumValue(int value){
        FmodDSPType[] values = FmodDSPType.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].getValue() == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    /**
     * Constructor
     * @param value Integer value
     */
    FmodDSPType(int value){
        this.value = value;
    }

    /**
     * @return Gets the enums integer value
     */
    public int getValue(){
        return value;
    }
}
