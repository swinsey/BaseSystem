package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

import static de.silveryard.basesystem.sdk.kernel.gui.RenderObject.*;

/**
 * Created by Sebif on 08.04.2017.
 */
public abstract class RenderObject implements IDisposable {
    /**
     * Wrapper for systemcalls return code results
     */
    protected final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    /**
     * Wrapper for systemcalls gui return code results
     */
    protected final Wrapper<GuiReturnCode> guiReturnCodeWrapper = new Wrapper<>();
    /**
     * Wrapper for systemcalls integer results
     */
    protected final Wrapper<Integer> integerWrapper = new Wrapper<>();

    /**
     * Returns the renderobjects internal id
     * @return Identifier that identifies this renderobject
     */
    public abstract int getId();

    /**
     * Sets the renderobjects layer. Objects with higher layers will be rendered on top of others
     * @param layer Layer value
     */
    public synchronized void setLayer(int layer){
        systemCallRenderObjectSetLayer(getId(), layer, returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }
    /**
     * Returns the renderobjects layer. Objects with higher layers will be rendered on top of others
     * @return Layer value
     */
    public synchronized int getLayer(){
        systemCallRenderObjectGetLayer(getId(), returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }

    /**
     * Sets the dirty flag of this render object.
     * When the dirty flag is set, the render system will rerender the image.
     * This flag will also automatically be set, if other values of a render object are changed.
     * So setting this manually is not needed most of the time
     */
    public synchronized void setDirty(){
        systemCalRenderObjectSetDirty(getId(), returnCodeWrapper, guiReturnCodeWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }
    }

    @Override
    public synchronized void dispose() {
        //TODO
    }
}
