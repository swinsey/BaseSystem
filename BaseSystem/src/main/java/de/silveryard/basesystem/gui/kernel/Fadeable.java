package de.silveryard.basesystem.gui.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.gui.IFadeable;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 20.03.2017.
 */
abstract class Fadeable {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.fadeable.get", Fadeable::systemCallFadeableGet);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.fadeable.set", Fadeable::systemCallFadeableSet);
    }

    private static QAMessage systemCallFadeableGet(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int fadeableId = message.getParameters().get(0).getInt();

        Object obj = app.getRegisteredObject(fadeableId);
        IFadeable fadeable = Utils.as(IFadeable.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(result.value));
        }

        if(fadeable == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_FADEABLE;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(result.value));
        }

        result.value = fadeable.getAlpha();

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallFadeableSet(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);

        final int fadeableId = message.getParameters().get(0).getInt();
        final byte alpha = message.getParameters().get(1).getInt().byteValue();

        final Object obj = app.getRegisteredObject(fadeableId);
        final IFadeable fadeable = Utils.as(IFadeable.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        if(fadeable == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_FADEABLE;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                fadeable.setAlpha(alpha);
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
}
