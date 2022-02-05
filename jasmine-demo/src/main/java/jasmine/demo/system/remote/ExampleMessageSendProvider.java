package jasmine.demo.system.remote;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.framework.remote.sender.MessageSendProvider;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author mh.z
 */
@Component("exampleMessageSendProvider")
public class ExampleMessageSendProvider implements MessageSendProvider {
    private RabbitTemplate template;

    public ExampleMessageSendProvider(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void send(Object data) {
        QCheckUtil.notNull(data, "data null");
        Map<String, Object> map = (Map<String, Object>) data;

        MessageProperties properties = new MessageProperties();
        properties.setHeader("category", map.get("category"));

        String json = QJsonUtil.toJson(data);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        Message message = new Message(bytes, properties);

        template.send("demo.example.exchange1", null, message);
    }


}
