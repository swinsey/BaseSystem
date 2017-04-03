package de.awesome.smarthome.highlevelprotocols.event;

import de.awesome.smarthome.transport.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebif on 30.03.2017.
 */
public class EventServerManager {
    /**
     * Key: eventGuid
     * Value: List of destination IDs currently registered
     */
    private final Map<String, List<String>> mapList;
    private final String senderId;

    /**
     * Constructor
     * @param senderId Sender GUID
     */
    public EventServerManager(String senderId){
        mapList = new HashMap<>();
        this.senderId = senderId;
    }

    /**
     * Registers a new event
     * @param eventGuid Event Hash
     */
    public void registerEvent(String eventGuid){
        if(mapList.containsKey(eventGuid)){
            return;
        }

        mapList.put(eventGuid, new ArrayList<>());
    }
    /**
     * Unregisters an existing event
     * @param eventGuid Event Hash
     */
    public void unregisterEvent(String eventGuid){
        if(!mapList.containsKey(eventGuid)){
            return;
        }

        mapList.remove(eventGuid);
    }

    /**
     * Calls an event and notifies all listeners
     * @param eventGuid Event Hash
     * @param params List of parameters
     */
    public void callEvent(String eventGuid, Parameter... params){
        if(!mapList.containsKey(eventGuid)){
            return;
        }

        List<String> identifiers = mapList.get(eventGuid);
        for(int i = 0; i < identifiers.size(); i++){
            String destinationId = identifiers.get(i);
            EventMessage msg = new EventMessage(senderId, destinationId, eventGuid, EventMessageType.CALL, params);
        }
    }
}
