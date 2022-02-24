package jasmine.framework.remote.rabbit;

import jasmine.core.context.CurrentSubject;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QObjectUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.common.util.SimpleConvertUtil;
import jasmine.framework.remote.mq.MessageReceiver;
import jasmine.framework.remote.mq.impl.AbstractReceiveMessageService;
import jasmine.framework.remote.mq.interceptor.DefaultReceiveInvocationInfo;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * @author mh.z
 */
public class RabbitReceiveMessageService extends AbstractReceiveMessageService<Message> {
    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiveMessageService.class);
    private RuntimeProvider runtimeProvider;

    /** 消息接收者 bean 的名称后缀 */
    private static final String RECEIVER_BEAN_SUFFIX = "MessageReceiver";

    public RabbitReceiveMessageService(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    protected ReceiveInvocationInfo doReceive(ReceiveInterceptor interceptor, String category, Message message) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");
        byte[] messageBody = message.getBody();

        DefaultReceiveInvocationInfo invocationInfo = null;
        Exception error = null;

        // TODO 此处捕获到异常只是输出日志，实际业务场景中需要做对应的错误处理
        try {
            MessageProperties messageProperties = message.getMessageProperties();
            String subject = messageProperties.getHeader("subject");
            String messageId = messageProperties.getMessageId();

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

            invocationInfo = new DefaultReceiveInvocationInfo(messageId, content, message);
            interceptor.afterDeserialize(invocationInfo);

            // 接收消息
            receiver.receive(content);
        } catch (Exception e) {
            error = e;
            logger.error("receive failed", e);
        }

        if (invocationInfo == null) {
            invocationInfo = new DefaultReceiveInvocationInfo(null, null, message);
        }
        invocationInfo.setError(error);

        return invocationInfo;
    }

    /**
     * 查找消息接收者并返回
     *
     * @param category
     * @param required
     * @return
     */
    protected MessageReceiver getReceiver(String category, boolean required) {
        QCheckUtil.notNull(category, "category null");

        String receiverName = category + RECEIVER_BEAN_SUFFIX;
        MessageReceiver receiver = runtimeProvider.getByName(receiverName, false);

        if (receiver == null && required) {
            throw new RuntimeException("not found the MessageReceiver(category=" + category + ")");
        }

        return receiver;
    }

}
