package de.silveryard.basesystem.app.kernel.gui;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 22.03.2017.
 */
abstract class RenderObject {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.renderobject.setlayer", RenderObject::systemCallRenderObjectSetLayer);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.renderobject.getlayer", RenderObject::systemCallRenderObjectGetLayer);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.renderobject.setdirty", RenderObject::systemCallRenderObjectSetDirty);
    }

    private static QAMessage systemCallRenderObjectSetLayer(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int renderObjectId = message.getParameters().get(0).getInt();
        final int layer = message.getParameters().get(1).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                de.silveryard.basesystem.gui.RenderObject renderObj = Utils.as(de.silveryard.basesystem.gui.RenderObject.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(renderObj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_RENDEROBJECT;
                    result.value = -1;
                    return;
                }

                renderObj.setLayer(layer);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(),guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallRenderObjectGetLayer(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int renderObjectId = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                de.silveryard.basesystem.gui.RenderObject renderObj = Utils.as(de.silveryard.basesystem.gui.RenderObject.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(renderObj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_RENDEROBJECT;
                    result.value = -1;
                    return;
                }

                result.value = renderObj.getLayer();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(),guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallRenderObjectSetDirty(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int renderObjectId = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                de.silveryard.basesystem.gui.RenderObject renderObj = Utils.as(de.silveryard.basesystem.gui.RenderObject.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(renderObj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_RENDEROBJECT;
                    result.value = -1;
                    return;
                }

                renderObj.setDirty();
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(),guiReturnCode.value.getValue());
    }
}
