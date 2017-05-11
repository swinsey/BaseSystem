package de.silveryard.basesystem.driver.bluetoothaudio;

import de.silveryard.basesystem.driver.bluetooth.BluetoothDevice;
import de.silveryard.basesystem.driver.bluetoothaudio.dbus.MediaPlayer;
import org.freedesktop.DBus;
import org.freedesktop.dbus.UInt32;
import org.freedesktop.dbus.Variant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by silveryard on 07.05.17.
 */
final class BluetoothAudioDeviceLinux extends BluetoothAudioDevice {
    private static final String INTERFACE = "org.bluez.MediaPlayer1";

    private final BluetoothDevice device;
    private final DBus.Properties properties;
    private final MediaPlayer mediaPlayer;

    public BluetoothAudioDeviceLinux(BluetoothDevice device, DBus.Properties properties, MediaPlayer mediaPlayer){
        this.device = device;
        this.properties = properties;
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public BluetoothDevice getDevice() {
        return device;
    }

    @Override
    public RepeatMode getRepeat() {
        try{
            String repeat = properties.Get(INTERFACE, "Repeat");

            if(repeat.equals("off")){
                return RepeatMode.OFF;
            }
            if(repeat.equals("singletrack")){
                return RepeatMode.SINGLE_TRACK;
            }
            if(repeat.equals("alltracks")){
                return RepeatMode.ALL_TRACKS;
            }
            if(repeat.equals("group")){
                return RepeatMode.GROUP;
            }

            System.out.println("Unknown RepeatMode: " + repeat);
            return RepeatMode.ERROR;
        }catch(Throwable t){
            System.out.println("Failed to retrieve repeat: " + t.getMessage());
            return RepeatMode.ERROR;
        }
    }
    @Override
    public void setRepeat(RepeatMode repeatMode) {
        try{
            String value = null;

            switch (repeatMode) {
                case OFF:
                    value = "off";
                    break;
                case SINGLE_TRACK:
                    value = "singletrack";
                    break;
                case ALL_TRACKS:
                    value = "alltracks";
                    break;
                case GROUP:
                    value = "group";
                    break;
                case ERROR:
                    System.out.println("Cannot set repeat mode to error!");
                    break;
                default:
                    System.out.println("Unknown RepeatMode: " + repeatMode);
            }

            if(value != null){
                properties.Set(INTERFACE, "Repeat", value);
            }
        }catch(Throwable t){
            System.out.println("Failed to set repeat: " + t.getMessage());
        }
    }

    @Override
    public ShuffleMode getShuffle() {
        try{
            String value = properties.Get(INTERFACE, "Shuffle");

            if(value.equals("off")){
                return ShuffleMode.OFF;
            }
            if(value.equals("alltracks")){
                return ShuffleMode.ALL_TRACKS;
            }
            if(value.equals("group")){
                return ShuffleMode.GROUP;
            }

            System.out.println("Unknown shuffle value: " + value);
            return ShuffleMode.ERROR;
        }catch(Throwable t){
            System.out.println("Failed to get shuffle: " + t.getMessage());
            return ShuffleMode.ERROR;
        }
    }
    @Override
    public void setShuffle(ShuffleMode shuffleMode) {
        try{
            String value = null;

            switch (shuffleMode) {
                case OFF:
                    value = "off";
                    break;
                case ALL_TRACKS:
                    value = "alltracks";
                    break;
                case GROUP:
                    value = "group";
                    break;
                case ERROR:
                    System.out.println("Cannot set shuffle mode to error!");
                    break;
                default:
                    System.out.println("Unknown shuffle mode: " + shuffleMode);
            }

            if(value != null){
                properties.Set(INTERFACE, "Shuffle", value);
            }
        }catch(Throwable t){
            System.out.println("Failed to set shuffle: " + t.getMessage());
        }
    }

    @Override
    public AudioStatus getStatus() {
        try{
            String value = properties.Get(INTERFACE, "Status");

            if(value.equals("playing")){
                return AudioStatus.PLAYING;
            }
            if(value.equals("stopped")){
                return AudioStatus.STOPPED;
            }
            if(value.equals("paused")){
                return AudioStatus.PAUSED;
            }
            if(value.equals("forward-seek")){
                return AudioStatus.FORWARD_SEEK;
            }
            if(value.equals("reverse-seek")){
                return AudioStatus.REVERSE_SEEK;
            }
            if(value.equals("error")){
                return AudioStatus.ERROR;
            }

            System.out.println("Unknown Status: " + value);
            return AudioStatus.ERROR;
        }catch(Throwable t){
            System.out.println("Failed to get status: " + t.getMessage());
            return AudioStatus.ERROR;
        }
    }

    @Override
    public String getCurrentTrackTitle() {
        Map<String, Variant> trackData = getTrackData();
        String title = (String)trackData.get("Title").getValue();

        if(title == null){
            return "";
        } else {
            return title;
        }
    }
    @Override
    public String getCurrentTrackArtist() {
        Map<String, Variant> trackData = getTrackData();
        String artist = (String)trackData.get("Artist").getValue();

        if(artist == null){
            return "";
        }else{
            return artist;
        }
    }
    @Override
    public String getCurrentTrackAlbum() {
        Map<String, Variant> trackData = getTrackData();
        String album = (String)trackData.get("Album").getValue();

        if(album == null){
            return "";
        }else{
            return album;
        }
    }
    @Override
    public long getCurrentTrackDuration() {
        Map<String, Variant> trackData = getTrackData();
        UInt32 duration = (UInt32)trackData.get("Duration").getValue();

        if(duration == null){
            return 0;
        }else{
            return duration.longValue();
        }
    }

    @Override
    public void play() {
        mediaPlayer.Play();
    }
    @Override
    public void pause() {
        mediaPlayer.Pause();
    }
    @Override
    public void stop() {
        mediaPlayer.Stop();
    }
    @Override
    public void next() {
        mediaPlayer.Next();
    }
    @Override
    public void previous() {
        mediaPlayer.Previous();
    }
    @Override
    public void fastForward() {
        mediaPlayer.FastForward();
    }
    @Override
    public void rewind() {
        mediaPlayer.Rewind();
    }

    /**
     * Possible Values:
     *
     * string Title:
     *  Track title name
     *
     * string Artist:
     *  Track artist name
     *
     * string Album:
     *  Track album name
     *
     * string Genre:
     *  Track genre name
     *
     * uint32 NumberOfTracks:
     *  Number of tracks in total
     *
     * uint32 TrackNumber:
     *  Track number
     *
     * uint32 Duration:
     *  Track duration in milliseconds
     *
     * @return
     */
    private Map<String, Variant> getTrackData(){
        try {
            return properties.Get(INTERFACE, "Track");
        }catch(Throwable t){
            System.out.println("Failed to get track data: " + t.getMessage());
            return new HashMap<>();
        }
    }
}
