package jasmine.framework.context;

import jasmine.core.util.ref.ObjectValue;
import jasmine.framework.context.handler.ContextHandlerFacade;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class ContextManagementHelperTest {
    private ContextHandlerFacade prevContextHandlerFacade;

    @Before
    public void setUp() {
        prevContextHandlerFacade = ContextManagementHelper.getHandlerFacade();
        ContextManagementHelper.initUtil(null);
    }

    @After
    public void tearDown() {
        ContextManagementHelper.initUtil(prevContextHandlerFacade);
    }

    @Test
    public void testManageContext1() {
        ContextHandlerFacade facade = Mockito.mock(ContextHandlerFacade.class);

        // 调用 initAllToCurrentThread 方法
        // 则标记 initAllToCurrentThreadCalledFlag 变量
        ObjectValue initAllToCurrentThreadCalledFlag = new ObjectValue(false);
        Mockito.doAnswer((invocation) -> {
            initAllToCurrentThreadCalledFlag.set(true);
            return null;
        }).when(facade).initAllToCurrentThread();

        // 调用 clearAllFromCurrentThread 方法
        // 则标记 clearAllFromCurrentThreadCalledFlag 变量
        ObjectValue clearAllFromCurrentThreadCalledFlag = new ObjectValue(false);
        Mockito.doAnswer((invocation) -> {
            clearAllFromCurrentThreadCalledFlag.set(true);
            return null;
        }).when(facade).clearAllFromCurrentThread();

        ContextManagementHelper.initUtil(facade);
        ObjectValue value = new ObjectValue(null);
        Object actual = ContextManagementHelper.manageContext(() -> {
            value.set("function.call called");

            return "Hello, test!";
        });

        Assert.assertEquals("Hello, test!", actual);
        Assert.assertEquals("function.call called", value.get());
        Assert.assertEquals(true, initAllToCurrentThreadCalledFlag.get());
        Assert.assertEquals(true, clearAllFromCurrentThreadCalledFlag.get());
    }

    @Test
    public void testManageContext2() {
        ContextHandlerFacade facade = Mockito.mock(ContextHandlerFacade.class);

        // 调用 initAllToCurrentThread 方法
        // 则标记 initAllToCurrentThreadCalledFlag 变量
        ObjectValue initAllToCurrentThreadCalledFlag = new ObjectValue(false);
        Mockito.doAnswer((invocation) -> {
            initAllToCurrentThreadCalledFlag.set(true);
            return null;
        }).when(facade).initAllToCurrentThread();

        // 调用 clearAllFromCurrentThread 方法
        // 则标记 clearAllFromCurrentThreadCalledFlag 变量
        ObjectValue clearAllFromCurrentThreadCalledFlag = new ObjectValue(false);
        Mockito.doAnswer((invocation) -> {
            clearAllFromCurrentThreadCalledFlag.set(true);
            return null;
        }).when(facade).clearAllFromCurrentThread();

        ContextManagementHelper.initUtil(facade);
        ObjectValue value = new ObjectValue(null);
        ContextManagementHelper.manageContext(() -> {
            value.set("function.call called");
        });

        Assert.assertEquals("function.call called", value.get());
        Assert.assertEquals(true, initAllToCurrentThreadCalledFlag.get());
        Assert.assertEquals(true, clearAllFromCurrentThreadCalledFlag.get());
    }

}
