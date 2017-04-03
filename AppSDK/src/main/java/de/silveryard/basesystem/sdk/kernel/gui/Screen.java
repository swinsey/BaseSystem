package de.silveryard.basesystem.sdk.kernel.gui;

import de.silveryard.transport.highlevelprotocols.qa.QAMessage;
import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 13.03.2017.
 */
public abstract class Screen {
    /**
     * Returns the screen width
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outWidth Screen Width
     */
    public static void systemCallScreenGetWidth(
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode, Wrapper<Integer> outWidth){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.screen.getscreenwidth", new ArrayList<>());

        int returnCodeInt = response.getParameters().get(0).getInt();
        int guiReturnCodeInt = response.getParameters().get(1).getInt();
        int width = response.getParameters().get(2).getInt();

        outReturnCode.value = ReturnCode.getEnumValue(returnCodeInt);
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(guiReturnCodeInt);
        outWidth.value = width;
    }
    /**
     * Returns the screen height
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outHeight Screen Height
     */
    public static void systemCallScreenGetHeight(
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode, Wrapper<Integer> outHeight){
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.screen.getscreenheight", new ArrayList<>());

        int returnCodeInt = response.getParameters().get(0).getInt();
        int guiReturnCodeInt = response.getParameters().get(1).getInt();
        int height = response.getParameters().get(2).getInt();

        outReturnCode.value = ReturnCode.getEnumValue(returnCodeInt);
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(guiReturnCodeInt);
        outHeight.value = height;
    }

    /**
     * Adds a given render object to the render list. Only added render objects will be actually rendered
     * @param renderObjectId ID of the render object
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallScreenAddRenderObject(
            int renderObjectId,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.screen.addrenderobject", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Removes a given render object from the render list. It will no longer be rendered
     * @param renderObjectId ID of the render object
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallScreenRemoveRenderObject(
            int renderObjectId,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectId));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.screen.removerenderobject", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
