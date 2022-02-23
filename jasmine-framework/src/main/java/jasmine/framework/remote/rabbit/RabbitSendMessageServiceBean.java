package jasmine.framework.remote.rabbit;

import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.core.util.QNewUtil;
import jasmine.framework.remote.mq.impl.AbstractSendMessageService;
import jasmine.framework.remote.mq.interceptor.DefaultSendInvocationInfo;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;
import jasmine.framework.remote.mq.routing.PublisherRouting;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class RabbitSendMessageServiceBean extends AbstractSendMessageService
        implements SmartInitializingSingleton, ApplicationContextAware {
    private RabbitTemplate template;
    private Map<String, RabbitPublisherRouting> routingMap;
    private static ApplicationContext applicationContext;

    public RabbitSendMessageServiceBean(RabbitTemplate rabbitTemplate) {
        template = rabbitTemplate;
        routingMap = Collections.emptyMap();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitSendMessageServiceBean.applicationContext = applicationContext;
    }

    @Override
    protected SendInvocationInfo doSend(SendInterceptor interceptor, String key, String category, Object content) {
        QCheckUtil.notNull(content, "content null");

        RabbitPublisherRouting routing = routingMap.get(category);
        if (routing == null) {
            throw new RuntimeException("not found the RabbitPublisherRouting(category=" + category + ")");
        }

        MessageProperties properties = new MessageProperties();
        properties.setHeader("subject", "userId:" + CurrentSubject.getUserId());

        String json = QJsonUtil.toJson(content);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        Message message = new Message(bytes, properties);

        String routingKey = null;
        if (routing instanceof RabbitPublisherDirectRouting) {
            RabbitPublisherDirectRouting directRouting = (RabbitPublisherDirectRouting) routing;
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
            if (routing instanceof RabbitPublisherRouting) {
                this.routingMap.put(routing.getCategory(), (RabbitPublisherRouting) routing);
            }
        });
    }

}
