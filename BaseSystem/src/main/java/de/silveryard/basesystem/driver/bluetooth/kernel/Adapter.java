package de.silveryard.basesystem.driver.bluetooth.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.kernel.Kernel;
import de.silveryard.basesystem.app.kernel.ReturnCode;
import de.silveryard.basesystem.driver.DriverManager;
import de.silveryard.basesystem.driver.bluetooth.BluetoothDriver;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

/**
 * Created by silveryard on 01.05.17.
 */
abstract class Adapter {
    public static void enableKernel(){
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.getaddress", Adapter::systemcallDriverBTAdapterGetAddress);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.getalias", Adapter::systemcallDriverBTAdapterGetAlias);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.getmodalias", Adapter::systemcallDriverBTAdapterGetModalias);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.getname", Adapter::systemcallDriverBTAdapterGetName);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.isdiscoverable", Adapter::systemcallDriverBTAdapterIsDiscoverable);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.isdiscovering", Adapter::systemcallDriverBTAdapterIsDiscovering);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.ispairable", Adapter::systemcallDriverBTAdapterIsPairable);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.ispowered", Adapter::systemcallDriverBTAdapterIsPowered);

        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.startdiscovery", Adapter::systemcallDriverBTAdapterStartDiscovery);
        Kernel.getInstance().registerSystemCall("de.silveryard.basesystem.systemcall.driver.bt.adapter.stopdiscovery", Adapter::systemcallDriverBTAdapterStopDiscovery);
    }

    private static QAMessage systemcallDriverBTAdapterGetAddress(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(),
                Parameter.createString(DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().getAddress())
        );
    }
    private static QAMessage systemcallDriverBTAdapterGetAlias(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(),
                Parameter.createString(DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().getAlias())
        );
    }
    private static QAMessage systemcallDriverBTAdapterGetModalias(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(),
                Parameter.createString(DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().getModalias())
        );
    }
    private static QAMessage systemcallDriverBTAdapterGetName(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(),
                Parameter.createString(DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().getName())
        );
    }

    private static QAMessage systemcallDriverBTAdapterIsDiscoverable(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(),
                Parameter.createBoolean(DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().isDiscoverable())
        );
    }
    private static QAMessage systemcallDriverBTAdapterIsDiscovering(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(),
                Parameter.createBoolean(DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().isDiscovering())
        );
    }
    private static QAMessage systemcallDriverBTAdapterIsPairable(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(),
                Parameter.createBoolean(DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().isPairable())
        );
    }
    private static QAMessage systemcallDriverBTAdapterIsPowered(RunningApp app, QAMessage message){
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue(),
                Parameter.createBoolean(DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().isPowered())
        );
    }

    private static QAMessage systemcallDriverBTAdapterStartDiscovery(RunningApp app, QAMessage message){
        DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().startDiscovery();
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue()
        );
    }
    private static QAMessage systemcallDriverBTAdapterStopDiscovery(RunningApp app, QAMessage message){
        DriverManager.getInstance().getDriver(BluetoothDriver.class).getAdapter().stopDiscovery();
        return Kernel.getInstance().createResponse(
                message, ReturnCode.OK.getValue(), BtReturnCode.OK.getValue()
        );
    }
}
