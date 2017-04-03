package de.awesome.smarthome.td.app.kernel.gui;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.app.RunningApp;
import de.awesome.smarthome.td.app.kernel.Kernel;
import de.awesome.smarthome.td.app.kernel.ReturnCode;
import de.awesome.smarthome.td.gui.GraphicsManager;
import de.awesome.smarthome.td.gui.Texture;
import de.awesome.smarthome.td.util.Action;
import de.awesome.smarthome.td.util.Utils;
import de.awesome.smarthome.td.util.Wrapper;
import de.awesome.smarthome.transport.Parameter;

/**
 * Created by Sebif on 19.03.2017.
 */
abstract class TextureSprite {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.texturesprite.create", TextureSprite::systemCallTextureSpriteCreate);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.texturesprite.gettexture", TextureSprite::systemCallTextureSpriteGetTexture);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.texturesprite.getsourcex", TextureSprite::systemCallTextureSpriteGetSourceX);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.texturesprite.getsourcey", TextureSprite::systemCallTextureSpriteGetSourceY);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.texturesprite.getsourcewidth", TextureSprite::systemCallTextureSpriteGetSourceWidth);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.texturesprite.getsourceheight", TextureSprite::systemCallTextureSpriteGetSourceHeight);
    }

    private static QAMessage systemCallTextureSpriteCreate(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int textureID = message.getParameters().get(0).getInt();
        final int sourceX = message.getParameters().get(1).getInt();
        final int sourceY = message.getParameters().get(2).getInt();
        final int sourceWidth = message.getParameters().get(3).getInt();
        final int sourceHeight = message.getParameters().get(4).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object objTexture = app.getRegisteredObject(textureID);
                de.awesome.smarthome.td.gui.Texture texture = Utils.as(Texture.class, objTexture);

                if(objTexture == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(texture == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_TEXTURE;
                    result.value = -1;
                    return;
                }

                de.awesome.smarthome.td.gui.TextureSprite textureSprite = new de.awesome.smarthome.td.gui.TextureSprite(
                        texture,
                        sourceX, sourceY,
                        sourceWidth, sourceHeight
                );
                result.value = app.registerObject(textureSprite);
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallTextureSpriteGetTexture(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int textureSpriteID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(textureSpriteID);
                de.awesome.smarthome.td.gui.TextureSprite textureSprite = Utils.as(de.awesome.smarthome.td.gui.TextureSprite.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(textureSprite == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_TEXTURESPRITE;
                    result.value = -1;
                }

                Texture texture = textureSprite.getTexture();
                int id = app.getId(texture);
                result.value = id;
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallTextureSpriteGetSourceX(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int textureSpriteID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(textureSpriteID);
                de.awesome.smarthome.td.gui.TextureSprite textureSprite = Utils.as(de.awesome.smarthome.td.gui.TextureSprite.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(textureSprite == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_TEXTURESPRITE;
                    result.value = -1;
                }

                result.value = textureSprite.getTextureX();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallTextureSpriteGetSourceY(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int textureSpriteID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(textureSpriteID);
                de.awesome.smarthome.td.gui.TextureSprite textureSprite = Utils.as(de.awesome.smarthome.td.gui.TextureSprite.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(textureSprite == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_TEXTURESPRITE;
                    result.value = -1;
                }

                result.value = textureSprite.getTextureY();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallTextureSpriteGetSourceWidth(RunningApp app, QAMessage message){
         final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
         final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
         final Wrapper<Integer> result = new Wrapper<>();
         final int textureSpriteID = message.getParameters().get(0).getInt();

         GraphicsManager.getInstance().runNextFrame(new Action() {
             @Override
             public void invoke() {
                 Object obj = app.getRegisteredObject(textureSpriteID);
                 de.awesome.smarthome.td.gui.TextureSprite textureSprite = Utils.as(de.awesome.smarthome.td.gui.TextureSprite.class, obj);

                 if(obj == null){
                     returnCode.value = ReturnCode.ERROR;
                     guiReturnCode.value = GuiReturnCode.INVALID_ID;
                     result.value = -1;
                     return;
                 }

                 if(textureSprite == null){
                     returnCode.value = ReturnCode.ERROR;
                     guiReturnCode.value = GuiReturnCode.NOT_A_TEXTURESPRITE;
                     result.value = -1;
                 }

                 result.value = textureSprite.getTextureWidth();
             }
         });

         Utils.waitForWrapper(result);

         return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                 Parameter.createInt(result.value));
     }
    private static QAMessage systemCallTextureSpriteGetSourceHeight(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final int textureSpriteID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(textureSpriteID);
                de.awesome.smarthome.td.gui.TextureSprite textureSprite = Utils.as(de.awesome.smarthome.td.gui.TextureSprite.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(textureSprite == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_TEXTURESPRITE;
                    result.value = -1;
                }

                result.value = textureSprite.getTextureHeight();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
}
