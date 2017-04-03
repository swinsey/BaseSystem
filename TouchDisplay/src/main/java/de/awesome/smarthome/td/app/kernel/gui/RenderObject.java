package de.awesome.smarthome.td.app.kernel.gui;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.app.RunningApp;
import de.awesome.smarthome.td.app.kernel.Kernel;
import de.awesome.smarthome.td.app.kernel.ReturnCode;
import de.awesome.smarthome.td.gui.GraphicsManager;
import de.awesome.smarthome.td.util.Action;
import de.awesome.smarthome.td.util.Utils;
import de.awesome.smarthome.td.util.Wrapper;

/**
 * Created by Sebif on 22.03.2017.
 */
abstract class RenderObject {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.renderobject.setlayer", RenderObject::systemCallRenderObjectSetLayer);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.renderobject.getlayer", RenderObject::systemCallRenderObjectGetLayer);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.renderobject.setdirty", RenderObject::systemCallRenderObjectSetDirty);
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
                de.awesome.smarthome.td.gui.RenderObject renderObj = Utils.as(de.awesome.smarthome.td.gui.RenderObject.class, obj);

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
                de.awesome.smarthome.td.gui.RenderObject renderObj = Utils.as(de.awesome.smarthome.td.gui.RenderObject.class, obj);

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
                de.awesome.smarthome.td.gui.RenderObject renderObj = Utils.as(de.awesome.smarthome.td.gui.RenderObject.class, obj);

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
