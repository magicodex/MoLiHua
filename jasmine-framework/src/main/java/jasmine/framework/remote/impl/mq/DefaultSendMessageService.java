package jasmine.framework.remote.impl.mq;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.FrameworkConfig;
import jasmine.framework.remote.mq.MessageSender;
import jasmine.framework.remote.mq.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public class DefaultSendMessageService implements SendMessageService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSendMessageService.class);
    private RuntimeProvider runtimeProvider;
    /** 配置 */
    private FrameworkConfig frameworkConfig;

    /** 消息发送者 bean 的名称后缀 */
    private static final String SENDER_BEAN_SUFFIX = "messageSender";

    public DefaultSendMessageService(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
        this.frameworkConfig = runtimeProvider.getByType(FrameworkConfig.class);
    }

    @Override
    public void send(String category, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        if (Boolean.TRUE.equals(frameworkConfig.getMessageQueuePublisherEnabled())) {
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
