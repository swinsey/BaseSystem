import de.awesome.smarthome.transport.Parameter;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by CHofm on 11.01.2017.
 */
public class ParameterTest {
    @Test
    public void createBoolean() throws Exception {
        Parameter p = Parameter.createBoolean(false);
        Parameter p2 = Parameter.createBoolean(true);
        assertFalse(p.getBoolean());
        assertTrue(p2.getBoolean());
    }

    @Test
    public void createInt() throws Exception {
        assertEquals(1928374, Parameter.createInt(1928374).getInt().intValue());
    }

    @Test
    public void createString() throws Exception {
        assertEquals("Test passed", Parameter.createString("Test passed").getString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void limitDataSize(){
        Parameter.createString(
                "In all energy exchanges if no energy enters or leaves an isolated system " +
                "the entropy of that system increases. Energy continuously flows from being concentrated " +
                "to becoming dispersed, spread out, wasted and useless. " +
                "An economy based on endless growth is unsustainable.");
    }

    @Test
    public void getSize() throws Exception {
        assertEquals(4, Parameter.createInt(1928374).getSize());
        assertEquals(4, Parameter.createInt(-1928374).getSize());
    }

    @Test
    public void getData() throws Exception {
        assertTrue(
                Arrays.equals(
                        "Crowded elevators have a different smell to children and midgets.".getBytes(),
                        Parameter.createString("Crowded elevators have a different smell to children and midgets.").getData()
                )
        );
    }

    @Test
    public void getBoolean() throws Exception {
        assertTrue(Parameter.createBoolean(false).getBoolean() instanceof Boolean);
    }

    @Test
    public void getInt() throws Exception {
        assertTrue(Parameter.createInt(12345).getInt() instanceof Integer);
    }

    @Test
    public void getString() throws Exception {
        assertTrue(Parameter.createString("hi").getString() instanceof String);
    }

    @Test
    public void equals() throws Exception {
        assertTrue(Parameter.createString("last test woho").equals(Parameter.createString("last test woho")));
    }
}