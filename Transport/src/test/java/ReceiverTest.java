import de.awesome.smarthome.filecache.FileCache;
import de.awesome.smarthome.transport.Message;
import de.awesome.smarthome.transport.Transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * Created by CHofm on 16.01.2017.
 */
public class ReceiverTest {

    private static long start = 0;
    private static long end = 0;
    private static long bytesReceived = 0;
    private static int msgCount = 0;
    private static boolean loop = true;
    private static Connector con;

    public static void main(String... args) throws InterruptedException, IOException {

        ServerSocket ss = new ServerSocket(0);
        System.out.println(ss.getLocalPort());
        Socket s = ss.accept();

        con = new ReceiverTest().new Connector();

        Transport t = new Transport(s.getOutputStream(), s.getInputStream(), m -> {
            start();
            con.put(m);
        });

        FileCache fc = new FileCache(t::send, (sourceID, uuid, commandHash, params, data) -> {
            System.out.println("File received!");
            end();
            bytesReceived = data.length;
            try {
                Files.write(Paths.get(".", "lep2.bin"), data, StandardOpenOption.CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Wrote it");
        });

        con.setConsumer(fc::handle);
        con.start();

        while(loop){
            Thread.sleep(1000);
        }

        double sec = (end - start) / (double)1000;
        double speedInKB = bytesReceived / sec / 1024;


        System.out.println("Transfer took " + sec + "s.");
        System.out.println("Approx speed was " + speedInKB + "KB/s.");
    }

    public static void start(){
        if(start == 0){
            start = System.currentTimeMillis();
        }
    }

    public static void end(){
        end = System.currentTimeMillis();
        loop = false;
        con.stop();
    }

    private class Connector{

        private BlockingQueue<Message> messages;
        private Consumer<Message> consumer;
        private Thread thread;

        public Connector(){
            messages = new ArrayBlockingQueue<Message>(1000);
        }

        public void setConsumer(Consumer<Message> cons){
            this.consumer = cons;
        }

        public void put(Message m ){
            messages.add(m);
        }

        public void start(){
            thread = new Thread(() -> {
                while(true){
                    try {
                        consumer.accept(messages.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

        public void stop(){
//            thread.interrupt();
        }

    }
}
