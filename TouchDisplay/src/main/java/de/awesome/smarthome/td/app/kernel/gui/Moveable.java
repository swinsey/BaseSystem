package de.awesome.smarthome.td.app.kernel.gui;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.app.RunningApp;
import de.awesome.smarthome.td.app.kernel.Kernel;
import de.awesome.smarthome.td.app.kernel.ReturnCode;
import de.awesome.smarthome.td.gui.GraphicsManager;
import de.awesome.smarthome.td.gui.IMoveable;
import de.awesome.smarthome.td.util.Action;
import de.awesome.smarthome.td.util.Utils;
import de.awesome.smarthome.td.util.Wrapper;
import de.awesome.smarthome.transport.Parameter;

/**
 * Created by Sebif on 19.03.2017.
 */
abstract class Moveable {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.moveable.getposition", Moveable::systemCallMoveableGetPosition);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.moveable.getpositionx", Moveable::systemCallMoveableGetPositionX);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.moveable.getpositiony", Moveable::systemCallMoveableGetPositionY);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.moveable.setposition", Moveable::systemCallMoveableSetPosition);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.moveable.setpositionx", Moveable::systemCallMoveableSetPositionX);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.moveable.setpositiony", Moveable::systemCallMoveableSetPositionY);
    }

    private static QAMessage systemCallMoveableGetPosition(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> positionX = new Wrapper<>(0);
        final Wrapper<Integer> positionY = new Wrapper<>(0);
        final Wrapper<Boolean> operationDone = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectID);
                IMoveable moveable = Utils.as(IMoveable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    operationDone.value = true;
                    return;
                }

                if(moveable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
                    operationDone.value = true;
                    return;
                }

                positionX.value = moveable.getPositionX();
                positionY.value = moveable.getPositionY();
                operationDone.value = true;
            }
        });

        Utils.waitForWrapper(operationDone);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(positionX.value), Parameter.createInt(positionY.value));
    }
    private static QAMessage systemCallMoveableGetPositionX(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectID);
                IMoveable moveable = Utils.as(IMoveable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(moveable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
                    result.value = -1;
                    return;
                }

                result.value = moveable.getPositionX();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallMoveableGetPositionY(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectID);
                IMoveable moveable = Utils.as(IMoveable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(moveable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
                    result.value = -1;
                    return;
                }

                result.value = moveable.getPositionY();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallMoveableSetPosition(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Boolean> operationDone = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();
        final int positionX = message.getParameters().get(1).getInt();
        final int positionY = message.getParameters().get(2).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectID);
                IMoveable moveable = Utils.as(IMoveable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    operationDone.value = true;
                    return;
                }

                if(moveable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
                    operationDone.value = true;
                    return;
                }

                moveable.setPosition(positionX, positionY);
                operationDone.value = true;
            }
        });

        Utils.waitForWrapper(operationDone);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallMoveableSetPositionX(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Boolean> operationDone = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();
        final int positionX = message.getParameters().get(1).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectID);
                IMoveable moveable = Utils.as(IMoveable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    operationDone.value = true;
                    return;
                }

                if(moveable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
                    operationDone.value = true;
                    return;
                }

                moveable.setPositionX(positionX);
                operationDone.value = true;
            }
        });

        Utils.waitForWrapper(operationDone);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallMoveableSetPositionY(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Boolean> operationDone = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();
        final int positionY = message.getParameters().get(1).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(renderObjectID);
                IMoveable moveable = Utils.as(IMoveable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    operationDone.value = true;
                    return;
                }

                if(moveable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
                    operationDone.value = true;
                    return;
                }

                moveable.setPositionY(positionY);
                operationDone.value = true;
            }
        });

        Utils.waitForWrapper(operationDone);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
}
