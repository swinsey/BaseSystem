package de.silveryard.basesystem.app;

import de.silveryard.basesystem.logging.LogManager;
import de.silveryard.basesystem.logging.LogMessageType;
import de.silveryard.basesystem.util.Networking;
import de.silveryard.transport.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Created by Sebif on 11.03.2017.
 */
class AppLoader {
    /**
     * de.silveryard.basesystem.system.initapp
     */
    private static final String COMMANDHASH_INITAPP = "427f8150c474bcad4152b18eef6fa9ee";
    /**
     * de.silveryard.basesystem.system.initsystem
     */
    private static final String COMMANDHASH_INITSYSTEM = "6b9994d2c83b1c047d5c436c79fa9b4f";
    /**
     * de.silveryard.basesystem.system.initend
     */
    private static final String COMMANDHASH_INITEND = "bae5a28dbc29ebe6de10eeaa11bc34f9";

    private static final String SYSTEM_ID = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";

    public static AppLoader create(Path binaryPath, Path dataDirPath, Path readonlyPath){

        try{
            AppLoader loader = new AppLoader(binaryPath, dataDirPath, readonlyPath);
            return loader;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private enum InitState{
        PHASE_1,
        PHASE_2,
        ERROR,
        SUCCESS
    }

    private Networking networking;
    private volatile InitState initializing;
    private Path dataDirPath;
    private Path readonlyDirPath;
    private Consumer<Message> systemMessageHandler;

    private AppLoader(Path binaryPath, Path dataDirPath, Path readonlyPath) throws Exception{
        networking = new Networking();

        this.dataDirPath = dataDirPath;
        this.readonlyDirPath = readonlyPath;

       /*
        *   [0] Communication Port Number
        *   [1] Readonly Directory Path
        *   [2] Data Directory 00
        *   */
        StringBuilder cmdBuilder = new StringBuilder();
        cmdBuilder.append("java -jar ");
        cmdBuilder.append(binaryPath.toString()).append(" ");
        cmdBuilder.append(networking.getPort()).append(" ");
        cmdBuilder.append(dataDirPath.toString()).append(" ");
        cmdBuilder.append(readonlyPath.toString());

        initializing = InitState.PHASE_1;
        Process p = Runtime.getRuntime().exec(cmdBuilder.toString());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                        try {
                            String line;
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(p.getInputStream()));
                            while ((line = in.readLine()) != null) {
                                LogManager.getInstance().log("App", line, LogMessageType.OUT);
                            }
                    in.close();
                }catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        });
        t.setDaemon(true);
        t.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String line;
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(p.getErrorStream()));
                    while ((line = in.readLine()) != null) {
                        LogManager.getInstance().log("App", line, LogMessageType.ERROR);
                    }
                    in.close();
                }catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        });
        t2.setDaemon(true);
        t2.start();
        networking.open(this::handleMessage, 5000);

        while(!(initializing == InitState.SUCCESS || initializing == InitState.ERROR)){
            Thread.sleep(200);
        }
        if(initializing == InitState.ERROR){
            throw new Exception("Failed loading app");
        }
    }

    public void setSystemMessageHandler(Consumer<Message> systemMessageHandler){
        this.systemMessageHandler = systemMessageHandler;
    }

    public Path getDataDirPath(){
        return dataDirPath;
    }

    public void sendMessage(Message message){
        networking.send(message);
    }

    private void handleMessage(Message message){
        switch(initializing){
            case PHASE_1:{
                if(!message.getCommandHash().equals(COMMANDHASH_INITAPP)){
                    initializing = InitState.PHASE_2;
                    return;
                }else{
                    Message initsystem = new Message(SYSTEM_ID, SYSTEM_ID, COMMANDHASH_INITSYSTEM, new ArrayList<>());
                    networking.send(initsystem);
                    initializing = InitState.PHASE_2;
                }
                break;
            }
            case PHASE_2:{
                if(message.getCommandHash().equals(COMMANDHASH_INITEND)){
                    initializing = InitState.SUCCESS;
                }else{
                    initializing = InitState.ERROR;
                }
                break;
            }
            case SUCCESS:{
                if(systemMessageHandler != null){
                    systemMessageHandler.accept(message);
                }
                break;
            }
            default:{
                throw new RuntimeException("AppLoader is in an invalid state");
            }
        }
    }
    public void dispose(){
        networking.closeConnection();
    }
}
