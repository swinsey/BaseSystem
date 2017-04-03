package de.awesome.smarthome.transport;

/**
 * Created by chhofm on 12.01.2017.
 */
public class InvalidMessageException extends RuntimeException {

    /**
     * Constructor
     * @param s Error Message
     */
    public InvalidMessageException(String s){
        super(s);
    }
}
