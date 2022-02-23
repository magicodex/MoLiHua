package jasmine.framework.remote.rabbit;

import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.core.util.QNewUtil;
import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.framework.remote.mq.impl.AbstractSendMessageService;
import jasmine.framework.remote.mq.MessagePublisherRouting;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

/**
 * @author mh.z
 */
public class RabbitSendMessageService extends AbstractSendMessageService implements SmartInitializingSingleton {
    private RabbitTemplate template;
    private Map<String, RabbitPublisherRouting> routings;
    private SpringRuntimeProvider runtimeProvider;

    public RabbitSendMessageService(SpringRuntimeProvider runtimeProvider) {
        super(runtimeProvider);
        this.runtimeProvider = runtimeProvider;
        template = runtimeProvider.getByType(RabbitTemplate.class);
        routings = Collections.emptyMap();
    }

    @Override
    protected void doSend(String category, Object content) {
        QCheckUtil.notNull(content, "content null");

        RabbitPublisherRouting routing = routings.get(category);
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

        Exchange exchange = routing.getExchange();
        String exchangeName = exchange.getName();
        template.send(exchangeName, routingKey, message);
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, MessagePublisherRouting> routingMap = runtimeProvider.getMapByType(MessagePublisherRouting.class);
        routings = QNewUtil.map();

        routingMap.forEach((name, routing) -> {
            if (routing instanceof RabbitPublisherRouting) {
                routings.put(routing.getCategory(), (RabbitPublisherRouting) routing);
            }
        });
    }

}
