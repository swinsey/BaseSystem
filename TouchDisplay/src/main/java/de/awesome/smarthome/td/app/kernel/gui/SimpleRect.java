package de.awesome.smarthome.td.app.kernel.gui;

import de.awesome.smarthome.highlevelprotocols.qa.QAMessage;
import de.awesome.smarthome.td.app.RunningApp;
import de.awesome.smarthome.td.app.kernel.Kernel;
import de.awesome.smarthome.td.app.kernel.ReturnCode;
import de.awesome.smarthome.td.gui.GraphicsManager;
import de.awesome.smarthome.td.util.Action;
import de.awesome.smarthome.td.util.Utils;
import de.awesome.smarthome.td.util.Wrapper;
import de.awesome.smarthome.transport.Parameter;

/**
 * Created by Sebif on 21.03.2017.
 */
abstract class SimpleRect {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.create", SimpleRect::systemCallSimpleRectCreate);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.getfilled", SimpleRect::systemCallSimpleRectGetFilled);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setfilled", SimpleRect::systemCallSimpleRectSetFilled);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.getcolorr", SimpleRect::systemCallSimpleRectGetColorR);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.getcolorg", SimpleRect::systemCallSimpleRectGetColorG);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.getcolorb", SimpleRect::systemCallSimpleRectGetColorB);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setcolor", SimpleRect::systemCallSimpleRectSetColor);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setcolorr", SimpleRect::systemCallSimpleRectSetColorR);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setcolorg", SimpleRect::systemCallSimpleRectSetColorG);
        Kernel.getInstance().registerSystemCall("de.awesome.smarthome.td.systemcall.gui.simplerect.setcolorb", SimpleRect::systemCallSimpleRectSetColorB);
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
                de.awesome.smarthome.td.gui.SimpleRect rect = new de.awesome.smarthome.td.gui.SimpleRect(
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
                de.awesome.smarthome.td.gui.SimpleRect rect = Utils.as(de.awesome.smarthome.td.gui.SimpleRect.class, obj);

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
                de.awesome.smarthome.td.gui.SimpleRect rect = Utils.as(de.awesome.smarthome.td.gui.SimpleRect.class, obj);

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
                de.awesome.smarthome.td.gui.SimpleRect rect = Utils.as(de.awesome.smarthome.td.gui.SimpleRect.class, obj);

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
                de.awesome.smarthome.td.gui.SimpleRect rect = Utils.as(de.awesome.smarthome.td.gui.SimpleRect.class, obj);

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
                de.awesome.smarthome.td.gui.SimpleRect rect = Utils.as(de.awesome.smarthome.td.gui.SimpleRect.class, obj);

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
                de.awesome.smarthome.td.gui.SimpleRect rect = Utils.as(de.awesome.smarthome.td.gui.SimpleRect.class, obj);

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
                de.awesome.smarthome.td.gui.SimpleRect rect = Utils.as(de.awesome.smarthome.td.gui.SimpleRect.class, obj);

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
                de.awesome.smarthome.td.gui.SimpleRect rect = Utils.as(de.awesome.smarthome.td.gui.SimpleRect.class, obj);

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
                de.awesome.smarthome.td.gui.SimpleRect rect = Utils.as(de.awesome.smarthome.td.gui.SimpleRect.class, obj);

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
