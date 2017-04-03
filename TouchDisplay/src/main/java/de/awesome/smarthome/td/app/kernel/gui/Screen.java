package de.awesome.smarthome.td.app.kernel.gui;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.app.RunningApp;
import de.awesome.smarthome.td.app.kernel.Kernel;
import de.awesome.smarthome.td.app.kernel.ReturnCode;
import de.awesome.smarthome.td.gui.Frame;
import de.awesome.smarthome.td.gui.GraphicsManager;
import de.awesome.smarthome.td.gui.RenderObject;
import de.awesome.smarthome.td.util.Action;
import de.awesome.smarthome.td.util.Utils;
import de.awesome.smarthome.td.util.Wrapper;
import de.awesome.smarthome.transport.Parameter;

/**
 * Created by Sebif on 13.03.2017.
 */
abstract class Screen {
    public static void enableKernel() {
        Kernel kernel = Kernel.getInstance();
        kernel.registerSystemCall("de.awesome.smarthome.td.systemcall.gui.screen.getscreenwidth", Screen::systemCallScreenGetScreenWidth);
        kernel.registerSystemCall("de.awesome.smarthome.td.systemcall.gui.screen.getscreenheight", Screen::systemCallScreenGetScreenHeight);
        kernel.registerSystemCall("de.awesome.smarthome.td.systemcall.gui.screen.addrenderobject", Screen::systemCallScreenAddRenderObject);
        kernel.registerSystemCall("de.awesome.smarthome.td.systemcall.gui.screen.removerenderobject", Screen::systemCallScreenRemoveRenderObject);
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
