package de.silveryard.basesystem.sdk.kernel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Sebif on 10.03.2017.
 */
public abstract class AppSDK {
    /**
     * Path to the apps data directory
     */
    public static Path dataDirectory;
    /**
     * Path to the apps readonly directory
     */
    public static Path readonlyDirectory;

    private static volatile List<Action> pendingActions;

    /**
     * @param args Argument list:
     *             [0] Communication Port Number
     *             [1] Data Directory Path
     *             [2] Readonly Directory Path
     * @param  app
     * @param kernelLogging
     */
    public static void appMain(String[] args, App app, int kernelLogging){
        try{
            assert (args.length == 3);

            int portNr = Integer.parseInt(args[0]);
            dataDirectory = Paths.get(args[1]);
            readonlyDirectory = Paths.get(args[2]);

            assert(Files.exists(dataDirectory) && Files.isDirectory(dataDirectory));
            assert(Files.exists(readonlyDirectory) && Files.isDirectory(readonlyDirectory));

            Kernel.initialize(portNr, app, kernelLogging);

            while(Kernel.isIsInitializing()){
                Thread.sleep(200);
            }
            pendingActions = new Vector<>();

            app.onAppLoaded();

            while(Kernel.isConnectionActive()){
                synchronized (pendingActions){
                    while(pendingActions.size() > 0){
                        Action action = pendingActions.get(0);
                        pendingActions.remove(0);
                        action.invoke();
                    }
                }
                Thread.sleep(200);
            }

            Thread.sleep(100);
            java.lang.System.out.println("App exited gracefully");
            java.lang.System.exit(0);
        }catch (Throwable t){
            java.lang.System.out.println("App exited with error: " + t);
            t.printStackTrace();
            java.lang.System.exit(1);
        }
    }

    public static synchronized void runOnMainThread(Action action){
        pendingActions.add(action);
    }
}
