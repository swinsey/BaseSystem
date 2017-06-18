package de.silveryard.basesystem.app;

import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.gui.Frame;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.logging.LogMessageType;
import de.silveryard.basesystem.sound.FmodChannelGroup;
import de.silveryard.basesystem.sound.FmodResult;
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
 * Created by Sebif on 18.06.2017.
 */
class RunningAppImpl extends RunningApp {
    private static final int FRAME_LAYER = 5;

    private final AppLoader appLoader;
    private final Frame frame;
    private final FmodChannelGroup channelGroup;

    private final Map<String, QAMessage> pendingMessages;

    /**
     * Constructor
     * @param appIdentifier Application identifier
     * @param appLoader AppLoader that loaded the application
     */
    public RunningAppImpl(String appIdentifier, AppLoader appLoader){
        super(appIdentifier);

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

        pendingMessages = new HashMap<>();

        appLoader.setSystemMessageHandler(this::handleMessage);
    }

    @Override
    public Frame getFrame(){
        return frame;
    }

    @Override
    public void sendMessage(Message message){
        appLoader.sendMessage(message);
    }
    @Override
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
     * Returns if this application is still running
     * @return
     */
    @Override
    public boolean isAppRunning(){
        return appLoader.isRunning();
    }

    @Override
    public void disposeApp() {
        frame.dispose();
        appLoader.dispose();
    }
}
