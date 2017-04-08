package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.GuiKernelException;
import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

import static de.silveryard.basesystem.sdk.kernel.gui.Screen.*;

/**
 * Created by Sebif on 08.04.2017.
 */
public abstract class Screen {
    private static final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private static final Wrapper<GuiReturnCode> guiReturnCodeWrapper = new Wrapper<>();
    private static final Wrapper<Integer> integerWrapper = new Wrapper<>();

    /**
     * Returns the screens width in pixels
     * @return Width value
     */
    public static synchronized int getScreenWidth(){
        systemCallScreenGetWidth(returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }
    /**
     * Returns the screens height in pixels
     * @return Height value
     */
    public static synchronized int getScreenHeight(){
        systemCallScreenGetHeight(returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }

    /**
     * Adds a given render object to the render list. Only added render objects will be actually rendered
     * @param renderObject Object to add to the render list
     */
    public static synchronized void addRenderObject(RenderObject renderObject){
        systemCallScreenAddRenderObject(renderObject.getId(), returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Removes a given render object from the render list. It will no longer be rendered
     * @param renderObject Object to remove from the render list
     */
    public static synchronized void removeRenderObject(RenderObject renderObject){
        systemCallScreenRemoveRenderObject(renderObject.getId(), returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
}
