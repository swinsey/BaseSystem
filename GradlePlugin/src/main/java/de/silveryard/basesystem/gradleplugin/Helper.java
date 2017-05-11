package de.silveryard.basesystem.gradleplugin;

import java.io.*;

/**
 * Created by silveryard on 09.05.17.
 */
public class Helper {
    public static int execute(String command, File workingDirectory) throws Exception {
        final Process exec = Runtime.getRuntime().exec(command, null, workingDirectory);
        final Wrapper<Boolean> finished = new Wrapper<>();
        finished.value = false;

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream stream = exec.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader bufReader = new BufferedReader(reader);

                while(exec.isAlive()){
                    try {
                        String l = bufReader.readLine();
                        if(l == null)
                            continue;
                        System.out.println(l);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }

                finished.value = true;
            }
        });
        t1.setDaemon(true);
        t1.start();

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                InputStream stream = exec.getErrorStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader bufReader = new BufferedReader(reader);

                while(exec.isAlive()){
                    try{
                        String l = bufReader.readLine();
                        if(l == null)
                            continue;
                        System.out.println(l);
                    }catch(Exception e){
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        t2.setDaemon(true);
        t2.start();

        int res = exec.waitFor();
        while(!finished.value){
            Thread.sleep(200);
        }
        return res;
    }
}
