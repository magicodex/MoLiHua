package jasmine.framework.mq.impl;

import jasmine.framework.common.util.ref.ObjectValue;
import jasmine.framework.context.thread.ContextManagementUtil;
import jasmine.framework.context.thread.ContextHandlerFacade;
import jasmine.framework.mq.impl.interceptor.DefaultReceiveInterceptor;
import jasmine.framework.mq.impl.interceptor.DefaultReceiveInvocationInfo;
import jasmine.framework.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.mq.interceptor.ReceiveInvocationInfo;
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
        prevContextHandlerFacade = ContextManagementUtil.getHandlerFacade();
        ContextManagementUtil.initUtil(null);

        lastInterceptor = null;
        lastCategory = null;
        lastMessage = null;
    }

    @After
    public void tearDown() {
        ContextManagementUtil.initUtil(prevContextHandlerFacade);
    }

    @Test
    public void test() {
        ContextManagementUtil.initUtil(Mockito.mock(ContextHandlerFacade.class));

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
