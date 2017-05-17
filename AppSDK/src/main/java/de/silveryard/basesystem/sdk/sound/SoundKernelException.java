package de.silveryard.basesystem.sdk.sound;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.sound.SoundReturnCode;

/**
 * Created by Sebif on 13.04.2017.
 */
public class SoundKernelException extends KernelException {
    private SoundReturnCode soundReturnCode;

    /**
     * Constructor
     * @param SoundReturnCode SoundReturnCode enum value
     */
    public SoundKernelException(SoundReturnCode SoundReturnCode) {
        super(ReturnCode.ERROR);
        this.soundReturnCode = SoundReturnCode;
    }
    /**
     * Constructor
     * @param SoundReturnCode SoundReturnCode enum value
     * @param message Exception message
     */
    public SoundKernelException(SoundReturnCode SoundReturnCode, String message) {
        super(ReturnCode.ERROR, message);
        this.soundReturnCode = SoundReturnCode;
    }
    /**
     * Constructor
     * @param SoundReturnCode SoundReturnCode enum value
     * @param base Base exception
     */
    public SoundKernelException(SoundReturnCode SoundReturnCode, Throwable base) {
        super(ReturnCode.ERROR, base);
        this.soundReturnCode = SoundReturnCode;
    }
    /**
     * Constructor
     * @param SoundReturnCode SoundReturnCode enum value
     * @param message Exception message
     * @param base Base exception
     */
    public SoundKernelException(SoundReturnCode SoundReturnCode, String message, Throwable base) {
        super(ReturnCode.ERROR, message, base);
        this.soundReturnCode = SoundReturnCode;
    }

    /**
     * Returns the SoundReturnCode enum value
     * @return soundReturnCode
     */
    public SoundReturnCode getSoundReturnCode(){
        return soundReturnCode;
    }
}
