package de.silveryard.basesystem.gui.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.*;
import de.silveryard.basesystem.gui.TextureSprite;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 20.03.2017.
 */
final class NineSliceSprite {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.nineslicesprite.create", NineSliceSprite::systemCallNineSliceSpriteCreate);
    }

    public static QAMessage systemCallNineSliceSpriteCreate(RunningApp app, QAMessage message){
        int textureSpriteId = message.getParameters().get(0).getInt();
        int leftSlice = message.getParameters().get(1).getInt();
        int rightSlice = message.getParameters().get(2).getInt();
        int topSlice = message.getParameters().get(3).getInt();
        int bottomSlice = message.getParameters().get(4).getInt();
        int posX = message.getParameters().get(5).getInt();
        int posY = message.getParameters().get(6).getInt();
        int width = message.getParameters().get(7).getInt();
        int height = message.getParameters().get(8).getInt();

        de.silveryard.basesystem.gui.TextureSprite textureSprite = Utils.as(TextureSprite.class, app.getRegisteredObject(textureSpriteId));

        if(textureSprite == null){
            return Kernel.getInstance().createResponse(message, ReturnCode.ERROR.getValue(), GuiReturnCode.INVALID_ID.getValue(), Parameter.createInt(-1));
        }

        de.silveryard.basesystem.gui.NineSliceSprite sprite = new de.silveryard.basesystem.gui.NineSliceSprite(
                textureSprite,
                leftSlice, rightSlice,
                topSlice, bottomSlice,
                posX, posY,
                width, height
        );
        int id = app.registerObject(sprite);
        return Kernel.getInstance().createResponse(message, ReturnCode.OK.getValue(), GuiReturnCode.OK.getValue(), Parameter.createInt(id));
    }

    private NineSliceSprite(){}
}
