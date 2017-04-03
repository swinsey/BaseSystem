package de.awesome.smarthome.td.networkinterface;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 15.03.2017.
 */
@FunctionalInterface
public interface IQACommandHandler {
    QAMessage handle(QAMessage message, byte[] data);
}
