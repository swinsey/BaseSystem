package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.GuiKernelException;
import de.silveryard.basesystem.sdk.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

import static de.silveryard.basesystem.sdk.kernel.gui.Font.systemCallFontGetSize;
import static de.silveryard.basesystem.sdk.kernel.gui.Font.systemCallFontLoadSystemFont;

/**
 * Created by Sebif on 08.04.2017.
 */
public class Font implements IDisposable {
    /**
     * Loads a system font by name
     * @param font Font name
     * @param size Font size
     * @return Loaded font
     */
    public static Font loadSystemFont(String font, int size){
        Wrapper<ReturnCode> returnCode = new Wrapper<>();
        Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>();
        Wrapper<Integer> result = new Wrapper<>();

        systemCallFontLoadSystemFont(font, size, returnCode, guiReturnCode, result);

        if(guiReturnCode.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCode.value);
        }

        if(returnCode.value != ReturnCode.OK){
            throw new KernelException(returnCode.value);
        }

        return new Font(result.value, true);
    }

    private final Wrapper<ReturnCode> returnCodeWrapper = new Wrapper<>();
    private final Wrapper<GuiReturnCode> guiReturnCodeWrapper = new Wrapper<>();
    private final Wrapper<Integer> integerWrapper = new Wrapper<>();

    private final int fontId;
    private final boolean systemFont;

    private Font(int fontId, boolean systemFont){
        this.fontId = fontId;
        this.systemFont = systemFont;
    }

    /**
     * @return Returns the fonts internal id
     */
    public int getFontId(){
        return fontId;
    }

    /**
     * @return Returns the fonts size
     */
    public synchronized int getSize(){
        systemCallFontGetSize(fontId, returnCodeWrapper, guiReturnCodeWrapper, integerWrapper);

        if(guiReturnCodeWrapper.value != GuiReturnCode.OK){
            throw new GuiKernelException(guiReturnCodeWrapper.value);
        }

        if(returnCodeWrapper.value != ReturnCode.OK){
            throw new KernelException(returnCodeWrapper.value);
        }

        return integerWrapper.value;
    }

    @Override
    public synchronized void dispose() {
        if(systemFont){
            return;
        }

        //TODO
    }
}
