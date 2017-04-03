package de.awesome.smarthome.highlevelprotocols.event;

import de.awesome.smarthome.transport.InvalidMessageException;
import de.awesome.smarthome.transport.Message;
import de.awesome.smarthome.transport.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sebif on 29.03.2017.
 */
public class EventMessage {
    private Message message;

    private EventMessageType type;
    private Parameter[] params;

    /**
     * Constructor
     * @param senderId Sender GUID
     * @param destinationId Receiver GUID
     * @param eventGuid Event GUID
     * @param type Event Message Type
     * @param params List of parameters
     */
    public EventMessage(String senderId, String destinationId, String eventGuid, EventMessageType type, Parameter... params){
        this.type = type;
        this.params = params;

        List<Parameter> msgParams = new ArrayList<>();
        msgParams.add(Parameter.createString("HIGHLEVELPROTOCOL")); //Highlevel Protocol Identifier
        msgParams.add(Parameter.createString("EVENT")); //Highlevel QA Protocol
        msgParams.add(Parameter.createInt(type.getValue()));
        Arrays.stream(params).forEach(msgParams::add);

        message = new Message(senderId, destinationId, eventGuid, msgParams);
    }

    /**
     * Constructor
     * @param message Source message
     * @throws InvalidMessageException Thrown if the message is no event message
     */
    public EventMessage(Message message) throws InvalidMessageException {
        if(message.getParams().size() < 3){
            throw new InvalidMessageException("The given message is not an event message");
        }

        String firstParam = message.getParams().get(0).getString();
        if(firstParam == null || !firstParam.equals("HIGHLEVELPROTOCOL")){
            throw new InvalidMessageException("The given message is not a highlevelprotocol message");
        }

        String secondParam = message.getParams().get(1).getString();
        if(secondParam == null || !secondParam.equals("EVENT")){
            throw new InvalidMessageException("The given message is not an event message");
        }

        this.type = EventMessageType.getEnumValue(message.getParams().get(2).getInt());
        this.params = new Parameter[message.getParams().size() - 3];
        for(int i = 3; i < message.getParams().size(); i++){
            this.params[i - 3] = (message.getParams().get(i));
        }
    }
}
