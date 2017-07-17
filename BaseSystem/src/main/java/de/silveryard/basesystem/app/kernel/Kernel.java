package de.silveryard.basesystem.app.kernel;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.util.IDisposable;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;

import java.util.*;

/**
 * Created by Sebif on 13.03.2017.
 */
public class Kernel {
    private static Kernel instance;
    /**
     * Singleton getter
     * @return Singleton instance
     */
    public static Kernel getInstance(){
        return instance;
    }
    /**
     * Starts the initialization
     * Registering systemcalls is only possible during initialization
     */
    public static void beginInitialize(){
        instance = new Kernel();
    }
    /**
     * Finalizes the initialization
     */
    public static void endInitialize(){
        instance.endInitializeKernel();
    }

    private boolean initializing;

    private Map<String, String> hashToSystemCallMap;
    private Map<String, ISystemCallHandler> systemCalls;

    private Kernel(){
        initializing = true;

        hashToSystemCallMap = new HashMap<>();
        systemCalls = new HashMap<>();
    }

    /**
     * Finalizes the initialization.
     * Called by endInitialize
     */
    public void endInitializeKernel(){
        if(!initializing){
            throw new RuntimeException("Kernel aready finished initializing");
        }

        registerSystemCall("de.silveryard.basesystem.systemcall.dummy", this::systemCallDummy);
        registerSystemCall("de.silveryard.basesystem.systemcall.dispose", this::systemCallDispose);
        initializing = false;
    }

    /**
     * Registers a new systemcall the kernel listens for
     * @param command Systemcall command name e.g. 'de.silveryard.testsystem.testsyscall'
     * @param handler Handler that handles the systemcall when it is called
     */
    public void registerSystemCall(String command, ISystemCallHandler handler){
        if(!initializing){
            throw new RuntimeException("Cannot register initialization after endInitializeKernel has been called");
        }

        String md5 = Utils.generateMd5(command);
        hashToSystemCallMap.put(md5, command);
        systemCalls.put(md5, handler);
    }

    /**
     * @return Returns a list of all registered systemcalls
     */
    public List<String> dump(){
        List<String> calls = new ArrayList<>();
        for(String key : hashToSystemCallMap.keySet()){
            calls.add(hashToSystemCallMap.get(key));
        }
        Collections.sort(calls);
        return calls;
    }

    /**
     * Redirects a systemcall to the handler in charge
     * @param app App invoking the systemcall
     * @param message Systemcall Message
     * @return Response
     */
    public QAMessage handleSystemCall(RunningApp app, QAMessage message){
        QAMessage response = null;

        if(systemCalls.containsKey(message.getMessage().getCommandHash())){
            ISystemCallHandler handler = systemCalls.get(message.getMessage().getCommandHash());
            response = handler.invoke(app, message);
        }else{
            System.out.println("Unknown SystemCall: " + message.getMessage().getCommandHash());
            List<Parameter> params = new ArrayList<>();
            params.add(Parameter.createInt(ReturnCode.NOT_IMPLEMENTED.getValue()));
            response = new QAMessage(message, params);
        }

        return response;
    }
    /**
     * Helper functin to create a response for a systemcall
     * @param source Systemcall message to create the response for
     * @param genericReturnCode Value of ReturnCode
     * @param specificReturnCode A more specific return code value
     * @param result An optional list of parameters to be included in the response
     * @return Response message
     */
    public QAMessage createResponse(QAMessage source, int genericReturnCode, int specificReturnCode, Parameter... result){
        List<Parameter> params = new ArrayList<>(2 + result.length);
        params.add(Parameter.createInt(genericReturnCode));
        params.add(Parameter.createInt(specificReturnCode));

        for(Parameter param : result){
            params.add(param);
        }

        return new QAMessage(source, params);
    }
    public QAMessage createErrorResponse(QAMessage source, ReturnCode returnCode){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(returnCode.getValue()));

        return new QAMessage(source, params);
    }
    public QAMessage createErrorResponse(QAMessage source, ReturnCode returnCode, int specificReturnCode){
        List<Parameter> params = new ArrayList<>(1);
        params.add(Parameter.createInt(returnCode.getValue()));
        params.add(Parameter.createInt(specificReturnCode));

        return new QAMessage(source, params);
    }

    private QAMessage systemCallDummy(RunningApp app, QAMessage message){
        return createResponse(message, ReturnCode.OK.getValue(), 0);
    }
    private QAMessage systemCallDispose(RunningApp app, QAMessage message){
        int objId = message.getParameters().get(0).getInt();
        app.unregisterObject(objId);
        return createResponse(message, ReturnCode.OK.getValue(), 0);
    }
}
