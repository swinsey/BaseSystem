package de.silveryard.basesystem.driver.bluetoothphone;

import de.silveryard.basesystem.driver.bluetoothphone.ofono.VoiceCall;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;

/**
 * Created by silveryard on 03.06.17.
 */
public class CallLinux extends Call {
    private final CallDirection direction;
    private final VoiceCall call;
    private final String path;

    public CallLinux(DBusConnection systemConnection, String bus, String path){
        this.path = path;

        try {
            call = new VoiceCall(systemConnection.getRemoteObject(bus, path, de.silveryard.basesystem.driver.bluetoothphone.dbus.ofono.VoiceCall.class));
        } catch (DBusException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        CallState state = getCallState();
        if(state == CallState.INCOMING){
            direction = CallDirection.INCOMING;
        } else if (state == CallState.DIALING){
            direction = CallDirection.OUTGOING;
        } else {
            System.out.println("Unknown calling direction");
            direction = CallDirection.UNKNOWN;
        }
    }

    public String getPath(){
        return path;
    }

    @Override
    public CallDirection getCallDirection() {
        return direction;
    }
    @Override
    public CallState getCallState() {
        String state = call.getState();

        switch(state){
            case "incoming":
                return CallState.INCOMING;
            case "dialing":
                return CallState.DIALING;
            case "active":
                return CallState.ACTIVE;
            default:
                System.out.println("Unknown call state: " + state);
                return CallState.UNKNOWN;
        }
    }

    @Override
    public String getIdentification() {
        return call.getLineIdentification();
    }
    @Override
    public String getName() {
        return call.getName();
    }

    @Override
    public String getStartTime() {
        return call.getStartTime();
    }

    @Override
    public void hangup() {
        call.hangup();
    }
    @Override
    protected void acceptInternal() {
        call.answer();
    }
}
