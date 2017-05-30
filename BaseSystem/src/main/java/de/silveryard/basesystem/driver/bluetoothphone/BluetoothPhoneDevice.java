package de.silveryard.basesystem.driver.bluetoothphone;

import de.silveryard.basesystem.driver.Device;
import de.silveryard.basesystem.driver.DriverManager;

import java.util.List;

/**
 * Created by silveryard on 28.05.17.
 */
public abstract class BluetoothPhoneDevice extends Device {
    public static BluetoothPhoneDevice getDevice(int id){
        List<BluetoothPhoneDevice> devices = DriverManager.getInstance().getDriver(BluetoothPhoneDriver.class).getDevices();

        for(int i = 0; i < devices.size(); i++){
            if(devices.get(i).getId() == id){
                return devices.get(i);
            }
        }

        return null;
    }

    private static int nextId = 1;

    private int id;

    public BluetoothPhoneDevice(){
        id = nextId;
        nextId++;
    }

    public int getId(){
        return id;
    }

    //////
    ///General Data
    //////

    public abstract String getName();
    public abstract String getAddress();

    //////
    ///Volume
    //////

    public abstract boolean supportsVolumeInformation();
    public final float getVolumeSpeaker(){
        if(supportsVolumeInformation()){
            return getVolumeSpeakerInternal();
        }else{
            System.out.println("BT Phone: Could not get speaker volume. Volume Information is not avaliable");
            return 0;
        }
    }
    public final void setVolumeSpeaker(float volume){
        if(supportsVolumeInformation()){
            if(volume < 0){
                System.out.println("BT Phone: Volume is negative. Assuming 0");
                volume = 0;
            }
            if(volume > 1){
                System.out.println("BT Phone: Volume is greater than 1. Assuming 1");
                volume = 1;
            }

            setVolumeSpeakerInternal(volume);
        }else{
            System.out.println("BT Phone: Could not set speaker volume. Volume Information is not avaliable");
        }
    }
    public final float getVolumeMicrophone(){
        if(supportsVolumeInformation()){
            return getVolumeMicrophoneInternal();
        }else {
            System.out.println("BT Phone: Could not get microphone volume. Volume Information is not avaliable");
            return 0;
        }
    }
    public final void setVolumeMicrophone(float volume){
        if(supportsVolumeInformation()){
            if(volume < 0){
                System.out.println("BT Phone: Volume is negative. Assuming 0");
                volume = 0;
            }
            if(volume > 1){
                System.out.println("BT Phone: Volume is greater than 1. Assuming 1");
                volume = 1;
            }

            setVolumeMicrophoneInternal(volume);
        }else{
            System.out.println("BT Phone: Could not set microphone volume. Volume Information is not avaliable");
        }
    }
    public final boolean getVolumeMuted(){
        if(supportsVolumeInformation()){
            return getVolumeMutedInternal();
        }else{
            System.out.println("BT Phone: Could not get volume muted. Volume Information is not avaliable");
            return false;
        }
    }
    public final void setVolumeMuted(boolean muted){
        if(supportsVolumeInformation()){
            setVolumeMutedInternal(muted);
        }else{
            System.out.println("BT Phone: Could not set volume muted. Volume Information is not avaliable");
        }
    }

    protected abstract float getVolumeSpeakerInternal();
    protected abstract void setVolumeSpeakerInternal(float volume);
    protected abstract float getVolumeMicrophoneInternal();
    protected abstract void setVolumeMicrophoneInternal(float volume);
    protected abstract boolean getVolumeMutedInternal();
    protected abstract void setVolumeMutedInternal(boolean muted);

    //////
    ///Battery
    //////

    public abstract boolean supportsBatteryChargeLevel();
    public final float getBatteryChargeLevel(){
        if(supportsBatteryChargeLevel()){
            return getBatteryChargeLevelInternal();
        }else{
            System.out.println("BT Phone: Could not get battery charge level. Battery Charge Level is not avaliable");
            return 0;
        }
    }

    protected abstract float getBatteryChargeLevelInternal();

    //////
    ///Network
    //////

    public abstract boolean supportsNetworkInformation();

    public final String getNetworkName(){
        if(supportsNetworkInformation()){
            return getNetworkNameInternal();
        }else{
            System.out.println("BT Phone: Could not get network name. Network Information is not avaliable");
            return "error";
        }
    }
    public final float getNetworkStrength(){
        if(supportsNetworkInformation()){
            return getNetworkStrengthInternal();
        }else{
            System.out.println("BT Phone: Could not get network strength. Network Information is not avaliable");
            return 0;
        }
    }
    public final NetworkStatus getNetworkStatus(){
        if(supportsNetworkInformation()){
            return getNetworkStatusInternal();
        }else{
            System.out.println("BT Phone: Could not get network status. Network Information is not avaliable");
            return NetworkStatus.UNKNOWN;
        }
    }

    protected abstract String getNetworkNameInternal();
    protected abstract float getNetworkStrengthInternal();
    protected abstract NetworkStatus getNetworkStatusInternal();

    //////
    ///Calling
    //////
    //TODO

    //////
    ///Phonebook
    //////
    public abstract boolean supportsContactsPhonebook();
    public abstract boolean supportsIncomingHistoryPhonebook();
    public abstract boolean supportsOutgoingHistoryPhonebook();
    public abstract boolean supportsMissedHistoryPhonebook();

    public final Phonebook getContactsPhonebook(){
        if(supportsContactsPhonebook()){
            return getContactsPhonebookInternal();
        }else{
            System.out.println("BT Phone: Could not get contacts phonebook. Information is not avaliable");
            return new PhonebookFallback();
        }
    }
    public final Phonebook getIncomingHistoryPhonebook(){
        if(supportsIncomingHistoryPhonebook()){
            return getIncomingHistoryPhonebookInternal();
        }else{
            System.out.println("BT Phone: Could not get incoming history phonebook. Information is not avaliable");
            return new PhonebookFallback();
        }
    }
    public final Phonebook getOutgoingHistoryPhonebook(){
        if(supportsOutgoingHistoryPhonebook()){
            return getOutgoingHistoryPhonebookInternal();
        }else{
            System.out.println("BT Phone: Could not get outgoing history phonebook. Information is not avaliable");
            return new PhonebookFallback();
        }
    }
    public final Phonebook getMissedHistoryPhonebook(){
        if(supportsMissedHistoryPhonebook()){
            return getMissedHistoryPhonebookInternal();
        }else{
            System.out.println("BT Phone: Could not get missed history phonebook. Information is not avaliable");
            return new PhonebookFallback();
        }
    }

    protected abstract Phonebook getContactsPhonebookInternal();
    protected abstract Phonebook getIncomingHistoryPhonebookInternal();
    protected abstract Phonebook getOutgoingHistoryPhonebookInternal();
    protected abstract Phonebook getMissedHistoryPhonebookInternal();
}
