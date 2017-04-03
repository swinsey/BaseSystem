package de.silveryard.basesystem.sdk.kernel.gui;

import de.silveryard.transport.highlevelprotocols.qa.QAMessage;
import de.silveryard.basesystem.sdk.kernel.Kernel;
import de.silveryard.basesystem.sdk.kernel.ReturnCode;
import de.silveryard.basesystem.sdk.kernel.Wrapper;
import de.silveryard.transport.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebif on 20.02.2017.
 */
public abstract class Label {
    /**
     * Creates a new Label
     * @param fontID ID of the font to use
     * @param text Initial Text of the label
     * @param horizontalAlignment Initial Horizontal Alignment respective to position, internal text size and width
     * @param verticalAlignment Initial Vertical Alignment respective to position, internal text size and height
     * @param positionX Initial X position
     * @param positionY Initial Y position
     * @param width Initial width
     * @param height Initial height
     * @param color Intial RGBA Color
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outLabelId ID of the created label
     */
    public static void  systemCallLabelCreate(
            int fontID, String text,
            HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment,
            int positionX, int positionY,
            int width, int height,
            Color color,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outLabelId){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(fontID));
        params.add(Parameter.createString(text));
        params.add(Parameter.createInt(horizontalAlignment.getValue()));
        params.add(Parameter.createInt(verticalAlignment.getValue()));
        params.add(Parameter.createInt(positionX));
        params.add(Parameter.createInt(positionY));
        params.add(Parameter.createInt(width));
        params.add(Parameter.createInt(height));
        params.add(Parameter.createInt(color.getR()));
        params.add(Parameter.createInt(color.getG()));
        params.add(Parameter.createInt(color.getB()));
        params.add(Parameter.createInt(color.getA()));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.create", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outLabelId.value = response.getParameters().get(2).getInt();
    }

    /**
     * Returns the font ID used by a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outFontID ID of the used font
     */
    public static void systemCallLabelGetFont(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outFontID
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.getfont", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outFontID.value = response.getParameters().get(2).getInt();
    }
    /**
     * Sets the font used by a given label
     * @param labelID ID of the label
     * @param fontID ID of the new font to use
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallLabelSetFont(
            int labelID, int fontID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        params.add(Parameter.createInt(fontID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.setfont", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Returns the text of a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outText Text value
     */
    public static void systemCallLabelGetText(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<String> outText
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.gettext", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outText.value = response.getParameters().get(2).getString();
    }
    /**
     * Sets the text of a given label
     * @param labelID ID of the label
     * @param text Text value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallLabelSetText(
            int labelID, String text,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        params.add(Parameter.createString(text));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.settext", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Returns the horizontal alignment of a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Value
     * @param outGuiReturnCode Gui Return Value
     * @param outHorizontalAlignment Horizontal Alignment Value
     */
    public static void systemCallLabelGetHorizontalAlignment(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<HorizontalAlignment> outHorizontalAlignment
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.gethorizontalalignment", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outHorizontalAlignment.value = HorizontalAlignment.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Sets the horizontal alignment of a given label
     * @param labelID ID of the label
     * @param horizontalAlignment Horizontal Alignment Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallLabelSetHorizontalAlignment(
            int labelID, HorizontalAlignment horizontalAlignment,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        params.add(Parameter.createInt(horizontalAlignment.getValue()));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.sethorizontalalignment", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Returns the vertical alignment of a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outVerticalAlignment Vertical Alignment Value
     */
    public static void systemCallLabelGetVerticalAlignment(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<VerticalAlignment> outVerticalAlignment
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.getverticalalignment", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outVerticalAlignment.value = VerticalAlignment.getEnumValue(response.getParameters().get(2).getInt());
    }
    /**
     * Sets the vertical alignment of a given label
     * @param labelID ID of the label
     * @param verticalAlignment Vertical Alignment Value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallLabelSetVerticalAlignment(
            int labelID, VerticalAlignment verticalAlignment,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        params.add(Parameter.createInt(verticalAlignment.getValue()));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.setverticalalignment", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Returns the RGBA value of a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColor RGBA Color value
     */
    public static void systemCallLabelGetColor(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Color> outColor
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.getcolor", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());

        byte colorR = response.getParameters().get(2).getInt().byteValue();
        byte colorG = response.getParameters().get(3).getInt().byteValue();
        byte colorB = response.getParameters().get(4).getInt().byteValue();
        byte colorA = response.getParameters().get(5).getInt().byteValue();
        outColor.value = new Color(colorR, colorG, colorB, colorA);
    }
    /**
     * Returns the red value of a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColorR Red value
     */
    public static void systemCallLabelGetColorR(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Byte> outColorR
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.getcolorr", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outColorR.value = response.getParameters().get(2).getInt().byteValue();
    }
    /**
     * Returns the green value of a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColorG Green value
     */
    public static void systemCallLabelGetColorG(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Byte> outColorG
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.getcolorg", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outColorG.value = response.getParameters().get(2).getInt().byteValue();
    }
    /**
     * Returns the blue value of a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outColorB Blue value
     */
    public static void systemCallLabelGetColorB(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Byte> outColorB
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.getcolorb", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outColorB.value = response.getParameters().get(2).getInt().byteValue();
    }

    /**
     * Sets the RGBA Color of a given label
     * @param labelID ID of the label
     * @param color RGBA Color
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallLabelSetColor(
            int labelID, Color color,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        params.add(Parameter.createInt(color.getR()));
        params.add(Parameter.createInt(color.getG()));
        params.add(Parameter.createInt(color.getB()));
        params.add(Parameter.createInt(color.getA()));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.setcolor", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the red value of a given label
     * @param labelID ID of the label
     * @param colorR Red value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallLabelSetColorR(
            int labelID, byte colorR,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        params.add(Parameter.createInt(colorR));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.setcolorr", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the green value of a given label
     * @param labelID ID of the label
     * @param colorG Green value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallLabelSetColorG(
            int labelID, byte colorG,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        params.add(Parameter.createInt(colorG));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.setcolorg", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }
    /**
     * Sets the blue value of a given label
     * @param labelID ID of the label
     * @param colorB Blue value
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     */
    public static void systemCallLabelSetColorB(
            int labelID, byte colorB,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        params.add(Parameter.createInt(colorB));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.setcolorb", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
    }

    /**
     * Returns the internal text width of a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outInternalWidth Internal text width
     */
    public static void systemCallLabelGetInternalWidth(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outInternalWidth
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.getinternalwidth", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outInternalWidth.value = response.getParameters().get(2).getInt();
    }
    /**
     * Returns the internal text height of a given label
     * @param labelID ID of the label
     * @param outReturnCode General Return Code
     * @param outGuiReturnCode Gui Return Code
     * @param outInternalHeight Internal text height
     */
    public static void systemCallLabelGetInternalHeight(
            int labelID,
            Wrapper<ReturnCode> outReturnCode, Wrapper<GuiReturnCode> outGuiReturnCode,
            Wrapper<Integer> outInternalHeight
    ){

        List<Parameter> params = new ArrayList<>();
        params.add(Parameter.createInt(labelID));
        QAMessage response = Kernel.systemCall("de.awesome.smarthome.td.systemcall.gui.label.getinternalheight", params);

        outReturnCode.value = ReturnCode.getEnumValue(response.getParameters().get(0).getInt());
        outGuiReturnCode.value = GuiReturnCode.getEnumValue(response.getParameters().get(1).getInt());
        outInternalHeight.value = response.getParameters().get(2).getInt();
    }
}
