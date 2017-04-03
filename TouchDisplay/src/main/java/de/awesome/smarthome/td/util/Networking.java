package de.awesome.smarthome.td.util;

import de.awesome.smarthome.transport.Message;
import de.awesome.smarthome.transport.MessageHandler;
import de.awesome.smarthome.transport.Transport;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sebif on 11.03.2017.
 */
public class Networking {
    private ServerSocket serverSocket;
    private Socket socket;
    private Transport transport;

    public Networking()throws Exception {
        this(0);
    }
    public Networking(int port) throws Exception{
        serverSocket = new ServerSocket(port);
    }

    public void open(MessageHandler messageHandler) throws Exception {
        open(messageHandler, 0);
    }
    public void open(MessageHandler messageHandler, int timeout) throws Exception {
        if(timeout != 0) {
            serverSocket.setSoTimeout(timeout);
        }
        socket = serverSocket.accept();
        transport = new Transport(socket.getOutputStream(), socket.getInputStream(), messageHandler);
    }
    public void send(Message message){
        transport.send(message);
    }

    public int getPort(){
        return serverSocket.getLocalPort();
    }
    public boolean isConnected(){
        return transport.isConnected();
    }
    public void closeConnection(){
        transport.disconnect();
    }
}
