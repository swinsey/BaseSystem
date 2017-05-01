package de.silveryard.basesystem.app.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 13.03.2017.
 */
@FunctionalInterface
public interface ISystemCallHandler {
    /**
     * Invoked when a systemcall is invoked
     * @param app App that call the systemcall
     * @param message SystemCall Message
     * @return Response
     */
    QAMessage invoke(RunningApp app, QAMessage message);
}
