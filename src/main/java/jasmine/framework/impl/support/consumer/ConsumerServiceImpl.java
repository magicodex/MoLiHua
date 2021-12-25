package jasmine.framework.impl.support.consumer;

import jasmine.common.context.RuntimeProvider;
import jasmine.common.support.consumer.ConsumerProvider;
import jasmine.common.support.consumer.ConsumerService;
import jasmine.common.util.QStringUtil;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author mh.z
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    private RuntimeProvider runtimeProvider;

    public ConsumerServiceImpl(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void consume(String category, Object data) {
        ConsumerProvider consumerProvider = runtimeProvider
                .getByName(category + "ConsumerProvider");

        Message message = (Message) data;
        byte[] body = message.getBody();
        String bodyStr = QStringUtil.str(body, StandardCharsets.UTF_8);

        consumerProvider.consume(bodyStr);
    }

}
