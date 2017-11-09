package de.silveryard.appmanager.Model;

import de.silveryard.appmanager.Utility.IAction;
import de.silveryard.appmanager.Utility.Util;
import de.silveryard.transport.Message;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.Transport;
import de.silveryard.transport.filecache.FileCache;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Sebif on 18.03.2017.
 */
public abstract class Connection {
    private static final String SERVER_UUID = "00000000000000000000000000000000";

    private static Socket socket;
    private static Transport transport;
    private static FileCache fileCache;
    private static String uuid;

    private static Map<String, QAMessage> pendingRequests;
    private static List<IAction> disconnectHandlers;

    public static boolean connect(String address){
        pendingRequests = new HashMap<>();
        uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");

        OutputStream outStream = null;
        InputStream inStream = null;

        try {
            socket = new Socket(address, 1337);

            outStream = socket.getOutputStream();
            inStream = socket.getInputStream();
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

        transport = new Transport(outStream, inStream, Connection::handleMessage);
        fileCache = new FileCache(transport::send, Connection::handleFileReceive, Paths.get("."));

        Thread thread = new Thread(Connection::observerThread);
        thread.isDaemon();
        thread.start();
        return true;
    }
    public static void disconnect(){
        transport.disconnect();
    }

    public static boolean isConnected(){
        return transport != null && transport.isConnected();
    }

    public static void registerDisconnectHandler(IAction handler){
        if(disconnectHandlers == null){
            disconnectHandlers = new ArrayList<>();
        }

        disconnectHandlers.add(handler);
    }
    public static void unregisterDisconnectHandler(IAction handler){
        disconnectHandlers.remove(handler);
    }

    public static QAMessage sendMessage(String command, List<Parameter> params){
        return sendMessage(command, params, null);
    }
    public static QAMessage sendMessage(String command, List<Parameter> params, byte[] data){
        QAMessage message = new QAMessage(false, uuid, SERVER_UUID, Util.generateMd5(command), params);
        pendingRequests.put(message.getUUID(), null);

        if(data == null) {
            transport.send(message.getMessage());
        }else{
            fileCache.sendFile(message.getMessage().getSenderID(), message.getMessage().getDestinationID(), message.getMessage().getCommandHash(), message.getMessage().getParams(), data);
        }

        while(pendingRequests.get(message.getUUID()) == null){
            try{
                Thread.sleep(200);
            }catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        QAMessage response = pendingRequests.get(message.getUUID());
        pendingRequests.remove(message.getUUID());
        return response;
    }

    private static void handleMessage(Message message){
        QAMessage qaMessage = new QAMessage(message);
        if(!pendingRequests.containsKey(qaMessage.getUUID())){
            System.out.println("Unknown request");
            return;
        }

        pendingRequests.put(qaMessage.getUUID(), qaMessage);
    }

    private static void handleFileReceive(String sourceID, String uuid, String commandHash, List<Parameter> params, Path pathToFile){

    }

    private static void observerThread(){
        while(transport.isConnected()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        transport.disconnect();
        transport = null;
        fileCache = null;

        if(disconnectHandlers != null){
            for(int i = 0; i < disconnectHandlers.size(); i++){
                disconnectHandlers.get(i).invoke();
            }
        }
    }
}
