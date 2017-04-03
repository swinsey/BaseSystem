package de.silveryard.apf;

/**
 * Created by Sebif on 25.02.2017.
 */
public class ParseException extends RuntimeException {
    /**
     * Constructor
     * @param message Error message
     */
    public ParseException(String message){
        super(message);
    }
    /**
     * Constructor
     * @param inner Parent exception
     * @param message Error message
     */
    public ParseException(Exception inner, String message){
        super(message, inner);
    }
}
