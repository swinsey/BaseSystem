package de.silveryard.basesystem.app;

import de.silveryard.basesystem.gui.Frame;
import de.silveryard.transport.Message;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 18.06.2017.
 */
public class RunningAppMock extends RunningApp {
    /**
     * Constructor
     *
     * @param appIdentifier Application identifier
     */
    public RunningAppMock(String appIdentifier) {
        super(appIdentifier);
    }

    @Override
    public Frame getFrame() {
        return null;
    }

    @Override
    public void sendMessage(Message message) {

    }
    @Override
    public QAMessage sendQAMessage(QAMessage message) {
        return null;
    }

    @Override
    public boolean isAppRunning() {
        return true;
    }

    @Override
    protected void disposeApp() {

    }
}
