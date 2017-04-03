package de.silveryard.basesystem.app.kernel.gui;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.Frame;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.gui.RenderObject;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 13.03.2017.
 */
abstract class Screen {
    public static void enableKernel() {
        Kernel kernel = Kernel.getInstance();
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.screen.getscreenwidth", Screen::systemCallScreenGetScreenWidth);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.screen.getscreenheight", Screen::systemCallScreenGetScreenHeight);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.screen.addrenderobject", Screen::systemCallScreenAddRenderObject);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.screen.removerenderobject", Screen::systemCallScreenRemoveRenderObject);
    }

    private static QAMessage systemCallScreenGetScreenWidth(RunningApp app, QAMessage message){
        Wrapper<Integer> wrapper = new Wrapper<>();
        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                wrapper.value = graphicsManager.getWidth();
            }
        });

        Utils.waitForWrapper(wrapper);

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), GuiReturnCode.OK.getValue(), Parameter.createInt(wrapper.value));
    }
    private static QAMessage systemCallScreenGetScreenHeight(RunningApp app, QAMessage message){
        Wrapper<Integer> wrapper = new Wrapper<>();
        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                wrapper.value = graphicsManager.getHeight();
            }
        });

        Utils.waitForWrapper(wrapper);

        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), GuiReturnCode.OK.getValue(), Parameter.createInt(wrapper.value));
    }

    private static QAMessage systemCallScreenAddRenderObject(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>();
        returnCode.value = ReturnCode.OK;

        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>();
        guiReturnCode.value = GuiReturnCode.OK;

        final Wrapper<Boolean> operationDone = new Wrapper<>();
        final int renderObjectId = message.getParameters().get(0).getInt();
        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    operationDone.value = true;
                    return;
                }

                RenderObject renderObject = Utils.as(RenderObject.class, obj);
                if(renderObject == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_RENDEROBJECT;
                    operationDone.value = true;
                    return;
                }

                Frame frame = app.getFrame();
                frame.addObjectToRenderList(renderObject);
                operationDone.value = true;
            }
        });

        Utils.waitForWrapper(operationDone);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallScreenRemoveRenderObject(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>();
        returnCode.value = ReturnCode.OK;

        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>();
        guiReturnCode.value = GuiReturnCode.OK;

        final Wrapper<Boolean> operationDone = new Wrapper<>();
        final int renderObjectId = message.getParameters().get(0).getInt();
        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    operationDone.value = true;
                    return;
                }

                RenderObject renderObject = Utils.as(RenderObject.class, obj);
                if(renderObject == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_RENDEROBJECT;
                    operationDone.value = true;
                    return;
                }

                Frame frame = app.getFrame();
                frame.removeObjectFromRenderList(renderObject);
                operationDone.value = true;
            }
        });

        Utils.waitForWrapper(operationDone);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
}
