package de.silveryard.basesystem.sound.kernel;

/**
 * Created by Sebif on 06.04.2017.
 */
public abstract class SoundKernel {
    public static void enableKernel(){
        FmodChannel.enableKernel();
        FmodCreateSoundExInfo.enableKernel();
        FmodSound.enableKernel();
        FmodSystem.enableKernel();
    }
}
