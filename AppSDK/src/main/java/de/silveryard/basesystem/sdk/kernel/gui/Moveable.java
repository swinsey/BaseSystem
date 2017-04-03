package de.silveryard.basesystem.sdk.kernel.gui;

import de.silveryard.transport.highlevelprotocols.qa.QAMessage;
import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 20.02.2017.
 */
public abstract class Moveable {
    /**
     * Returns the position of a given moveable render object
     * @param renderObjectID ID of the render object
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outPositionX X Position Value
     * @param outPositionY Y Position Value
     */
    public static void systemCallMoveableGetPosition(
            int renderObjectID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outPositionX, Wrapper<Integer> outPositionY
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.moveable.getposition", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outPositionX.value = message.getParameters().get(2).getInt();
        outPositionY.value = message.getParameters().get(3).getInt();
    }
    /**
     * Returns the x position of a given moveable render object
     * @param renderObjectID ID of the render object
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outPositionX X Position Value
     */
    public static void systemCallMoveableGetPositionX(
            int renderObjectID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outPositionX
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.moveable.getpositionx", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outPositionX.value = message.getParameters().get(2).getInt();
    }
    /**
     * Returns the y position of a given moveable render object
     * @param renderObjectID ID of the render object
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outPositionY Y Position Value
     */
    public static void systemCallMoveableGetPositionY(
            int renderObjectID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outPositionY
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.moveable.getpositiony", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(message.getParameters().get(1).getInt());
        outPositionY.value = message.getParameters().get(2).getInt();
    }

    /**
     * Sets the position of a given moveable render object
     * @param renderObjectID ID of the render object
     * @param positionX X Position Value
     * @param positionY Y Position Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallMoveableSetPosition(
            int renderObjectID, int positionX, int positionY,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        params.add(Parameter.createInt(positionX));
        params.add(Parameter.createInt(positionY));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.moveable.setposition", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(message.getParameters().get(1).getInt());
    }
    /**
     * Sets the x position of a given moveable render object
     * @param renderObjectID ID of the render object
     * @param positionX X Position Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallMoveableSetPositionX(
            int renderObjectID, int positionX,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        params.add(Parameter.createInt(positionX));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.moveable.setpositionx", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(message.getParameters().get(1).getInt());
    }
    /**
     * Sets the y position of a given moveable render object
     * @param renderObjectID ID of the render object
     * @param positionY Y Position Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallMoveableSetPositionY(
            int renderObjectID, int positionY,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        params.add(Parameter.createInt(positionY));
        QAMessage message = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.moveable.setpositiony", params);

        outReturnCode.value = ReturnCode.getEnumValue(message.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(message.getParameters().get(1).getInt());
    }
}
