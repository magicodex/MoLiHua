package jasmine.framework.remote.mq.impl;

import jasmine.core.util.QCheckUtil;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.interceptor.DefaultReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public abstract class AbstractReceiveMessageService<T> implements ReceiveMessageService {
    private ReceiveInterceptor interceptor;
    private Boolean receiveEnabled;
    private static final ReceiveInterceptor DEFAULT_INTERCEPTOR;

    private static final Logger logger = LoggerFactory.getLogger(AbstractReceiveMessageService.class);

    static {
        DEFAULT_INTERCEPTOR = new DefaultReceiveInterceptor();
    }

    public AbstractReceiveMessageService() {
        interceptor = new DefaultReceiveInterceptor();
        receiveEnabled = true;
    }

    public void setInterceptor(ReceiveInterceptor interceptor) {
        this.interceptor = DEFAULT_INTERCEPTOR;
    }

    public void setEnabled(Boolean receiveEnabled) {
        this.receiveEnabled = receiveEnabled;
    }

    @Override
    public void receive(String category, Object message) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");

        // 接收消息
        receive(interceptor, category, (T) message);
    }

    @Override
    public void receiveOnly(String category, Object message) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");

        // 接收消息
        receive(DEFAULT_INTERCEPTOR, category, (T) message);
    }

    /**
     * 接收消息
     *
     * @param tempInterceptor
     * @param category
     * @param message
     */
    protected void receive(ReceiveInterceptor tempInterceptor, String category, Object message) {
        if (Boolean.TRUE.equals(receiveEnabled)) {
            // 接收消息
            tempInterceptor.onReceive((newCategory, newMessage) -> {
                return doReceive(tempInterceptor, newCategory, (T) newMessage);
            }, category, message);
        } else {
            logger.warn("receive skipped(jasmine.message-queue.consumer.enabled=false)");
        }
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
