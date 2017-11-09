package de.silveryard.basesystem.logging.NetworkInterface;

import de.silveryard.basesystem.networkinterface.NetworkInterface;
import de.silveryard.transport.Message;

import java.nio.file.Path;

/**
 * Created by Sebif on 12.04.2017.
 */
public abstract class LogInterface {
    /**
     * Enables the interface
     */
    public static void enableInterface(){
        NetworkInterface.registerCommand("de.silveryard.basesystem.networkinterface.logviewer.registerLogListener", LogInterface::registerLogListener);
    }

    private static void registerLogListener(Message message, Path filePath){
        NetworkLogListener listener = new NetworkLogListener(message.getSenderID());
        de.silveryard.basesystem.logging.LogManager.getInstance().registerListener(listener);
    }
}
