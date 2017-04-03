package de.awesome.smarthome.td.networkinterface;

import de.awesome.smarthome.transport.Message;

/**
 * Created by Sebif on 15.03.2017.
 */
@FunctionalInterface
public interface ICommandHandler {
    void handle(Message message, byte[] data);
}
