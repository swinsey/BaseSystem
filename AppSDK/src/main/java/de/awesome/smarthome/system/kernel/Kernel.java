package de.awesome.smarthome.system.kernel;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.transport.Message;
import de.awesome.smarthome.transport.Parameter;
import de.awesome.smarthome.transport.Transport;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebif on 20.02.2017.
 */
public abstract class Kernel {
    /**
     * Connection:
     *
     * Handshake:
     * Client -> Server: de.awesome.smarthome.system.initapp
     * Server -> Client: de.awesome.smarthome.system.initsystem
     * Client-> Server: de.awesome.smarthome.system.initend
     */

    /**
     * de.awesome.smarthome.system.initapp
     */
    private static final String COMMANDHASH_INITAPP = "427f8150c474bcad4152b18eef6fa9ee";
    /**
     * de.awesome.smarthome.system.initsystem
     */
    private static final String COMMANDHASH_INITSYSTEM = "6b9994d2c83b1c047d5c436c79fa9b4f";
    /**
     * de.awesome.smarthome.system.initend
     */
    private static final String COMMANDHASH_INITEND = "bae5a28dbc29ebe6de10eeaa11bc34f9";

    private static final String SYSTEM_ID = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";

    private static boolean isInitializing;
    private static Socket socket;
    private static Transport transport;
    private static App app;
    private static volatile Map<String, QAMessage> qaMessageCache;

    /**
     * Initializes the kernel
     * @param port Port to connect to
     * @param app Application instance
     * @throws Exception Thrown if something goes wrong
     */
    public static synchronized void initialize(int port, App app) throws Exception {
        Kernel.app = app;
        socket = new Socket("127.0.0.1", port);
        transport = new Transport(socket.getOutputStream(), socket.getInputStream(), Kernel::handleMessage);
        qaMessageCache = new HashMap<>();

        isInitializing = true;
        Message initapp = new Message(SYSTEM_ID, SYSTEM_ID, COMMANDHASH_INITAPP, new ArrayList<>());
        transport.send(initapp);
    }

    /**
     * @return True if the kernel is initializing
     */
    public static synchronized boolean isIsInitializing(){
        return isInitializing;
    }

    /**
     * @return True if a connection to the base system is active
     */
    public static synchronized boolean isConnectionActive(){
        return transport.isConnected();
    }

    /**
     * Sends a systemcall to the base system. You may want to use the provided classes for this functionality
     * (which in turn call this function with the right arguments)
     * @param command Command name
     * @param params List of parameters
     * @return Response of the systemcall
     */
    public static synchronized QAMessage systemCall(String command, List<Parameter> params){
        QAMessage qaMessage = new QAMessage(false, SYSTEM_ID, SYSTEM_ID, MD5.generateMd5(command), params);
        qaMessageCache.put(qaMessage.getUUID(), null);
        transport.send(qaMessage.getMessage());

        while(qaMessageCache.get(qaMessage.getUUID()) == null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        qaMessage = qaMessageCache.get(qaMessage.getUUID());
        qaMessageCache.remove(qaMessage.getUUID());
        return qaMessage;
    }

    private static void handleMessage(Message message){
        if(isInitializing){
            if(!message.getCommandHash().equals(COMMANDHASH_INITSYSTEM)){
                throw new RuntimeException("Expected initsystem command");
            }

            Message initend = new Message(SYSTEM_ID, SYSTEM_ID, COMMANDHASH_INITEND, new ArrayList<>());
            transport.send(initend);
            isInitializing = false;
        }else {
            QAMessage qaMessage = new QAMessage(message);
            if(!qaMessageCache.containsKey(qaMessage.getUUID())){
                throw new RuntimeException("Unknown message!");
            }

            qaMessageCache.put(qaMessage.getUUID(), qaMessage);
        }
    }
}
