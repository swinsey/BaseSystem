package de.silveryard.basesystem.logging;

/**
 * Created by Sebif on 12.04.2017.
 */
@FunctionalInterface
public interface ILogListener {
    void logMessage(String logger, String message, LogMessageType type);
}
