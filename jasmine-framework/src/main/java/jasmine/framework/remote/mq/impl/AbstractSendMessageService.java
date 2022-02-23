package jasmine.framework.remote.mq.impl;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.remote.mq.MessageSender;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public abstract class AbstractSendMessageService implements SendMessageService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractSendMessageService.class);
    private RuntimeProvider runtimeProvider;
    protected SendInterceptor interceptor;

    /** 消息发送者 bean 的名称后缀 */
    private static final String SENDER_BEAN_SUFFIX = "MessageSender";

    public AbstractSendMessageService(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    public void setInterceptor(SendInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void send(String category, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        // 发送消息
        doSend(category, content);
    }

    /**
     * 发送消息
     *
     * @param category
     * @param content
     */
    protected abstract void doSend(String category, Object content);

    /**
     * 查找消息发送者并返回
     *
     * @param category
     * @param required
     * @return
     */
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
