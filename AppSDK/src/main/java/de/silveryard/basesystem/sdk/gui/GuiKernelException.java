package de.silveryard.basesystem.sdk.gui;

import de.silveryard.basesystem.sdk.kernel.KernelException;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.gui.GuiReturnCode;

/**
 * Created by Sebif on 08.04.2017.
 */
public class GuiKernelException extends KernelException {
    private GuiReturnCode guiReturnCode;

    /**
     * Constructor
     * @param guiReturnCode GuiReturnCode enum value
     */
    public GuiKernelException(GuiReturnCode guiReturnCode) {
        super(ReturnCode.ERROR);
        this.guiReturnCode = guiReturnCode;
    }
    /**
     * Constructor
     * @param guiReturnCode GuiReturnCode enum value
     * @param message Exception message
     */
    public GuiKernelException(GuiReturnCode guiReturnCode, String message) {
        super(ReturnCode.ERROR, message);
        this.guiReturnCode = guiReturnCode;
    }
    /**
     * Constructor
     * @param guiReturnCode GuiReturnCode enum value
     * @param base Base exception
     */
    public GuiKernelException(GuiReturnCode guiReturnCode, Throwable base) {
        super(ReturnCode.ERROR, base);
        this.guiReturnCode = guiReturnCode;
    }
    /**
     * Constructor
     * @param guiReturnCode GuiReturnCode enum value
     * @param message Exception message
     * @param base Base exception
     */
    public GuiKernelException(GuiReturnCode guiReturnCode, String message, Throwable base) {
        super(ReturnCode.ERROR, message, base);
        this.guiReturnCode = guiReturnCode;
    }

    /**
     * Returns the GuiReturnCode enum value
     * @return GuiReturnCode
     */
    public GuiReturnCode getGuiReturnCode(){
        return guiReturnCode;
    }
}
