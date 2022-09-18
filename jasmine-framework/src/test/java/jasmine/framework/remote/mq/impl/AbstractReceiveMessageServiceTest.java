package jasmine.framework.remote.mq.impl;

import jasmine.core.util.wrapper.ObjectValue;
import jasmine.framework.context.ContextManagementHelper;
import jasmine.framework.context.handler.ContextHandlerFacade;
import jasmine.framework.remote.mq.impl.interceptor.DefaultReceiveInterceptor;
import jasmine.framework.remote.mq.impl.interceptor.DefaultReceiveInvocationInfo;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocationInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class AbstractReceiveMessageServiceTest extends AbstractReceiveMessageService {
    private ContextHandlerFacade prevContextHandlerFacade;

    private static ReceiveInterceptor lastInterceptor;
    private static String lastCategory;
    private static Object lastMessage;

    @Before
    public void setUp() {
        prevContextHandlerFacade = ContextManagementHelper.getHandlerFacade();
        ContextManagementHelper.initUtil(null);

        lastInterceptor = null;
        lastCategory = null;
        lastMessage = null;
    }

    @After
    public void tearDown() {
        ContextManagementHelper.initUtil(prevContextHandlerFacade);
    }

    @Test
    public void test() {
        ContextManagementHelper.initUtil(Mockito.mock(ContextHandlerFacade.class));

        AbstractReceiveMessageService service = new AbstractReceiveMessageServiceTest();
        service.setInterceptor(new DefaultReceiveInterceptor());
        service.setEnabled(true);

        ObjectValue decoratorDecorateMethodCalledFlag = new ObjectValue(false);
        // 接收消息
        service.receive("test", "Hello, test!", (interceptor) -> {
            decoratorDecorateMethodCalledFlag.set(true);

            return interceptor;
        });

        Assert.assertEquals(true, decoratorDecorateMethodCalledFlag.get());
        Assert.assertNotNull(lastInterceptor);
        Assert.assertEquals("test", lastCategory);
        Assert.assertEquals("Hello, test!", lastMessage);
    }

    @Override
    protected ReceiveInvocationInfo doReceive(ReceiveInterceptor interceptor,
                                              String category, Object message) {
        lastInterceptor = interceptor;
        lastCategory = category;
        lastMessage = message;

        ReceiveInvocationInfo invocationInfo = new DefaultReceiveInvocationInfo(
                "-1", message, message);

        return invocationInfo;
    }

}