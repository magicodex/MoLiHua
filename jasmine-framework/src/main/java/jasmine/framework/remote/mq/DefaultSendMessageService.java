package jasmine.framework.remote.mq;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public class DefaultSendMessageService implements SendMessageService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSendMessageService.class);
    private RuntimeProvider runtimeProvider;
    /** 是否发布消息到消息队列 */
    private Boolean messageQueuePublisherEnabled;

    /** 消息发送者 bean 的名称后缀 */
    private static final String SENDER_BEAN_SUFFIX = "MessageSender";

    public DefaultSendMessageService(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
        this.messageQueuePublisherEnabled = false;
    }

    public void setMessageQueuePublisherEnabled(Boolean messageQueuePublisherEnabled) {
        this.messageQueuePublisherEnabled = messageQueuePublisherEnabled;
    }

    @Override
    public void send(String category, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        if (Boolean.TRUE.equals(messageQueuePublisherEnabled)) {
            // 发送消息
            doSend(category, content);
        } else {
            logger.warn("send skipped(jasmine.message-queue.publisher.enabled=false)");
        }
    }

    /**
     * 发送消息
     *
     * @param category
     * @param content
     */
    protected void doSend(String category, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        // 获取消息发送者
        MessageSender sender = getSender(category, true);
        // 发送消息
        sender.send(null, content);
    }

    @Override
    public MessageSender getSender(String category, boolean required) {
        QCheckUtil.notNull(category, "category null");

        String senderName = category + SENDER_BEAN_SUFFIX;
        MessageSender sender = runtimeProvider.getByName(senderName, false);

        if (sender == null && required) {
            throw new RuntimeException("not found the MessageSender(category=" + category + ")");
        }

        return sender;
    }

}
