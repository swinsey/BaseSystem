package de.silveryard.basesystem.logging.NetworkInterface;

import de.silveryard.basesystem.logging.ILogListener;
import de.silveryard.basesystem.logging.LogMessageType;
import de.silveryard.basesystem.networkinterface.NetworkInterface;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.transport.Message;
import de.silveryard.transport.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 12.04.2017.
 */
public class NetworkLogListener implements ILogListener {
    private static final String SOURCE_ID = "00000000000000000000000000000000";

    private final String commandHash;
    private final String destinationID;

    public NetworkLogListener(String destinationID){
        this.destinationID = destinationID;
        commandHash = Utils.generateMd5("de.silveryard.basesystem.networkinterface.logviewer.onMessageLog");
    }

    @Override
    public void logMessage(String logger, String message, LogMessageType type) {
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createString(logger));
        params.add(Parameter.createString(message));
        params.add(Parameter.createInt(type.getValue()));
        Message msg = new Message(SOURCE_ID, destinationID, commandHash, params);
        boolean result = NetworkInterface.sendMessage(msg);

        if(!result){
            de.silveryard.basesystem.logging.LogManager.getInstance().unregisterListener(this);
        }
    }
}
