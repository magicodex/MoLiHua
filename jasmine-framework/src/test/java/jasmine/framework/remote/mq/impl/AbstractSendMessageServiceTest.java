package jasmine.framework.remote.mq.impl;

import jasmine.core.util.wrapper.ObjectValue;
import jasmine.framework.remote.mq.impl.interceptor.DefaultSendInterceptor;
import jasmine.framework.remote.mq.impl.interceptor.DefaultSendInvocationInfo;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class AbstractSendMessageServiceTest extends AbstractSendMessageService {

    private static SendInterceptor lastInterceptor;
    private static String lastCategory;
    private static String lastKey;
    private static Object lastContent;

    @Test
    public void test() {
        AbstractSendMessageService service = new AbstractSendMessageServiceTest();
        service.setInterceptor(new DefaultSendInterceptor());
        service.setEnabled(true);

        ObjectValue decoratorDecorateMethodCalledFlag = new ObjectValue(false);
        // 发送消息
        service.send("test", "-1", "Hello, test!", (interceptor) -> {
            decoratorDecorateMethodCalledFlag.set(true);

            return interceptor;
        });

        Assert.assertEquals(true, decoratorDecorateMethodCalledFlag.get());
        Assert.assertNotNull(lastInterceptor);
        Assert.assertEquals("test", lastCategory);
        Assert.assertEquals("-1", lastKey);
        Assert.assertEquals("Hello, test!", lastContent);
    }

    @Override
    protected SendInvocationInfo doSend(SendInterceptor interceptor, String category,
                                        String key, Object content) {
        lastInterceptor = interceptor;
        lastCategory = category;
        lastKey = key;
        lastContent = content;

        SendInvocationInfo invocationInfo = new DefaultSendInvocationInfo(key, content, content);
        return invocationInfo;
    }

}
