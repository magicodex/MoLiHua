package jasmine.cloud.stream.mq;

import jasmine.cloud.stream.testdependency.MockSendInterceptor;
import jasmine.cloud.stream.testdependency.MockStreamBridgeInvoker;
import jasmine.core.util.QCollUtil;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;
import jasmine.framework.test.context.AppTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class StreamSendMessageServiceTest extends AppTestContext {

    @Test
    public void testDoSend() {
        MockStreamBridgeInvoker invoker = new MockStreamBridgeInvoker();
        MockSendInterceptor interceptor = new MockSendInterceptor();
        StreamSendMessageService service = new StreamSendMessageService(invoker);

        SendInvocationInfo actual = service.doSend(interceptor,
                "test", null, "Hello, world!");
        Assert.assertNotNull(actual);

        Collection<Object> data = invoker.getData("test-out-0");
        Assert.assertNotNull(data);
        Assert.assertEquals(1, data.size());

        List<SendInvocationInfo> invocationInfoList = interceptor.getSendInvocationInfo();
        Assert.assertEquals(1, invocationInfoList.size());
        Assert.assertSame(actual, QCollUtil.getFirst(invocationInfoList));
    }

    @Test
    public void testCreateMessage() {
        MockStreamBridgeInvoker invoker = new MockStreamBridgeInvoker();
        StreamSendMessageService service = new StreamSendMessageService(invoker);

        // 创建消息对象
        Message message = service.createMessage("1001", "Hello, world!");
        Assert.assertNotNull(message);

        // 检查内容
        Object payload = message.getPayload();
        Assert.assertEquals("Hello, world!", payload);

        // 检查消息头
        MessageHeaders headers = message.getHeaders();
        Assert.assertEquals("1001", headers.get("messageKey"));
        Assert.assertNotNull(headers.get("subject"));
    }

}
