package jasmine.demo.framework.mq;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QObjectUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.remote.rabbit.RabbitReceiveMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * @author mh.z
 */
public class CustomRabbitReceiveMessageService extends RabbitReceiveMessageService {
    private static final Logger logger = LoggerFactory.getLogger(CustomRabbitReceiveMessageService.class);

    public CustomRabbitReceiveMessageService(RuntimeProvider runtimeProvider) {
        super(runtimeProvider);
    }

    @Override
    protected void doReceive(String category, Message message) {
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

            super.doReceive(category, message);
        } catch (Exception e) {
            logger.error("receive failed", e);
        }
    }

}
