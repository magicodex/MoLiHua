package jasmine.framework.remote.mq.impl;

import jasmine.core.util.QCheckUtil;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.interceptor.DefaultReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocationInfo;

/**
 * @author mh.z
 */
public abstract class AbstractReceiveMessageService<T> implements ReceiveMessageService {
    private ReceiveInterceptor interceptor;

    public AbstractReceiveMessageService() {
        interceptor = new DefaultReceiveInterceptor();
    }

    public void setInterceptor(ReceiveInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void receive(String category, Object message) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");

        // 接收消息
        interceptor.onReceive((newCategory, newMessage) -> {
            return doReceive(interceptor, newCategory, (T) newMessage);
        }, category, message);
    }

    /**
     * 接收消息
     *
     * @param interceptor
     * @param category
     * @param message
     */
    protected abstract ReceiveInvocationInfo doReceive(ReceiveInterceptor interceptor, String category, T message);

}
