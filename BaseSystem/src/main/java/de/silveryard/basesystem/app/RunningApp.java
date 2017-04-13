package de.silveryard.basesystem.app;

import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.gui.Frame;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.util.IDisposable;
import de.silveryard.transport.Message;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebif on 12.03.2017.
 */
public class RunningApp implements IDisposable {
    private static final int FRAME_LAYER = 5;

    private String appIdentifier;
    private AppLoader appLoader;
    private Frame frame;

    private int nextObjectId;
    private Map<Integer, Object> objects;

    /**
     * Constructor
     * @param appIdentifier Application identifier
     * @param appLoader AppLoader that loaded the application
     */
    public RunningApp(String appIdentifier, AppLoader appLoader){
        this.appIdentifier = appIdentifier;
        this.appLoader = appLoader;

        this.frame = GraphicsManager.getInstance().createFrame();
        this.frame.setLayer(FRAME_LAYER);

        nextObjectId = 1;
        objects = new HashMap<>();

        appLoader.setSystemMessageHandler(this::handleMessage);
    }

    /**
     * @return Returns the application identifier of this RunningApp
     */
    public String getAppIdentifier(){
        return appIdentifier;
    }
    /**
     * @return Returns the gui frame of this RunningApp
     */
    public Frame getFrame(){
        return frame;
    }

    /**
     * Sends a message to the app
     * @param message Message to send
     */
    public void sendMessage(Message message){
        appLoader.sendMessage(message);
    }
    private void handleMessage(Message message){
        QAMessage qaMessage = new QAMessage(message);
        QAMessage qaResponse = Kernel.getInstance().handleSystemCall(this, qaMessage);
        appLoader.sendMessage(qaResponse.getMessage());
    }

    /**
     * Registers a new object to the apps internal cache
     * @param obj Object to register
     * @return Identifier that uniquely identifies the registered object
     */
    public int registerObject(Object obj){
        int i = nextObjectId;
        nextObjectId++;
        objects.put(i, obj);
        return i;
    }
    /**
     * Returns an object from the apps internal cache based on an identifier
     * @param objectId Identifier that references a registered object
     * @return An object instance on success. Null otherwise
     */
    public Object getRegisteredObject(int objectId){
        if(objects.containsKey(objectId)){
            return objects.get(objectId);
        }else{
            return null;
        }
    }
    /**
     * Unregisters an object from the apps internal cache based on an identifier
     * @param objectId Identifier that references a registered object
     */
    public void unregisterObject(int objectId){
        objects.remove(objectId);
    }
    /**
     * Returns the reference identifier from a given registered object
     * @param obj A already registered object
     * @return Reference identifier on success. -1 otherwise
     */
    public Integer getId(Object obj){
        for(Integer key : objects.keySet()){
            if(objects.get(key) == obj){
                return key;
            }
        }
        return -1;
    }

    @Override
    public void dispose() {
        frame.dispose();
        appLoader.dispose();
    }
}
