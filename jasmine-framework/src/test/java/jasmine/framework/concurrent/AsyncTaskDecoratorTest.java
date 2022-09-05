package jasmine.framework.concurrent;

import jasmine.core.util.wrapper.ObjectValue;
import jasmine.framework.context.handler.ContextHandlerFacade;
import jasmine.framework.context.handler.ContextSnapshot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

/**
 * @author mh.z
 */
public class AsyncTaskDecoratorTest {
    private Boolean copyAllFromCurrentThreadCalledFlag;
    private Boolean clearAllFromCurrentThreadCalledFlag;

    @Before
    @After
    public void setUpOrTearDown() {
        copyAllFromCurrentThreadCalledFlag = false;
        clearAllFromCurrentThreadCalledFlag = false;
    }

    @Test
    public void testDecorate() {
        ContextHandlerFacade facade = mockHandlerFacade();
        AsyncTaskDecorator decorator = new AsyncTaskDecorator(facade);
        ObjectValue value = new ObjectValue(null);

        decorator.decorate(() -> {
            value.set("runnable.run called");
        }).run();

        Assert.assertEquals("runnable.run called", value.get());
        Assert.assertTrue(copyAllFromCurrentThreadCalledFlag);
        Assert.assertTrue(clearAllFromCurrentThreadCalledFlag);
    }

    /**
     * 模拟 ContextHandlerFacade 对象
     *
     * @return
     */
    private ContextHandlerFacade mockHandlerFacade() {
        ContextHandlerFacade facade = Mockito.mock(ContextHandlerFacade.class);

        // 调用 copyAllFromCurrentThreadCalledFlag 方法
        // 则标记 copyAllFromCurrentThreadCalledFlag 变量
        Mockito.when(facade.copyAllFromCurrentThread()).thenAnswer((answer) -> {
            copyAllFromCurrentThreadCalledFlag = true;

            ContextSnapshot snapshot = Mockito.mock(ContextSnapshot.class);
            return Collections.singletonList(snapshot);
        });

        // 调用 clearAllFromCurrentThread 方法
        // 则标记 clearAllFromCurrentThreadCalledFlag 变量
        Mockito.doAnswer((answer) -> {
            clearAllFromCurrentThreadCalledFlag = true;

            return null;
        }).when(facade).clearAllFromCurrentThread();

        return facade;
    }

}
