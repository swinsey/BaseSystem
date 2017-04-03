package de.silveryard.basesystem.sdk.kernel.gui;

import de.silveryard.transport.highlevelprotocols.qa.QAMessage;
import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 20.02.2017.
 */
public abstract class Texture {
    /**
     * Loads a texture from the file system
     * @param path Path to an image file to load
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outTextureId ID of the created texture
     */
    public static void systemCallTextureLoad(
            Path path,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode, Wrapper<Integer> outTextureId){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createString(path.toString()));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texture.load", params);

        int returnCodeInt = response.getParameters().get(0).getInt();
        int guiReturnCodeInt = response.getParameters().get(1).getInt();
        int textureId = response.getParameters().get(2).getInt();

        outReturnCode.value = ReturnCode.getEnumValue(returnCodeInt);
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(guiReturnCodeInt);
        outTextureId.value = textureId;
    }
    /**
     * Unloads a given texture. It will no longer use system resources and cannot be used anymore
     * @param textureID ID of the texture
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallTextureUnload(
            int textureID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texture.unload", params);

        int returnCodeInt = response.getParameters().get(0).getInt();
        int guiReturnCodeInt = response.getParameters().get(1).getInt();

        outReturnCode.value = ReturnCode.getEnumValue(returnCodeInt);
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(guiReturnCodeInt);
    }

    /**
     * Returns the width of a given texture
     * @param textureID ID of the texture
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outWidth Width Value
     */
    public static void systemCallTextureGetWidth(
            int textureID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode, Wrapper<Integer> outWidth){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texture.getwidth", params);

        int returnCodeInt = response.getParameters().get(0).getInt();
        int guiReturnCodeInt = response.getParameters().get(1).getInt();
        int width = response.getParameters().get(2).getInt();

        outReturnCode.value = ReturnCode.getEnumValue(returnCodeInt);
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(guiReturnCodeInt);
        outWidth.value = width;
    }
    /**
     * Returns the height of a given texture
     * @param textureID ID of the texture
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outHeight Height Value
     */
    public static void systemCallTextureGetHeight(
            int textureID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode, Wrapper<Integer> outHeight){
        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(textureID));
        QAMessage response = Kernel.systemCall("de.silveryard.basesystem.systemcall.gui.texture.getheight", params);

        int returnCodeInt = response.getParameters().get(0).getInt();
        int guiReturnCodeInt = response.getParameters().get(1).getInt();
        int height = response.getParameters().get(2).getInt();

        outReturnCode.value = ReturnCode.getEnumValue(returnCodeInt);
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(guiReturnCodeInt);
        outHeight.value = height;
    }
}
