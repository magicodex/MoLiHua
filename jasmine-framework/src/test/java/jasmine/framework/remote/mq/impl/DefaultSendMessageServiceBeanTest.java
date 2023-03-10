package jasmine.framework.remote.mq.impl;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.SubjectProvider;
import jasmine.core.util.ref.ObjectValue;
import jasmine.framework.common.util.SimpleConvertUtil;
import jasmine.framework.remote.mq.impl.routing.PublisherExchangeDirectRouting;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;
import jasmine.framework.remote.mq.routing.PublisherExchangeRouting;
import jasmine.framework.remote.mq.routing.PublisherRouting;
import jasmine.framework.remote.testdependency.MockSendInterceptor;
import jasmine.mock.core.context.MockSubjectProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author mh.z
 */
public class DefaultSendMessageServiceBeanTest {
    private SubjectProvider prevSubjectProvider;

    private ObjectValue lastExchangeName;
    private ObjectValue lastRoutingKey;
    private ObjectValue lastMessage;


    @Before
    public void setUp() {
        prevSubjectProvider = CurrentSubject.getSubjectProvider();
        CurrentSubject.initUtil(null);

        lastExchangeName = new ObjectValue(null);
        lastRoutingKey = new ObjectValue(null);
        lastMessage = new ObjectValue(null);
    }

    @After
    public void tearDown() {
        CurrentSubject.initUtil(prevSubjectProvider);
    }

    @Test
    public void testDoSend() {
        // 初始 CurrentSubject 工具类
        CurrentSubject.initUtil(new MockSubjectProvider());
        CurrentSubject.setSubject(-1L, 666666L);

        AmqpTemplate amqpTemplate = mockAmqpTemplate();
        DefaultSendMessageServiceBean bean = new DefaultSendMessageServiceBean(amqpTemplate);
        // 初始路由信息
        DirectExchange exchange = new DirectExchange("exchange1");
        PublisherExchangeDirectRouting routing = new PublisherExchangeDirectRouting(
                "test", exchange, "routingKey1");
        bean.setRoutingMap(Map.of("test", routing));

        MockSendInterceptor interceptor = new MockSendInterceptor();
        // 发送消息
        SendInvocationInfo actual = bean.doSend(interceptor,
                "-1", "test", "Hello, test!");
        Assert.assertNotNull(actual);

        Assert.assertNotNull(interceptor.getSendInvocationInfo());
        Assert.assertEquals("exchange1", lastExchangeName.get());
        Assert.assertEquals("routingKey1", lastRoutingKey.get());
        Assert.assertNotNull(lastMessage.get());
    }

    @Test
    public void testCreateMessage() {
        // 初始 CurrentSubject 工具类
        CurrentSubject.initUtil(new MockSubjectProvider());
        CurrentSubject.setSubject(-1L, 666666L);

        DefaultSendMessageServiceBean bean = new DefaultSendMessageServiceBean(
                Mockito.mock(AmqpTemplate.class));
        // 创建消息对象
        Message message = bean.createMessage("-1", "Hello, test!");
        Assert.assertNotNull(message);

        Assert.assertArrayEquals(message.getBody(),
                SimpleConvertUtil.serialize("Hello, test!"));
        MessageProperties properties = message.getMessageProperties();
        Object header = properties.getHeader("subject");
        Assert.assertEquals("userId:666666", header);
    }

    @Test
    public void testAfterSingletonsInstantiated() {
        ApplicationContext applicationContext = mockApplicationContext();
        DefaultSendMessageServiceBean bean = new DefaultSendMessageServiceBean(
                Mockito.mock(AmqpTemplate.class));
        bean.setApplicationContext(applicationContext);

        bean.afterSingletonsInstantiated();
        Map<String, PublisherExchangeRouting> routingMap = bean.getRoutingMap();
        Assert.assertNotNull(routingMap);
        Assert.assertEquals(1, routingMap.size());
        Assert.assertNotNull(routingMap.get("test"));
    }

    /**
     * 模拟 ApplicationContext 对象
     *
     * @return
     */
    private ApplicationContext mockApplicationContext() {
        DirectExchange exchange = new DirectExchange("exchange1");
        PublisherExchangeDirectRouting routing = new PublisherExchangeDirectRouting(
                "test", exchange, "routingKey1");

        ApplicationContext context = Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getBeansOfType(PublisherRouting.class))
                .thenReturn(Map.of("test", routing));

        return context;
    }

    /**
     * 模拟 AmqpTemplate 对象
     *
     * @return
     */
    private AmqpTemplate mockAmqpTemplate() {
        AmqpTemplate amqpTemplate = Mockito.mock(AmqpTemplate.class);
        Mockito.doAnswer((invocation) -> {
            lastExchangeName.set(invocation.getArguments()[0]);
            lastRoutingKey.set(invocation.getArguments()[1]);
            lastMessage.set(invocation.getArguments()[2]);

            return null;
        }).when(amqpTemplate).send(Mockito.anyString(),
                Mockito.anyString(), Mockito.any(Message.class));

        return amqpTemplate;
    }

}
