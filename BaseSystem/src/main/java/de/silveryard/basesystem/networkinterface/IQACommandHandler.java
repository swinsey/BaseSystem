package de.silveryard.basesystem.networkinterface;

import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 15.03.2017.
 */
@FunctionalInterface
public interface IQACommandHandler {
    QAMessage handle(QAMessage message, byte[] data);
}
