package jasmine.support.impl.consumer;

import jasmine.common.support.consumer.ConsumerProvider;
import jasmine.common.util.QCheckUtil;
import jasmine.common.util.QJsonUtil;
import jasmine.common.util.QStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author mh.z
 */
@ConditionalOnProperty(value = "app.message-queue.consumer.enabled",
        havingValue = "true", matchIfMissing = false)
@Component
public class Example1ConsumerProvider implements ConsumerProvider {
    private static final Logger logger = LoggerFactory.getLogger(Example1ConsumerProvider.class);

    @RabbitListener(queues = "demo.example.queue1")
    public void handle(Message message) {
        QCheckUtil.notNull(message, "message null");

        byte[] body = message.getBody();
        String bodyStr = QStringUtil.str(body, StandardCharsets.UTF_8);
        Map<String, Object> dataMap = QJsonUtil.fromJson(bodyStr, Map.class);

        consume(dataMap);
    }

    @Override
    public void consume(Object data) {
        logger.debug("consume <= " + QJsonUtil.toJson(data));
    }

}
