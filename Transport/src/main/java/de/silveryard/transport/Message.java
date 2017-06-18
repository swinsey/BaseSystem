package de.silveryard.transport;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by CHofm on 08.01.2017.
 */
public class Message {
    /**
     * Sequence that indicates the start of a new message
     */
    public static final byte[] MESSAGE_START_SEQUENCE = new byte[]{1,2,3,5,8,13,21,34};
    /**
     * Sequence that indicates the end of a message
     */
    public static final byte[] MESSAGE_END_SEQUENCE = new byte[]{1,3,5,7,11,13,17,19};

    /**
     * Maximum number of parameters that can be sent with a single message
     */
    public static final int MAX_PARAM_COUNT = 255;

    private String senderID;
    private String destinationID;
    private String commandHash;
    private List<Parameter> params;
    private byte[] senderBuffer = new byte[16];
    private byte[] receiverBuffer = new byte[16];
    private byte[] commandBuffer = new byte[16];

    /**
     * Constructor
     * @param senderID GUID of the sender
     * @param destinationID GUID of the receiver
     * @param commandHash Hash of commant to call
     * @param params List of parameters
     */
    public Message(String senderID, String destinationID, String commandHash, List<Parameter> params){
        Objects.requireNonNull(senderID);
        Objects.requireNonNull(destinationID);
        Objects.requireNonNull(commandHash);
        Objects.requireNonNull(params);

        String tmpSenderID = senderID;//senderID.toLowerCase().replaceAll("-", "");
        String tmpDestinationID = destinationID;//destinationID.toLowerCase().replaceAll("-", "");
        String tmpCmdHash = commandHash;//commandHash.toLowerCase().replaceAll("-", "");

        checkValidHexString(tmpSenderID);
        checkValidHexString(tmpDestinationID);
        checkValidHexString(tmpCmdHash);

        if(params.size() > MAX_PARAM_COUNT){
            throw new IllegalArgumentException("Too many params (max: " + MAX_PARAM_COUNT + ", actual: " + params.size() + ")");
        }

        this.senderID = tmpSenderID;
        this.destinationID = tmpDestinationID;
        this.commandHash = tmpCmdHash;
        this.params = params;
    }

    /**
     * @return Returns the sender guid
     */
    public String getSenderID() {
        return senderID;
    }

    /**
     * @return Returns the receiver guid
     */
    public String getDestinationID() {
        return destinationID;
    }

    /**
     * @return Returns the commands hash value
     */
    public String getCommandHash() {
        return commandHash;
    }

    /**
     * @return Returns the list of parameters
     */
    public List<Parameter> getParams() {
        return params;
    }

    private void checkValidHexString(String s){
        for(char c : s.toCharArray()){
            if(Character.getNumericValue(c) > 15){
                throw new IllegalArgumentException(s + " is not a valid hex string");
            }
        }
    }

    /**
     * Checks for equality
     * @param o Other object to compare
     * @return True if equal
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Message){
            Message m = (Message)o;

            return  m.getParams().equals(params) &&
                    m.getSenderID().equals(senderID) &&
                    m.getDestinationID().equals(destinationID) &&
                    m.getCommandHash().equals(commandHash);
        }
        return false;
    }

    /**
     * Converts the parameter to a string
     * @return String representation
     */
    @Override
    public String toString(){
       return "Message:[SourceID:" + senderID
            + ", DestinationID:" + destinationID
            + ", CommandHash:" + commandHash
            + ", Params:{" + params.stream().map(p -> p.toString()).collect(Collectors.joining(","))
            + "}]";
    }

    /**
     *  Dissembles this message into a byte array
     */
    public byte[] toBytes(){
        hashToBinary(senderID, senderBuffer);
        hashToBinary(destinationID, receiverBuffer);
        hashToBinary(commandHash, commandBuffer);

        byte[] paramData = extractParams(params);
        byte[] result = concatArrays(
                MESSAGE_START_SEQUENCE,
                senderBuffer,
                receiverBuffer,
                commandBuffer,
                new byte[]{(byte)getParams().size()},
                paramData,
                MESSAGE_END_SEQUENCE
        );
        return result;
    }

    private void hashToBinary(String hash, byte[] buffer){
        for(int i = 0; i < 16; i++){
            int i1 = Character.getNumericValue(hash.charAt((i * 2) + 0));
            int i2 = Character.getNumericValue(hash.charAt((i * 2) + 1));
            byte r = (byte)((i1 << 4) + i2);
            buffer[i] = r;
        }
    }

    /**
     * Produces a new byte array from a list of given byte arrays.
     * @param arrays a list of arrays to be merged into one single array
     * @return a single byte array containing all arrays in the right order and internal order.
     */
    private byte[] concatArrays(byte[]... arrays){
        int totalLength = 0;
        for(int i = 0; i < arrays.length; i++){
            totalLength += arrays[i].length;
        }
        int offset = 0;

        byte[] res = new byte[totalLength];
        for(byte[] b : arrays){
            System.arraycopy(b, 0, res, offset, b.length);
            offset += b.length;
        }
        return res;
    }

    /**
     * Creates a byte array from a list of parameters
     * @param params the list of parameters to be dissembled into a single byte array
     * @return the byte array containing the parameters dissembled to bytes
     */
    private byte[] extractParams(List<Parameter> params){
        int size = 0;
        for(int i = 0; i < params.size(); i++){
            size += params.get(i).getSize() + 1;
        }

        byte[] byteParams = new byte[size];
        int offset = 0;

        for(int i = 0; i < params.size(); i++){
            Parameter p = params.get(i);
            byteParams[offset++] = (byte)p.getSize();
            byte[] data = p.getData();
            System.arraycopy(data, 0, byteParams, offset, data.length);
            offset += data.length;
        }
        return byteParams;
    }
}
