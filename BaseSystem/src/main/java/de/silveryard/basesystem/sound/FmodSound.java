package de.silveryard.basesystem.sound;

import de.silveryard.basesystem.util.IDisposable;
import de.silveryard.basesystem.util.Wrapper;

/**
 * Created by Sebif on 27.03.2017.
 */
public class FmodSound implements IDisposable{
    private long handle;

    /**
     * Constructor
     */
    public FmodSound(){
        handle = 0;
    }

    /**
     * Sets the internal handle
     * @param handle Internal handle
     */
    public native void setHandle(long handle);
    /**
     * Returns the internal handle
     * @return Internal handle
     */
    public native long getHandle();

    /**
     * FMOD::Sound::getLength
     * @param length Length Value
     * @param lengthType LengthType Value
     * @return Fmod Result
     */
    public native FmodResult getLength(Wrapper<Integer> length, int lengthType);

    /**
     * FMOD::Sound::release
     * @return Fmod Result
     */
    public native FmodResult release();

    @Override
    public void dispose() {
        release();
    }
}
