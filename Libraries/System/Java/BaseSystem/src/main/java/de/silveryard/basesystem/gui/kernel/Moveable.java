package de.silveryard.basesystem.gui.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.gui.IMoveable;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 19.03.2017.
 */
abstract class Moveable {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.moveable.getposition", Moveable::systemCallMoveableGetPosition);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.moveable.getpositionx", Moveable::systemCallMoveableGetPositionX);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.moveable.getpositiony", Moveable::systemCallMoveableGetPositionY);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.moveable.setposition", Moveable::systemCallMoveableSetPosition);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.moveable.setpositionx", Moveable::systemCallMoveableSetPositionX);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.moveable.setpositiony", Moveable::systemCallMoveableSetPositionY);
    }

    private static QAMessage systemCallMoveableGetPosition(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> positionX = new Wrapper<>(0);
        final Wrapper<Integer> positionY = new Wrapper<>(0);
        final Wrapper<Boolean> operationDone = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();

        Object obj = app.getRegisteredObject(renderObjectID);
        IMoveable moveable = Utils.as(IMoveable.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            operationDone.value = true;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(positionX.value), Parameter.createInt(positionY.value));
        }

        if(moveable == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
            operationDone.value = true;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(positionX.value), Parameter.createInt(positionY.value));
        }

        positionX.value = moveable.getPositionX();
        positionY.value = moveable.getPositionY();
        operationDone.value = true;

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(positionX.value), Parameter.createInt(positionY.value));
    }
    private static QAMessage systemCallMoveableGetPositionX(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();

        Object obj = app.getRegisteredObject(renderObjectID);
        IMoveable moveable = Utils.as(IMoveable.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(result.value));
        }

        if(moveable == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(result.value));
        }

        result.value = moveable.getPositionX();
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallMoveableGetPositionY(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();

        Object obj = app.getRegisteredObject(renderObjectID);
        IMoveable moveable = Utils.as(IMoveable.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(result.value));
        }

        if(moveable == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(result.value));
        }

        result.value = moveable.getPositionY();
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

        final Object obj = app.getRegisteredObject(renderObjectID);
        final IMoveable moveable = Utils.as(IMoveable.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            operationDone.value = true;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }
        if(moveable == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
            operationDone.value = true;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }


        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                moveable.setPosition(positionX, positionY);
                operationDone.value = true;
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallMoveableSetPositionX(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Boolean> operationDone = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();
        final int positionX = message.getParameters().get(1).getInt();

        Object obj = app.getRegisteredObject(renderObjectID);
        IMoveable moveable = Utils.as(IMoveable.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            operationDone.value = true;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        if(moveable == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
            operationDone.value = true;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                moveable.setPositionX(positionX);
                operationDone.value = true;
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallMoveableSetPositionY(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Boolean> operationDone = new Wrapper<>();
        final int renderObjectID = message.getParameters().get(0).getInt();
        final int positionY = message.getParameters().get(1).getInt();

        Object obj = app.getRegisteredObject(renderObjectID);
        IMoveable moveable = Utils.as(IMoveable.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            operationDone.value = true;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        if(moveable == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_MOVEABLE;
            operationDone.value = true;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                moveable.setPositionY(positionY);
                operationDone.value = true;
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
}
