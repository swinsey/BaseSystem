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
public abstract class SimpleLine {
    /**
     * Creates a new simple line. Simple lines cannot have alpha value. They wil always be fully visible
     * @param positionX1 Start X Position. Can be requested and changed by moveable systemcalls
     * @param positionY1 Start Y Position. Can be requested and changed by moveable systemcalls
     * @param positionX2 End X Position. Can be requested and changed by endpoint systemcalls
     * @param positionY2 End Y Position. Can be requested and changed by enpoint systemcalls
     * @param colorR Red Value 0-255
     * @param colorG Green value 0-255
     * @param colorB Blue Value 0-255
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outSimpleLineID ID of the created simple line
     */
    public static void systemCallSimpleLineCreate(
            int positionX1, int positionY1,
            int positionX2, int positionY2,
            byte colorR, byte colorG, byte colorB,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outSimpleLineID
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(positionX1));
        params.add(Parameter.createInt(positionY1));
        params.add(Parameter.createInt(positionX2));
        params.add(Parameter.createInt(positionY2));
        params.add(Parameter.createInt(colorR));
        params.add(Parameter.createInt(colorG));
        params.add(Parameter.createInt(colorB));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.create", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outSimpleLineID.value = response.getParameters().get(2).getInt();
    }

    /**
     * Returns the end x position of a given simple line
     * @param simpleLineID ID of the simple line
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outEndpointX X Position Value
     */
    public static void systemCallSimpleLineGetEndpointX(
            int simpleLineID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outEndpointX
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.getendpointx", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outEndpointX.value = response.getParameters().get(2).getInt();
    }
    /**
     * Returns the end y position of a given simple line
     * @param simpleLineID ID of the simple line
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outEndpointY Y Position Value
     */
    public static void systemCallSimpleLineGetEndpointY(
            int simpleLineID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outEndpointY
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.getendpointy", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outEndpointY.value = response.getParameters().get(2).getInt();
    }

    /**
     * Sets the end position of a given simple line
     * @param simpleLineID ID of the simple line
     * @param positionX X Position Value
     * @param positionY Y Position Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleLineSetEndpoint(
            int simpleLineID, int positionX, int positionY,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        params.add(Parameter.createInt(positionX));
        params.add(Parameter.createInt(positionY));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.setendpoint", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the end x position of a given simple line
     * @param simpleLineID ID of the simple line
     * @param positionX Y Position Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleLineSetEndpointX(
            int simpleLineID, int positionX,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        params.add(Parameter.createInt(positionX));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.setendpointx", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the end y position of a given simple line
     * @param simpleLineID ID of the simple line
     * @param positionY Y Position Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleLineSetEndpointY(
            int simpleLineID, int positionY,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        params.add(Parameter.createInt(positionY));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.setendpointy", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Returns the red value of a simple line
     * @param simpleLineID ID of the simple line
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColorR Red value 0-255
     */
    public static void systemCallSimpleLineGetColorR(
            int simpleLineID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Byte> outColorR
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.getcolorr", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outColorR.value = response.getParameters().get(2).getInt().byteValue();
    }
    /**
     * Returns the green value of a simple line
     * @param simpleLineID ID of the simple line
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColorG Green value 0-255
     */
    public static void systemCallSimpleLineGetColorG(
            int simpleLineID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Byte> outColorG
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.getcolorg", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outColorG.value = response.getParameters().get(2).getInt().byteValue();
    }
    /**
     * Returns the blue value of a simple line
     * @param simpleLineID ID of the simple line
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColorB Blue value 0-255
     */
    public static void systemCallSimpleLineGetColorB(
            int simpleLineID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Byte> outColorB
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.getcolorb", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outColorB.value = response.getParameters().get(2).getInt().byteValue();
    }

    /**
     * Sets the color value of a given simple line
     * @param simpleLineID ID of the simple line
     * @param colorR Red value 0-255
     * @param colorG Green value 0-255
     * @param colorB Blue value 0-255
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleLineSetColor(
            int simpleLineID, byte colorR, byte colorG, byte colorB,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        params.add(Parameter.createInt(colorR));
        params.add(Parameter.createInt(colorG));
        params.add(Parameter.createInt(colorB));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.setcolor", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the blue value of a given simple line
     * @param simpleLineID ID of the simple line
     * @param colorR Red value 0-255
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleLineSetColorR(
            int simpleLineID, byte colorR,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        params.add(Parameter.createInt(colorR));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.setcolorr", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the blue value of a given simple line
     * @param simpleLineID ID of the simple line
     * @param colorG Green value 0-255
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleLineSetColorG(
            int simpleLineID, byte colorG,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        params.add(Parameter.createInt(colorG));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.setcolorg", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the blue value of a given simple line
     * @param simpleLineID ID of the simple line
     * @param colorB Blue value 0-255
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleLineSetColorB(
            int simpleLineID, byte colorB,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleLineID));
        params.add(Parameter.createInt(colorB));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simpleline.setcolorb", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
