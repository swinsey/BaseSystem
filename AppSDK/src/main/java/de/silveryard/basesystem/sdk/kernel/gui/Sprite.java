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
public abstract class Sprite {
    /**
     * Creates a sprite
     * @param textureSpriteID ID of the texture sprite to use
     * @param positionX X Position Value
     * @param positionY Y Position Value
     * @param width Width Value
     * @param height Height Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param spriteID ID of the created sprite
     */
    public static void systemCallSpriteCreate(
            int textureSpriteID,
            int positionX, int positionY,
            int width, int height,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> spriteID
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureSpriteID));
        params.add(Parameter.createInt(positionX));
        params.add(Parameter.createInt(positionY));
        params.add(Parameter.createInt(width));
        params.add(Parameter.createInt(height));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.sprite.create", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        spriteID.value = response.getParameters().get(2).getInt();
    }

    /**
     * Returns the texture sprite ID of a given texture
     * @param spriteID ID of the texture
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param textureSpriteID ID of the used texture sprite
     */
    public static void systemCallSpriteGetTextureSprite(
            int spriteID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> textureSpriteID
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(spriteID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.sprite.gettexturesprite", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        textureSpriteID.value = response.getParameters().get(2).getInt();
    }

    /*public static void systemCallSpriteGetAngle(int spriteID){
    }
    public static void systemCallSpriteSetAngle(int spriteID, double angle){
    }*/
}
