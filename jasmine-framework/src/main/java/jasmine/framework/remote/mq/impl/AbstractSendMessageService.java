package jasmine.framework.remote.mq.impl;

import jasmine.core.util.QCheckUtil;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.remote.mq.interceptor.DefaultSendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public abstract class AbstractSendMessageService implements SendMessageService {
    private SendInterceptor interceptor;
    private Boolean sendEnabled;
    private static final SendInterceptor EMPTY_INTERCEPTOR;

    private static final Logger logger = LoggerFactory.getLogger(AbstractSendMessageService.class);

    static {
        EMPTY_INTERCEPTOR = new DefaultSendInterceptor();
    }

    public AbstractSendMessageService() {
        interceptor = EMPTY_INTERCEPTOR;
        sendEnabled = true;
    }

    public void setInterceptor(SendInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public void setEnabled(Boolean sendEnabled) {
        this.sendEnabled = sendEnabled;
    }

    @Override
    public void send(String category, String key, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        // 发送消息
        send(interceptor, category, key, content);
    }

    @Override
    public void sendOnly(String category, String key, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        // 发送消息
        send(EMPTY_INTERCEPTOR, category, key, content);
    }

    /**
     * 发送消息
     *
     * @param tempInterceptor
     * @param category
     * @param key
     * @param content
     */
    protected void send(SendInterceptor tempInterceptor, String category,
                        String key, Object content) {
        if (Boolean.TRUE.equals(sendEnabled)) {
            // 发送消息
            tempInterceptor.onSend((newCategory, newKey, newContent) -> {
                return doSend(tempInterceptor, category, newKey, content);
            }, category, key, content);
        } else {
            logger.warn("send skipped(jasmine.message-queue.publisher.enabled=false)");
        }
    }

    /**
     * 发送消息
     *
     * @param interceptor
     * @param category
     * @param key
     * @param content
     */
    protected abstract SendInvocationInfo doSend(SendInterceptor interceptor,
                                                 String category, String key, Object content);

}
