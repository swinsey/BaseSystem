package de.silveryard.basesystem.networkinterface;

import de.silveryard.transport.Message;

import java.nio.file.Path;

/**
 * Created by Sebif on 15.03.2017.
 */
@FunctionalInterface
public interface ICommandHandler {
    /**
     * Called when a command is called
     * @param message Commands message
     * @param filePath Path to file.  Optional and null when not used
     */
    void handle(Message message, Path filePath);
}
