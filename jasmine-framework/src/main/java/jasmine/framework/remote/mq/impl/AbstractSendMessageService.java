package jasmine.framework.remote.mq.impl;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.remote.mq.MessageSender;
import jasmine.framework.remote.mq.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public abstract class AbstractSendMessageService implements SendMessageService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractSendMessageService.class);
    private RuntimeProvider runtimeProvider;
    /** 是否发布消息到消息队列 */
    private Boolean messageQueuePublisherEnabled;

    /** 消息发送者 bean 的名称后缀 */
    private static final String SENDER_BEAN_SUFFIX = "MessageSender";

    public AbstractSendMessageService(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
        this.messageQueuePublisherEnabled = true;
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
    protected abstract void doSend(String category, Object content);

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
