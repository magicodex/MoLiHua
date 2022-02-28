package jasmine.framework.remote.mq.impl;

import jasmine.core.util.QCheckUtil;
import jasmine.framework.context.handler.ContextInitAndClearHelper;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.interceptor.DefaultReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptorDecorator;
import jasmine.framework.remote.mq.interceptor.ReceiveInvocationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public abstract class AbstractReceiveMessageService<T> implements ReceiveMessageService {
    /** 拦截器 */
    private ReceiveInterceptor interceptor;
    /** 是否接收消息 */
    private Boolean receiveEnabled;

    private static final ReceiveInterceptor EMPTY_INTERCEPTOR;
    private static final Logger logger = LoggerFactory.getLogger(AbstractReceiveMessageService.class);

    static {
        EMPTY_INTERCEPTOR = new DefaultReceiveInterceptor();
    }

    public AbstractReceiveMessageService() {
        interceptor = new DefaultReceiveInterceptor();
        receiveEnabled = true;
    }

    public void setInterceptor(ReceiveInterceptor interceptor) {
        this.interceptor = (interceptor != null)
                ? interceptor : EMPTY_INTERCEPTOR;
    }

    public void setEnabled(Boolean receiveEnabled) {
        this.receiveEnabled = receiveEnabled;
    }

    @Override
    public void receive(String category, Object message) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");

        // 接收消息
        receive(interceptor, category, message);
    }

    @Override
    public void receive(String category, Object message, ReceiveInterceptorDecorator decorator) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");

        ReceiveInterceptor tempInterceptor = interceptor;
        if (decorator != null) {
            tempInterceptor = decorator.decorate(interceptor);
        }

        // 接收消息
        receive(tempInterceptor, category, message);
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
            ContextInitAndClearHelper.initThenClear(() -> {
                // 接收消息
                tempInterceptor.onReceive((newCategory, newMessage) -> {
                    return doReceive(tempInterceptor, newCategory, (T) newMessage);
                }, category, message);
            });
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
