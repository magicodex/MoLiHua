package jasmine.framework.remote.impl.rabbit;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.common.conversion.DeserializationHelper;
import jasmine.framework.remote.impl.mq.AbstractReceiveMessageService;
import jasmine.framework.remote.mq.MessageReceiver;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class RabbitReceiveMessageService extends AbstractReceiveMessageService<Message> {
    private DeserializationHelper deserializationHelper;

    public RabbitReceiveMessageService(RuntimeProvider runtimeProvider) {
        super(runtimeProvider);
        this.deserializationHelper = new DeserializationHelper();
    }

    @Override
    protected void doReceive(String category, Message message) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");
        byte[] messageBody = message.getBody();

        // 获取消息接收者
        MessageReceiver receiver = getReceiver(category, true);
        Class<?> targetType = receiver.getType();
        Object content = null;

        // 转换成指定的类型
        if (targetType != null) {
            content = deserializationHelper.deserialize(messageBody, targetType);
        } else {
            content = messageBody;
        }

        // 接收消息
        receiver.receive(content);
    }

}
