package de.awesome.smarthome.td.app.kernel.gui;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.app.RunningApp;
import de.awesome.smarthome.td.app.kernel.Kernel;
import de.awesome.smarthome.td.app.kernel.ReturnCode;
import de.awesome.smarthome.td.gui.GraphicsManager;
import de.awesome.smarthome.td.gui.IFadeable;
import de.awesome.smarthome.td.util.Action;
import de.awesome.smarthome.td.util.Utils;
import de.awesome.smarthome.td.util.Wrapper;
import de.awesome.smarthome.transport.Parameter;

/**
 * Created by Sebif on 20.03.2017.
 */
abstract class Fadeable {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.fadeable.get", Fadeable::systemCallFadeableGet);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.fadeable.set", Fadeable::systemCallFadeableSet);
    }

    private static QAMessage systemCallFadeableGet(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int fadeableId = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(fadeableId);
                IFadeable fadeable = Utils.as(IFadeable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(fadeable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_FADEABLE;
                    result.value = -1;
                    return;
                }

                result.value = fadeable.getAlpha();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallFadeableSet(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int fadeableId = message.getParameters().get(0).getInt();
        final byte alpha = message.getParameters().get(1).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(fadeableId);
                IFadeable fadeable = Utils.as(IFadeable.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(fadeable == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_FADEABLE;
                    result.value = -1;
                    return;
                }

                fadeable.setAlpha(alpha);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
}
