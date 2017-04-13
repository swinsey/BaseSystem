package de.silveryard.basesystem.util;

import de.silveryard.transport.Message;
import de.silveryard.transport.MessageHandler;
import de.silveryard.transport.Transport;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sebif on 11.03.2017.
 */
public class Networking {
    private ServerSocket serverSocket;
    private Socket socket;
    private Transport transport;

    /**
     * Constructor
     * @throws Exception Thrown if something goes wrong
     */
    public Networking()throws Exception {
        this(0);
    }
    /**
     * Constructor
     * @param port Port to use
     * @throws Exception Thrown if something goes wrong
     */
    public Networking(int port) throws Exception{
        serverSocket = new ServerSocket(port);
    }

    /**
     * Opens the connection
     * @param messageHandler Handler that handles messages
     * @throws Exception Thrown if something goes wrong
     */
    public void open(MessageHandler messageHandler) throws Exception {
        open(messageHandler, 0);
    }
    /**
     * Opens the connection
     * @param messageHandler Handler that handles the messages
     * @param timeout TImeout for connection
     * @throws Exception Thrown if something goes wrong
     */
    public void open(MessageHandler messageHandler, int timeout) throws Exception {
        if(timeout != 0) {
            serverSocket.setSoTimeout(timeout);
        }
        socket = serverSocket.accept();
        transport = new Transport(socket.getOutputStream(), socket.getInputStream(), messageHandler);
    }
    /**
     * Sends a message
     * @param message Message to send
     */
    public void send(Message message){
        transport.send(message);
    }

    /**
     * Returns the connections port
     * @return Port value
     */
    public int getPort(){
        return serverSocket.getLocalPort();
    }
    /**
     * Returns if the connection is still active
     * @return True if the connection is active. False otherwise
     */
    public boolean isConnected(){
        return transport.isConnected();
    }
    /**
     * Closes the connection
     */
    public void closeConnection(){
        transport.disconnect();
    }
}
