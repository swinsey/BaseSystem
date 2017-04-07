package de.silveryard.basesystem.gui.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.gui.GraphicsManager;
import de.silveryard.basesystem.util.Action;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.basesystem.util.Wrapper;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by Sebif on 21.03.2017.
 */
abstract class SimpleRect {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.create", SimpleRect::systemCallSimpleRectCreate);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.getfilled", SimpleRect::systemCallSimpleRectGetFilled);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.setfilled", SimpleRect::systemCallSimpleRectSetFilled);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.getcolorr", SimpleRect::systemCallSimpleRectGetColorR);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.getcolorg", SimpleRect::systemCallSimpleRectGetColorG);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.getcolorb", SimpleRect::systemCallSimpleRectGetColorB);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.setcolor", SimpleRect::systemCallSimpleRectSetColor);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.setcolorr", SimpleRect::systemCallSimpleRectSetColorR);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.setcolorg", SimpleRect::systemCallSimpleRectSetColorG);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simplerect.setcolorb", SimpleRect::systemCallSimpleRectSetColorB);
    }

    private static QAMessage systemCallSimpleRectCreate(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int positionX = message.getParameters().get(0).getInt();
        final int positionY = message.getParameters().get(1).getInt();
        final int width = message.getParameters().get(2).getInt();
        final int height = message.getParameters().get(3).getInt();
        final boolean filled = message.getParameters().get(4).getBoolean();
        final byte colorR = message.getParameters().get(5).getInt().byteValue();
        final byte colorG = message.getParameters().get(6).getInt().byteValue();
        final byte colorB = message.getParameters().get(7).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                de.silveryard.basesystem.gui.SimpleRect rect = new de.silveryard.basesystem.gui.SimpleRect(
                        positionX, positionY,
                        width, height,
                        filled,
                        colorR, colorG, colorB
                );
                result.value = app.registerObject(rect);
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallSimpleRectGetFilled(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Boolean> result = new Wrapper<>();

        final int rectId = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(rectId);
                de.silveryard.basesystem.gui.SimpleRect rect = Utils.as(de.silveryard.basesystem.gui.SimpleRect.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = false;
                    return;
                }

                if(rect == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLERECT;
                    result.value = false;
                    return;
                }

                result.value = rect.getFilled();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createBoolean(result.value));
    }
    private static QAMessage systemCallSimpleRectSetFilled(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int rectId = message.getParameters().get(0).getInt();
        final boolean filled = message.getParameters().get(1).getBoolean();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(rectId);
                de.silveryard.basesystem.gui.SimpleRect rect = Utils.as(de.silveryard.basesystem.gui.SimpleRect.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(rect == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLERECT;
                    result.value = -1;
                    return;
                }

                rect.setFilled(filled);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }

    private static QAMessage systemCallSimpleRectGetColorR(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int rectId = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(rectId);
                de.silveryard.basesystem.gui.SimpleRect rect = Utils.as(de.silveryard.basesystem.gui.SimpleRect.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(rect == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLERECT;
                    result.value = -1;
                    return;
                }

                result.value = rect.getColorR();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallSimpleRectGetColorG(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int rectId = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(rectId);
                de.silveryard.basesystem.gui.SimpleRect rect = Utils.as(de.silveryard.basesystem.gui.SimpleRect.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(rect == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLERECT;
                    result.value = -1;
                    return;
                }

                result.value = rect.getColorG();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallSimpleRectGetColorB(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int rectId = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(rectId);
                de.silveryard.basesystem.gui.SimpleRect rect = Utils.as(de.silveryard.basesystem.gui.SimpleRect.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(rect == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLERECT;
                    result.value = -1;
                    return;
                }

                result.value = rect.getColorB();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallSimpleRectSetColor(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int rectId = message.getParameters().get(0).getInt();
        final byte colorR = message.getParameters().get(1).getInt().byteValue();
        final byte colorG = message.getParameters().get(2).getInt().byteValue();
        final byte colorB = message.getParameters().get(3).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(rectId);
                de.silveryard.basesystem.gui.SimpleRect rect = Utils.as(de.silveryard.basesystem.gui.SimpleRect.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(rect == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLERECT;
                    result.value = -1;
                    return;
                }

                rect.setColor(colorR, colorG, colorB);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSimpleRectSetColorR(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int rectId = message.getParameters().get(0).getInt();
        final byte colorR = message.getParameters().get(1).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(rectId);
                de.silveryard.basesystem.gui.SimpleRect rect = Utils.as(de.silveryard.basesystem.gui.SimpleRect.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(rect == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLERECT;
                    result.value = -1;
                    return;
                }

                rect.setColorR(colorR);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSimpleRectSetColorG(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int rectId = message.getParameters().get(0).getInt();
        final byte colorG = message.getParameters().get(1).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(rectId);
                de.silveryard.basesystem.gui.SimpleRect rect = Utils.as(de.silveryard.basesystem.gui.SimpleRect.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(rect == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLERECT;
                    result.value = -1;
                    return;
                }

                rect.setColorG(colorG);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSimpleRectSetColorB(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Byte> result = new Wrapper<>();

        final int rectId = message.getParameters().get(0).getInt();
        final byte colorB = message.getParameters().get(1).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(rectId);
                de.silveryard.basesystem.gui.SimpleRect rect = Utils.as(de.silveryard.basesystem.gui.SimpleRect.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(rect == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLERECT;
                    result.value = -1;
                    return;
                }

                rect.setColorB(colorB);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
}
