package de.silveryard.transport.highlevelprotocols.qa;

/**
 * Created by Beppo on 28.01.2017.
 */
@FunctionalInterface
public interface QAMessageHandler {
    /**
     * Invokes when a message is received that requires a response
     * @param message Request
     * @return Response
     */
    QAMessage invoke(QAMessage message);
}
