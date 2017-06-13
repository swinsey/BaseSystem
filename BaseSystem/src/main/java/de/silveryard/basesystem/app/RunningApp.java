package de.silveryard.basesystem.app;

import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.gui.Frame;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.logging.LogMessageType;
import de.silveryard.basesystem.sound.FmodChannelGroup;
import de.silveryard.basesystem.sound.FmodResult;
import de.silveryard.basesystem.sound.FmodSpeakerMode;
import de.silveryard.basesystem.sound.FmodSystem;
import de.silveryard.basesystem.util.IDisposable;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.transport.Message;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebif on 12.03.2017.
 */
public class RunningApp implements IDisposable {
    private static final int FRAME_LAYER = 5;

    private static int nextIdentifier = 1;

    private final String appIdentifier;
    private final AppLoader appLoader;
    private final Frame frame;
    private final int processId;
    private final FmodChannelGroup channelGroup;

    private int nextObjectId;
    private final Map<Integer, Object> objects;
    private final Map<Integer, IDisposable> disposables;
    private final Map<String, QAMessage> pendingMessages;

    /**
     * Constructor
     * @param appIdentifier Application identifier
     * @param appLoader AppLoader that loaded the application
     */
    public RunningApp(String appIdentifier, AppLoader appLoader){
        this.appIdentifier = appIdentifier;
        this.appLoader = appLoader;

        if(GraphicsManager.getInstance() != null) {
            this.frame = GraphicsManager.getInstance().createFrame();
            this.frame.setLayer(FRAME_LAYER);
        }else{
            this.frame = null;
        }
        if(FmodSystem.getInstance() != null){
            channelGroup = new FmodChannelGroup();
            FmodResult result = FmodSystem.getInstance().createChannelGroup(appIdentifier, channelGroup);
            if(result != FmodResult.FMOD_OK){
                throw new RuntimeException("Failed to create channel group for app " + appIdentifier + ": " + result);
            }

            FmodChannelGroup masterGroup = new FmodChannelGroup();
            result = FmodSystem.getInstance().getMasterChannelGroup(masterGroup);
            if(result != FmodResult.FMOD_OK){
                throw new RuntimeException("Failed to get master channel group while creating app " + appIdentifier + ": " + result);
            }

            result = masterGroup.addGroup(channelGroup);
            if(result != FmodResult.FMOD_OK){
                throw new RuntimeException("Failed to assing new channel group to master group while creating app " + appIdentifier + ": " + result);
            }
        }else{
            channelGroup = null;
        }

        this.processId = nextIdentifier;
        nextIdentifier++;

        nextObjectId = 1;
        objects = new HashMap<>();
        disposables = new HashMap<>();
        pendingMessages = new HashMap<>();

        appLoader.setSystemMessageHandler(this::handleMessage);
    }

    /**
     * Returns an identifier that uniquelly identifies this process
     * @return Process ID
     */
    public int getProcessId(){
        return processId;
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
    public QAMessage sendQAMessage(QAMessage message){
        pendingMessages.put(message.getUUID(), null);
        appLoader.sendMessage(message.getMessage());
        QAMessage response = null;

        while((response = pendingMessages.get(message.getUUID())) == null){
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return response;
    }
    private void handleMessage(Message message){
        QAMessage qaMessage = new QAMessage(message);

        if(pendingMessages.containsKey(qaMessage.getUUID())){
            pendingMessages.put(qaMessage.getUUID(), qaMessage);
        }else {
            QAMessage qaResponse = Kernel.getInstance().handleSystemCall(this, qaMessage);
            appLoader.sendMessage(qaResponse.getMessage());
        }
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

        if(Utils.as(IDisposable.class, obj) != null){
            disposables.put(i, (IDisposable)obj);
        }
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
        IDisposable disposable = disposables.get(objectId);

        if(disposable != null){
            disposable.dispose();
        }
    }
    /**
     * Unregisters all objects fromt the apps internal cache
     */
    public void unregisterAllObjects(){
        int numObjects = objects.size();
        int numDisposables = disposables.size();

        List<Integer> ids = new ArrayList<>(objects.size());
        for(Integer key : objects.keySet()){
            ids.add(key);
        }

        for(int i = 0; i < ids.size(); i++){
            unregisterObject(ids.get(i));
        }

        appLoader.logAsApp("Unregistered " + numObjects + " Objects. " + numDisposables + " Disposables", LogMessageType.OUT);
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

    /**
     * Returns if this application is still running
     * @return
     */
    public boolean isAppRunning(){
        return appLoader.isRunning();
    }

    @Override
    public void dispose() {
        frame.dispose();
        appLoader.dispose();
        unregisterAllObjects();
    }
}
