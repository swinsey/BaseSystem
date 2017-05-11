package de.silveryard.transport.highlevelprotocols.qa;

import de.silveryard.transport.InvalidMessageException;
import de.silveryard.transport.Message;
import de.silveryard.transport.MessageHandler;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beppo on 28.01.2017.
 */
public class QAManager {
    private class AsyncWrapper implements QACallback {
        private QAMessage response;

        @Override
        public void invoke(QAMessage response) {
            this.response = response;
        }

        public QAMessage getResponse(){
            return response;
        }
    }
    private Map<String, QAMessageHandler> commands;
    private Map<String, String> commandHashs;
    private Map<String, QACallback> runningCommands;
    private MessageHandler sendMethod;

    /**
     * Constructor
     * @param sendMethod Method that can send messages
     */
    public QAManager(MessageHandler sendMethod){
        commands = new HashMap<>();
        commandHashs = new HashMap<>();
        runningCommands = new HashMap<>();
        this.sendMethod = sendMethod;
    }

    /**
     * Registers a new command
     * @param command Command name
     * @param handler Handler that gets invoked when the command is called
     */
    public void registerQACommand(String command, QAMessageHandler handler){
        commands.put(command, handler);
        commandHashs.put(generateMd5(command), command);
    }
    /**
     * Returns a list of Commands with their respective handler
     * @return Binding Values
     */
    public Map<String, MessageHandler> getBindings(){
        Map<String, MessageHandler> bindings = new HashMap<>();
        for(String key : commands.keySet()){
            bindings.put(key, this::handle);
        }
        return bindings;
    }

    /**
     * Sends a new QAMessage
     * @param message Message
     * @return Response
     */
    public QAMessage send(QAMessage message){
        AsyncWrapper wrapper = new AsyncWrapper();
        sendAsync(message, wrapper);

        while(wrapper.getResponse() == null){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return wrapper.getResponse();
    }
    /**
     * Sends a message asynchronous
     * @param message Message
     * @param callback Callback that gets invoked when the response is received
     */
    public void sendAsync(QAMessage message, QACallback callback){
        if(message.isResponse()){
            throw new RuntimeException("Cannot send a response message");
        }

        runningCommands.put(message.getUUID(), callback);
        sendMethod.handle(message.getMessage());
    }

    private void handle(Message m) throws InvalidMessageException {
        QAMessage qaMessage = new QAMessage(m);

        if(qaMessage.isResponse()){
            QACallback callback = runningCommands.get(qaMessage.getUUID());
            runningCommands.remove(qaMessage.getUUID());
            callback.invoke(qaMessage);
        }else{
            String command = commandHashs.get(m.getCommandHash());
            QAMessageHandler handler = commands.get(command);
            QAMessage response = handler.invoke(qaMessage);
            sendMethod.handle(response.getMessage());
        }
    }

    private String generateMd5(String string){
        return generateMd5(string.getBytes());
    }
    private String generateMd5(byte[] data){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(data);
            String hash = DatatypeConverter.printHexBinary(bytes).toLowerCase();
            return hash;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
