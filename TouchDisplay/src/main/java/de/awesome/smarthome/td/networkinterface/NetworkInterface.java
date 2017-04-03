package de.awesome.smarthome.td.networkinterface;

import de.awesome.smarthome.filecache.FileCache;
import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.util.Utils;
import de.awesome.smarthome.transport.Message;
import de.awesome.smarthome.transport.Parameter;

import java.util.*;

/**
 * Created by Sebif on 15.03.2017.
 */
public class NetworkInterface {
    private static Network network;
    private static FileCache fileCache;
    private static boolean initializing;

    private static Map<String, String> commandHashMapping;
    private static Map<String, ICommandHandler> commandHandlers;
    private static Map<String, IQACommandHandler> qaCommandHanders;

    public static void beginInitialize(){
        initializing = true;

        network = new Network(NetworkInterface::handleMessage);
        network.listen();

        fileCache = new FileCache(network::send, NetworkInterface::handleFileReceive);

        commandHashMapping = new HashMap<>();
        commandHandlers = new HashMap<>();
        qaCommandHanders = new HashMap<>();
    }
    public static void endInitialize(){
        if(!initializing){
            throw new RuntimeException("Already ended initialization");
        }
        initializing = false;
    }

    public static void registerCommand(String command, ICommandHandler handler){
        if(!initializing){
            throw new RuntimeException("Cannot register command after initialization has finished");
        }

        String md5 = Utils.generateMd5(command);
        commandHashMapping.put(md5, command);
        commandHandlers.put(md5, handler);
    }
    public static void registerQaCommand(String command, IQACommandHandler handler){
        if(!initializing){
            throw new RuntimeException("Cannot register command after initialization has finished");
        }

        String md5 = Utils.generateMd5(command);
        commandHashMapping.put(md5, command);
        qaCommandHanders.put(md5, handler);
    }

    public static List<String> dump(){
        List<String> interfaceCalls = new ArrayList<>();
        for(String key : commandHashMapping.keySet()){
            interfaceCalls.add(commandHashMapping.get(key));
        }
        Collections.sort(interfaceCalls);
        return interfaceCalls;
    }

    private static void handleMessage(Message message){
        boolean result = handleMessageInternal(message, null);
        if(result){
            return;
        }

        fileCache.handle(message);
    }
    private static void handleFileReceive(String sourceID, String uuid, String commandHash, List<Parameter> params, byte[] data){
        Message message = new Message(sourceID, "00000000000000000000000000000000", commandHash, params);
        handleMessageInternal(message, data);
    }
    private static boolean handleMessageInternal(Message message, byte[] data){
        if(commandHandlers.containsKey(message.getCommandHash())){
            ICommandHandler handler = commandHandlers.get(message.getCommandHash());
            handler.handle(message, data);
            return true;
        }
        if(qaCommandHanders.containsKey(message.getCommandHash())){
            QAMessage qaMessage = new QAMessage(message);
            IQACommandHandler handler = qaCommandHanders.get(message.getCommandHash());
            QAMessage response = handler.handle(qaMessage, data);
            network.send(response.getMessage());
            return true;
        }

        return false;
    }
}
