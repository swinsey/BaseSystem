package de.silveryard.basesystem.sdk.kernel;

import de.silveryard.transport.*;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Sebif on 20.02.2017.
 */
public abstract class Kernel {
    /**
     * Connection:
     *
     * Handshake:
     * Client -> Server: de.silveryard.basesystem.system.initapp
     * Server -> Client: de.silveryard.basesystem.system.initsystem
     * Client-> Server: de.silveryard.basesystem.system.initend
     */

    /**
     * The kernel will log no messages
     */
    public static final int KERNEL_LOGGING_NONE = 0x000;
    /**
     * When set the kernel will log a message each time a systemcall is made
     */
    public static final int KERNEL_LOGGING_SYSTEMCALLS = 0x001;
    /**
     * When set the kernel will log the complete qa cache each time a qa message arrives.
     * Generates lots of output
     */
    public static final int KERNEL_LOGGING_QACACHE = 0x002;
    /**
     * When set the kernel will log a message each time a command is invoked
     */
    public static final int KERNEL_LOGGING_INCOMING_COMMANDS = 0x004;
    /**
     * When set the kernel will log a message each time a message arrives.
     * This includes systemcall responses
     */
    public static final int KERNEL_LOGGING_INCOMING_MESSAGES = 0x008;

    /**
     * de.silveryard.basesystem.system.initapp
     */
    private static final String COMMANDHASH_INITAPP = "427f8150c474bcad4152b18eef6fa9ee";
    /**
     * de.silveryard.basesystem.system.initsystem
     */
    private static final String COMMANDHASH_INITSYSTEM = "6b9994d2c83b1c047d5c436c79fa9b4f";
    /**
     * de.silveryard.basesystem.system.initend
     */
    private static final String COMMANDHASH_INITEND = "bae5a28dbc29ebe6de10eeaa11bc34f9";

    private static final String SYSTEM_ID = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";

    private static boolean isInitializing;
    private static Socket socket;
    private static Transport transport;
    private static App app;
    private static Map<String, QAMessage> qaMessageCache;
    private static Map<String, List<MessageHandler>> commandHandlers;
    private static int logging;

    /**
     * Initializes the kernel
     * @param port Port to connect to
     * @param app Application instance
     * @throws Exception Thrown if something goes wrong
     */
    public static synchronized void initialize(int port, App app) throws Exception{
        initialize(port, app, KERNEL_LOGGING_NONE);
    }

    /**
     * Initializes the kernel
     * @param port Port to connect to
     * @param app Application instance
     * @param logging If true the kernel will log messages when sending system calls
     * @throws Exception Thrown if something goes wrong
     */
    public static synchronized void initialize(int port, App app, int logging) throws Exception {
        Kernel.app = app;
        socket = new Socket("127.0.0.1", port);
        transport = new Transport(socket.getOutputStream(), socket.getInputStream(), Kernel::handleMessage);
        qaMessageCache = new HashMap<>();
        commandHandlers = new HashMap<>();
        Kernel.logging = logging;

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
    public static QAMessage systemCall(String command, List<Parameter> params){
        if((logging & KERNEL_LOGGING_SYSTEMCALLS) == KERNEL_LOGGING_SYSTEMCALLS){
            System.out.println("SystemCall: " + command);
        }

        QAMessage qaMessage = new QAMessage(false, SYSTEM_ID, SYSTEM_ID, MD5.generateMd5(command), params);
        qaMessageCache.put(qaMessage.getUUID(), null);
        transport.send(qaMessage.getMessage());

        while(qaMessageCache.get(qaMessage.getUUID()) == null){
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        qaMessage = qaMessageCache.get(qaMessage.getUUID());
        qaMessageCache.remove(qaMessage.getUUID());
        return qaMessage;
    }

    /**
     * Sends a systemcall to the base system. You may want to use the provided classes for this functionality
     * (which in turn call this function with the right arguments)
     * This operation is asynchronous
     * @param command Command Name
     * @param params List of parameters
     * @return CompletableFuture object
     */
    public static CompletableFuture<QAMessage> systemCallAsync(String command, List<Parameter> params){
        return CompletableFuture.supplyAsync(() -> systemCall(command, params));
    }

    /**
     * Registers a handler for a specific command. Each time the command is called from the system, the handler will be invoked
     * @param command Command to listen for
     * @param handler Handler that gets invoked when the command is received
     */
    public static synchronized void registerForCommand(String command, MessageHandler handler){
        String hash = MD5.generateMd5(command);
        if(!commandHandlers.containsKey(hash)){
            commandHandlers.put(hash, new ArrayList<>());
        }

        commandHandlers.get(hash).add(handler);
    }

    /**
     * Dummy systemcall. Takes no params and returns nothing
     * @param outReturnCode General Return Code
     */
    public static void systemCallDummy(Wrapper<ReturnCode> outReturnCode){
        QAMessage response = systemCall("de.silveryard.basesystem.systemcall.dummy", new ArrayList<>());
        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
    }

    private static void handleMessage(Message message){
        if(isInitializing){
            if(!message.getCommandHash().equals(COMMANDHASH_INITSYSTEM)){
                throw new RuntimeException("Expected initsystem command");
            }

            Message initend = new Message(SYSTEM_ID, SYSTEM_ID, COMMANDHASH_INITEND, new ArrayList<>());
            transport.send(initend);
            isInitializing = false;
            return;
        }

        if((logging & KERNEL_LOGGING_INCOMING_MESSAGES) == KERNEL_LOGGING_INCOMING_MESSAGES){
            System.out.println("Incoming Message: " + message.getCommandHash());
        }

        if(commandHandlers.containsKey(message.getCommandHash())){
            if((logging & KERNEL_LOGGING_INCOMING_COMMANDS) == KERNEL_LOGGING_INCOMING_COMMANDS){
                System.out.println("Command: " + message.getCommandHash());
            }
            List<MessageHandler> handlers = commandHandlers.get(message.getCommandHash());
            for(int i = 0; i < handlers.size(); i++){
                handlers.get(i).handle(message);
            }
            return;
        }

        try {
            QAMessage qaMessage = new QAMessage(message);

            if((logging & KERNEL_LOGGING_QACACHE) == KERNEL_LOGGING_QACACHE){
                System.out.print("QA ID: " + qaMessage.getUUID());
                System.out.println("QA Cache: ");
                for(String key : qaMessageCache.keySet()){
                    System.out.println("    " + key);
                }
            }

            if (!qaMessageCache.containsKey(qaMessage.getUUID())) {
                System.out.println("Unknown Message: " + qaMessage.getUUID());
            }

            qaMessageCache.put(qaMessage.getUUID(), qaMessage);
        }catch(InvalidMessageException e){
            return;
        }
    }
}
