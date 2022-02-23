package jasmine.framework.remote.rabbit;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QObjectUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.common.util.SimpleConvertUtil;
import jasmine.framework.remote.mq.impl.AbstractReceiveMessageService;
import jasmine.framework.remote.mq.MessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * @author mh.z
 */
public class RabbitReceiveMessageService extends AbstractReceiveMessageService<Message> {
    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiveMessageService.class);

    public RabbitReceiveMessageService(RuntimeProvider runtimeProvider) {
        super(runtimeProvider);
    }

    @Override
    protected void doReceive(String category, Message message) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");
        byte[] messageBody = message.getBody();

        // TODO 此处捕获到异常只是输出日志，实际业务场景中需要做对应的错误处理
        try {
            MessageProperties messageProperties = message.getMessageProperties();
            String subject = messageProperties.getHeader("subject");

            // 初始安全上下文
            if (QStringUtil.isNotEmpty(subject)) {
                if (subject.startsWith("userId:")) {
                    String userIdStr = subject.substring(7);
                    Long userId = QObjectUtil.parseLong(userIdStr);

                    CurrentSubject.setSubject(null, userId);
                }
            }

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
        } catch (Exception e) {
            logger.error("receive failed", e);
        }
    }

}
