package jasmine.framework.remote.mq.impl;

import jasmine.core.util.CheckUtil;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.remote.mq.impl.interceptor.DefaultSendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInterceptorDecorator;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public abstract class AbstractSendMessageService implements SendMessageService {
    /** 拦截器 */
    private SendInterceptor interceptor;
    /** 是否发送消息 */
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
        this.interceptor = (interceptor != null)
                ? interceptor : EMPTY_INTERCEPTOR;
    }

    public void setEnabled(Boolean sendEnabled) {
        this.sendEnabled = sendEnabled;
    }

    @Override
    public void send(String category, String key, Object content) {
        CheckUtil.notNull(category, "category null");
        CheckUtil.notNull(content, "content null");

        // 发送消息
        send(interceptor, category, key, content);
    }

    @Override
    public void send(String category, String key, Object content, SendInterceptorDecorator decorator) {
        CheckUtil.notNull(category, "category null");
        CheckUtil.notNull(content, "content null");

        SendInterceptor tempInterceptor = interceptor;
        if (decorator != null) {
            tempInterceptor = decorator.decorate(interceptor);
        }

        // 发送消息
        send(tempInterceptor, category, key, content);
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
                return doSend(tempInterceptor, newCategory, newKey, content);
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
     * @return
     */
    protected abstract SendInvocationInfo doSend(SendInterceptor interceptor,
                                                 String category, String key, Object content);

}
