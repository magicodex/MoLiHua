package jasmine.framework.remote.mq.impl;

import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.core.util.QNewUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.common.util.UniqueKeyUtil;
import jasmine.framework.remote.mq.interceptor.DefaultSendInvocationInfo;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;
import jasmine.framework.remote.mq.routing.PublisherRouting;
import jasmine.framework.remote.mq.impl.routing.PublisherExchangeDirectRouting;
import jasmine.framework.remote.mq.routing.PublisherExchangeRouting;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

/**
 * @author mh.z
 */
public class DefaultSendMessageServiceBean extends AbstractSendMessageService
        implements SmartInitializingSingleton, ApplicationContextAware {
    private AmqpTemplate template;
    private Map<String, PublisherExchangeRouting> routingMap;
    private static ApplicationContext applicationContext;

    public DefaultSendMessageServiceBean(AmqpTemplate rabbitTemplate) {
        template = rabbitTemplate;
        routingMap = Collections.emptyMap();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DefaultSendMessageServiceBean.applicationContext = applicationContext;
    }

    @Override
    protected SendInvocationInfo doSend(SendInterceptor interceptor, String key, String category, Object content) {
        QCheckUtil.notNull(content, "content null");

        PublisherExchangeRouting routing = routingMap.get(category);
        if (routing == null) {
            throw new RuntimeException("not found the RabbitPublisherRouting(category=" + category + ")");
        }

        if (key == null) {
            key = QStringUtil.toString(UniqueKeyUtil.nextLong());
        }

        MessageProperties properties = new MessageProperties();
        properties.setHeader("subject", "userId:" + CurrentSubject.getUserId());
        properties.setMessageId(key);

        String json = QJsonUtil.toJson(content);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        Message message = new Message(bytes, properties);

        String routingKey = null;
        if (routing instanceof PublisherExchangeDirectRouting) {
            PublisherExchangeDirectRouting directRouting = (PublisherExchangeDirectRouting) routing;
            routingKey = directRouting.getRoutingKey();
        }

        DefaultSendInvocationInfo invocationInfo = new DefaultSendInvocationInfo(key, content, message);
        interceptor.afterSerialize(invocationInfo);

        Exchange exchange = routing.getExchange();
        String exchangeName = exchange.getName();
        template.send(exchangeName, routingKey, message);

        return invocationInfo;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, PublisherRouting> routingMap = applicationContext.getBeansOfType(PublisherRouting.class);
        this.routingMap = QNewUtil.map();

        routingMap.forEach((name, routing) -> {
            if (routing instanceof PublisherExchangeRouting) {
                this.routingMap.put(routing.getCategory(), (PublisherExchangeRouting) routing);
            }
        });
    }

}
