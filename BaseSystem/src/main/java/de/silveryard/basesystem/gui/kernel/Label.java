package de.silveryard.basesystem.gui.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.Frame;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.gui.HorizontalAlignment;
import de.silveryard.basesystem.gui.VerticalAlignment;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 19.03.2017.
 */
abstract class Label {
    public static void enableKernel(){
        Kernel kernel = Kernel.getInstance();
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.create", Label::systemCallLabelCreate);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.getfont", Label::systemCalLabelGetFont);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.setfont", Label::systemCallLabelSetFont);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.gettext", Label::systemCallLabelGetText);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.settext", Label::systemCalLabelSetText);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.gethorizontalalignment", Label::systemCallLabelGetHorizontalAlignment);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.sethorizontalalignment", Label::systemCallLabelSetHorizontalAlignment);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.getverticalalignment", Label::systemCallLabelGetVerticalAlignment);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.setverticalalignemt", Label::systemCallLabelSetVerticalAlignment);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.getcolor", Label::systemCallLabelGetColor);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.getcolorr", Label::systemCallLabelGetColorR);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.getcolorg", Label::systemCallLabelGetColorG);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.getcolorb", Label::systemCallLabelGetColorB);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.setcolor", Label::systemCallLabelSetColor);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.setcolorr", Label::systemCallLabelSetColorR);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.setcolorg", Label::systemCallLabelSetColorG);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.setcolorb", Label::systemCallLabelSetColorB);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.getinternalwidth", Label::systemCallLabelGetInternalWidth);
        kernel.registerSystemCall("de.silveryard.basesystem.systemcall.gui.label.getinternalheight", Label::systemCallLabelGetInternalHeight);
    }

    private static QAMessage systemCallLabelCreate(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>();
        returnCode.value = ReturnCode.OK;

        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>();
        guiReturnCode.value = GuiReturnCode.OK;

        final Wrapper<Integer> result = new Wrapper<>();

        final int fontId = message.getParameters().get(0).getInt();
        final String text = message.getParameters().get(1).getString();
        final HorizontalAlignment horizontalAlignment = HorizontalAlignment.getEnumValue(message.getParameters().get(2).getInt());
        final VerticalAlignment verticalAlignment = VerticalAlignment.getEnumValue(message.getParameters().get(3).getInt());
        final int positionX = message.getParameters().get(4).getInt();
        final int positionY = message.getParameters().get(5).getInt();
        final int width = message.getParameters().get(6).getInt();
        final int height = message.getParameters().get(7).getInt();
        final byte colorR = message.getParameters().get(8).getInt().byteValue();
        final byte colorG = message.getParameters().get(9).getInt().byteValue();
        final byte colorB = message.getParameters().get(10).getInt().byteValue();
        final byte colorA = message.getParameters().get(11).getInt().byteValue();

        GraphicsManager graphicsManager = GraphicsManager.getInstance();
        graphicsManager.runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(fontId);
                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                de.silveryard.basesystem.gui.Font font = Utils.as(de.silveryard.basesystem.gui.Font.class, obj);
                if(font == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_FONT;
                    result.value = -1;
                    return;
                }

                Frame frame = app.getFrame();
                de.silveryard.basesystem.gui.Label label = frame.createLabel(
                        font, text,
                        horizontalAlignment, verticalAlignment,
                        positionX, positionY,
                        width, height,
                        colorR, colorG, colorB, colorA
                );
                if(label == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.UNKNOWN_ERROR;
                    result.value = -1;
                    return;
                }

                result.value = app.registerObject(label);
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
    }

    private static QAMessage systemCalLabelGetFont(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();

        Object obj = app.getRegisteredObject(labelId);
        de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
        }

        de.silveryard.basesystem.gui.Font font = label.getFont();
        result.value = app.getId(font);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
    }
    private static QAMessage systemCallLabelSetFont(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        final int fontId = message.getParameters().get(1).getInt();

        final Object objLabel = app.getRegisteredObject(labelId);
        final Object objFont = app.getRegisteredObject(fontId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, objLabel);
        final de.silveryard.basesystem.gui.Font font = Utils.as(de.silveryard.basesystem.gui.Font.class, objFont);

        if(objLabel == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }
        if(objFont == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }
        if(font == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_FONT;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                label.setFont(font);
                result.value = -1;
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }

    private static QAMessage systemCallLabelGetText(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<String> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = "";
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createString(result.value));
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = "";
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createString(result.value));
        }

        result.value = label.getText();

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createString(result.value));
    }
    private static QAMessage systemCalLabelSetText(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        final String text = message.getParameters().get(1).getString();

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }
        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                label.setText(text);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }

    private static QAMessage systemCallLabelGetHorizontalAlignment(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<HorizontalAlignment> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = HorizontalAlignment.LEFT;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value.getValue()));
        }
        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = HorizontalAlignment.LEFT;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value.getValue()));
        }

        result.value = label.getHorizontalAlignment();
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value.getValue()));
    }
    private static QAMessage systemCallLabelSetHorizontalAlignment(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        final HorizontalAlignment alignment = HorizontalAlignment.getEnumValue(message.getParameters().get(1).getInt());

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }
        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                label.setHorizontalAlignment(alignment);
                result.value = -1;
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }

    private static QAMessage systemCallLabelGetVerticalAlignment(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<VerticalAlignment> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = VerticalAlignment.TOP;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value.getValue()));
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = VerticalAlignment.TOP;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value.getValue()));
        }

        result.value = label.getVerticalAlignment();
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value.getValue()));
    }
    private static QAMessage systemCallLabelSetVerticalAlignment(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        final VerticalAlignment alignment = VerticalAlignment.getEnumValue(message.getParameters().get(1).getInt());

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                label.setVerticalAlignment(alignment);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }

    private static QAMessage systemCallLabelGetColor(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();
        final Wrapper<Byte> colorR = new Wrapper<>((byte)0);
        final Wrapper<Byte> colorG = new Wrapper<>((byte)0);
        final Wrapper<Byte> colorB = new Wrapper<>((byte)0);
        final Wrapper<Byte> colorA = new Wrapper<>((byte)0);

        final int labelId = message.getParameters().get(0).getInt();
        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(colorR.value), Parameter.createInt(colorG.value), Parameter.createInt(colorB.value));
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                    Parameter.createInt(colorR.value), Parameter.createInt(colorG.value), Parameter.createInt(colorB.value));
        }

        colorR.value = label.getColorR();
        colorG.value = label.getColorG();
        colorB.value = label.getColorB();
        colorA.value = label.getAlpha();

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(colorR.value), Parameter.createInt(colorG.value), Parameter.createInt(colorB.value));
    }
    private static QAMessage systemCallLabelGetColorR(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
        }

        result.value = label.getColorR();
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
    }
    private static QAMessage systemCallLabelGetColorG(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();

        Object obj = app.getRegisteredObject(labelId);
        de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
        }

        result.value = label.getColorG();
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
    }
    private static QAMessage systemCallLabelGetColorB(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();

        Object obj = app.getRegisteredObject(labelId);
        de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
        }

        result.value = label.getColorB();
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
    }

    private static QAMessage systemCallLabelSetColor(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        final byte colorR = message.getParameters().get(1).getInt().byteValue();
        final byte colorG = message.getParameters().get(2).getInt().byteValue();
        final byte colorB = message.getParameters().get(3).getInt().byteValue();
        final byte colorA = message.getParameters().get(4).getInt().byteValue();

        Object obj = app.getRegisteredObject(labelId);
        de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                label.setColor(colorR, colorG, colorB);
                label.setAlpha(colorA);
                result.value = -1;
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallLabelSetColorR(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        final byte colorR = message.getParameters().get(1).getInt().byteValue();

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {


                label.setColorR(colorR);
                result.value = -1;
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallLabelSetColorG(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        final byte colorG = message.getParameters().get(1).getInt().byteValue();

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                label.setColorG(colorG);
                result.value = -1;
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallLabelSetColorB(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        final byte colorB = message.getParameters().get(1).getInt().byteValue();

        final Object obj = app.getRegisteredObject(labelId);
        final de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

        if(obj == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.INVALID_ID;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        if(label == null){
            returnCode.value = ReturnCode.ERROR;
            guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
            result.value = -1;
            return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
        }

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                label.setColorB(colorB);
                result.value = -1;
            }
        });

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }

    private static QAMessage systemCallLabelGetInternalWidth(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(labelId);
                de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(label == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
                    result.value = -1;
                    return;
                }

                result.value = label.getInternalWidth();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
    }
    private static QAMessage systemCallLabelGetInternalHeight(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int labelId = message.getParameters().get(0).getInt();
        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(labelId);
                de.silveryard.basesystem.gui.Label label = Utils.as(de.silveryard.basesystem.gui.Label.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(label == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_LABEL;
                    result.value = -1;
                    return;
                }

                result.value = label.getInternalHeight();
            }
        });

        Utils.waitForWrapper(result);

        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(), Parameter.createInt(result.value));
    }
}
