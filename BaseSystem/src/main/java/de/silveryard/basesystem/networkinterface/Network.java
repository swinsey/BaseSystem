package de.silveryard.basesystem.networkinterface;

import de.silveryard.transport.Message;
import de.silveryard.transport.MessageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by CHofm on 07.01.2017.
 */
class Network {

    private final int INCOMING_MESSAGE_PORT = 1337;
    private volatile boolean shutdown;

    private ServerSocket serverSocket;
    private List<RemoteClient> clients;

    private MessageHandler messageHandler;


    public static final String SERVER_ID = "00000000000000000000000000000000";

    public Network(MessageHandler messageHandler){
        this.messageHandler = messageHandler;
    }


    /*  Start listening for incoming connections.
     */
    public void listen(){
        if(serverSocket != null && !serverSocket.isClosed()){
            System.out.println("Network already listening");
            return;
        }

        shutdown = false;

        try {
            serverSocket = new ServerSocket(INCOMING_MESSAGE_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
            //stopListening();
        }

        new Thread(() -> {
            try {
                clients = new LinkedList<>();

                while(!shutdown){
                    Socket clientSocket = serverSocket.accept();

                    RemoteClient remote = new RemoteClient(clientSocket, messageHandler::handle);
                    clients.add(remote);
                }
            } catch (IOException e) {handleServerThreadError(e);}
        }).start();
    }

    /**
     *  Makes the server stop listening for new client sockets
     */
    public void stopListening(){
        shutdown = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends the message to the client specified by the destinationID.
     * @param m The message to be sent
     */
    public boolean send(Message m){
        if(!isListening()){
            return false;
        }
        Optional<RemoteClient> client =
                clients
                        .stream()
                        .filter(c -> c.getID().equals(m.getDestinationID()))
                        .findFirst();

        if(client.isPresent()){
            if(client.get().isConnected()){
                Message msg = new Message(SERVER_ID, m.getDestinationID(), m.getCommandHash(), m.getParams());
                client.get().send(msg);
                return true;
            } else {
                clients.remove(client.get());
            }
        }
        return false;
    }

    /**
     * @return true if server is listening, false is server is not listening
     */
    public boolean isListening(){
        return serverSocket != null && !serverSocket.isClosed();
    }

    /**
     * @return A list containing the IDs of all connected clients
     */
    public List<String> getConnectedClientIds(){
        return clients.stream().map(c -> c.getID()).collect(Collectors.toList());
    }

    /**
     * Handles exceptions occurring on the client acceptor thread
     * @param e The exception that occurred in the acceptor thread
     */
    private void handleServerThreadError(IOException e){
        switch(e.getMessage()){
            case "socket closed":

                //If shutdown was not ordered
                if(!shutdown){
                    throw new RuntimeException("ConnectionAcceptorThread: Unintended socket closure");
                    //TODO stacktrace
                } else {
                    System.out.println("Shuttong down server");
                }
                disconnectAll();
                break;
            default:
                e.printStackTrace();
                throw new RuntimeException(e);
                //disconnectAll();
        }
    }

    /**
     * Disconnects all connected clients
     */
    private void disconnectAll(){
        /*Debug.log(LogType.ERROR, "Server", "Disconnecting all clients");
        clients.forEach(c -> c.disconnect());
        Debug.log(LogType.ERROR, "Server", "Disconnected all clients");*/
    }
}

