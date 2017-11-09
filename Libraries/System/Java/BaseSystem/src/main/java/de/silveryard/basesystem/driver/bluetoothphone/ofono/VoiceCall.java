package de.silveryard.basesystem.driver.bluetoothphone.ofono;

/**
 * Created by silveryard on 03.06.17.
 */
public class VoiceCall {
    private de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.VoiceCall call;

    public VoiceCall(de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.VoiceCall call) {
        this.call = call;
    }

    public void hangup(){
        call.Hangup();
    }
    public void answer(){
        call.Answer();
    }

    public String getLineIdentification(){
        return (String)call.GetProperties().get("LineIdentification").getValue();
    }
    public String getName(){
        return (String)call.GetProperties().get("Name").getValue();
    }
    public String getState(){
        return (String)call.GetProperties().get("State").getValue();
    }
    public String getStartTime(){
        return (String)call.GetProperties().get("StartTime").getValue();
    }
}
