package de.silveryard.basesystem.sdk.kernel.gui;

import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 20.02.2017.
 */
public abstract class TextureSprite {
    /**
     * Creates a new texture sprite
     * @param textureID ID of the texture to use
     * @param sourceX X Position on the source texture
     * @param sourceY Y Position on the source texture
     * @param sourceWidth Width on the source texture
     * @param sourceHeight Height on the source texture
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param textureSpriteID ID of the created texture sprite
     */
    public static void systemCallTextureSpriteCreate(
            int textureID,
            int sourceX, int sourceY,
            int sourceWidth, int sourceHeight,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> textureSpriteID
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureID));
        params.add(Parameter.createInt(sourceX));
        params.add(Parameter.createInt(sourceY));
        params.add(Parameter.createInt(sourceWidth));
        params.add(Parameter.createInt(sourceHeight));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texturesprite.create", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        textureSpriteID.value = response.getParameters().get(2).getInt();
    }

    /**
     * Returns the texture ID of a given texture sprite
     * @param textureSpriteID ID of the texture sprite
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param textureID Texture ID
     */
    public static void systemCallTextureSpriteGetTexture(
            int textureSpriteID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> textureID
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureSpriteID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texturesprite.gettexture", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        textureID.value = response.getParameters().get(2).getInt();
    }

    /**
     * Returns the x position of a given texture sprite
     * @param textureSpriteID ID of the texture sprite
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param sourceX X Position Value
     */
    public static void systemCallTextureSpriteGetSourceX(
            int textureSpriteID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> sourceX
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureSpriteID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texturesprite.getsourcex", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        sourceX.value = response.getParameters().get(2).getInt();
    }
    /**
     * Returns the y position of a given texture sprite
     * @param textureSpriteID ID of the texture sprite
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param sourceY Y Position Value
     */
    public static void systemCallTextureSpriteGetSourceY(
            int textureSpriteID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> sourceY
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureSpriteID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texturesprite.getsourcey", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        sourceY.value = response.getParameters().get(2).getInt();
    }
    /**
     * Returns the height of a given texture sprite
     * @param textureSpriteID ID of the texture sprite
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return cCode
     * @param sourceWidth Width Value
     */
    public static void systemCallTextureSpriteGetSourceWidth(
            int textureSpriteID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> sourceWidth
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureSpriteID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texturesprite.getsourcewidth", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        sourceWidth.value = response.getParameters().get(2).getInt();
    }
    /**
     * Returns the height of a given texture sprite
     * @param textureSpriteID ID of the texture sprite
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param sourceHeight Height Value
     */
    public static void systemCallTextureSpriteGetSourceHeight(
            int textureSpriteID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> sourceHeight
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureSpriteID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texturesprite.getsourceheight", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        sourceHeight.value = response.getParameters().get(2).getInt();
    }
}
