package de.silveryard.transport.highlevelprotocols.qa;

import de.silveryard.transport.InvalidMessageException;
import de.silveryard.transport.Message;
import de.silveryard.transport.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Beppo on 28.01.2017.
 */
public class QAMessage {
    private static final byte[] HIGHLEVELPROTOCOL = "HIGHLEVELPROTOCOL".getBytes();
    private static final byte[] QA = "QA".getBytes();

    private Message message;
    private String uuid;
    private List<Parameter> parameters;
    private boolean isResponse;

    /**
     * Constructor. Creates a response message
     * @param source Message you want to respond to
     * @param params List of parameters
     */
    public QAMessage(QAMessage source, List<Parameter> params){
        this(true, source.getUUID(),
                source.getMessage().getDestinationID(), source.getMessage().getSenderID(),
                source.getMessage().getCommandHash(), params);
    }
    /**
     * Constructor
     * @param isResponse True if the message is a response
     * @param senderID Sender GUID
     * @param destinationID Receiver GUID
     * @param commandHash Command Hash
     * @param params List of parameters
     */
    public QAMessage(boolean isResponse, String senderID, String destinationID, String commandHash, List<Parameter> params) {
        this(isResponse, UUID.randomUUID().toString(), senderID, destinationID, commandHash, params);
    }
    /**
     * Constructor
     * @param message Base message
     * @throws InvalidMessageException Thrown if given message is no qa message
     */
    public QAMessage(Message message) throws InvalidMessageException {
        if(message.getParams().size() < 4){
            throw new InvalidMessageException("The given message is not a qa message");
        }

        if(!(Arrays.equals(message.getParams().get(0).getData(), HIGHLEVELPROTOCOL))){
            throw new InvalidMessageException("The given message is not a highlevelprotocol message");
        }

        if(!(Arrays.equals(message.getParams().get(1).getData(), QA))){
            throw new InvalidMessageException("The given message is not a qa message");
        }

        this.isResponse = message.getParams().get(2).getBoolean();
        this.uuid = message.getParams().get(3).getString();
        this.parameters = new ArrayList<>();
        this.message = message;
        for(int i = 4; i < message.getParams().size(); i++){
            this.parameters.add(message.getParams().get(i));
        }
    }
    /**
     * Constructor
     * @param isResponse True if message is a response
     * @param uuid Unique ID
     * @param senderID Sender GUID
     * @param destinationID Receiver GUID
     * @param commandHash Command Hash
     * @param params List of parameters
     */
    private QAMessage(boolean isResponse, String uuid, String senderID, String destinationID, String commandHash, List<Parameter> params){
        List<Parameter> messageParams = new ArrayList<>();
        messageParams.add(Parameter.createByteArray(HIGHLEVELPROTOCOL)); //Highlevel Protocol Identifier
        messageParams.add(Parameter.createByteArray(QA)); //Highlevel QA Protocol

        this.isResponse = isResponse;
        messageParams.add(Parameter.createBoolean(this.isResponse)); //Is Response
        this.uuid = uuid;
        messageParams.add(Parameter.createString(uuid)); //Unique Identifier

        messageParams.addAll(params);
        message = new Message(senderID, destinationID, commandHash, messageParams);

        parameters = params;
    }

    /**
     * @return Returns the base message
     */
    public Message getMessage(){
        return message;
    }
    /**
     * @return Returns the unique id
     */
    public String getUUID(){
        return uuid;
    }
    /**
     * @return Returns the list of parameters
     */
    public List<Parameter> getParameters(){
        return parameters;
    }
    /**
     * @return Returns if the message is a response
     */
    public boolean isResponse(){
        return isResponse;
    }
}
