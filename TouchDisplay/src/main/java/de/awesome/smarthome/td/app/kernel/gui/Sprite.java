package de.awesome.smarthome.td.app.kernel.gui;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.app.RunningApp;
import de.awesome.smarthome.td.app.kernel.Kernel;
import de.awesome.smarthome.td.app.kernel.ReturnCode;
import de.awesome.smarthome.td.gui.GraphicsManager;
import de.awesome.smarthome.td.gui.TextureSprite;
import de.awesome.smarthome.td.util.Action;
import de.awesome.smarthome.td.util.Utils;
import de.awesome.smarthome.td.util.Wrapper;
import de.awesome.smarthome.transport.Parameter;

/**
 * Created by Sebif on 19.03.2017.
 */
abstract class Sprite {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.sprite.create", Sprite::systemCallSpriteCreate);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.sprite.gettexturesprite", Sprite::systemCallSpriteGetTextureSprite);
    }

    private static QAMessage systemCallSpriteCreate(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int textureSpriteID = message.getParameters().get(0).getInt();
        final int positionX = message.getParameters().get(1).getInt();
        final int positionY = message.getParameters().get(2).getInt();
        final int width = message.getParameters().get(3).getInt();
        final int height = message.getParameters().get(4).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object objTS = app.getRegisteredObject(textureSpriteID);
                de.awesome.smarthome.td.gui.TextureSprite textureSprite = Utils.as(TextureSprite.class, objTS);

                if(objTS == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(textureSprite == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_TEXTURESPRITE;
                    result.value = -1;
                    return;
                }

                de.awesome.smarthome.td.gui.Sprite sprite = new de.awesome.smarthome.td.gui.Sprite(
                        textureSprite,
                        positionX, positionY,
                        width, height,
                        0
                );
                result.value = app.registerObject(sprite);
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallSpriteGetTextureSprite(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int spriteID = message.getParameters().get(0).getInt();
        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(spriteID);
                de.awesome.smarthome.td.gui.Sprite sprite = Utils.as(de.awesome.smarthome.td.gui.Sprite.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(sprite == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SPRITE;
                    result.value = -1;
                    return;
                }

                TextureSprite textureSprite = sprite.getTextureSprite();
                int id = app.getId(textureSprite);
                result.value = id;
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
}
