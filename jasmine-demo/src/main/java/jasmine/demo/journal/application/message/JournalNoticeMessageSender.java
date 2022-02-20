package jasmine.demo.journal.application.message;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.demo.journal.business.dto.JournalNoticeMessageDTO;
import jasmine.framework.remote.mq.MessageSender;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author mh.z
 */
@Component
public class JournalNoticeMessageSender implements MessageSender<JournalNoticeMessageDTO> {
    private RabbitTemplate template;

    public JournalNoticeMessageSender(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void send(String key, JournalNoticeMessageDTO messageDTO) {
        QCheckUtil.notNull(messageDTO, "messageDTO null");

        MessageProperties properties = new MessageProperties();
        properties.setHeader("category", "notice");

        String json = QJsonUtil.toJson(messageDTO);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        Message message = new Message(bytes, properties);

        template.send("demo.journal.exchange", null, message);
    }

}
