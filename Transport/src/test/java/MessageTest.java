import de.silveryard.transport.Message;
import de.silveryard.transport.Parameter;
import org.junit.*;

import javax.xml.bind.DatatypeConverter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by chhofm on 10.01.2017.
 */
public class MessageTest {

    private static Message m;
    private static String uid1, uid2, uid3;

    @BeforeClass
    public static void init(){
       uid1 = UUID.randomUUID().toString().replaceAll("-", "");
       uid2 = UUID.randomUUID().toString().replaceAll("-", "");
       uid3 = UUID.randomUUID().toString().replaceAll("-", "");

        List<Parameter> params = new LinkedList<>();
        params.add(Parameter.createInt(2));
        params.add(Parameter.createInt(-140));
        /*params.add(Parameter.createBoolean(false));
        params.add(Parameter.createBoolean(true));
        params.add(Parameter.createFloat(0f));
        params.add(Parameter.createFloat(-1.9379f));
        params.add(Parameter.createString("Hello World"));
        params.add(Parameter.createString(""));
        params.add(Parameter.createByteArray(Message.MESSAGE_START_SEQUENCE));
        */m = new Message(uid1, uid2, uid3, params);
    }

    @Test
    public void toBytes(){
        m.toBytes();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvalidSender(){
        new Message("penis", uid1, uid2, Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvalidDestination(){
        new Message(uid1, "yoloswag", uid2, Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorInvalidCommandHash(){
        new Message(uid1, uid2, "styleandmoney", Collections.emptyList());
    }

    @Test(expected = NullPointerException.class)
    public void senderNull(){
        new Message(null, uid1, uid2, Collections.emptyList());
    }

    @Test(expected = NullPointerException.class)
    public void destinationNull(){
        new Message(uid1, null, uid2, Collections.emptyList());
    }

    @Test(expected = NullPointerException.class)
    public void hashNull(){
        new Message(uid1, uid2, null, Collections.emptyList());
    }

    @Test(expected = NullPointerException.class)
    public void paramsNull(){
        new Message(uid1, uid2, uid3, null);
    }

    @Test
    public void getSenderID() throws Exception {
        assertNotNull(m.getSenderID());
        assertTrue(m.getSenderID().length() == 32);
    }

    @Test
    public void getDestinationID() throws Exception {
        assertNotNull(m.getSenderID());
        assertTrue(m.getSenderID().length() == 32);
    }

    @Test
    public void getCommandHash() throws Exception {
        assertNotNull(m.getSenderID());
        assertTrue(m.getSenderID().length() == 32);
    }

    @Test
    public void getParams() throws Exception {
        assertTrue(m.getParams() instanceof List);
        assertNotNull(m.getParams());
    }

    @Test(expected = IllegalArgumentException.class)
    public void limitParams(){
        new Message(uid1, uid2, uid3,
                Stream.generate(() -> Parameter.createBoolean(true))
                      .limit(300)
                      .collect(Collectors.toList())
        );
    }

    @Test
    public void equals() throws Exception {
        assertTrue(new Message(m.getSenderID(), m.getDestinationID(), m.getCommandHash(), m.getParams()).equals(m));
    }
}