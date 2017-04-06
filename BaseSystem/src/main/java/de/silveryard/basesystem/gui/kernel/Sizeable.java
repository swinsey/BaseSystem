package de.silveryard.basesystem.gui.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.gui.ISizeable;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 20.03.2017.
 */
abstract class Sizeable {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.kernel.sizeable.getwidth", Sizeable::systemCallSizeableGetWidth);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.kernel.sizeable.getheight", Sizeable::systemCallSizeableGetHeight);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.kernel.sizeable.getsize", Sizeable::systemCallSizeableSetSize);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.kernel.sizeable.setwidth", Sizeable::systemCallSizeableSetWidth);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.kernel.sizeable.setheight", Sizeable::systemCallSizeableSetHeight);
    }

    private static QAMessage systemCallSizeableGetWidth(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int renderObjectId = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                ISizeable sizeable = Utils.as(ISizeable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(sizeable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIZEABLE;
                    result.value = -1;
                    return;
                }

                result.value = sizeable.getWidth();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallSizeableGetHeight(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int renderObjectId = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                ISizeable sizeable = Utils.as(ISizeable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(sizeable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIZEABLE;
                    result.value = -1;
                    return;
                }

                result.value = sizeable.getHeight();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallSizeableSetSize(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int renderObjectId = message.getParameters().get(0).getInt();
        final int width = message.getParameters().get(1).getInt();
        final int height = message.getParameters().get(2).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                ISizeable sizeable = Utils.as(ISizeable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(sizeable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIZEABLE;
                    result.value = -1;
                    return;
                }

                sizeable.setSize(width, height);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSizeableSetWidth(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int renderObjectId = message.getParameters().get(0).getInt();
        final int width = message.getParameters().get(1).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                ISizeable sizeable = Utils.as(ISizeable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(sizeable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIZEABLE;
                    result.value = -1;
                    return;
                }

                sizeable.setWidth(width);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSizeableSetHeight(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int renderObjectId = message.getParameters().get(0).getInt();
        final int height = message.getParameters().get(1).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectId);
                ISizeable sizeable = Utils.as(ISizeable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(sizeable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIZEABLE;
                    result.value = -1;
                    return;
                }

                sizeable.setHeight(height);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
}
