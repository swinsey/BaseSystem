package de.silveryard.basesystem.logging;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 12.04.2017.
 */
public class LogManager {
    private static LogManager instance;
    /**
     * Initializes this log manager
     * @param logToConsole When true, everything will be logged to the console
     */
    public static void initialize(boolean logToConsole){
        instance = new LogManager(logToConsole);
    }
    /**
     * Singleton getter
     * @return LogManager instance
     */
    public static LogManager getInstance(){
        return instance;
    }

    //Redirection of System.out
    private final ByteArrayOutputStream outStream;
    private final PrintStream printOutStream;
    private final InputStreamReader outReader;
    private final BufferedReader bufferedOutReader;
    private final PipedInputStream pipedInOutStream;
    private final PipedOutputStream pipedOutOutStream;

    //Redirection of System.err
    private final ByteArrayOutputStream errStream;
    private final PrintStream printErrStream;
    private final InputStreamReader errReader;
    private final BufferedReader bufferedErrReader;
    private final PipedInputStream pipedInErrStream;
    private final PipedOutputStream pipedOutErrStream;

    //Threads
    private final Thread threadRedirect;
    private final Thread threadReadOut;
    private final Thread threadReadErr;

    //Misc
    private final List<ILogListener> listeners;

    private LogManager(boolean logToConsole){
        listeners = new ArrayList<>();

        if(logToConsole){
            registerListener(new ConsoleLogListener());
        }

        try {
            outStream = new ByteArrayOutputStream();
            pipedInOutStream = new PipedInputStream();
            pipedOutOutStream = new PipedOutputStream(pipedInOutStream);
            printOutStream = new PrintStream(outStream);
            outReader = new InputStreamReader(pipedInOutStream);
            bufferedOutReader = new BufferedReader(outReader);
            System.setOut(printOutStream);

            errStream = new ByteArrayOutputStream();
            pipedInErrStream = new PipedInputStream();
            pipedOutErrStream = new PipedOutputStream(pipedInErrStream);
            printErrStream = new PrintStream(errStream);
            errReader = new InputStreamReader(pipedInErrStream);
            bufferedErrReader = new BufferedReader(errReader);
            System.setErr(printErrStream);

            threadReadOut = new Thread(this::threadOutReader);
            threadReadOut.setDaemon(true);
            threadReadOut.start();
            threadReadErr = new Thread(this::threadErrReader);
            threadReadErr.setDaemon(true);
            threadReadErr.start();
            threadRedirect = new Thread(this::threadRedirectStreams);
            threadRedirect.setDaemon(true);
            threadRedirect.start();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Logs a message. Should only be called by AppLoader
     * @param logger Identity that logged this message. System on system logs and the apps name on app logs
     * @param message Message that got logged
     * @param type Stream that was used to log the message
     */
    public synchronized void log(String logger, String message, LogMessageType type){
        for(int i = 0; i < listeners.size(); i++){
            listeners.get(i).logMessage(logger, message, type);
        }
    }

    /**
     * Registers a new log listener. This listener will be notyfied every time a message gets logged
     * @param listener Listener to register
     */
    public synchronized void registerListener(ILogListener listener){
        listeners.add(listener);
    }
    /**
     * Unregisters a log listener. This listener will no longer receive notifications
     * @param listener Listener to remove
     */
    public synchronized void unregisterListener(ILogListener listener){
        listeners.remove(listener);
    }

    private void threadRedirectStreams(){
        try{
            while(true) {
                outStream.writeTo(pipedOutOutStream);
                outStream.reset();
                errStream.writeTo(pipedOutErrStream);
                errStream.reset();
                Thread.sleep(200);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private void threadOutReader(){
        try {
            while (true) {
                String line = bufferedOutReader.readLine();
                log("System", line, LogMessageType.OUT);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    private void threadErrReader(){
        try {
            while (true) {
                String line = bufferedErrReader.readLine();
                log("System", line, LogMessageType.ERROR);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
