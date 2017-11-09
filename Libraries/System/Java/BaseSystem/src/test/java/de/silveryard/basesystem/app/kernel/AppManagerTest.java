package de.silveryard.basesystem.app.kernel;

import de.silveryard.basesystem.app.AppManagerResult;
import de.silveryard.basesystem.app.RunningApp;
import de.silveryard.basesystem.app.RunningAppMock;
import de.silveryard.basesystem.util.TestUtils;
import de.silveryard.basesystem.util.Utils;
import de.silveryard.transport.Parameter;
import de.silveryard.transport.highlevelprotocols.qa.QAMessage;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static de.silveryard.basesystem.util.TestUtils.*;

/**
 * Created by Sebif on 18.06.2017.
 */
public class AppManagerTest {
    private static Path appPath;
    private static Path dataPath;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String startup = Utils.getStartupPath();
        appPath = Paths.get(new File(startup).toPath().toString(), "MockAppDir");
        dataPath = Paths.get(new File(startup).toPath().toString(), "MockDataDir");

        if(!Files.exists(appPath)) {
            Files.createDirectory(appPath);
        }
        if(!Files.exists(dataPath)) {
            Files.createDirectory(dataPath);
        }

        de.silveryard.basesystem.app.AppManager.initialize(appPath, dataPath);
        Kernel.beginInitialize();
        Kernel.endInitialize();
    }
    @AfterClass
    public static void afterClass() throws Exception {
        de.silveryard.basesystem.app.AppManager.getInstance().dispose();

        FileUtils.deleteDirectory(appPath.toFile());
        FileUtils.deleteDirectory(dataPath.toFile());
    }

    @Test
    public void testGetInstalledApps(){
        QAMessage src = new QAMessage(false, MOCK_HASH, MOCK_HASH, MOCK_HASH, new ArrayList<>(0));
        QAMessage rsp = AppManager.systemCallAppAppManagerGetInstalledApps(MOCK_RUNNING_APP, src);

        assertEquals(3, rsp.getParameters().size());

        ReturnCode returnCode = getEnumValue(ReturnCode.class, rsp.getParameters().get(0).getInt());
        AppManagerResult appManagerResult = getEnumValue(AppManagerResult.class, rsp.getParameters().get(1).getInt());

        assertEquals(ReturnCode.OK, returnCode);
        assertEquals(AppManagerResult.OK, appManagerResult);
        assertEquals(0, (int)rsp.getParameters().get(2).getInt());
    }
    @Test
    public void testGetInstalledAppsInvalidArgs(){
        List<Parameter> parameters = new ArrayList<>(1);
        parameters.add(Parameter.createString("Hello World"));
        QAMessage src = new QAMessage(false, MOCK_HASH, MOCK_HASH, MOCK_HASH, parameters);
        QAMessage rsp = AppManager.systemCallAppAppManagerGetInstalledApps(MOCK_RUNNING_APP, src);

        assertEquals(1, rsp.getParameters().size());

        ReturnCode returnCode = getEnumValue(ReturnCode.class, rsp.getParameters().get(0).getInt());

        assertEquals(ReturnCode.INVALID_MESSAGE, returnCode);
    }

    @Test
    public void testRunningApps(){
        QAMessage src = new QAMessage(false, MOCK_HASH, MOCK_HASH, MOCK_HASH, new ArrayList<>(0));
        QAMessage rsp = AppManager.systemCallAppAppManagerGetRunningApps(MOCK_RUNNING_APP, src);

        assertEquals(3, rsp.getParameters().size());

        ReturnCode returnCode = getEnumValue(ReturnCode.class, rsp.getParameters().get(0).getInt());
        AppManagerResult appManagerResult = getEnumValue(AppManagerResult.class, rsp.getParameters().get(1).getInt());

        assertEquals(ReturnCode.OK, returnCode);
        assertEquals(AppManagerResult.OK, appManagerResult);
        assertEquals(0, (int)rsp.getParameters().get(2).getInt());
    }
    @Test
    public void testGetRunningAppsInvalidArgs(){
        List<Parameter> parameters = new ArrayList<>(1);
        parameters.add(Parameter.createString("Hello World"));
        QAMessage src = new QAMessage(false, MOCK_HASH, MOCK_HASH, MOCK_HASH, parameters);
        QAMessage rsp = AppManager.systemCallAppAppManagerGetInstalledApps(MOCK_RUNNING_APP, src);

        assertEquals(1, rsp.getParameters().size());

        ReturnCode returnCode = getEnumValue(ReturnCode.class, rsp.getParameters().get(0).getInt());

        assertEquals(ReturnCode.INVALID_MESSAGE, returnCode);
    }
}