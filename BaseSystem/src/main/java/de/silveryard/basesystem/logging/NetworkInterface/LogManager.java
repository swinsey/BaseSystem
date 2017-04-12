package de.silveryard.basesystem.logging.NetworkInterface;

import de.silveryard.basesystem.networkinterface.NetworkInterface;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 12.04.2017.
 */
public abstract class LogManager {
    public static void enableInterface(){
        NetworkInterface.registerQaCommand("de.silveryard.basesystem.networkinterface.logviewer.registerLogListener", LogManager::registerLogListener);
        NetworkInterface.registerQaCommand("de.silveryard.basesystem.networkinterface.logviewer.unregisterLogListener", LogManager::unregisterLogListener);
    }

    private static QAMessage registerLogListener(QAMessage message, byte[] data){
        return null;
    }
    private static QAMessage unregisterLogListener(QAMessage message, byte[] data){
        return null;
    }
}
