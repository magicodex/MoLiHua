package jasmine.demo.system.remote.rabbit;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.framework.remote.mq.PublishMessageContext;
import jasmine.framework.remote.mq.PublishMessageProvider;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author mh.z
 */
@Component
public class JournalNoticePublishMessageProvider implements PublishMessageProvider {
    private RabbitTemplate template;

    public JournalNoticePublishMessageProvider(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void publish(PublishMessageContext context, Object data) {
        QCheckUtil.notNull(context, "context null");
        QCheckUtil.notNull(data, "data null");

        MessageProperties properties = new MessageProperties();
        properties.setHeader("category", "notice");

        String json = QJsonUtil.toJson(data);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        Message message = new Message(bytes, properties);

        template.send("demo.journal.exchange", null, message);
    }

}
