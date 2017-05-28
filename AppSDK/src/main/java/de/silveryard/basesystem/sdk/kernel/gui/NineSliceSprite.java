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
public abstract class NineSliceSprite {
    public static void systemCallNineSliceSpriteCreate(
            int textureSpriteId,
            int leftSlice, int rightSlice, int topSlice, int bottomSlice,
            int posX, int posY, int width, int height,
            Wrapper<ReturnCode> outReturnCode,
            Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outObjectId
    ){

        List<Parameter> params = new ArrayList<>(9);
        params.add(Parameter.createInt(textureSpriteId));
        params.add(Parameter.createInt(leftSlice));
        params.add(Parameter.createInt(rightSlice));
        params.add(Parameter.createInt(topSlice));
        params.add(Parameter.createInt(bottomSlice));
        params.add(Parameter.createInt(posX));
        params.add(Parameter.createInt(posY));
        params.add(Parameter.createInt(width));
        params.add(Parameter.createInt(height));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.nineslicesprite.create", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outObjectId.value = response.getParameters().get(2).getInt();
    }
}
