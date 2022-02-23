package jasmine.framework.remote.mq.impl;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.remote.mq.MessageReceiver;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mh.z
 */
public abstract class AbstractReceiveMessageService<T> implements ReceiveMessageService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractReceiveMessageService.class);
    private RuntimeProvider runtimeProvider;
    protected ReceiveInterceptor interceptor;

    /** 消息接收者 bean 的名称后缀 */
    private static final String RECEIVER_BEAN_SUFFIX = "MessageReceiver";

    public AbstractReceiveMessageService(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    public void setInterceptor(ReceiveInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void receive(String category, Object message) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(message, "message null");

        // 接收消息
        doReceive(category, (T) message);
    }

    /**
     * 接收消息
     *
     * @param category
     * @param message
     */
    protected abstract void doReceive(String category, T message);

    /**
     * 查找消息接收者并返回
     *
     * @param category
     * @param required
     * @return
     */
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
