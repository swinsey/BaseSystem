package de.silveryard.basesystem.driver.bluetoothphone;

/**
 * Created by silveryard on 03.06.17.
 */
public abstract class Call {
    public abstract CallDirection getCallDirection();
    public abstract CallState getCallState();

    public abstract String getIdentification();
    public abstract String getName();
    public abstract String getStartTime();

    public void accept(){
        if(getCallDirection() != CallDirection.INCOMING){
            System.out.println("Cannot accept call. This call is not incoming");
        }

        if(getCallState() != CallState.INCOMING){
            System.out.println("Cannot accept call. Has not the state 'incoming'");
        }

        acceptInternal();
    }
    public abstract void hangup();

    protected abstract void acceptInternal();
}
