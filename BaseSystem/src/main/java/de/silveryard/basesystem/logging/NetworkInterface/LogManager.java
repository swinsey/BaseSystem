package de.silveryard.basesystem.logging.NetworkInterface;

import de.silveryard.basesystem.networkinterface.NetworkInterface;
import de.silveryard.transport.Message;

/**
 * Created by Sebif on 12.04.2017.
 */
public abstract class LogManager {
    public static void enableInterface(){
        NetworkInterface.registerCommand("de.silveryard.basesystem.networkinterface.logviewer.registerLogListener", LogManager::registerLogListener);
    }

    private static void registerLogListener(Message message, byte[] data){
        NetworkLogListener listener = new NetworkLogListener(message.getSenderID());
        de.silveryard.basesystem.logging.LogManager.getInstance().registerListener(listener);
    }
}
