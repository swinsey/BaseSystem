package de.silveryard.basesystem.networkinterface;

import de.silveryard.transport.Message;
import de.silveryard.transport.MessageHandler;
import de.silveryard.transport.Transport;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

class RemoteClient {

    private String id;
    private Socket socket;
    private Transport transport;
    private MessageHandler messageHandler;

    protected RemoteClient(Socket s, MessageHandler mh) throws IOException{
        Objects.requireNonNull(s);
        Objects.requireNonNull(mh);

        this.id = "";
        this.socket = s;
        this.messageHandler = mh;
        this.transport = new Transport(s.getOutputStream(), s.getInputStream(), m -> {
            if(id.isEmpty()){
                id = m.getSenderID();
            }
            messageHandler.handle(m);
        });
    }

    /**
     * Sends the message {@code m} to the client specified by this object.
     * @param m The message to be sent.
     * @return true if the message was successfully sent, false if message was not sent.
     */
    public boolean send(Message m){
        return transport.send(m);
    }

    /**
     * Disconnects this client from the server so that no more messages can be sent to this endpoint.
     */
    public void disconnect(){
        try{
            transport.disconnect();
            socket.close();
        }catch(IOException e){ /* IGNORE */}
    }

    /**
     * @return true if this client is connected and able to send messages, false if the connection is closed
     */
    public boolean isConnected(){
        return socket != null && !socket.isClosed();
    }

    /**
     * @return the id of this client
     */
    public String getID(){
        return id;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof RemoteClient){
            RemoteClient rc = (RemoteClient) o;
            return rc.getID().equals(id);
        }
        return false;
    }
}
