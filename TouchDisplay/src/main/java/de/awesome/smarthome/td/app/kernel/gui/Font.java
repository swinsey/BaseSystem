package de.awesome.smarthome.td.app.kernel.gui;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.app.RunningApp;
import de.awesome.smarthome.td.app.kernel.Kernel;
import de.awesome.smarthome.td.app.kernel.ReturnCode;
import de.awesome.smarthome.td.gui.GraphicsManager;
import de.awesome.smarthome.td.util.Action;
import de.awesome.smarthome.td.util.Utils;
import de.awesome.smarthome.td.util.Wrapper;
import de.awesome.smarthome.transport.Parameter;

/**
 * Created by Sebif on 19.03.2017.
 */
abstract class Font {
    public static void enableKernel(){
        Kernel kernel = Kernel.getInstance();
        kernel.registerSystemCall("de.awesome.smarthome.td.systemcall.gui.font.loadsystemfont", Font::systemCallFontLoadSystemFont);
        kernel.registerSystemCall("de.awesome.smarthome.td.systemcall.gui.font.getsize", Font::systemCallFontGetSize);
    }

    private static QAMessage systemCallFontLoadSystemFont(RunningApp app, QAMessage message){
        /*final Wrapper<ReturnCode> returnCode = new Wrapper<>();
        returnCode.value = ReturnCode.OK;

        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>();
        guiReturnCode.value = GuiReturnCode.OK;

        final Wrapper<Integer> result = new Wrapper<>();

        final String fontName = message.getParameters().get(0).getString();
        final int size = message.getParameters().get(1).getInt();

        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                de.awesome.smarthome.td.gui.Font font = SystemResources.getInstance().getFontCollection().get(fontName, size);
                if(font == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.UNKNOWN_ERROR;
                    result.value = -1;
                    return;
                }

                result.value = app.registerObject(font);
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
    */
        return Kernel.getInstance().createResponse(message, ReturnCode.NOT_IMPLEMENTED.getValue(), GuiReturnCode.UNKNOWN_ERROR.getValue(), Parameter.createInt(-1));
    }
    private static QAMessage systemCallFontGetSize(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>();
        returnCode.value = ReturnCode.OK;

        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>();
        guiReturnCode.value = GuiReturnCode.OK;

        final Wrapper<Integer> result = new Wrapper<>();

        final int fontId = message.getParameters().get(0).getInt();

        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(fontId);
                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                de.awesome.smarthome.td.gui.Font font = Utils.as(de.awesome.smarthome.td.gui.Font.class, obj);
                if(font == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_FONT;
                    result.value = -1;
                    return;
                }

                result.value = font.getSize();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
    }
}
