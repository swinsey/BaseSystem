package de.silveryard.basesystem.logging;

/**
 * Created by Sebif on 12.04.2017.
 */
@FunctionalInterface
public interface ILogListener {
    /**
     * Called when a message gets logged
     * @param logger Identity that logged this message. System on system logs and the apps name on app logs
     * @param message Message that got logged
     * @param type Stream that was used to log the message
     */
    void logMessage(String logger, String message, LogMessageType type);
}
