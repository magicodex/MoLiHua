package jasmine.cloud.stream.mq;

import jasmine.cloud.stream.testdependency.MockMessageReceiver;
import jasmine.cloud.stream.testdependency.MockReceiveInterceptor;
import jasmine.cloud.stream.testdependency.MockRuntimeProvider;
import jasmine.core.context.CurrentSubject;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCollUtil;
import jasmine.framework.remote.mq.MessageReceiver;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocationInfo;
import jasmine.framework.test.context.AppTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class StreamReceiveMessageServiceTest extends AppTestContext {
    @Autowired
    private RuntimeProvider runtimeProvider;

    @Test
    public void testDoReceive() {
        MockMessageReceiver<Object> mockReceiver = new MockMessageReceiver<>(Object.class);
        MockRuntimeProvider mockProvider = new MockRuntimeProvider(runtimeProvider,
                Map.of("testMessageReceiver", mockReceiver));
        MockReceiveInterceptor mockInterceptor = new MockReceiveInterceptor();

        Message message = MessageBuilder.withPayload("Hello, world!")
                .setHeader("subject", "userId:666666")
                .build();

        StreamReceiveMessageService testService = new StreamReceiveMessageService(mockProvider);
        ReceiveInvocationInfo actual = testService.doReceive(mockInterceptor, "test", message);
        Assert.assertNotNull(actual);

        // 检查发送的消息内容
        List<Object> contentList = mockReceiver.getReceivedContents();
        Assert.assertNotNull(contentList);
        Assert.assertEquals(1, contentList.size());
        Assert.assertEquals("Hello, world!", QCollUtil.getFirst(contentList));
    }

    @Test
    public void testInitCurrentContext() {
        MockMessageReceiver<Object> mockReceiver = new MockMessageReceiver<>(Object.class);
        MockRuntimeProvider mockProvider = new MockRuntimeProvider(runtimeProvider,
                Map.of("testMessageReceiver", mockReceiver));

        Message message = MessageBuilder.withPayload("Hello, world!")
                .setHeader("subject", "userId:666666")
                .build();

        StreamReceiveMessageService testService = new StreamReceiveMessageService(mockProvider);
        testService.initCurrentContext(message);

        // 当前的用户ID应当是消息头里的用户ID
        Long userId = CurrentSubject.getUserId();
        Assert.assertEquals(Long.valueOf(666666L), userId);
    }

    @Test
    public void testGetReceiver() {
        MockMessageReceiver<Object> mockReceiver = new MockMessageReceiver<>(Object.class);
        MockRuntimeProvider mockProvider = new MockRuntimeProvider(runtimeProvider,
                Map.of("testMessageReceiver", mockReceiver));
        StreamReceiveMessageService testService = new StreamReceiveMessageService(mockProvider);

        // 能获取到名称是 testMessageReceiver 的消息接收者
        {
            MessageReceiver actual = testService.getReceiver("test", true);
            Assert.assertNotNull(actual);
        }

        // 应当获取不到名称是 mockMessageReceiver 的消息接收者以及抛出异常
        {
            Assert.assertThrows(Exception.class, () -> {
                testService.getReceiver("mock", true);
            });
        }

        // 应当获取不到名称是 mockMessageReceiver 的消息接收者，但不抛出异常
        {
            try {
                MessageReceiver actual = testService.getReceiver("mock", false);

                Assert.assertNull(actual);
            } catch (Exception e) {
                Assert.fail();
            }
        }
    }

}
