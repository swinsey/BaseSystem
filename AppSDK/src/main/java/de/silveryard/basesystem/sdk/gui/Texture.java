package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import static de.silveryard.basesystem.sdk.kernel.gui.Texture.*;

/**
 * Created by Sebif on 08.04.2017.
 */
public class Texture implements IDisposable {
    public static Texture create(Path path){
        return new Texture(path);
    }
    public static CompletableFuture<Texture> createAsync(Path path){
        return systemCallTextureLoadAsync(path)
                .thenApply((de.silveryard.basesystem.sdk.kernel.gui.Texture.TextureLoadResponse resp) -> {
                    if(resp.guiReturnCode != GuiReturnCode.OK){
                        throw new GuiKernelException(resp.guiReturnCode);
                    }

                    if(resp.returnCode != ReturnCode.OK){
                        throw new KernelException(resp.returnCode);
                    }

                    return new Texture(resp.textureId);
                });
    }

    private final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private final Wrapper<GuiReturnCode> guiReturnCodeWrapper = new Wrapper<>();
    private final Wrapper<Integer> integerWrapper = new Wrapper<>();

    private final int textureId;

    /**
     * Loads a texture from the file system
     * @param path Path to an image file to load
     */
    @Deprecated
    public Texture(Path path){
        systemCallTextureLoad(path, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        textureId = integerWrapper.value;
    }
    private Texture(int textureId){
        this.textureId = textureId;
    }

    /**
     * Returns the textures internal id
     * @return Texture ID
     */
    public int getTextureId(){
        return textureId;
    }

    /**
     * Returns the width of this texture
     * @return Width value
     */
    public synchronized int getWidth(){
        systemCallTextureGetWidth(textureId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Returns the height of this texture
     * @return Height value
     */
    public synchronized int getHeight(){
        systemCallTextureGetHeight(textureId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }

    @Override
    public void dispose() {
        //TODOs
    }
}
