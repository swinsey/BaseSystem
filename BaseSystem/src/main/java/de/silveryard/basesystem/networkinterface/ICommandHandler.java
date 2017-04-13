package de.silveryard.basesystem.networkinterface;

import de.silveryard.transport.Message;

/**
 * Created by Sebif on 15.03.2017.
 */
@FunctionalInterface
public interface ICommandHandler {
    /**
     * Called when a command is called
     * @param message Commands message
     * @param data Data sent with this command. Optional and null when not used
     */
    void handle(Message message, byte[] data);
}
