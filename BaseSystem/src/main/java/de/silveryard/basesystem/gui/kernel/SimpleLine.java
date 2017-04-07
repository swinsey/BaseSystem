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
abstract class SimpleLine {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.create", SimpleLine::systemCallSimpleLineCreate);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.getendpointx", SimpleLine::systemCallSimpleLineGetEndpointX);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.getendpointy", SimpleLine::systemCallSimpleLineGetEndpointY);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.setendpoint", SimpleLine::systemCallSimpleLineSetEndpoint);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.setendpointx", SimpleLine::systemCallSimpleLineSetEndpointX);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.setendpointy", SimpleLine::systemCallSimpleLineSetEndpointY);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.getcolorr", SimpleLine::systemCallSimpleLineGetColorR);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.getcolorg", SimpleLine::systemCallSimpleLineGetColorG);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.getcolorb", SimpleLine::systemCallSimpleLineGetColorB);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.setcolor", SimpleLine::systemCallSimpleLineSetColor);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.setcolorr", SimpleLine::systemCallSimpleLineSetColorR);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.setcolorg", SimpleLine::systemCallSimpleLineSetColorG);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.gui.simpleline.setcolorb", SimpleLine::systemCallSimpleLineSetColorB);
    }

    private static QAMessage systemCallSimpleLineCreate(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int positionX1 = message.getParameters().get(0).getInt();
        final int positionY1 = message.getParameters().get(1).getInt();
        final int positionX2 = message.getParameters().get(2).getInt();
        final int positionY2 = message.getParameters().get(3).getInt();
        final byte colorr = message.getParameters().get(4).getInt().byteValue();
        final byte colorg = message.getParameters().get(5).getInt().byteValue();
        final byte colorb = message.getParameters().get(6).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                de.silveryard.basesystem.gui.SimpleLine line = new de.silveryard.basesystem.gui.SimpleLine(
                        positionX1, positionY1, positionX2, positionY2,
                        colorr, colorg, colorb
                );
                result.value = app.registerObject(line);
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallSimpleLineGetEndpointX(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                result.value = line.getX2();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallSimpleLineGetEndpointY(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                result.value = line.getY2();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallSimpleLineSetEndpoint(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();
        final int endpointX = message.getParameters().get(1).getInt();
        final int endpointY = message.getParameters().get(2).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                line.setPoint2(endpointX, endpointY);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSimpleLineSetEndpointX(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();
        final int endpointX = message.getParameters().get(1).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                line.setX2(endpointX);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSimpleLineSetEndpointY(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();
        final int endpointY = message.getParameters().get(1).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                line.setY2(endpointY);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }

    private static QAMessage systemCallSimpleLineGetColorR(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                result.value = (int)line.getColorR();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallSimpleLineGetColorG(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                result.value = (int)line.getColorG();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }
    private static QAMessage systemCallSimpleLineGetColorB(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                result.value = (int)line.getColorB();
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue(),
                Parameter.createInt(result.value));
    }

    private static QAMessage systemCallSimpleLineSetColor(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();
        final byte colorR = message.getParameters().get(1).getInt().byteValue();
        final byte colorG = message.getParameters().get(2).getInt().byteValue();
        final byte colorB = message.getParameters().get(3).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                line.setColor(colorR, colorG, colorB);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSimpleLineSetColorR(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();
        final byte colorR = message.getParameters().get(1).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                line.setColorR(colorR);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSimpleLineSetColorG(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();
        final byte colorG = message.getParameters().get(1).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                line.setColorG(colorG);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
    private static QAMessage systemCallSimpleLineSetColorB(RunningApp app, QAMessage message){
        final Wrapper<ReturnCode> returnCode = new Wrapper<>(ReturnCode.OK);
        final Wrapper<GuiReturnCode> guiReturnCode = new Wrapper<>(GuiReturnCode.OK);
        final Wrapper<Integer> result = new Wrapper<>();

        final int lineID = message.getParameters().get(0).getInt();
        final byte colorB = message.getParameters().get(1).getInt().byteValue();

        GraphicsManager.getInstance().runNextFrame(new Action() {
            @Override
            public void invoke() {
                Object obj = app.getRegisteredObject(lineID);
                de.silveryard.basesystem.gui.SimpleLine line = Utils.as(de.silveryard.basesystem.gui.SimpleLine.class, obj);

                if(obj == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.INVALID_ID;
                    result.value = -1;
                    return;
                }

                if(line == null){
                    returnCode.value = ReturnCode.ERROR;
                    guiReturnCode.value = GuiReturnCode.NOT_A_SIMPLELINE;
                    result.value = -1;
                    return;
                }

                line.setColorB(colorB);
                result.value = -1;
            }
        });

        Utils.waitForWrapper(result);
        return Kernel.getInstance().createResponse(message, returnCode.value.getValue(), guiReturnCode.value.getValue());
    }
}
