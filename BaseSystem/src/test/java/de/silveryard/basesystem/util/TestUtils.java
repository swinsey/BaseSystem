package de.silveryard.basesystem.util;

import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.RunningAppMock;
import de.silveryard.basesystem.app.kernel.ReturnCode;

import java.lang.reflect.Method;

/**
 * Created by Sebif on 18.06.2017.
 */
public abstract class TestUtils {
    public static final String MOCK_HASH = "00000000000000000000000000000000";
    public static final String MOCK_APP_ID = "de.silveryard.testapp";
    public static final RunningApp MOCK_RUNNING_APP = new RunningAppMock(MOCK_APP_ID);

    public static <T1> T1 getEnumValue(Class<T1> cls, int value){
        try {
            Method method = cls.getMethod("getEnumValue", int.class);
            Object o = method.invoke(null, value);
            return (T1)o;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private TestUtils(){}
}
