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
public abstract class Font {
    /**
     * Loads a font provided by the system
     * @param font Font name
     * @param size Font size
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outFontId ID of the loaded font
     */
    public static void systemCallFontLoadSystemFont(
            String font, int size,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outFontId) {

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createString(font));
        params.add(Parameter.createInt(size));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.font.loadsystemfont", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFontId.value = response.getParameters().get(2).getInt();
    }

    /**
     * Returns the size of a loaded font
     * @param fontId ID of the font
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outFontSize Font Size
     */
    public static void systemCallFontGetSize(
            int fontId,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outFontSize){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(fontId));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.font.getsize", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFontSize.value = response.getParameters().get(2).getInt();
    }
}
