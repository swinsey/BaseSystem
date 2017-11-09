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
public abstract class RunningApp implements IDisposable {
    private static int nextIdentifier = 1;

    protected final String appIdentifier;
    private final int processId;

    private int nextObjectId;
    private final Map<Integer, Object> objects;
    private final Map<Integer, IDisposable> disposables;

    /**
     * Constructor
     * @param appIdentifier Application identifier
     */
    public RunningApp(String appIdentifier){
        this.appIdentifier = appIdentifier;

        this.processId = nextIdentifier;
        nextIdentifier++;

        nextObjectId = 1;
        objects = new HashMap<>();
        disposables = new HashMap<>();
    }

    /**
     * Returns an identifier that uniquelly identifies this process
     * @return Process ID
     */
    public final int getProcessId(){
        return processId;
    }

    /**
     * @return Returns the application identifier of this RunningApp
     */
    public final String getAppIdentifier(){
        return appIdentifier;
    }
    /**
     * @return Returns the gui frame of this RunningApp
     */
    public abstract Frame getFrame();

    /**
     * Sends a message to the app
     * @param message Message to send
     */
    public abstract void sendMessage(Message message);
    public abstract QAMessage sendQAMessage(QAMessage message);

    /**
     * Registers a new object to the apps internal cache
     * @param obj Object to register
     * @return Identifier that uniquely identifies the registered object
     */
    public final int registerObject(Object obj){
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
    public final Object getRegisteredObject(int objectId){
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
    public final void unregisterObject(int objectId){
        objects.remove(objectId);
        IDisposable disposable = disposables.get(objectId);

        if(disposable != null){
            disposable.dispose();
        }
    }
    /**
     * Unregisters all objects fromt the apps internal cache
     */
    public final void unregisterAllObjects(){
        int numObjects = objects.size();
        int numDisposables = disposables.size();

        List<Integer> ids = new ArrayList<>(objects.size());
        for(Integer key : objects.keySet()){
            ids.add(key);
        }

        for(int i = 0; i < ids.size(); i++){
            unregisterObject(ids.get(i));
        }

        System.out.println(appIdentifier + ": Unregistered " + numObjects + " Objects. " + numDisposables + " Disposables");
    }
    /**
     * Returns the reference identifier from a given registered object
     * @param obj A already registered object
     * @return Reference identifier on success. -1 otherwise
     */
    public final Integer getId(Object obj){
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
    public abstract boolean isAppRunning();

    @Override
    public final void dispose() {
        disposeApp();
        unregisterAllObjects();
    }
    protected abstract void disposeApp();
}
