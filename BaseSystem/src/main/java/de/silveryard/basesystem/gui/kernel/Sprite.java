package de.silveryard.basesystem.gui.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 19.03.2017.
 */
abstract class Sprite {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.kernel.sprite.create", Sprite::systemCallSpriteCreate);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.kernel.sprite.gettexturesprite", Sprite::systemCallSpriteGetTextureSprite);
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
                de.silveryard.basesystem.gui.TextureSprite textureSprite = Utils.as(de.silveryard.basesystem.gui.TextureSprite.class, objTS);

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

                de.silveryard.basesystem.gui.Sprite sprite = new de.silveryard.basesystem.gui.Sprite(
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
                de.silveryard.basesystem.gui.Sprite sprite = Utils.as(de.silveryard.basesystem.gui.Sprite.class, obj);

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

                de.silveryard.basesystem.gui.TextureSprite textureSprite = sprite.getTextureSprite();
                int id = app.getId(textureSprite);
                result.value = id;
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
}
