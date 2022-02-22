package jasmine.framework.remote.mq;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public abstract class AbstractReceiveMessageService<T> implements ReceiveMessageService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractReceiveMessageService.class);
    private RuntimeProvider runtimeProvider;
    /** 是否消费消息队列的消息 */
    private Boolean messageQueueConsumerEnabled;

    /** 消息接收者 bean 的名称后缀 */
    private static final String RECEIVER_BEAN_SUFFIX = "MessageReceiver";

    public AbstractReceiveMessageService(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
        messageQueueConsumerEnabled = false;
    }

    public void setMessageQueueConsumerEnabled(Boolean messageQueueConsumerEnabled) {
        this.messageQueueConsumerEnabled = messageQueueConsumerEnabled;
    }

    @Override
    public void receive(String category, Object message) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");

        if (Boolean.TRUE.equals(messageQueueConsumerEnabled)) {
            // 接收消息
            doReceive(category, (T) message);
        } else {
            logger.warn("receive skipped(jasmine.message-queue.consumer.enabled=false)");
        }
    }

    /**
     * 接收消息
     *
     * @param category
     * @param message
     */
    protected abstract void doReceive(String category, T message);

    @Override
    public MessageReceiver getReceiver(String category, boolean required) {
        QCheckUtil.notNull(category, "category null");

        String receiverName = category + RECEIVER_BEAN_SUFFIX;
        MessageReceiver receiver = runtimeProvider.getByName(receiverName, false);

        if (receiver == null) {
            throw new RuntimeException("not found the MessageReceiver(category=" + category + ")");
        }

        return receiver;
    }

}
