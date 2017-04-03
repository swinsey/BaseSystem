import de.silveryard.transport.Message;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.Transport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by CHofm on 10.01.2017.
 */
public class TransportTest {

    private static ServerSocket ss;

    @BeforeClass
    public static void init(){
        try {
            ss = new ServerSocket(1337);
            new Thread(() -> {
                while(true)
                    try {
                        ss.accept();
                    } catch (IOException e) {}
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void shutdown(){
        if(ss != null){
            try{
                ss.close();
            }catch(Exception e){}
        }
    }


    @Test(timeout = 5000)
    public void send() throws Exception {
        Message m = getRandomMessage();
        List<Parameter> params = m.getParams();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Dummy d = new Dummy();

        Transport t = new Transport(out, new ByteArrayInputStream(new byte[1]), me -> {});
        t.send(m);

        Transport t2 = new Transport(new ByteArrayOutputStream(), new ByteArrayInputStream(out.toByteArray()), me -> {
            boolean paramsSize = me.getParams().size() == params.size();
            boolean paramsEqual = me.getParams().equals(params);

            d.setTestPassed(paramsSize && paramsEqual);
            d.callHandler();
        });

        while(!d.wasHandlerCalled()){Thread.sleep(100);}
        assertTrue(d.testPassed());
        t.disconnect();
        t2.disconnect();
    }

    @Test(expected = NullPointerException.class)
    public void outStreamNull() throws Exception{
        Transport t = new Transport(null, new ByteArrayInputStream(new byte[1]), m -> {});
    }

    @Test(expected = NullPointerException.class)
    public void inStreamNull() throws Exception{
        Transport t = new Transport(new ByteArrayOutputStream(), null, m -> {});
    }

    @Test(expected = NullPointerException.class)
    public void handlerNull() throws Exception{
        Transport t = new Transport(new ByteArrayOutputStream(), new ByteArrayInputStream(new byte[]{}), null);
    }

    @Test
    public void disconnect() throws Exception {
        Socket client = new Socket("127.0.0.1", 1337);

        Transport t = new Transport(client.getOutputStream(), client.getInputStream(), m -> {});
        t.disconnect();
        assertFalse(t.isConnected());
        assertFalse(t.send(getRandomMessage()));
    }

    @Test
    public void isConnected() throws Exception{
        Transport t = new Transport(new ByteArrayOutputStream(), new ByteArrayInputStream(new byte[1]), m -> {});
        t.disconnect();
        assertFalse(t.isConnected());
    }

    private class Dummy{
        private boolean handlerWasCalled;
        private boolean testPassed;

        public Dummy(){
            handlerWasCalled = false;
            testPassed = false;
        }

        public boolean wasHandlerCalled(){
            return handlerWasCalled;
        }

        public void callHandler(){
            handlerWasCalled = true;
        }

        public void setTestPassed(boolean b){
            testPassed = b;
        }

        public boolean testPassed(){
            return testPassed;
        }
    }

    private class Dummy2{
        private int expectedHandlerCallCount;
        private int handlerCallCount;

        public Dummy2(int expectedHandlerCallCount){
            this.expectedHandlerCallCount = expectedHandlerCallCount;
            this.handlerCallCount = 0;
        }

        public void callHandler(){ handlerCallCount++;
            System.out.println("Call handler");}

        public boolean testPassed(){
            return expectedHandlerCallCount == handlerCallCount;
        }

    }

    public static Message getRandomMessage(){
        String uid1 = UUID.randomUUID().toString().replaceAll("-", "");
        String uid2 = UUID.randomUUID().toString().replaceAll("-", "");
        String uid3 = UUID.randomUUID().toString().replaceAll("-", "");

        List<Parameter> params = new LinkedList<>();
        Random r = new Random();

        IntStream
                .range(0, 10)
                .forEach(i -> {
                    switch(r.nextInt(3)){
                        case 0:
                            params.add(Parameter.createBoolean(System.currentTimeMillis() % 2 == 0 ? true : false));
                            break;
                        case 1:
                            params.add(Parameter.createInt(r.nextInt()));
                            break;
                        case 2:
                            params.add(Parameter.createString(UUID.randomUUID().toString() + UUID.randomUUID().toString()));
                            break;
                    }
                });
        return new Message(uid1, uid2, uid3, params);
    }
}


