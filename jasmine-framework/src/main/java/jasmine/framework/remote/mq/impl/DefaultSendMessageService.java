package jasmine.framework.remote.mq.impl;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.remote.mq.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public class DefaultSendMessageService extends AbstractSendMessageService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSendMessageService.class);
    private RuntimeProvider runtimeProvider;
    /** 是否发布消息到消息队列 */
    private Boolean messageQueuePublisherEnabled;

    /** 消息发送者 bean 的名称后缀 */
    private static final String SENDER_BEAN_SUFFIX = "MessageSender";

    public DefaultSendMessageService(RuntimeProvider runtimeProvider) {
        super(runtimeProvider);
    }

    /**
     * 发送消息
     *
     * @param category
     * @param content
     */
    @Override
    protected void doSend(String category, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        // 获取消息发送者
        MessageSender sender = getSender(category, true);
        // 发送消息
        sender.send(null, content);
    }

}
