package de.silveryard.basesystem.gui.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.Frame;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sebif on 13.03.2017.
 */
abstract class Texture {
    public static void enableKernel(){
        Kernel kernel = Kernel.getInstance();
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.kernel.texture.load", Texture::systemCallTextureLoad);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.kernel.texture.unload", Texture::systemCallTextureUnload);

        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.kernel.texture.getwidth", Texture::systemCallTextureGetWidth);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.kernel.texture.getheight", Texture::systemCallTextureGetHeight);
    }

    public static QAMessage systemCallTextureLoad(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> genericReturnCode = new Wrapper<>();
        genericReturnCode.value = ReturnCode.OK;

        final Wrapper<GuiReturnCode> specificReturnCode = new Wrapper<>();
        specificReturnCode.value = GuiReturnCode.OK;

        final Wrapper<Integer> result = new Wrapper<>();
        String path = message.getParameters().get(0).getString();
        GraphicsManager graphicsManager = GraphicsManager.getInstance();

        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                if(!Files.exists(Paths.get(path))) {
                    genericReturnCode.value = ReturnCode.ERROR;
                    specificReturnCode.value = GuiReturnCode.PATH_NOT_EXISTENT;
                    result.value = -1;
                    return;
                }

                if(Files.isDirectory(Paths.get(path))) {
                    genericReturnCode.value = ReturnCode.ERROR;
                    specificReturnCode.value = GuiReturnCode.PATH_NO_FILE;
                    result.value = -1;
                    return;
               }

                Frame frame = app.getFrame();
                de.silveryard.basesystem.gui.Texture texture = frame.createTexture(path);

                if(texture == null) {
                    genericReturnCode.value = ReturnCode.ERROR;
                    specificReturnCode.value = GuiReturnCode.UNKNOWN_ERROR;
                    result.value = -1;
                    return;
                }

                result.value = app.registerObject(texture);
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, genericReturnCode.value.getValue(), specificReturnCode.value.getValue(), Parameter.createInt(result.value));
    }
    public static QAMessage systemCallTextureUnload(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> genericReturnCode = new Wrapper<>();
        genericReturnCode.value = ReturnCode.OK;

        final Wrapper<GuiReturnCode> specificReturnCode = new Wrapper<>();
        specificReturnCode.value = GuiReturnCode.OK;

        final Wrapper<Boolean> operationDone = new Wrapper<>();

        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                if(message.getParameters().size() != 1) {
                    genericReturnCode.value = ReturnCode.INVALID_MESSAGE;
                    operationDone.value = true;
                    return;
                }

                Integer textureId = message.getParameters().get(0).getInt();
                if(textureId == null){
                    genericReturnCode.value = ReturnCode.INVALID_MESSAGE;
                    operationDone.value = true;
                    return;
                }

                Object obj = app.getRegisteredObject(textureId);
                if(obj == null){
                    genericReturnCode.value = ReturnCode.ERROR;
                    specificReturnCode.value = GuiReturnCode.INVALID_ID;
                    operationDone.value = true;
                    return;
                }

                de.silveryard.basesystem.gui.Texture texture = Utils.as(de.silveryard.basesystem.gui.Texture.class, obj);
                if(texture == null){
                    genericReturnCode.value = ReturnCode.ERROR;
                    specificReturnCode.value = GuiReturnCode.NOT_A_TEXTURE;
                    operationDone.value = true;
                    return;
                }

                app.unregisterObject(textureId);
                Frame frame = app.getFrame();
                frame.disposeObject(texture);
            }
        });

        Utils.waitForWrapper(operationDone);

        return Kernel.getInstance().createResponse(message, genericReturnCode.value.getValue(), specificReturnCode.value.getValue());
    }

    public static QAMessage systemCallTextureGetWidth(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> genericReturnCode = new Wrapper<>();
        genericReturnCode.value = ReturnCode.OK;

        final Wrapper<GuiReturnCode> specificReturnCode = new Wrapper<>();
        specificReturnCode.value = GuiReturnCode.OK;

        final Wrapper<Integer> result = new Wrapper<>();

        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                if(message.getParameters().size() != 1) {
                    genericReturnCode.value = ReturnCode.INVALID_MESSAGE;
                    result.value = -1;
                    return;
                }

                Integer textureId = message.getParameters().get(0).getInt();
                if(textureId == null){
                    genericReturnCode.value = ReturnCode.INVALID_MESSAGE;
                    result.value = -1;
                    return;
                }

                Object obj = app.getRegisteredObject(textureId);
                if(obj == null){
                    genericReturnCode.value = ReturnCode.ERROR;
                    specificReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                de.silveryard.basesystem.gui.Texture texture = Utils.as(de.silveryard.basesystem.gui.Texture.class, obj);
                if(texture == null){
                    genericReturnCode.value = ReturnCode.ERROR;
                    specificReturnCode.value = GuiReturnCode.NOT_A_TEXTURE;
                    result.value = -1;
                    return;
                }

                result.value = texture.getWidth();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, genericReturnCode.value.getValue(), specificReturnCode.value.getValue(), Parameter.createInt(result.value));
    }
    public static QAMessage systemCallTextureGetHeight(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> genericReturnCode = new Wrapper<>();
        genericReturnCode.value = ReturnCode.OK;

        final Wrapper<GuiReturnCode> specificReturnCode = new Wrapper<>();
        specificReturnCode.value = GuiReturnCode.OK;

        final Wrapper<Integer> result = new Wrapper<>();

        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                if(message.getParameters().size() != 1) {
                    genericReturnCode.value = ReturnCode.INVALID_MESSAGE;
                    result.value = -1;
                    return;
                }

                Integer textureId = message.getParameters().get(0).getInt();
                if(textureId == null){
                    genericReturnCode.value = ReturnCode.INVALID_MESSAGE;
                    result.value = -1;
                    return;
                }

                Object obj = app.getRegisteredObject(textureId);
                if(obj == null){
                    genericReturnCode.value = ReturnCode.ERROR;
                    specificReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                de.silveryard.basesystem.gui.Texture texture = Utils.as(de.silveryard.basesystem.gui.Texture.class, obj);
                if(texture == null){
                    genericReturnCode.value = ReturnCode.ERROR;
                    specificReturnCode.value = GuiReturnCode.NOT_A_TEXTURE;
                    result.value = -1;
                    return;
                }

                result.value = texture.getHeight();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, genericReturnCode.value.getValue(), specificReturnCode.value.getValue(), Parameter.createInt(result.value));
    }
}
