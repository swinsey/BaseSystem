package de.silveryard.transport;

/**
 * Created by CHofm on 08.01.2017.
 */
@FunctionalInterface
public interface MessageHandler {
    /**
     * Will be called if transport received a message
     * @param m the message received by the transport
     * @throws InvalidMessageException thrown when a message was received but contained faulty data
     */
    void handle(Message m) throws InvalidMessageException;
}
