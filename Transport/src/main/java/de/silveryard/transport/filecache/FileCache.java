package de.silveryard.transport.filecache;

import de.silveryard.transport.MessageHandler;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.Message;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by CHofm on 14.01.2017.
 */
public class FileCache implements MessageHandler {

    /**
     * MD5 -> de.awesome.smarthome.filecache.uploadstart
     */
    private static final String UPLOAD_START = "ef9531a094aaac4a3f3a0f4cd02c4db6";

    /**
     * MD5 -> de.awesome.smarthome.filecache.uploadprogress
     */
    private static final String UPLOAD_PROGRESS = "620c728f9ca9d8075ad354dff6804d83";

    /**
     * MD5 -> de.awesome.smarthome.filecache.uploadfinish
     */
    private static final String UPLOAD_FINISH= "3682a7b006c08d8ec4070abdffee976f";

    /**
     * MD5 -> de.awesome.smarthome.filecache.uploadsuccess
     */
    private static final String UPLOAD_CONFIRM_SUCCESS = "79f9cde1281e278866943537ebb53526";

    /**
     * MD5 -> de.awesome.smarthome.filecache.uploadrequestpackage
     */
    private static final String UPLOAD_REQUEST_PACKAGE = "47cb0562ea7fdf63d8e5869aa9752370";

    private MessageHandler messageHandler;
    private FileReceiveHandler fileHandler;

    private Map<String, FileTransport> outgoingTransports;
    private Map<String, FileReceiver> incomingTransports;

    private boolean isShuttingDown;

    /**
     * Constructor
     * @param onSend Handler that can send messages
     * @param onFileReceive Called when a file is received
     */
    public FileCache(MessageHandler onSend, FileReceiveHandler onFileReceive){
        messageHandler = onSend;
        fileHandler = onFileReceive;
        outgoingTransports = new HashMap<>();
        incomingTransports = new HashMap<>();
        isShuttingDown = false;
    }

    /**
     * Handles a message
     * @param m the message received by the transport
     */
    @Override
    public void handle(Message m) {
        if(isShuttingDown) {
            return;
        }
        String transportID = m.getParams().get(0).getString();
        switch(m.getCommandHash()){
            case UPLOAD_START:
                incomingTransports.put(m.getParams().get(0).getString(), new FileReceiver(m));
                break;
            case UPLOAD_FINISH:
            case UPLOAD_PROGRESS :
                if(incomingTransports.containsKey(transportID)){
                        incomingTransports.get(transportID).receive(m);
                }
                break;
            case UPLOAD_CONFIRM_SUCCESS:
                outgoingTransports.remove(transportID);
                System.gc();
                break;
        }
    }

    /**
     * Sends
     * @param sourceID the ID of the sending client
     * @param destinationID the ID of the receiving client
     * @param commandHash the command that shall be invoked when the file was successfully transferred
     * @param params a list of parameters for the method that is associated with the commandHash
     * @param fileData the data to be send
     */
    public void sendFile(String sourceID, String destinationID, String commandHash, List<Parameter> params, byte[] fileData){
        if(isShuttingDown){
            return;
        }
        String id = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        FileTransport ft = new FileTransport(messageHandler, sourceID, destinationID, commandHash, id, params, fileData);
        outgoingTransports.put(id, ft);
        ft.send();
    }

    /**
     * Terminates all sending and receiving processes so that no more data will be sent
     * and no more incoming messages will be processed.
     */
    public void shutdown(){
        isShuttingDown = true;
        outgoingTransports.values().forEach(ft -> ft.cancel());
        System.gc();
    }

    private class FileTransport{

        private MessageHandler sender;
        private String sourceID;
        private String destinationID;
        private String commandHash;
        private String transportID;
        private List<Parameter> params;
        private byte[] data;
        private Thread senderThread;
        private boolean isCancelled;

        public FileTransport(MessageHandler sender, String sourceID, String destinationID, String commandHash, String transportID, List<Parameter> params, byte[] data){
            this.sender = sender;
            this.sourceID = sourceID;
            this.destinationID = destinationID;
            this.commandHash = commandHash;
            this.params = params;
            this.data = data;
            this.transportID = transportID;
            this.isCancelled = false;
        }

        public void send(){
            senderThread = new Thread(() -> {
                int neededParams = (data.length / Parameter.PARAM_DATA_MAX_LENGTH) + Math.min(data.length % Parameter.PARAM_DATA_MAX_LENGTH, 0);
                int neededMessages = (neededParams / (Message.MAX_PARAM_COUNT - 2)) + Math.min(neededParams % (Message.MAX_PARAM_COUNT - 2), 1);
                int index = 0;

                if(isCancelled){
                    return;
                }

                List<Parameter> initialParams = Stream.of(
                        Parameter.createString(transportID),
                        Parameter.createInt(data.length),
                        Parameter.createString(commandHash),
                        Parameter.createInt(params.size())
                ).collect(Collectors.toList());
                initialParams.addAll(params);

                Message initialMessage = new Message(sourceID, destinationID, UPLOAD_START, initialParams);
                sender.handle(initialMessage);

                if(isCancelled){
                    return;
                }

                for(int i = 0; i < neededMessages; i++){
                    List<Parameter> params = new LinkedList<>();
                    //ID
                    params.add(Parameter.createString(transportID));
                    //MessageNumber
                    params.add(Parameter.createInt(i));
                    for(int j = 253; j > 0 && index <= data.length; j--){
                        if(isCancelled){
                            return;
                        }
                        params.add(Parameter.createByteArray(Arrays.copyOfRange(data, index, Math.min(index + Parameter.PARAM_DATA_MAX_LENGTH, data.length))));
                        index += Parameter.PARAM_DATA_MAX_LENGTH;
                    }
                    sender.handle(new Message(sourceID, destinationID, UPLOAD_PROGRESS, params));
                }

                if(isCancelled){
                    return;
                }

                List<Parameter> finalParams = Stream.of(Parameter.createString(transportID)).collect(Collectors.toList());
                Message finalMessage = new Message(sourceID, destinationID, UPLOAD_FINISH, finalParams);
                sender.handle(finalMessage);
            });
            senderThread.setDaemon(true);
            senderThread.start();
        }

        public void cancel(){
            isCancelled = true;
            senderThread.interrupt();
        }

        public String getTransportID(){
            return transportID;
        }
    }

    private class FileReceiver{
        private String transportID;
        private Set<Message> messages;
        private Message initialMessage;

        private int expectedBytes;

        public FileReceiver(Message initialMessage){
            this.transportID = initialMessage.getParams().get(0).getString();
            this.expectedBytes = initialMessage.getParams().get(1).getInt();
            this.initialMessage = initialMessage;
            this.messages = new TreeSet<>(Comparator.comparingInt(m -> m.getParams().get(1).getInt()));
        }

        public void receive(Message m){
            if(m.getCommandHash().equals(UPLOAD_FINISH)){
                if(checkIntegrity()){

                    //Send acknowledge
                    Message ack = new Message(
                            initialMessage.getSenderID(),
                            initialMessage.getDestinationID(),
                            UPLOAD_CONFIRM_SUCCESS,
                            new LinkedList<>(Arrays.asList(m.getParams().get(0)))
                            );
                    messageHandler.handle(ack);

                    fileHandler.handle(
                            initialMessage.getSenderID(),
                            transportID,
                            initialMessage.getParams().get(2).getString(),  //CommandHash for the receiver
                            initialMessage.getParams().stream().skip(4).collect(Collectors.toList()), //Initial params for actual call
                            getData()
                    );
                    incomingTransports.remove(this);
                    System.gc();
                }
            } else {
                messages.add(m);
            }
        }

        private boolean checkIntegrity(){
            return messages
                    .stream()
                    .map(m -> m.getParams())
                    .flatMap(params -> params.stream().skip(2))
                    .mapToInt(p -> p.getSize())
                    .sum() == expectedBytes;
        }

        private byte[] getData() {
            byte[] b = new byte[expectedBytes];
            int c = 0;

            for (Message m : messages) {
                for (int i = 2; i < m.getParams().size(); i++) {
                    for (int j = 0; j < m.getParams().get(i).getData().length; j++) {
                        b[c++] = m.getParams().get(i).getData()[j];
                    }
                }
            }
            return  b;
        }
    }
}
