package jasmine.framework.remote.rabbit;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.common.util.SimpleConvertUtil;
import jasmine.framework.remote.mq.AbstractReceiveMessageService;
import jasmine.framework.remote.mq.MessageReceiver;
import org.springframework.amqp.core.Message;

/**
 * @author mh.z
 */
public class RabbitReceiveMessageService extends AbstractReceiveMessageService<Message> {

    public RabbitReceiveMessageService(RuntimeProvider runtimeProvider) {
        super(runtimeProvider);
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
            content = SimpleConvertUtil.deserialize(messageBody, targetType);
        } else {
            content = messageBody;
        }

        // 接收消息
        receiver.receive(content);
    }

}
