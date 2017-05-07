package de.silveryard.basesystem.driver.bluetoothaudio;

import de.silveryard.basesystem.driver.Device;
import de.silveryard.basesystem.driver.bluetooth.BluetoothDevice;

/**
 * Created by silveryard on 07.05.17.
 */
public abstract class BluetoothAudioDevice extends Device {
    public abstract BluetoothDevice getDevice();

    public abstract RepeatMode getRepeat();
    public abstract void setRepeat(RepeatMode repeatMode);

    public abstract ShuffleMode getShuffle();
    public abstract void setShuffle(ShuffleMode shuffleMode);

    public abstract AudioStatus getStatus();

    public abstract String getCurrentTrackTitle();
    public abstract String getCurrentTrackArtist();
    public abstract String getCurrentTrackAlbum();
    public abstract long getCurrentTrackDuration();

    public abstract void play();
    public abstract void pause();
    public abstract void stop();
    public abstract void next();
    public abstract void previous();
    public abstract void fastForward();
    public abstract void rewind();
}
