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
public abstract class Sizeable {
    /**
     * Returns the width of a sizeable render object
     * @param renderObjectID ID of the render object
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outWidth Width Value
     */
    public static void systemCallSizeableGetWidth(
            int renderObjectID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outWidth
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.sizeable.getwidth", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outWidth.value = response.getParameters().get(2).getInt();
    }
    /**
     * Returns the height of a sizeable render object
     * @param renderObjectID ID of the render object
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outHeight Height Value
     */
    public static void systemCallSizeableGetHeight(
            int renderObjectID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outHeight
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.sizeable.getheight", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outHeight.value = response.getParameters().get(2).getInt();
    }

    /**
     * Sets the size of a sizeable render object
     * @param renderObjectID ID of the render object
     * @param width Width Value
     * @param height Height Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSizeableSetSize(
            int renderObjectID, int width, int height,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        params.add(Parameter.createInt(width));
        params.add(Parameter.createInt(height));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.sizeable.setsize", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the width of a sizeable render object
     * @param renderObjectID ID of the render object
     * @param width Width Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSizeableSetWidth(
            int renderObjectID, int width,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        params.add(Parameter.createInt(width));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.sizeable.setwidth", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the height of a sizeable render object
     * @param renderObjectID ID of the render object
     * @param height Height Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallSizeableSetHeight(
            int renderObjectID, int height,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectID));
        params.add(Parameter.createInt(height));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.sizeable.setheight", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
