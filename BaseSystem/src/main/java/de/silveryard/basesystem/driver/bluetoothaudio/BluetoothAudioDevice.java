package de.silveryard.basesystem.driver.bluetoothaudio;

import de.silveryard.basesystem.driver.Device;
import de.silveryard.basesystem.driver.bluetooth.BluetoothDevice;

/**
 * Created by silveryard on 07.05.17.
 */
public abstract class BluetoothAudioDevice extends Device {
    /**
     * Returns the internal bluetooth device
     * @return Bluetooth device
     */
    public abstract BluetoothDevice getDevice();

    /**
     * Returns the repeat mode
     * @return Repeat mode
     */
    public abstract RepeatMode getRepeat();
    /**
     * Sets the repeat mode
     * @param repeatMode Repeat mode
     */
    public abstract void setRepeat(RepeatMode repeatMode);

    /**
     * Returns the shuffle mode
     * @return Shuffle mode
     */
    public abstract ShuffleMode getShuffle();
    /**
     * Sets the shuffle mode
     * @param shuffleMode Shuffle mode
     */
    public abstract void setShuffle(ShuffleMode shuffleMode);

    /**
     * Returns the playback status
     * @return Playback status
     */
    public abstract AudioStatus getStatus();

    /**
     * Returns the current tracks title
     * @return Title of the current track played back
     */
    public abstract String getCurrentTrackTitle();
    /**
     * Returns the current tracks artist
     * @return Artist of the current track played back
     */
    public abstract String getCurrentTrackArtist();
    /**
     * Returns the current tracks album
     * @return Album of the current track played back
     */
    public abstract String getCurrentTrackAlbum();
    /**
     * Returns the current tracks duration
     * @return Duration of the current track played back
     */
    public abstract long getCurrentTrackDuration();

    /**
     * Resumes playback
     */
    public abstract void play();
    /**
     * Pauses playback
     */
    public abstract void pause();
    /**
     * Stops playback
     */
    public abstract void stop();
    /**
     * Plays the next title
     */
    public abstract void next();
    /**
     * Plays the previous title
     */
    public abstract void previous();
    /**
     * Starts fast forward
     */
    public abstract void fastForward();
    /**
     * Starts rewind
     */
    public abstract void rewind();
}
