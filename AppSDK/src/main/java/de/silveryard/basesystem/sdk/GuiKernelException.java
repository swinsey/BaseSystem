package de.silveryard.basesystem.sdk;

import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

/**
 * Created by Sebif on 08.04.2017.
 */
public class GuiKernelException extends KernelException {
    private GuiReturnCode guiReturnCode;

    public GuiKernelException(GuiReturnCode guiReturnCode) {
        super(ReturnCode.ERROR);
        this.guiReturnCode = guiReturnCode;
    }

    public GuiKernelException(GuiReturnCode guiReturnCode, String message) {
        super(ReturnCode.ERROR, message);
        this.guiReturnCode = guiReturnCode;
    }

    public GuiKernelException(GuiReturnCode guiReturnCode, Throwable base) {
        super(ReturnCode.ERROR, base);
        this.guiReturnCode = guiReturnCode;
    }

    public GuiKernelException(GuiReturnCode guiReturnCode, String message, Throwable base) {
        super(ReturnCode.ERROR, message, base);
        this.guiReturnCode = guiReturnCode;
    }

    public GuiReturnCode getGuiReturnCode(){
        return guiReturnCode;
    }
}
