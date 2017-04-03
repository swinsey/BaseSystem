package de.silveryard.basesystem.sdk.kernel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    /**
     * @param args Argument list:
     *             [0] Communication Port Number
     *             [1] Data Directory Path
     *             [2] Readonly Directory Path
     * @param  app
     */
    public static void appMain(String[] args, App app){
        try{
            assert (args.length == 3);

            int portNr = Integer.parseInt(args[0]);
            dataDirectory = Paths.get(args[1]);
            readonlyDirectory = Paths.get(args[2]);

            assert(Files.exists(dataDirectory) && Files.isDirectory(dataDirectory));
            assert(Files.exists(readonlyDirectory) && Files.isDirectory(readonlyDirectory));

            Kernel.initialize(portNr, app);

            while(Kernel.isIsInitializing()){
                Thread.sleep(200);
            }

            app.onAppLoaded();

            while(Kernel.isConnectionActive()){
                Thread.sleep(1000);
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
}
