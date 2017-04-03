package de.awesome.smarthome.system.kernel.gui;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.system.kernel.Kernel;
import de.awesome.smarthome.system.kernel.ReturnCode;
import de.awesome.smarthome.system.kernel.Wrapper;
import de.awesome.smarthome.transport.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 20.02.2017.
 */
public abstract class SimpleRect {
    /**
     * Creates a new simple rect. Simple rects cannot have alpha value. They will always be fully visible
     * @param positionX X Position Value
     * @param positionY Y Position Value
     * @param width Width Value
     * @param height Height Value
     * @param filled True if the rect is filled
     * @param color RGB Color Value. Alpha will be discarded
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outSimpleRectID ID of the created simple rect
     */
    public static void systemCallSimpleRectCreate(
            int positionX, int positionY,
            int width, int height,
            boolean filled,
            Color color,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outSimpleRectID
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(positionX));
        params.add(Parameter.createInt(positionY));
        params.add(Parameter.createInt(width));
        params.add(Parameter.createInt(height));
        params.add(Parameter.createBoolean(filled));
        params.add(Parameter.createInt(color.getR()));
        params.add(Parameter.createInt(color.getG()));
        params.add(Parameter.createInt(color.getB()));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.create", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outSimpleRectID.value = response.getParameters().get(2).getInt();
    }

    /**
     * Returns the filled flag for a given simple rect
     * @param simpleRectID ID of the simple rect
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outFilled Filled value
     */
    public static void systemCallSimpleRectGetFilled(
            int simpleRectID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Boolean> outFilled
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleRectID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.getfilled", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFilled.value = response.getParameters().get(2).getBoolean();
    }
    /**
     * Sets the filled flag of a given simple rect
     * @param simpleRectID ID of the simple rect
     * @param filled Filled Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleRectSetFilled(
            int simpleRectID, boolean filled,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleRectID));
        params.add(Parameter.createBoolean(filled));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setfilled", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Returns the red value of a given simple rect
     * @param simpleRectID ID of the simple rect
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColorR Red Value
     */
    public static void systemCallSimpleRectGetColorR(
            int simpleRectID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Byte> outColorR
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleRectID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.getcolorr", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outColorR.value = response.getParameters().get(2).getInt().byteValue();
    }
    /**
     * Returns the green value of a given simple rect
     * @param simpleRectID ID of the simple rect
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColorG Green Value
     */
    public static void systemCallSimpleRectGetColorG(
            int simpleRectID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Byte> outColorG
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleRectID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.getcolorg", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outColorG.value = response.getParameters().get(2).getInt().byteValue();
    }
    /**
     * Returns the blue value of a given simple rect
     * @param simpleRectID ID of the simple rect
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColorB Blue Value
     */
    public static void systemCallSimpleRectGetColorB(
            int simpleRectID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Byte> outColorB
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleRectID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.getcolorb", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outColorB.value = response.getParameters().get(2).getInt().byteValue();
    }
    /**
     * Sets the RGB color of a given simple rect
     * @param simpleRectID ID of the simple rect
     * @param color RGB Color Value. Alpha will be discarded
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleRectSetColor(
            int simpleRectID, Color color,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleRectID));
        params.add(Parameter.createInt(color.getR()));
        params.add(Parameter.createInt(color.getG()));
        params.add(Parameter.createInt(color.getB()));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setcolor", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the red value of a given simple rect
     * @param simpleRectID ID of the simple rect
     * @param colorR Red value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleRectSetColorR(
            int simpleRectID, byte colorR,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleRectID));
        params.add(Parameter.createInt(colorR));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setcolorr", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the green value of a given simple rect
     * @param simpleRectID ID of the simple rect
     * @param colorG Green value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleRectSetColorG(
            int simpleRectID, byte colorG,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleRectID));
        params.add(Parameter.createInt(colorG));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setcolorg", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the blue value of a given simple rect
     * @param simpleRectID ID of the simple rect
     * @param colorB Blue value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSimpleRectSetColorB(
            int simpleRectID, byte colorB,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(simpleRectID));
        params.add(Parameter.createInt(colorB));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setcolorb", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
