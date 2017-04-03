import de.silveryard.transport.filecache.FileCache;
import de.silveryard.transport.Message;
import de.silveryard.transport.Transport;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Created by CHofm on 18.01.2017.
 */
public class SenderTest {

    public static void main(String... args) throws IOException, InterruptedException {
        int port = 0;
        do{
            String in = JOptionPane.showInputDialog("Port");
            try{
                port = Integer.parseInt(in);
            }catch(NumberFormatException e){}
        }while(port == 0);

        Socket s = new Socket("127.0.0.1", port);

        Consumer<Message> c = m -> {};

        Transport t = new Transport(s.getOutputStream(), s.getInputStream(), c::accept);
        FileCache fc = new FileCache(t::send, (sourceID, uuid, commandHash, params, data) -> {});

        byte[] fileData = Files.readAllBytes(Paths.get(".", "lep.bin"));

        String id = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println("SenderID: " + id);
        fc.sendFile(id, id, id, new LinkedList<>(), fileData);


        Thread.sleep(10000);
        t.disconnect();
        s.close();
    }
}
