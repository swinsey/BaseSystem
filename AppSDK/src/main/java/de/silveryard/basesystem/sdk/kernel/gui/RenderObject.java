package de.silveryard.basesystem.sdk.kernel.gui;

import de.silveryard.transport.highlevelprotocols.qa.QAMessage;
import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 22.03.2017.
 */
public abstract class RenderObject {
    /**
     * Sets the render layer of a given render object.
     * Render objects with a higher layer will be rendered on top of the
     * render objects with lower layers
     * @param renderObjectId ID of the render object
     * @param layer Layer Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallRenderObjectSetLayer(
            int renderObjectId, int layer,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectId));
        params.add(Parameter.createInt(layer));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.renderobject.setlayer", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Returns the render layer of a given render object
     * @param renderObjectId ID of the render object
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outLayer Layer Value
     */
    public static void systemCallRenderObjectGetLayer(
            int renderObjectId,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outLayer
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectId));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.renderobject.getlayer", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outLayer.value = response.getParameters().get(2).getInt();
    }

    /**
     * Sets the dirty flag of a given render object.
     * When the dirty flag is set, the render system will rerender the image.
     * This flag will also automatically be set, if other values of a render object are changed.
     * So setting this manually is not needed most of the time
     * @param renderObjectId ID of the render object
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCalRenderObjectSetDirty(
            int renderObjectId,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(renderObjectId));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.renderobject.setdirty", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
}
