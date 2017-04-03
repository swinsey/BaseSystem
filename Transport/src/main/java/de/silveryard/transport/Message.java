package de.silveryard.transport;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by CHofm on 08.01.2017.
 */
public class Message {
    /**
     * Maximum number of parameters that can be sent with a single message
     */
    public static final int MAX_PARAM_COUNT = 255;

    private String senderID;
    private String destinationID;
    private String commandHash;
    private List<Parameter> params;

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

        checkValidHexString(senderID);
        checkValidHexString(destinationID);
        checkValidHexString(commandHash);

        if(params.size() > MAX_PARAM_COUNT){
            throw new IllegalArgumentException("Too many params (max: " + MAX_PARAM_COUNT + ", actual: " + params.size() + ")");
        }

        this.senderID = senderID;
        this.destinationID = destinationID;
        this.commandHash = commandHash;
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
}
