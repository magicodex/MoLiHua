package jasmine.framework.remote.mq.impl;

import jasmine.core.util.QCheckUtil;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.remote.mq.interceptor.DefaultSendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;

/**
 * @author mh.z
 */
public abstract class AbstractSendMessageService implements SendMessageService {
    private SendInterceptor interceptor;
    private static final SendInterceptor EMPTY_INTERCEPTOR;

    static {
        EMPTY_INTERCEPTOR = new DefaultSendInterceptor();
    }

    public AbstractSendMessageService() {
        interceptor = EMPTY_INTERCEPTOR;
    }

    public void setInterceptor(SendInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void send(String category, String key, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        // 发送消息
        interceptor.onSend((newCategory, newKey, newContent) -> {
            return doSend(interceptor, category, newKey, content);
        }, category, key, content);
    }

    @Override
    public void sendOnly(String category, String key, Object content) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(content, "content null");

        // 发送消息
        doSend(EMPTY_INTERCEPTOR, category, key, content);
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
