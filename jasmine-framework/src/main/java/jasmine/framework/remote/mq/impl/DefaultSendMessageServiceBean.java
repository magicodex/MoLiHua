package jasmine.framework.remote.mq.impl;

import jasmine.core.context.CurrentSubject;
import jasmine.core.exception.InvalidParameterException;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QNewUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.common.util.SimpleConvertUtil;
import jasmine.framework.common.util.UniqueKeyUtil;
import jasmine.framework.context.CustomInitializingSingleton;
import jasmine.framework.remote.mq.impl.interceptor.DefaultSendInvocationInfo;
import jasmine.framework.remote.mq.impl.routing.PublisherExchangeDirectRouting;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;
import jasmine.framework.remote.mq.routing.PublisherExchangeRouting;
import jasmine.framework.remote.mq.routing.PublisherRouting;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;

import java.util.Collections;
import java.util.Map;

/**
 * @author mh.z
 */
public class DefaultSendMessageServiceBean extends AbstractSendMessageService
        implements CustomInitializingSingleton, ApplicationContextAware {
    private AmqpTemplate template;
    private Map<String, PublisherExchangeRouting> routingMap;
    private static ApplicationContext applicationContext;

    private static final String HEADER_SUBJECT = "subject";
    private static final String PARAM_USER_ID = "userId";

    public DefaultSendMessageServiceBean(AmqpTemplate rabbitTemplate) {
        template = rabbitTemplate;
        routingMap = Collections.emptyMap();
    }

    public Map<String, PublisherExchangeRouting> getRoutingMap() {
        return routingMap;
    }

    protected void setRoutingMap(Map<String, PublisherExchangeRouting> routingMap) {
        this.routingMap = routingMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DefaultSendMessageServiceBean.applicationContext = applicationContext;
    }

    @Override
    protected SendInvocationInfo doSend(SendInterceptor interceptor, String key,
                                        String category, Object content) {
        QCheckUtil.notNull(interceptor, "interceptor null");
        QCheckUtil.notNull(category, "category null");

        // 获取路由信息
        PublisherExchangeRouting routing = routingMap.get(category);
        if (routing == null) {
            throw new InvalidParameterException(String.format("not found the %s(category=%s)",
                    PublisherExchangeRouting.class.getSimpleName(), category), null);
        }

        String routingKey = null;
        // 获取路由key
        if (routing instanceof PublisherExchangeDirectRouting) {
            PublisherExchangeDirectRouting directRouting = (PublisherExchangeDirectRouting) routing;
            routingKey = directRouting.getRoutingKey();
        }

        // 创建要发送的消息对象
        Message message = createMessage(key, content);

        DefaultSendInvocationInfo invocationInfo = new DefaultSendInvocationInfo(key, content, message);
        // 转化消息后调用
        interceptor.afterConvert(invocationInfo);

        Exchange exchange = routing.getExchange();
        String exchangeName = exchange.getName();
        // 发送消息
        template.send(exchangeName, routingKey, message);

        return invocationInfo;
    }

    /**
     * 创建消息对象
     *
     * @param key
     * @param content
     * @return
     */
    protected Message createMessage(String key, Object content) {
        // 保证消息key不能为空
        if (key == null) {
            key = QStringUtil.toString(UniqueKeyUtil.nextLong());
        }

        MessageProperties properties = new MessageProperties();
        // 设置安全信息
        String userIdStr = QStringUtil.orEmpty(CurrentSubject.getUserId());
        properties.setHeader(HEADER_SUBJECT, (PARAM_USER_ID + ":" + userIdStr));
        // 设置消息ID
        properties.setMessageId(key);
        // 创建要发送的消息对象
        byte[] bytes = SimpleConvertUtil.serialize(content);
        Message message = new Message(bytes, properties);

        return message;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, PublisherRouting> routingMap = applicationContext.getBeansOfType(PublisherRouting.class);
        this.routingMap = QNewUtil.map();

        // 获取路由信息
        routingMap.forEach((name, routing) -> {
            if (routing instanceof PublisherExchangeRouting) {
                this.routingMap.put(routing.getCategory(), (PublisherExchangeRouting) routing);
            }
        });
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
