package jasmine.framework.mq.impl;

import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.context.SubjectProvider;
import jasmine.framework.common.util.SimpleConvertUtil;
import jasmine.framework.mq.MessageReceiver;
import jasmine.framework.mq.interceptor.ReceiveInvocationInfo;
import jasmine.framework.mq.testdependency.MockReceiveInterceptor;
import jasmine.framework.testdependency.mock.MockRuntimeProvider;
import jasmine.framework.testdependency.mock.MockSubjectProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.util.Map;

/**
 * @author mh.z
 */
public class DefaultReceiveMessageServiceTest {
    private SubjectProvider prevSubjectProvider;
    private Object lastContent;

    @Before
    public void setUp() {
        prevSubjectProvider = CurrentSubject.getSubjectProvider();
        CurrentSubject.initUtil(null);

        lastContent = null;
    }

    @After
    public void tearDown() {
        CurrentSubject.initUtil(prevSubjectProvider);
    }

    @Test
    public void testDoReceive() {
        // 初始 CurrentSubject 工具类
        CurrentSubject.initUtil(new MockSubjectProvider());

        MessageProperties properties = new MessageProperties();
        properties.setHeader("subject", "userId:666666");
        Message message = new Message(SimpleConvertUtil.serialize("Hello, test!"), properties);

        DefaultReceiveMessageService service = createTestService();
        MockReceiveInterceptor interceptor = new MockReceiveInterceptor();
        ReceiveInvocationInfo actual = service.doReceive(interceptor, "test", message);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(interceptor.getLastInvocationInfo());
    }

    @Test
    public void testInitCurrentContext() {
        // 初始 CurrentSubject 工具类
        CurrentSubject.initUtil(new MockSubjectProvider());

        MessageProperties properties = new MessageProperties();
        properties.setHeader("subject", "userId:666666");
        Message message = new Message(SimpleConvertUtil.serialize("Hello, test!"), properties);

        DefaultReceiveMessageService service = createTestService();
        // 初始到当前上下文
        service.initCurrentContext(message);

        Assert.assertEquals(Long.valueOf(666666L), CurrentSubject.getUserId());
    }

    @Test
    public void testGetContent() {
        Message message = new Message(SimpleConvertUtil.serialize("Hello, test!"));

        DefaultReceiveMessageService service = createTestService();
        Object actual = service.getContent(message, String.class);

        Assert.assertEquals("Hello, test!", actual);
    }

    @Test
    public void testGetReceiver() {
        DefaultReceiveMessageService service = createTestService();

        // 找到消息接收者
        {
            MessageReceiver actual = service.getReceiver("test", true);

            Assert.assertNotNull(actual);
        }

        // 没找到消息接收者报错
        {
            Assert.assertThrows(Exception.class, () -> {
                service.getReceiver("test1", true);
            });
        }

        // 没找到消息接收者不报错
        {
            MessageReceiver actual = service.getReceiver("test1", false);

            Assert.assertNull(actual);
        }
    }

    /**
     * 创建测试对象
     *
     * @return
     */
    private DefaultReceiveMessageService createTestService() {
        MessageReceiver receiver = Mockito.mock(MessageReceiver.class);
        Mockito.doAnswer((invocation) -> {
            lastContent = invocation.getArguments()[0];
            return null;
        }).when(receiver).receive(Mockito.any());

        RuntimeProvider provider = new MockRuntimeProvider(null,
                Map.of("testMessageReceiver", receiver));
        DefaultReceiveMessageService service = new DefaultReceiveMessageService(provider);

        return service;
    }

}
