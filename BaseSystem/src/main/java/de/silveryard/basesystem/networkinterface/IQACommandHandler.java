package de.silveryard.basesystem.networkinterface;

import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.nio.file.Path;

/**
 * Created by Sebif on 15.03.2017.
 */
@FunctionalInterface
public interface IQACommandHandler {
    /**
     * Called when a qa command is called
     * @param message Command message
     * @param filePath Path to file. Optional and null when not used
     * @return Command Response
     */
    QAMessage handle(QAMessage message, Path filePath);
}
